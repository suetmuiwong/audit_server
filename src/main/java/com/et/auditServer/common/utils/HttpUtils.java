package com.et.auditServer.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

public class HttpUtils {
    private final static Logger log = LoggerFactory.getLogger(HttpUtils.class);
    /**
     * get方式
     * url 请求地址
     * */
    public static JSONObject doGet(String url, StringBuffer params){
        // 获得Http客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 参数
        /*StringBuffer params = new StringBuffer();
        try {
            // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
            params.append("name=" + URLEncoder.encode("&", "utf-8"));
            params.append("&");
            params.append("age=24");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }*/
        // 创建Get请求
        HttpGet httpGet = new HttpGet(url+ "?" + params);

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);

            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);

            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity == null) {
//                log.error("microfile no response!");
                return null;
            }
            String result = EntityUtils.toString(responseEntity,
                    Charset.forName("UTF-8"));
            if (StringUtils.isBlank(result)) {
                return null;
            }
            JSONObject resultObj = JSON.parseObject(result);
            if (resultObj.getInteger("code") != 0) {
//                log.error("microfile upload failed!");
                return null;
        }
            return resultObj.getJSONObject("data");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    /**
     * post方式
     * url 请求地址
     * map 请求参数
     * */
    public static JSONArray psotUpload(String url, Map<String, Object> map) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse httpResponse;
            HttpPost httpPost = new HttpPost(url);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));
            String fileName = "";
            for (String key : map.keySet()) {
                if (map.get(key) instanceof String) {
                    if ("userId".equals(key)) {
                        multipartEntityBuilder.addPart("userId", new StringBody(String.valueOf(map.get(key)),
                                ContentType.create("text/plain", Consts.UTF_8)));
                    } else {
                        multipartEntityBuilder.addTextBody(key, String.valueOf(map.get(key)));
                    }

                } else if (map.get(key) instanceof MultipartFile) {
                    MultipartFile file = (MultipartFile) map.get(key);
                    fileName = file.getOriginalFilename();
                    multipartEntityBuilder.addBinaryBody(key, file.getInputStream(),
                            ContentType.MULTIPART_FORM_DATA, fileName);
                }
                else {
                    continue;
                }
            }
            HttpEntity httpEntity = multipartEntityBuilder.build();

            httpPost.setEntity(httpEntity);

            httpResponse = httpClient.execute(httpPost);
            HttpEntity responseEntity = httpResponse.getEntity();
            if (responseEntity == null) {
                log.error("microfile no response!");
                return null;
            }
            String result = EntityUtils.toString(responseEntity,
                    Charset.forName("UTF-8"));
            log.info("file[" + fileName + "] microfile upload response: " +
                    result);
            if (StringUtils.isBlank(result)) {
                log.error("microfile no response!");
                return null;
            }
            JSONObject resultObj = JSON.parseObject(result);
            if (resultObj.getInteger("code") != 0) {
                log.error("microfile upload failed!");
                return null;
            }
            return resultObj.getJSONArray("data");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
