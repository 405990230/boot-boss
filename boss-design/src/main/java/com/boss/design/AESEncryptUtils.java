package com.boss.design;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加解密算法
 */
public class AESEncryptUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(AESEncryptUtils.class);

    private static String ivParameterSpec = "3465281205462396";
    private static String key = "uQAZBjZrbshFp9Ix";

    public static String encrypt(String phone) {
        return assemble(phone, key, ivParameterSpec);
    }

    public static String encrypt(String phone, String sKey) {
        return assemble(phone, sKey, ivParameterSpec);
    }

    public static String encrypt(String phone, String sKey, String sIv) {
        return assemble(phone, sKey, sIv);
    }

    public static String decrypt(String securityPhone) {
        return disassemble(securityPhone, key, ivParameterSpec);
    }

    public static String decrypt(String securityPhone, String sKey) {
        return disassemble(securityPhone, sKey, ivParameterSpec);
    }

    public static String decrypt(String securityPhone, String sKey, String sIv) {
        return disassemble(securityPhone, sKey, sIv);
    }

    private static String assemble(String sSrc, String sKey, String sIv) {
        try {
            if (sKey == null) {
                return null;
            }

            if (sKey.length() != 16) {
                return null;
            }
            byte[] raw = sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(sIv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes());
            System.out.println(encrypted);
            return byte2hex(encrypted).toLowerCase();
        } catch (Exception e) {
            LOGGER.error("AESEncryptUtils assemble : error", e);
        }
        return null;
    }

    private static String disassemble(String sSrc, String sKey, String sIv) {
        try {
            if (sKey == null) {
                return null;
            }

            if (sKey.length() != 16) {
                return null;
            }
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(sIv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = hex2byte(sSrc);
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original);
        } catch (Exception e) {
            LOGGER.error("AESEncryptUtils disassemble : error", e);
        }
        return null;
    }

    private static byte[] hex2byte(String strHex) {
        if (strHex == null) {
            return null;
        }
        int l = strHex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(strHex.substring(i * 2, i * 2 + 2), 16);
        }
        return b;
    }

    private static String byte2hex(byte[] b) {
        String hs = "";
        String tmp;
        for (int n = 0; n < b.length; n++) {
            tmp = (Integer.toHexString(b[n] & 0XFF));
            if (tmp.length() == 1) {
                hs = hs + "0" + tmp;
            } else {
                hs = hs + tmp;
            }
        }
        return hs.toUpperCase();
    }
}