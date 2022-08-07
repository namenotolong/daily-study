package com.huyong;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class Test1 {
    public static final String CHARSET = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA";
    public static final int KEY_SIZE = 4096;

    public static Map<String, String> createKeys() {
        //为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
        }

        //初始化KeyPairGenerator对象,密钥长度
        kpg.initialize(KEY_SIZE);
        //生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        //得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64String(publicKey.getEncoded());
        //得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.encodeBase64String(privateKey.getEncoded());
        Map<String, String> keyPairMap = new HashMap<String, String>();
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);

        return keyPairMap;
    }

    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }


    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    public static String privateEncrypt(String data, RSAPrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.encodeBase64String(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }


    public static String publicDecrypt(String data, RSAPublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            String base64Data = new String(Hex.decodeHex(data.toCharArray()), "UTF-8");
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(base64Data), publicKey.getModulus().bitLength()), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize) {
        int maxBlock = 0;
        if (opmode == Cipher.DECRYPT_MODE) {
            maxBlock = keySize / 8;
        } else {
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        byte[] resultDatas = out.toByteArray();
        try {
            out.close();
        } catch (Exception e) {
            e.getMessage();
        }
        return resultDatas;
    }

    public static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            str += Integer.toHexString(ch);
        }
        return str;
    }

    public static void main(String[] args) throws Exception {
        String publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEArPIOj+rFgUWiFibVx19W1TuA/iqeMpY4vNgzRgGPmKXY7PPcB20r9i6/tl6Sb78hCvDpMf3TWlY3C74u5XHkM66d5U0DdpmsKOFlMPtLCww02R1NF+viSTffK8Z7XJy5cQK5v5CDIRyqqbuiLiK80W5c+GzWOKL+L9uVWSHT8HTCOF1doXWI4m3FmLq1W/+Fi5LBN3juWj0Le5Snqr2/f/FKqmnUTcgbqZvRFvHtb+QDVsGtMKt7Sky1lmSN9zBWUWEisyHsz71E1WTA/CQv4Z8p+cEFTNDxsRU5N2dFyEsWg1EoAVwGslcfhkNHkrknBrGv39BJECKbrLY8GO0kpWfs0+iBcuxCwv6Il0DgKbWkPlopvtbbg2ePirw4Rnd+CrstGdtIjwcfO7Ye4mKJGq38cMx0rgxPOZPLo1mdesdvjbibwGqbAVsXmW5pDUS3EI0Ia5CvuU8QuL+IsOUW7G0OmBCvDO0kUUXJyA1YKea0OGmJ234eMUYmoRz2Fu5tDrpi6YC2NFwkii097R2CpuDoLiHGTeNU9tmwUjxhPniWAGRIwZCMYHIeTS/1CskgDtRYiCKuMbrLUGuQedLrlXFTDl27ZE8B3In8+oQ4kKT41hMCbQmvTHIva21+LIHeJ7M3Pd7PMdgyVpOfyT5VihA3yt6tvAywtYSB0SKGM0cCAwEAAQ==";
        String privateKey = "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";

        System.out.println("私钥加密——公钥解密");
        String str = "{\"vendorName\":\"观远数据\",\"customerName\":\"湘湖救援团\",\"expireTime\":\"2099-07-22\",\"serviceExpiresTime\":\"2099-07-22\",\"remindTime\":\"2022-07-31\",\"limitUserCount\":-1,\"editUserCount\":-1,\"generateTime\":\"2022-07-30 14:30:00\",\"licenseType\":1,\"features\":[]}";
        System.out.println("\r明文：\r\n" + str);
        long start = System.currentTimeMillis();
        String encodedData = privateEncrypt(str, getPrivateKey(privateKey));
        long end = System.currentTimeMillis();
        System.out.println("加密耗时：\r\n" + (start - end) + "ms");
        System.out.println("密文：\r\n" + encodedData);
        String ssoToken = toHexString(encodedData);
        System.out.println("ssoToken：\r\n" + ssoToken);
        start = System.currentTimeMillis();
        String decodedData = publicDecrypt(ssoToken, getPublicKey(publicKey));
        end = System.currentTimeMillis();
        System.out.println("解密耗时：\r\n" + (start - end) + "ms");
        System.out.println("解密后文字: \r\n" + decodedData);
    }
}
