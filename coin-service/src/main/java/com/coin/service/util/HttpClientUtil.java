package com.coin.service.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    private static final RequestConfig requestConfig;

    static {
        requestConfig = RequestConfig.custom()
                // 客户端和服务器建立连接的timeout
                .setConnectTimeout(1000 * 60)
                // 指从连接池获取连接的timeout
                .setConnectionRequestTimeout(6000)
                // 客户端从服务器读取数据的timeout
                .setSocketTimeout(1000 * 60 * 3)
                .build();
    }

    public static JSONObject doPost(String url, Map<String, String> header, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            log.debug("-->>Http POST请求地址：" + url);
            if (null != param) {
                log.debug("-->>Http 请求参数：" + param);
            }

            HttpPost httpPost = new HttpPost(url);
            for (String key : header.keySet()) {
                httpPost.setHeader(key, header.get(key));
            }
            httpPost.setConfig(requestConfig);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
                log.debug("<<--Http 响应内容：" + resultString);
            } else {
                log.error("<<--Http 响应状态码：" + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            log.error("Http 发送请求异常 url:{}", url, e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                log.error("Http 关闭流异常", e);
            }
        }
        return JSONUtil.parseObj(resultString);
    }
}
