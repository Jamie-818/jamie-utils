package com.jamie.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示：使用线程池和不使用线程池的对比
 * @author jamie
 * @date 2020/9/12 12:27
 */
public class ThreadVs {

    /**
     * 老的处理线程的操作
     */
    @Test
    public void oldHandle() throws InterruptedException {
        //使用循环来模拟许多用户请求的场景
        for(int request = 0; request < 100; request++){
            new Thread(() -> {
                System.out.println("文档处理开始");
                try{
                    // 将Word转换为PDF格式：处理时长很长的耗时过长
                    Thread.sleep(1000L * 30);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("文档处理结束");
            }).start();
        }
        Thread.sleep(1000L * 1000);
    }

    /**
     * 新的处理线程的操作
     */
    @Test
    public void newHandle() throws InterruptedException {
        //开启一个线程池：线程个数是10个
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //使用循环来模拟许多用户请求的场景
        for(int request = 0; request < 100; request++){
            executorService.execute(() -> {
                System.out.println("文档处理开始");
                try{
                    // 将Word转换为PDF格式：处理时长很长的耗时过长
                    Thread.sleep(1000L * 30);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("文档处理结束");
            });
        }
        Thread.sleep(1000L * 1000);
    }

}
