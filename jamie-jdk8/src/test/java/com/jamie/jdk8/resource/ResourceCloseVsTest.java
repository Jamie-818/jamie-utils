package com.jamie.jdk8.resource;

import com.jamie.jdk8.lambda.file.FileConsumer;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 资源关闭优化前与优化后代码量对象
 * @author jamie
 * @date 2020/9/10 18:47
 */
public class ResourceCloseVsTest {

    String url = null;

    FileConsumer fileConsumer = null;

    @Before
    public void init() {
        url = "E:\\jamie-product\\personal\\jamie-utils\\jamie-jdk8\\src\\main\\java\\com\\jamie\\jdk8\\resource"
                + "\\close\\ResourceCloseVsTest.java";
        fileConsumer = System.out::println;
    }

    /**
     * 新的方式资源关闭
     */
    @Test
    public void newFileHandle() {
        // 声明
        try(FileInputStream fileInputStream = new FileInputStream(url);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){

            // 定义行变量和内容sb
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            // 循环读取文件内容
            while(((line = bufferedReader.readLine()) != null)){
                stringBuilder.append(line)
                             .append("\n");
            }
            //调用函数式接口方法，将文件内容传递给lambda表达式，实现业务逻辑
            fileConsumer.fileHandle(stringBuilder.toString());
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 旧的方式资源关闭
     */
    @Test
    public void oldFileHandle() {
        // 声明
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try{
            fileInputStream = new FileInputStream(url);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            // 定义行变量和内容sb
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            // 循环读取文件内容
            while(((line = bufferedReader.readLine()) != null)){
                stringBuilder.append(line)
                             .append("\n");
            }
            //调用函数式接口方法，将文件内容传递给lambda表达式，实现业务逻辑
            fileConsumer.fileHandle(stringBuilder.toString());
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            // 关闭流资源
            if(bufferedReader != null){
                try{
                    bufferedReader.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(inputStreamReader != null){
                try{
                    inputStreamReader.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(fileInputStream != null){
                try{
                    fileInputStream.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
