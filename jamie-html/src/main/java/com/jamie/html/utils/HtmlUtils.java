package com.jamie.html.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * html操作工具类
 * 
 * @author jamie
 * @date 2020/7/23 9:23
 */
public class HtmlUtils {
    /**
     * 获取页面Document对象
     * 
     * @param html html页面
     * @return 页面Document对象
     */
    public static Document getDocument(String html) {
        return Jsoup.parse(html);
    }

    /**
     * 把cookie字段串解析成cookieMao
     * 
     * @param cookie cookie字符串
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    public static Map<String, String> getCookieMap(String cookie) {
        Map<String, String> map = new HashMap<>(16);
        String[] split = cookie.split(";");
        for (String s : split) {
            String[] split1 = s.split("=");
            map.put(split1[0], split1[1]);
        }
        return map;
    }
}
