package com.jamie.html.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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
    public Document getDocument(String html) {
        return Jsoup.parse(html);
    }
}
