package com.jamie.natice.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.jamie.natice.pojo.dto.HttpClientResponseDTO;

import lombok.Data;

/**
 * 应用模块名称：
 * 
 * @author show
 * @since 2020/6/30 14:56
 */
@Data
public class HttpClient4 {

    public static HttpClientResponseDTO doGet(String url, Map<String, String> headerMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        CookieStore cookieStore = new BasicCookieStore();
        try {
            // 通过址默认配置创建一个httpClient实例
            // httpClient = HttpClients.createDefault();
            httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build(); // 创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(url);
            // 设置请求头信息，鉴权
            for (Entry<String, String> stringStringEntry : headerMap.entrySet()) {
                String key = stringStringEntry.getKey();
                String value = stringStringEntry.getValue();
                httpGet.addHeader(key, value);
            }
            // httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            // 设置配置请求参数
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 请求超时时间
                .setSocketTimeout(60000)// 数据读取超时时间
                .build();
            // 为httpGet实例设置配置
            httpGet.setConfig(requestConfig);
            // 执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            // 通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            String resStr = EntityUtils.toString(entity);
            List<Cookie> cookies = cookieStore.getCookies();
            return HttpClientResponseDTO.builder().cookies(cookies).response(resStr).build();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            clone(httpClient, response);
        }
        return null;
    }

    public static String doPost(String url, Map<String, Object> paramMap, Map<String, String> handerMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        // 配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
            .setConnectionRequestTimeout(35000)// 设置连接请求超时时间
            .setSocketTimeout(60000)// 设置读取数据连接超时时间
            .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        for (Entry<String, String> stringStringEntry : handerMap.entrySet()) {
            String key = stringStringEntry.getKey();
            String value = stringStringEntry.getValue();
            httpPost.addHeader(key, value);
        }
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        // 封装post请求参数
        if (null != paramMap && paramMap.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // 通过map集成entrySet方法获取entity
            Set<Entry<String, Object>> entrySet = paramMap.entrySet();
            // 循环遍历，获取迭代器
            for (Entry<String, Object> mapEntry : entrySet) {
                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
            }

            // 为httpPost设置封装好的请求参数
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            clone(httpClient, httpResponse);
        }
        return result;
    }

    private static void clone(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse) {
        if (null != httpResponse) {
            try {
                httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != httpClient) {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
