package com.jamie.file.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 静态文件工具类
 * @author jamie
 */
public class ResourcesUtils {

    /**
     * 获取配置文件对象
     * @param filePath 文件名,记得在前面带"/"
     * @return java.util.Properties 配置文件对象
     */
    private static Properties readProperties(String filePath) throws IOException {
        InputStream in = ResourcesUtils.class.getResourceAsStream(filePath);
        Properties properties = new Properties();
        properties.load(in);
        return properties;
    }

    //    public static void main(String[] args) throws IOException {
    //        Properties properties = ResourcesUtils.readProperties("/application.properties");
    //        System.out.println(properties.toString());
    //    }

}
