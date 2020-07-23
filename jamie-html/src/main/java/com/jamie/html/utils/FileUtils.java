package com.jamie.html.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 文件操作类
 * 
 * @author jamie
 * @date 2020/7/23 9:23
 */
@Slf4j
public class FileUtils {

    /**
     * 读取File文件
     *
     * @param file 文件类型
     * @return 返回文件里面的文本字符串
     */
    public static String readFile(File file) {
        log.info("————开始读取" + file.getPath() + "文件————");
        try {
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file), UTF_8);
            int ch;
            StringBuilder sb = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                sb.append((char)ch);
            }
            fileReader.close();
            reader.close();
            String jsonStr = sb.toString();
            log.info("————读取" + file.getPath() + "文件结束!————");
            return jsonStr;
        } catch (Exception e) {
            log.info("————读取" + file.getPath() + "文件出现异常，读取失败!————");
            e.printStackTrace();
            return null;
        }
    }
}
