package com.jamie.html.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
    /**
     * 获取resources文件夹下的配置文件
     * 
     * @param propertiesName 配置文件名
     * @return java.util.Properties
     */
    public static Properties getProperties(String propertiesName) {
        InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(propertiesName);
        Properties p = new Properties();
        try {
            p.load(inputStream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return p;
    }

}
