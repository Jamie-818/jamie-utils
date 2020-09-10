package com.jamie.jdk8.lambda.file;

/**
 * 文件处理函数式接口
 * @author jamie
 * @date 2020/9/9 23:32
 */
@FunctionalInterface
public interface FileConsumer {

    /**
     * 函数式接口抽象方法
     * @param fileContent 文件内容字符串
     * @return void
     */
    void fileHandle(String fileContent);

}
