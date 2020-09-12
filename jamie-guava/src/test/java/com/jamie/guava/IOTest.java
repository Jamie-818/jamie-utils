package com.jamie.guava;

import com.google.common.base.Charsets;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 演示如何使用流(Source)与汇(Sink)来对文件进行常用操作
 * @author jamie
 * @date 2020/9/12 12:06
 */
public class IOTest {

    /**
     * 拷贝文件
     */
    @Test
    public void copyFile() throws IOException {
        // 创建对应的Source和Sink
        CharSource charSource = Files.asCharSource(new File("Source.txt"), Charsets.UTF_8);
        CharSink charSink = Files.asCharSink(new File("Sink.txt"), Charsets.UTF_8);
        // 拷贝
        charSource.copyTo(charSink);
    }

}
