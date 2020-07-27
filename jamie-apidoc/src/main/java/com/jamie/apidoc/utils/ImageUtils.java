package com.jamie.apidoc.utils;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;

/**
 * 应用模块名称：
 * 
 * @author show
 * @since 2020/7/20 下午 10:55
 */
@Slf4j
public class ImageUtils {
    /**
     * base64转图片流
     * 
     * @param base64string base64
     * @return java.awt.image.BufferedImage
     */
    public static BufferedImage base64String2BufferedImage(String base64string) {
        BufferedImage out = null;
        InputStream stream = baseToInputStream(base64string);
        try {
            out = ImageIO.read(stream);
            System.out.println(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    private static InputStream baseToInputStream(String base64string) {
        byte[] bytes1 = Base64.getDecoder().decode(base64string);
        return new ByteArrayInputStream(bytes1);
    }

    /**
     * 压缩图片
     * 
     * @author xuanweiyao
     * @date 2020/7/21 10:55
     * @param base64Img 图片base64
     * @param sizeRatio 大小压缩比率
     * @param widthRatio 宽度压缩比例
     * @param heightRatio 高度压缩比例
     * @return java.lang.String
     */
    public static String resizeImage(String base64Img, int sizeRatio, int widthRatio, int heightRatio) {
        if (StringUtils.isBlank(base64Img)) {
            log.error("base64Img:" + base64Img);
            throw new RuntimeException("图片size有误");
        }
        if (sizeRatio <= 0) {
            sizeRatio = 1;
        }
        if (widthRatio <= 0) {
            widthRatio = 1;
        }
        if (heightRatio <= 0) {
            heightRatio = 1;
        }
        try {
            BufferedImage src = base64String2BufferedImage(base64Img);
            if (src == null) {
                throw new RuntimeException("图片转换失败");
            }
            double ratio = txDouble(1, sizeRatio);
            BufferedImage output =
                Thumbnails.of(src).size(src.getWidth() / widthRatio, src.getHeight() / heightRatio).asBufferedImage();
            output = Thumbnails.of(output).scale(ratio).asBufferedImage();
            return imageToBase64(output);
        } catch (Exception e) {
            e.printStackTrace();
            return base64Img;
        }

    }

    /**
     * 图片流转base64
     * 
     * @param bufferedImage 图片流
     * @return java.lang.String
     */
    public static String imageToBase64(BufferedImage bufferedImage) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            BufferedImage tag;
            tag = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_BGR);
            Graphics g = tag.getGraphics();
            // 绘制缩小后的图
            g.drawImage(bufferedImage, 0, 0, null);
            g.dispose();
            bufferedImage = tag;
            boolean jpg = ImageIO.write(bufferedImage, "jpg", baos);
            System.out.println(jpg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();
        System.out.println(Arrays.asList(bytes));
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 除法运算，保留小数
     * 
     * @param a 被除数
     * @param b 除数
     * @return 商
     */
    public static double txDouble(int a, int b) {
        return (double)a / b;
    }

    public static void main(String[] args) {
        // File file = new File("src\\main\\resources\\static\\doc\\images.txt");
        // String base64Img = JsonUtil.readJsonFile(file);
        // int sizeRatio = 2;
        // int widthRatio = 2;
        // int heightRatio = 2;
        // String s = ImageUtils.resizeImageToSize(base64Img, sizeRatio, widthRatio, heightRatio);
        // System.out.println("压缩成功：");
        // assert base64Img != null;
        // System.out.println("压缩前：" + base64Img.length());
        // System.out.println("压缩后：" + s.length());

    }
}