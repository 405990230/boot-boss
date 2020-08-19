package com.boss.tools.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * Created by weidd on 2017/11/13.
 */
public class HttpUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

    private static final String ENCODE = "UTF-8";

    private static int SOCKET_TIMEOUT = 60000;
    private static int CONN_TIMEOUT = 30000;

    public static String httpPostTool(URI uri, List<NameValuePair> nvp) {
        return httpPostToolCommon(uri, nvp, null, null, null);
    }

    public static String httpPostTool(URI uri, List<NameValuePair> nvp, List<NameValuePair> headerNvp) {
        return httpPostToolCommon(uri, nvp, headerNvp, null, null);
    }

    /**
     * post请求
     * @param uri
     * @param nvp
     * @param headerNvp
     * @param socketTimeOut
     * @param connectTimeOut
     * @return
     */
    public static String httpPostToolCommon(URI uri, List<NameValuePair> nvp, List<NameValuePair> headerNvp, Integer socketTimeOut, Integer connectTimeOut) {
        if (socketTimeOut == null) {
            socketTimeOut = SOCKET_TIMEOUT;
        }
        if (connectTimeOut == null) {
            connectTimeOut = CONN_TIMEOUT;
        }
        HttpClientBuilder builder = HttpClientBuilder.create();
        HttpClient http = builder.build();
        HttpPost httpPost = new HttpPost(uri);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeOut).setConnectTimeout(connectTimeOut).build();
        httpPost.setConfig(requestConfig);
        UrlEncodedFormEntity entityForm;
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = null;
        try {
            entityForm = new UrlEncodedFormEntity(nvp, ENCODE);
            setHeader(httpPost, headerNvp);
            httpPost.setEntity(entityForm);
            HttpResponse response = http.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() != 200) {
                httpPost.abort();
                return String.format("{code:%s}", response.getStatusLine().getStatusCode());
            }
            reader = new BufferedReader(new InputStreamReader(entity.getContent(), ENCODE));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            LOGGER.error("HttpUtil.httpPostToolCommon error", e);
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
            httpPost.releaseConnection();
        }
        return sb.toString();

    }

    public static String httpPostWithJsonTool(URI uri, String body) {
        return httpPostRequestBodyTool(uri, body, null, null, null);

    }

    /**
     * post请求
     * @param uri
     * @param body
     * @param headerNvp
     * @param socketTimeOut
     * @param connectTimeOut
     * @return
     */
    public static String httpPostRequestBodyTool(URI uri, String body, List<NameValuePair> headerNvp, Integer socketTimeOut, Integer connectTimeOut) {
        if (socketTimeOut == null) {
            socketTimeOut = SOCKET_TIMEOUT;
        }
        if (connectTimeOut == null) {
            connectTimeOut = CONN_TIMEOUT;
        }
        HttpClientBuilder builder = HttpClientBuilder.create();
        HttpClient http = builder.build();
        HttpPost httpPost = new HttpPost(uri);
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeOut).setConnectTimeout(connectTimeOut).build();
        httpPost.setConfig(requestConfig);
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = null;
        try {
            setHeader(httpPost, headerNvp);
            if (StringUtils.isNotBlank(body)) {
                httpPost.setEntity(new StringEntity(body, Charset.forName("UTF-8")));
            }
            HttpResponse response = http.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() != 200) {
                httpPost.abort();
                return String.format("{code:%s}", response.getStatusLine().getStatusCode());
            }
            reader = new BufferedReader(new InputStreamReader(entity.getContent(), ENCODE));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            LOGGER.error("HttpUtil.httpPostRequestBodyTool error", e);
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
            httpPost.releaseConnection();
        }
        return sb.toString();

    }

    public static String httpPostWithFileTool(URI uri, Map<String, File> fileMap) {
        return httpPostWithFileTool(uri, null, fileMap, null, null, null);
    }

    public static String httpPostWithFileTool(URI uri, List<NameValuePair> nvp, Map<String, File> fileMap, List<NameValuePair> headerNvp, Integer socketTimeOut, Integer connectTimeOut) {
        if (socketTimeOut == null) {
            socketTimeOut = SOCKET_TIMEOUT;
        }
        if (connectTimeOut == null) {
            connectTimeOut = CONN_TIMEOUT;
        }
        HttpClientBuilder builder = HttpClientBuilder.create();
        HttpClient httpclient = builder.build();
        HttpPost httppost = new HttpPost(uri);
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeOut).setConnectTimeout(connectTimeOut).build();
        httppost.setConfig(requestConfig);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        if (null != nvp) {
            for (NameValuePair pair : nvp) {
                multipartEntityBuilder.addPart(pair.getName(), new StringBody(pair.getValue(), ContentType.APPLICATION_JSON));
            }
        }
        File file;
        for (Map.Entry<String, File> entry : fileMap.entrySet()) {
            file = entry.getValue();
            String fileName;
            try {
                fileName = URLEncoder.encode(file.getName(), ENCODE);
            } catch (UnsupportedEncodingException e) {
                fileName = file.getName();
            }
            multipartEntityBuilder.addPart(entry.getKey(), new FileBody(file, ContentType.DEFAULT_BINARY, fileName));
        }
        httppost.setEntity(multipartEntityBuilder.build());
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = null;
        try {
            setHeader(httppost, headerNvp);
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() != 200) {
                httppost.abort();
                return String.format("{code:%s}", response.getStatusLine().getStatusCode());
            }
            reader = new BufferedReader(new InputStreamReader(entity.getContent(), ENCODE));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            LOGGER.error("HttpUtil.httpPostWithFileTool error", e);
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
            httppost.releaseConnection();
        }
        return sb.toString();
    }

    public static String httpGetTool(URI uri) {
        return httpGetToolWeatherEncode(uri, null, null, true);
    }

    public static String httpGetTool(URI uri, Integer socketTimeOut, Integer connectTimeOut) {
        return httpGetToolWeatherEncode(uri, null, null, true, socketTimeOut, connectTimeOut);
    }

    public static String httpGetTool(URI uri, List<NameValuePair> nvp) {
        return httpGetToolWeatherEncode(uri, nvp, null, true);
    }

    public static String httpGetTool(URI uri, List<NameValuePair> nvp, Integer socketTimeOut, Integer connectTimeOut) {
        return httpGetToolWeatherEncode(uri, nvp, null, true, socketTimeOut, connectTimeOut);
    }

    public static String httpGetTool(URI uri, List<NameValuePair> nvp, List<NameValuePair> headerNvp) {
        return httpGetToolWeatherEncode(uri, nvp, headerNvp, true);
    }

    public static String httpGetToolWithoutLog(URI uri, List<NameValuePair> nvp, List<NameValuePair> headerNvp) {
        return httpGetToolWeatherEncode(uri, nvp, headerNvp, true);
    }

    public static String httpGetToolWithoutEncode(URI uri, List<NameValuePair> nvp, List<NameValuePair> headerNvp) {
        return httpGetToolWeatherEncode(uri, nvp, headerNvp, false);
    }

    public static String httpGetToolWeatherEncode(URI uri, List<NameValuePair> nvp, List<NameValuePair> headerNvp, boolean needEncode) {
        return httpGetToolWeatherEncode(uri, nvp, headerNvp, needEncode, SOCKET_TIMEOUT, CONN_TIMEOUT);
    }

    /**
     * get请求
     * @param uri
     * @param nvp
     * @param headerNvp
     * @param needEncode
     * @param socketTimeOut
     * @param connectTimeOut
     * @return
     */
    public static String httpGetToolWeatherEncode(URI uri, List<NameValuePair> nvp, List<NameValuePair> headerNvp, boolean needEncode, Integer socketTimeOut, Integer connectTimeOut) {
        if (socketTimeOut == null) {
            socketTimeOut = SOCKET_TIMEOUT;
        }
        if (connectTimeOut == null) {
            connectTimeOut = CONN_TIMEOUT;
        }
        HttpClientBuilder builder = HttpClientBuilder.create();
        HttpClient http = builder.build();
        StringBuilder url = new StringBuilder();
        url.append(uri.toString());
        if (null != nvp && nvp.size() > 0) {
            url.append("?");
            for (int i = 0; i < nvp.size(); i++) {
                try {
                    String value = nvp.get(i).getValue();
                    if (needEncode) {
                        if (value != null) {
                            value = URLEncoder.encode(nvp.get(i).getValue(), ENCODE).replace("+", "%20");
                        }
                    }
                    url.append(nvp.get(i).getName()).append("=")
                            .append(value).append("&");
                } catch (UnsupportedEncodingException e) {
                }
            }
            if (url.toString().endsWith("&")) {
                url.deleteCharAt(url.lastIndexOf("&"));
            }
        }
        HttpGet httpget = new HttpGet(url.toString());
        //设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeOut).setConnectTimeout(connectTimeOut).build();
        httpget.setConfig(requestConfig);
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = null;
        try {
            setHeader(httpget, headerNvp);
            HttpResponse response = http.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() != 200) {
                httpget.abort();
                return String.format("{code:%s}", response.getStatusLine().getStatusCode());
            }
            reader = new BufferedReader(new InputStreamReader(entity.getContent(), ENCODE));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            LOGGER.error("HttpUtil.httpGetToolWeatherEncode error", e);
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
            httpget.releaseConnection();
        }
        return sb.toString();
    }

    public static void setHeader(HttpRequestBase httpRequestBase, List<NameValuePair> headerNvpr) {
        if (headerNvpr != null && headerNvpr.size() > 0) {
            for (NameValuePair n : headerNvpr) {
                httpRequestBase.setHeader(n.getName(), n.getValue());
            }
        }
        httpRequestBase.setHeader("Connection", "close");
    }



    private static final String FAIL = "{\"code\": 400,\"errorMessage\": \"操作失败\"}";
}
