package com.boss.info.bmw.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class CMSApiHelper {

    private static final Logger logger = LoggerFactory.getLogger(CMSApiHelper.class);

    public static String generateHASHKey(String rawString, String key) {

        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] resultArray = mac.doFinal(rawString.getBytes());

            String resultString = Base64.encodeBase64String(resultArray);
            resultString = resultString.replaceAll("[+]", "-");
            resultString = resultString.replaceAll("[/]", "_");
            return resultString;
        } catch (Exception e) {
            logger.error("generateHASHKey error {} ", e);
            return null;
        }

    }

}
