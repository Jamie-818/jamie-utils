package com.jamie.html.test;

import com.jamie.html.utils.HtmlUtils;
import com.jamie.html.utils.RegesUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ImoocMain {
    public static void main(String[] args) throws IOException {
        String classId = "1225";
        printClass(classId);
    }

    private static void printClass(String classId) throws IOException {
        String cookie =
            "imooc_uuid=51c4b78b-b27b-411d-a2fc-8cb5e965d3f5; imooc_isnew_ct=1577092698; imooc_isnew=2; Hm_lvt_f0cfcccd7b1393990c78efdeebff3968=1579590545; "
                + "Hm_lpvt_f0cfcccd7b1393990c78efdeebff3968=1579593263; cninfo=weixin-4e0a8984f424039de11161539ef7e4c4; redrainTime=2020-2-24; zg_did=%7B%22did%22%3A%20%2216f459bc78074a-0594971492960a-504f221b-384000-16f459bc78189a%22%7D; zg_f375fe2f71e542a4b890d9a620f9fb32=%7B%22sid%22%3A%201588396364100%2C%22updated%22%3A%201588396366993%2C%22info%22%3A%201588396364105%2C%22superProperty%22%3A%20%22%7B%5C%22%E5%BA%94%E7%94%A8%E5%90%8D%E7%A7%B0%5C%22%3A%20%5C%22%E6%85%95%E8%AF%BE%E7%BD%91%E6%95%B0%E6%8D%AE%E7%BB%9F%E8%AE%A1%5C%22%2C%5C%22Platform%5C%22%3A%20%5C%22web%5C%22%7D%22%2C%22platform%22%3A%20%22%7B%7D%22%2C%22utm%22%3A%20%22%7B%7D%22%2C%22referrerDomain%22%3A%20%22%22%2C%22cuid%22%3A%20%22z30ymQQx4j4%2C%22%2C%22zs%22%3A%200%2C%22sc%22%3A%200%2C%22firstScreen%22%3A%201588396364100%7D; loginstate=1; apsid=JkMDYwZTdhMmUzNjY3ZmU3OTAxYzQyODlhYzViOTQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANTQ1MDA0OQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAxMDA0MTA4NDg4QHFxLmNvbQAAAAAAAAAAAAAAAAAAADUzYWRkZTYxNzZkODE2ZGUzOGJjM2Q4YjA3MTc1OTQxytEWX8rRFl8%3DOW; last_login_username=1004108488%40qq.com; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%225450049%22%2C%22first_id%22%3A%22172079724491ef-008ce951f617ce-d373666-3686400-1720797244aba%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22%24device_id%22%3A%22172079724491ef-008ce951f617ce-d373666-3686400-1720797244aba%22%7D; IMCDNS=0; cvde=5e00865ae3005-634";
        Document document = Jsoup.connect("https://class.imooc.com/course/" + classId).userAgent(
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36")
            .cookies(HtmlUtils.getCookieMap(cookie)).get();
        Element body = document.body();
        // 获取课程列表的dom
        Element courseBanner = body.getElementById("courseBanner");
        Elements h1 = courseBanner.select("h1");
        System.out.println(h1.text());
        Elements elementsByClass = body.getElementsByClass("chapter-item active learned");
        Elements title = elementsByClass.tagName("title");
        for (Element element : title) {
            System.out.println(element.select("h2").text());
            for (Element li : element.select("li")) {
                String trim = li.text().trim();
                System.out.println(RegesUtils.replaceBlank(trim));
            }
        }
    }

}
