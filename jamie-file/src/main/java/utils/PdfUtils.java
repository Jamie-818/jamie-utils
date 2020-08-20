package utils;

import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.Base64;
import java.util.Objects;

/**
 * pdf相关工具类
 * @author xuanweiyao
 * @date 2020/8/20 10:03
 */
public class PdfUtils {

    /**
     * base64转文件
     * @param base64Content base64内容
     * @param filePath      文件路径
     */
    public static void base64StringToPdf(String base64Content, String filePath) {
        BASE64Decoder decoder = new BASE64Decoder();
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try{
            // base64编码内容转换为字节数组
            byte[] bytes = decoder.decodeBuffer(base64Content);
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
    static String pdfToBase64(File file) {
        FileInputStream fin = null;
        BufferedInputStream bin = null;
        ByteArrayOutputStream baos = null;
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
                // 关闭 ByteArrayOutputStream 无效。此类中的方法在关闭此流后仍可被调用，而不会产生任何 IOException
                // IOException
                // baos.close();
                Objects.requireNonNull(bout).close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    //    public static void main(String[] args) {
    //        // 将PDF格式文件转成base64编码
    //        String base64String = pdfToBase64(new File("D:\\Desktop\\1597828079696No05.pdf"));
    //        System.out.println(base64String);
    //        base64StringToPdf(base64String, "D:\\Desktop\\test.pdf");
    //
    //    }

}
