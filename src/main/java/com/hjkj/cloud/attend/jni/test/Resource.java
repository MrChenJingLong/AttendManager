package com.hjkj.cloud.attend.jni.test;

import com.hj.jni.utils.HJFaceDrive;

/**
 * ========================================================
 * 日 期：2016年6月15日 下午2:32:23
 * 作 者：qijiabin
 * 版 本：1.0.0
 * 类说明：资源类
 * ========================================================
 * 修订日期     修订人    描述
 */
public class Resource {

	private  long enginId;
	private long compareId;
	private long compareThreadId;
	final HJFaceDrive test = new HJFaceDrive();
	
	public Resource() {
//		synchronized (this) {
			
			 /**
	         **  检测引擎初始化
	         * Parameters:
	         * 	nMinIod 最小眼间距
	         * 	nRollAngle 最大旋转角度
	         * 	nConfidence 预期匹配分数
	         */
	        this.enginId = test.HJDetectEngineIntial(20, 35, 60);
	        
	         /**
	          * * 检测引擎初始化
	          * Parameters:
	          * 	nMinIod 最小眼间距
	          * 	nRollAngle 最大旋转角度
	          * 	nConfidence 预期匹配分数
	          */
	        this.compareId = test.HJRecognizeEngineIntial();
	    	/**
			 * 比对引擎初始化

			 */
	        this.compareThreadId = test.HJCompareEngineIntial();
//		}
	}

	public long getEnginId() {
		return enginId;
	}

	public void setEnginId(long enginId) {
		this.enginId = enginId;
	}

	public long getCompareId() {
		return compareId;
	}

	public void setCompareId(long compareId) {
		this.compareId = compareId;
	}

	public long getCompareThreadId() {
		return compareThreadId;
	}

	public void setCompareThreadId(long compareThreadId) {
		this.compareThreadId = compareThreadId;
	}



	@Override
	public String toString() {
		return "Resource [enginId=" + enginId + ", compareId=" + compareId + ", compareThreadId=" + compareThreadId
				+ "]";
	}

	
	
}

