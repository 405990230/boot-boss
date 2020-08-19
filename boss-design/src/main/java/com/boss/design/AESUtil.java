package com.boss.design;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {

    private static final Cipher encryptCipher;
    private static final Cipher decryptCipher;

    //AES only supports key sizes of 16, 24 or 32 bytes
    private static final String aesKey = "bNpRbdSq1iBuGNYy";
    private static final SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey.getBytes(), "AES");

    static {
        try {
            encryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            decryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            decryptCipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String encryptData(String data) {
        try {
            return Base64.getEncoder().encodeToString(encryptCipher.doFinal(data.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decryptData(String base64Data) {
        try {
            return new String(decryptCipher.doFinal(Base64.getDecoder().decode(base64Data)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) {
//        String phoneAes = encryptData("18621843412");
//        System.out.println(phoneAes);
//    }

}
