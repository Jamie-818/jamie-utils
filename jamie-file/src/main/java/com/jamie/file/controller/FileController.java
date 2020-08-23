package com.jamie.file.controller;

import com.jamie.file.utils.FileUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

/**
 * 文件操作Controller
 * @author jamie
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 下载文件接口
     * @author xuanweiyao
     * @date 14:56 2019/4/17
     */
    @ApiOperation(value = "下载文件接口", notes = "下载文件接口", httpMethod = "GET")
    @GetMapping("get")
    public void downFile(HttpServletResponse response) {
        File file = new File("E:\\show-product\\study-product\\jamie-com.jamie.file.utils\\jamie-apidoc\\src"
                + "\\main\\resources\\test.pdf");
        String base64 = FileUtils.readFile(file);
        byte[] bytesByFile = Base64.getDecoder().decode(base64);
        // 清空response
        response.reset();
        response.addHeader("Content-Disposition", "inline;filename=" + file.getName() + "." + file.getName());
        response.addHeader("Content-Length", "" + bytesByFile.length);
        try(OutputStream toClient = new BufferedOutputStream(response.getOutputStream())){
            // 判断是否是pdf格式，不然按TXT处理
            if(StringUtils.equals("pdf", "pdf")){
                response.setContentType("application/pdf");
            }else{
                response.setContentType("text/plain");
            }
            toClient.write(bytesByFile);
            toClient.flush();
        }catch(IOException ex){
            log.error("获取 OutputStream 对象失败", ex);
            response.reset();
            response.setContentType("application/json");
            response.setStatus(500);
        }
    }

    /**
     * 下载文件接口
     * @author xuanweiyao
     * @date 14:56 2019/4/17
     */
    @ApiOperation(value = "下载文件接口", notes = "下载文件接口", httpMethod = "GET")
    @GetMapping("file1")
    public void downFile2(HttpServletResponse response) {
        String base64 = FileUtils.readFile(new File(
                "E:\\show-product\\study-product\\jamie-com.jamie.file.utils\\jamie-apidoc\\src"
                        + "\\main\\resources\\base64.txt"));
        // 以流的形式下载文件。
        byte[] bytesByFile = Base64.getDecoder().decode(base64);

        response.addHeader("Content-Disposition", "inline;filename=" + 123 + "." + "pdf");
        response.addHeader("Content-Length", "" + (bytesByFile.length));

        try(ServletOutputStream outputStream = response.getOutputStream()){

            response.setContentType("application/pdf");
            outputStream.write(bytesByFile);
        }catch(IOException ex){
            log.error("获取 OutputStream 对象失败", ex);
            response.reset();
            response.setContentType("application/json");
            response.setStatus(500);
        }
    }

    /**
     * @apiDescription 下载文件接口
     * @author xuanweiyao
     * @date 14:56 2019/4/17
     */
    @ApiOperation(value = "下载文件接口", notes = "下载文件接口", httpMethod = "GET")
    @GetMapping("file2")
    public void downFile1(HttpServletResponse response) throws IOException {
        String base64 = FileUtils.readFile(new File(
                "E:\\show-product\\study-product\\jamie-com.jamie.file.utils\\jamie-apidoc\\src"
                        + "\\main\\resources\\base64.txt"));

        // 以流的形式下载文件。
        byte[] buffer = Base64.getDecoder().decode(base64);
        response.getOutputStream().write(buffer);
    }

}
