package com.boss.tools.utils;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String get(String url, Map<String, String> header) {
        OkHttpClient client = new OkHttpClient();
        Request.Builder request = new Request.Builder().url(url).get();
        addHeaders(request, header);
        try (Response response = client.newCall(request.build()).execute()) {
            ResponseBody res = response.body();
            return res.string();
        } catch (Exception e) {
            logger.error("HttpUtil.get e:[{}]", e);
        }
        return null;
    }

    public static byte[] getBytes(String url, Map<String, String> header) {
        OkHttpClient client = new OkHttpClient();
        Request.Builder request = new Request.Builder().url(url).get();
        addHeaders(request, header);
        try (Response response = client.newCall(request.build()).execute()) {
            ResponseBody res = response.body();
            return res.bytes();
        } catch (Exception e) {
            logger.error("HttpUtil.getBytes e:[{}]", e);
        }
        return null;
    }

    public static String delete(String url, Map<String, String> header) {
        OkHttpClient client = new OkHttpClient();
        Request.Builder request = new Request.Builder().url(url).delete();
        addHeaders(request, header);
        try (Response response = client.newCall(request.build()).execute()) {
            ResponseBody res = response.body();
            return res.string();
        } catch (Exception e) {
            logger.error("HttpUtil.delete e:[{}]", e);
        }
        return null;
    }

    public static String post(String url, Map<String, String> param, Map<String, String> header) {
        OkHttpClient client = new OkHttpClient();
        Request.Builder request = new Request.Builder().url(url);
        FormBody.Builder formBody = new FormBody.Builder();
        if (param != null && !param.isEmpty()) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                if (entry.getKey() != null && entry.getValue() != null) {
                    formBody.add(entry.getKey(), entry.getValue());
                }
            }
        }
        request.post(formBody.build());
        addHeaders(request, header);
        try (Response response = client.newCall(request.build()).execute()) {
            ResponseBody res = response.body();
            return res.string();
        } catch (Exception e) {
            logger.error("HttpUtil.post e:[{}]", e);
        }
        return null;
    }

    public static String postBody(String url, String json, Map<String, String> header) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request.Builder request = new Request.Builder().url(url).post(body);
        addHeaders(request, header);
        try (Response response = client.newCall(request.build()).execute()) {
            ResponseBody res = response.body();
            return res.string();
        } catch (Exception e) {
            logger.error("HttpUtil.postBody e:[{}]", e);
        }
        return null;
    }

    public static String patchBody(String url, String json, Map<String, String> header) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request.Builder request = new Request.Builder().url(url).patch(body);
        addHeaders(request, header);
        try (Response response = client.newCall(request.build()).execute()) {
            ResponseBody res = response.body();
            return res.string();
        } catch (Exception e) {
            logger.error("HttpUtil.patchBody e:[{}]", e);
        }
        return null;
    }

    public static String putBody(String url, String json, Map<String, String> header) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request.Builder request = new Request.Builder().url(url).put(body);
        addHeaders(request, header);
        try (Response response = client.newCall(request.build()).execute()) {
            ResponseBody res = response.body();
            return res.string();
        } catch (Exception e) {
            logger.error("HttpUtil.putBody e:[{}]", e);
        }
        return null;
    }

    private static void addHeaders(Request.Builder request, Map<String, String> header) {
        if (header != null && !header.isEmpty()) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * MultipartFile 转 File
     *
     * @param file
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
