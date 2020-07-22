package com.jamie.natice.utils;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.http.cookie.Cookie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jamie.natice.pojo.dto.CookieDTO;
import com.jamie.natice.pojo.dto.ElecDTO;
import com.jamie.natice.pojo.dto.HttpClientResponseDTO;

/**
 * 应用模块名称：
 * 
 * @author show
 * @since 2020/7/10 10:57
 */
public class ElecUtils {

    public static void main(String[] args) {
        Properties p = PropertiesUtils.getProperties("application.properties");
        String loginPhone = p.getProperty("jamie.mail.loginPhone");
        String password = p.getProperty("jamie.mail.password");
        CookieDTO login = login(loginPhone, password);
        String elecHtml = getElecHtml(login, loginPhone, password);
        System.out.println(elecHtml);
    }

    /**
     * 登录
     */
    public static CookieDTO login(String loginPhone, String password) {
        String url = "http://www.quanfangtong.net/phonehtml/phoneLogin/login.action"
            + "?token=096b786d-e33c-490d-a5aa-a919d1e8986f" + "&conpanyId=Company_20170627113853JMD7cK"
            + "&corUrl=gzrdgy" + "&language=Chinese" + "&loginPhone=" + loginPhone + "&password=" + password;
        HttpClientResponseDTO httpClientResponseDTO = HttpClient4.doGet(url, new HashMap<>());
        assert httpClientResponseDTO != null;
        List<Cookie> cookies = httpClientResponseDTO.getCookies();
        CookieDTO cookieDTO = new CookieDTO();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            if (StringUtils.equals("JSESSIONID", name)) {
                cookieDTO.setJsessionId(value);
            }
            if (StringUtils.equals("SERVERID", name)) {
                cookieDTO.setServerId(value);
            }
            if (StringUtils.equals("route", name)) {
                cookieDTO.setRoute(value);
            }
        }
        return cookieDTO;
    }

    /**
     * 获取电量页面
     */
    public static String getElecHtml(CookieDTO cookieDto, String loginName, String password) {
        String url = "http://www.quanfangtong.net/phonehtml/myIntelligent/findMyelec.action";
        String route = cookieDto.getRoute();
        String jsesSionId = cookieDto.getJsessionId();
        String serverId = cookieDto.getServerId();
        String cookie = "route=" + route + ";JSESSIONID=" + jsesSionId + ";loginname=" + loginName + ";password="
            + password + ";language=Chinese;" + "SERVERID=" + serverId;
        Map<String, String> headerMap = getElecHeader(cookie);
        return Objects.requireNonNull(HttpClient4.doGet(url, headerMap)).getResponse();
    }

    /**
     * 从页面中抓出电量
     */
    public static ElecDTO getElecNumber(String html) {
        Document parse = Jsoup.parse(html);
        Element body = parse.body();
        Elements div = body.getElementsByTag("div");
        String text = div.get(0).text();
        String[] s = text.split(" ");
        if (s.length != 7) {
            throw new RuntimeException("页面更新，元素获取有问题");
        }
        // 今日用电电量
        String todayElec = s[2];
        // 剩余电量
        String surplusElec = s[3] + "度";
        // 本月用电电量
        String monthElec = s[5];
        return ElecDTO.builder().todayElec(todayElec).surplusElec(surplusElec).monthElec(monthElec).build();

    }

    /**
     * 获取电费查询的header
     */
    private static Map<String, String> getElecHeader(String cookie) {
        Map<String, String> header = new HashMap<>(16);
        header.put("Accept",
            "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        header.put("Accept-Encoding", "gzip,deflate");
        header.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        header.put("Connection", "keep-alive");
        header.put("Cookie", cookie);
        header.put("Host", "www.quanfangtong.net");
        header.put("Referer", "http://www.quanfangtong.net/phonehtml/myIntelligent/findIntelligentList.action");
        header.put("Upgrade-Insecure-Requests", "1");
        header.put("User-Agent",
            "Mozilla/5.0(WindowsNT10.0;Win64;x64)AppleWebKit/537.36(KHTML,likeGecko)Chrome/83.0.4103.116Safari/537.36");
        return header;
    }

}
