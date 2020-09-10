package com.jamie.file;

import com.jamie.file.utils.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileTest {

    @Test
    public void test() {
        //        FileUtils.listDirectory("D:\\jamie\\学习视频\\pm\\光环资料\\光环PMBOK第六版培训\\光环PMBOK第六版：培训课程（全）");
        FileUtils.listDirectory("D:\\jamie\\学习视频\\pm\\B站视频\\PMP 项目管理第六版培训教程");
    }

    @Test
    public void tes2() {
        List<String> strings = new ArrayList<>();
        File file = new File("E:\\jamie-product\\personal\\jamie-utils\\jamie-file\\src\\main\\resources\\test.txt");
        String base64 = FileUtils.readFile(file);
        String[] split = base64.split("\r\n");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(split));
        for(String s: list){
            String[] split1 = s.split("\\.");
            String number = split1[0];
            int i = Integer.parseInt(number);
            if(i < 10){
                number = "00" + number;
            }
            if(i >= 10 && i < 100){
                number = "0" + number;
            }
            String trim = split1[2].trim();
            strings.add(number + "." + trim.substring(0, trim.lastIndexOf("(")));
        }
        strings.forEach(System.out::println);

    }

}
