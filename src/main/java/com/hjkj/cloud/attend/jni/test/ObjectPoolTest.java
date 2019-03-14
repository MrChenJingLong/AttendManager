package com.hjkj.cloud.attend.jni.test;

import com.hj.biz.bean.CompareFilter;
import com.hj.biz.bean.RailwayFaceBean;
import com.hj.jni.bean.HJFaceFeature;
import com.hj.jni.bean.HJFaceModel;
import com.hj.jni.utils.HJFaceDrive;
import com.hj.jni.utils.RailwayFaceBiz;
import org.apache.commons.pool2.impl.GenericObjectPool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObjectPoolTest {

    private static final HJFaceDrive test = new HJFaceDrive();
    private static final RailwayFaceBiz railwayFaceBiz = new RailwayFaceBiz();


    private static ConcurrentLinkedQueue<Long> detectEngineList = new ConcurrentLinkedQueue<>();
    private static ConcurrentLinkedQueue<Long> featureEngineList = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {

        //初始化引擎
        EngineFactory engineFactory = new EngineFactory();
        engineFactory.init();
//        initEngine(4);

        //初始化人脸数据

        GenericObjectPool<Resource> enginePool = engineFactory.getObjectPool();

        Resource resource = null;
//
//        Long engineId = detectEngineList.poll();
//        Long compareId = featureEngineList.poll();
        try {
            resource = enginePool.borrowObject();
            HJFaceFeature hjFaceFeature = genHJFaceFeature("F://test.jpg",resource);
            initFaceData(hjFaceFeature,"20190123");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resource != null) {
                enginePool.returnObject(resource);
            }
        }


        //多线程比对

        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0;i < 8;i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
//                        Long engineId = null;
//                        Long compareId = null;
                        Resource resource = null;
                        try {

                            resource = enginePool.borrowObject();

                            System.out.println(resource.toString());

                            HJFaceFeature hjFaceFeature = genHJFaceFeature("F://test.jpg", resource);

//                            engineId = detectEngineList.poll();
//                            compareId = featureEngineList.poll();
//
//                            if (engineId == null || compareId == null) {
//                                continue;
//                            }

//                            HJFaceFeature hjFaceFeature = genHJFaceFeature("F://test.jpg", engineId,compareId);

                            RailwayFaceBean railwayFaceBean = new RailwayFaceBean();

                            //从文件中获取比对模版
                            railwayFaceBean.setModelData(hjFaceFeature.getpFeature());
                            CompareFilter filter = new CompareFilter();
                            int startAge = 12;
                            int endAge = 24;
                            Calendar calendar = Calendar.getInstance();
                            int currentYear = calendar.get(Calendar.YEAR);
                            filter.setReturnNum(2);
                            filter.setStartAge(0);
                            filter.setEndAge(0);
                            filter.setSex(0);
                            System.out.println(railwayFaceBiz.HJQryFaceFromSet(4, 10, "20190123", railwayFaceBean));

                        } catch (Exception e) {
//                            e.printStackTrace();
                            System.out.println(e.getMessage());
                        } finally {
                            if (resource != null) {
//                                try {
                                    System.out.println("回收resource：" + resource.toString());
                                    enginePool.returnObject(resource);
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                    resource = null;
//                                }

                            }
                        }
                    }
                }
            });
        }



    }


    private static void initEngine(int num) {
        for (int i = 0; i<num;i++) {
            long enginId = test.HJDetectEngineIntial(20, 35, 60);
            long compareId = test.HJRecognizeEngineIntial();
            detectEngineList.add(enginId);
            featureEngineList.add(compareId);
        }
    }

    private static HJFaceFeature genHJFaceFeature(String imgPath,Long engineId,Long compareId) throws Exception {
        File image = new File("F://test.jpg");
        BufferedImage sourceImg = ImageIO.read(new FileInputStream(image));

        System.out.println("width:" + sourceImg.getWidth() + ";height:" + sourceImg.getHeight());
        ArrayList<HJFaceModel> hjFaceModels = new ArrayList<>();
        hjFaceModels.add(new HJFaceModel());

        byte[] img = getMatrixBGR(sourceImg);
        writeImageFromArray("F://out.jpg", sourceImg.getWidth(), sourceImg.getHeight(), "jpg", img);
        System.out.println(img.length);

        HJFaceFeature hjFaceFeatureI = null;
        for (HJFaceModel hjFaceModel : hjFaceModels) {
            hjFaceFeatureI = new HJFaceFeature();
            long start = System.currentTimeMillis();
            test.HJFaceModel(engineId,compareId, img, 24, sourceImg.getWidth(), sourceImg.getHeight(), hjFaceFeatureI);
            //test.HJFaceFeaturePick(compareId, img, sourceImg.getWidth(), sourceImg.getHeight(), hjFaceModel.getDwReserved(), hjFaceFeatureI);
            System.out.println("face image feature pick:" + (System.currentTimeMillis() - start));
        }
        return hjFaceFeatureI;
    }

    private static HJFaceFeature genHJFaceFeature(String imgPath,Resource resource) throws Exception {
        File image = new File("F://test.jpg");
        BufferedImage sourceImg = ImageIO.read(new FileInputStream(image));

        System.out.println("width:" + sourceImg.getWidth() + ";height:" + sourceImg.getHeight());
        ArrayList<HJFaceModel> hjFaceModels = new ArrayList<>();
        hjFaceModels.add(new HJFaceModel());

        byte[] img = getMatrixBGR(sourceImg);
        //writeImageFromArray("F://out.jpg", sourceImg.getWidth(), sourceImg.getHeight(), "jpg", img);
        System.out.println(img.length);

        HJFaceFeature hjFaceFeatureI = null;
        for (HJFaceModel hjFaceModel : hjFaceModels) {
            hjFaceFeatureI = new HJFaceFeature();
            long start = System.currentTimeMillis();
            System.out.println("EnginId:" + resource.getEnginId() + "--CompareId:" + resource.getCompareId());
            String result = test.HJFaceModel(resource.getEnginId(), resource.getCompareId(), img, 24, sourceImg.getWidth(), sourceImg.getHeight(), hjFaceFeatureI);

            //test.HJFaceFeaturePick(compareId, img, sourceImg.getWidth(), sourceImg.getHeight(), hjFaceModel.getDwReserved(), hjFaceFeatureI);
            System.out.println("face image feature pick:" + (System.currentTimeMillis() - start));
        }
        return hjFaceFeatureI;
    }

    private static void initFaceData(HJFaceFeature hjFaceFeatureI,String group) {
        railwayFaceBiz.HJFaceSetBuild("20190123");
        // start_Time = System.currentTimeMillis();
        RailwayFaceBean temp = new RailwayFaceBean();
        temp.setKeyId("0001");
        temp.setModelData(hjFaceFeatureI.getpFeature());
        temp.setYear(1995);
        temp.setType("113");
        temp.setSex(1);
        temp.setArea("350524");
        railwayFaceBiz.HJInsertFaceToSet(group, temp);
        for (int i = 2; i <= 100; i++) {
            RailwayFaceBean railwayFaceBean = new RailwayFaceBean();
            railwayFaceBean.setKeyId("000" + i);
            railwayFaceBean.setModelData(hjFaceFeatureI.getpFeature());
            railwayFaceBean.setYear(1995);
            railwayFaceBean.setType("222");
            int six = i % 2 == 0 ? 1 : 2;
            railwayFaceBean.setSex(six);
            railwayFaceBean.setArea("35112" + i);
            railwayFaceBiz.HJInsertFaceToSet(group, railwayFaceBean);
        }
    }

    private static void writeImageFromArray(String imageFile, int width, int height, String type, byte[] gbrArray) {
        if (gbrArray != null && gbrArray.length == width * height * 3) {
            DataBufferByte dataBuffer = new DataBufferByte(gbrArray, gbrArray.length);
            ColorSpace cs = ColorSpace.getInstance(1000);
            int[] nBits = new int[]{8, 8, 8};
            int[] bOffs = new int[]{2, 1, 0};
            ComponentColorModel colorModel = new ComponentColorModel(cs, nBits, false, false, 1, 0);
            WritableRaster raster = Raster.createInterleavedRaster(dataBuffer, width, height, width * 3, 3, bOffs, (Point)null);
            BufferedImage newImg = new BufferedImage(colorModel, raster, false, (Hashtable)null);

            try {
                File file = new File(imageFile);
                ImageIO.write(newImg, type, file);
            } catch (IOException var13) {
                var13.printStackTrace();
            }

        } else {
            throw new IllegalArgumentException("invalid image description");
        }
    }


    private static byte[] getMatrixBGR(BufferedImage image) {
        byte[] matrixBGR;
        if (isBGR3Byte(image)) {
            matrixBGR = (byte[])image.getData().getDataElements(0, 0, image.getWidth(), image.getHeight(), (Object)null);
        } else {
            int[] intrgb = image.getRGB(0, 0, image.getWidth(), image.getHeight(), (int[])null, 0, image.getWidth());
            matrixBGR = new byte[image.getWidth() * image.getHeight() * 3];
            int i = 0;

            for(int j = 0; i < intrgb.length; j += 3) {
                matrixBGR[j] = (byte)(intrgb[i] & 255);
                matrixBGR[j + 1] = (byte)(intrgb[i] >> 8 & 255);
                matrixBGR[j + 2] = (byte)(intrgb[i] >> 16 & 255);
                ++i;
            }
        }

        return matrixBGR;
    }

    private static boolean equalBandOffsetWith3Byte(BufferedImage image, int[] bandOffset) {
        if (image.getType() == 5 && image.getData().getSampleModel() instanceof ComponentSampleModel) {
            ComponentSampleModel sampleModel = (ComponentSampleModel)image.getData().getSampleModel();
            if (Arrays.equals(sampleModel.getBandOffsets(), bandOffset)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isBGR3Byte(BufferedImage image) {
        return equalBandOffsetWith3Byte(image, new int[]{0, 1, 2});
    }


}
