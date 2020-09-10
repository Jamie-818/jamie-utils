package com.jamie.jdk8.stream;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 流的四种构建形式
 * @author jamie
 */
public class StreamConstructorTest {

    /**
     * 通过数值构建流
     */
    @Test
    public void streamFromValue() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        integerStream.forEach(System.out::println);
    }

    /**
     * 通过数组构建流
     */
    @Test
    public void streamFromArray() {
        int[] numbers = {1, 2, 3, 4, 5};
        IntStream stream = Arrays.stream(numbers);
        stream.forEach(System.out::println);
    }

    /**
     * 通过文件构建流
     */
    @Test
    public void streamFromFile() throws IOException {
        Stream<String> stringStream = Files.lines(Paths.get("E:\\jamie-product\\personal\\jamie-utils\\jamie-jdk8"
                + "\\src\\main\\java\\com\\jamie\\jdk8\\stream\\StreamConstructorTest.java"));
        stringStream.forEach(System.out::println);
    }

    /**
     * 通过函数构建流(无限流)
     */
    @Test
    public void streamFromFunction() {
        // 基于迭代器
        Stream<Integer> iterate = Stream.iterate(0, n -> n + 2);
        iterate.limit(100)
               .forEach(System.out::println);
        // 基于无序流
        Stream<Double> generate = Stream.generate(Math::random);
        generate.limit(100)
                .forEach(System.out::println);

    }

}
