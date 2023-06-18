package com.mrx.mrxapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * @Auther: Mrx
 * @DateTime: 2023/6/5 13:15
 * @Description:
 */
public class SignUtils {
    public static String getSign(String body, String secretKey) {
        Digester md5=new Digester(DigestAlgorithm.SHA256);
        String content= body+"."+secretKey;
        return md5.digestHex(content);
    }

}
