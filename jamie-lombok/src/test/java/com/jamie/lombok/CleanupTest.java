package com.jamie.lombok;

import lombok.Cleanup;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 演示：lombok @Cleanup 注解
 * 资源关闭
 * @author jamie
 * @date 2020/9/12 21:39
 */
public class CleanupTest {

    public void copyFile(String in, String out) throws IOException {
        @Cleanup FileInputStream originalFileInputStream = new FileInputStream(in);
        @Cleanup FileOutputStream targetFileOutputStream = new FileOutputStream(out);
        // 读取的字节信息
        int content;
        // 迭代，读取/写入字节
        while((content = originalFileInputStream.read()) != -1){
            targetFileOutputStream.write(content);
        }
    }

}

