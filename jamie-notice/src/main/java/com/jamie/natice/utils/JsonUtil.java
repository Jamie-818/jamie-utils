package com.jamie.natice.utils;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.*;

import lombok.extern.slf4j.Slf4j;

/**
 * Json读取工具类
 */
@Slf4j
public class JsonUtil {

    /**
     * 读取json文件
     * 
     * @param jsonFile json文件
     * @return 返回json字符串
     */
    public static String readJsonFile(File jsonFile) {

        log.info("————开始读取" + jsonFile.getPath() + "文件————");
        try {
            // File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), UTF_8);
            int ch;
            StringBuilder sb = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                sb.append((char)ch);
            }
            fileReader.close();
            reader.close();
            String jsonStr = sb.toString();
            log.info("————读取" + jsonFile.getPath() + "文件结束!————");
            return jsonStr;
        } catch (Exception e) {
            log.info("————读取" + jsonFile.getPath() + "文件出现异常，读取失败!————");
            e.printStackTrace();
            return null;
        }
    }

}
