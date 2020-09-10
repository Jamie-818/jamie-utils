package com.jamie.jdk8.resource;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * JDK7之前的文件拷贝功能
 * @author jamie
 * @date 2020/9/10 19:11
 */
public class OldFileCopyTest {

    @Test
    public void copyFile() {
        // 1.创建输入输出流
        // 2.执行文件拷贝，读取文件内容，写入到另一个文件中
        // 3.关闭文件流资源

        // 定义输入路径和输出路径
        String originalUrl = "lib\\FileCopyTest.java";
        String targetUrl = "targetTest\\target.txt";
        // 声明文件输入流，文件输出流
        FileInputStream originalFileInputStream = null;
        FileOutputStream targetFileOutputStream = null;
        try{
            // 实例化文件流对象
            originalFileInputStream = new FileInputStream(originalUrl);
            targetFileOutputStream = new FileOutputStream(targetUrl);
            // 读取的字节信息
            int content;
            // 迭代，读取/写入字节
            while((content = originalFileInputStream.read()) != -1){
                targetFileOutputStream.write(content);
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(targetFileOutputStream != null){
                try{
                    targetFileOutputStream.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(originalFileInputStream != null){
                try{
                    originalFileInputStream.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
