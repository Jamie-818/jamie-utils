package com.jamie.threadpool;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 演示：向线程池提交任务
 * @author jamie
 * @date 2020/9/12 13:26
 */
public class RunTest {

    @Test
    public void submit() throws ExecutionException, InterruptedException {
        // 创建线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        // 利用submit方法提交任务，接收任务的返回结果
        Future<Integer> submit = threadPool.submit(() -> {
            Thread.sleep(1000L * 10);
            return 2 * 5;
        });
        // 阻塞方法，直到任务有返回值后，才向下执行
        Integer integer = submit.get();
        System.out.println("执行结果" + integer);
    }

    @Test
    public void execute() throws InterruptedException {
        // 创建线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        // 利用execute方法提交任务，没有返回结果
        threadPool.execute(() -> {
            try{
                Thread.sleep(1000L * 10);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            int number = 2 * 5;
            System.out.println("执行结果" + number);
        });
        Thread.sleep(1000L * 100);
    }

}
