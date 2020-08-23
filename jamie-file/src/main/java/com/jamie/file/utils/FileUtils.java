package com.jamie.file.utils;

import java.io.*;
import java.util.Base64;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 文件操作类
 * @author jamie
 * @date 2020/7/23 9:23
 */
public class FileUtils {

    /**
     * 读取File文件
     * @param file 文件类型
     * @return 返回文件里面的文本字符串
     */
    public static String readFile(File file) {
        System.out.println("————开始读取" + file.getPath() + "文件————");
        try{
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file), UTF_8);
            int ch;
            StringBuilder sb = new StringBuilder();
            while((ch = reader.read()) != -1){
                sb.append((char)ch);
            }
            fileReader.close();
            reader.close();
            String jsonStr = sb.toString();
            System.out.println("————读取" + file.getPath() + "文件结束!————");
            return jsonStr;
        }catch(Exception e){
            System.out.println("————读取" + file.getPath() + "文件出现异常，读取失败!————");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把文件转成文件流
     * @param filePath 文件路径
     * @return byte[] 文件流
     */
    public static byte[] file2Byte(String filePath) {
        try{
            File file = new File(filePath);
            //获取输入流
            FileInputStream fis = new FileInputStream(file);

            //新的 byte 数组输出流，缓冲区容量1024byte
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
            //缓存
            byte[] b = new byte[1024];
            int n;
            while((n = fis.read(b)) != -1){
                bos.write(b, 0, n);
            }
            fis.close();
            //改变为byte[]
            byte[] data = bos.toByteArray();
            //
            bos.close();
            return data;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }

    /**
     * base64转文件
     * @param base64Str base64内容
     * @param filePath  文件路径
     */
    public static void base64Str2File(String base64Str, String filePath) {
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try{
            // base64编码内容转换为字节数组
            byte[] bytes = Base64.getDecoder().decode(base64Str);
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
            bis = new BufferedInputStream(byteInputStream);
            File file = new File(filePath);
            File path = file.getParentFile();
            if(!path.exists()){
                path.mkdirs();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            byte[] buffer = new byte[1024];
            int length = bis.read(buffer);
            while(length != -1){
                bos.write(buffer, 0, length);
                length = bis.read(buffer);
            }
            bos.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                Objects.requireNonNull(bos).close();
                fos.close();
                bis.close();
            }catch(IOException e){
                e.printStackTrace();
            }

        }
    }

    /**
     * 将PDF转换成base64编码
     * 1.使用BufferedInputStream和FileInputStream从File指定的文件中读取内容；
     * 2.然后建立写入到ByteArrayOutputStream底层输出流对象的缓冲输出流BufferedOutputStream
     * 3.底层输出流转换成字节数组，然后由BASE64Encoder的对象对流进行编码
     * @param file 文件
     */
    static String file2Base64(File file) {
        FileInputStream fin = null;
        BufferedInputStream bin = null;
        ByteArrayOutputStream baos;
        BufferedOutputStream bout = null;
        try{
            // 建立读取文件的文件输出流
            fin = new FileInputStream(file);
            // 在文件输出流上安装节点流（更大效率读取）
            bin = new BufferedInputStream(fin);
            // 创建一个新的 byte 数组输出流，它具有指定大小的缓冲区容量
            baos = new ByteArrayOutputStream();
            // 创建一个新的缓冲输出流，以将数据写入指定的底层输出流
            bout = new BufferedOutputStream(baos);
            byte[] buffer = new byte[1024];
            int len = bin.read(buffer);
            while(len != -1){
                bout.write(buffer, 0, len);
                len = bin.read(buffer);
            }
            // 刷新此输出流并强制写出所有缓冲的输出字节，必须这行代码，否则有可能有问题
            bout.flush();
            byte[] bytes = baos.toByteArray();
            byte[] decode = Base64.getEncoder().encode(bytes);
            return new String(decode);

        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                Objects.requireNonNull(fin).close();
                Objects.requireNonNull(bin).close();
                Objects.requireNonNull(bout).close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        byte[] bytes = FileUtils.file2Byte("src/main/resources/properties/application.properties");
        System.out.println(bytes);
    }

}
