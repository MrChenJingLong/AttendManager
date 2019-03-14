package com.hjkj.cloud.attend.infrastructure.utils;

import java.util.Random;
import java.util.UUID;

public class SafeModel
{

    private static String[] strs = new String[]{
            "a","b","c","d","e","f","g","h","i","g","k","l","m","n","o",
            "p","q","r","s","t","u","v","w","x","y","z",
            "A","B","C","D","E","F","G","H","I","G","K","L","M","N","O",
            "P","Q","R","S","T","U","V","W","X","Y","Z",
            "1","2","3","4","5","6","7","8","9","0",
            "+","-","/","=","#"
    };
    private static String strKey()
    {
        int count = strs.length;
        Random Random = new Random();
        String Key = "";
        for (int i = 0; i < 8; i++)
        {
            Key += strs[Random.nextInt(count)];
        }
        return Key;
    }
    /// <summary> 进行DES加密 </summary>
    /// <param name="pToEncrypt">要加密的字符串。</param>
    /// <returns>以Base64格式返回的加密字符串。</returns>
    public static String Encrypt(String toEncrypt) throws Exception
    {
        String key = strKey();

        return Go(key + DESPlus.encryptDES(toEncrypt, key));
    }
    /// <summary> 进行DES解密 </summary>
    /// <param name="toDecrypt">要解密的以Base64</param>
    /// <returns>已解密的字符串。</returns>
    public static String Decrypt(String toDecrypt) throws Exception
    {
        toDecrypt = Back(toDecrypt);
        String key = toDecrypt.substring(0, 8);
        toDecrypt = toDecrypt.substring(8);
        return DESPlus.decryptDES(toDecrypt, key);
    }

    /// <summary>
    /// 转换
    /// </summary>
    private static String Go(String strValue)
    {
        return strValue.replace("+", "%a")
                .replace("-", "%b")
                .replace("/", "%c")
                .replace("=", "%d")
                .replace("#", "%e");
    }
    /// <summary>
    /// 还原
    /// </summary>
    private static String Back(String strValue)
    {
        return strValue.replace("%a", "+")
                .replace("%b", "-")
                .replace("%c", "/")
                .replace("%d", "=")
                .replace("%e", "#");
    }

    public static void main(String[] args) throws Exception {
        String token = "http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name:aaa;Name:aaa;GivenName:超级管理员;System:Max;Role:Admin";
        String encrypt = Encrypt(token);
        System.out.println("encrypt after:" + encrypt);
        System.out.println("decrypt after:" + Decrypt(encrypt));



    }

}

