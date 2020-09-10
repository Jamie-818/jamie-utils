package com.jamie.lambda.file;

import org.junit.Test;

import java.io.IOException;

public class FileServiceTest {

    @Test
    public void fileHandle() throws IOException {
        FileService fileService = new FileService();
        // 通过lambda打印文件内容
        fileService.fileHandle("E:\\jamie-product\\personal\\jamie-utils\\jamie-lambda\\src\\test\\java\\com\\jamie"
                + "\\lambda\\file\\FileServiceTest.java", fileContent -> System.out.println(fileContent));
    }

}
