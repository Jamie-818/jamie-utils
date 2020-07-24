package com.jamie.html.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配工具类
 * 
 * @author jamie
 * @date 2020/7/24 9:09
 */
public class RegesUtils {
    private static final Pattern SPACE_PATTERN = Pattern.compile("\\s|\\t|\\r|\\n");

    /**
     * 去除空格\t、回车\n、换行符\r、制表符\t
     *
     * @param str 字符串
     * @return java.lang.String
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Matcher m = SPACE_PATTERN.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}
