package com.jamie.threadpool;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 演示：线程池可选择的队列
 * @author jamie
 * @date 2020/9/12 13:04
 */
public class QueueTest {

    /**
     * 基于数组的有界阻塞队列，队列容量为10
     * 当队列满了时，会阻塞住，等待队列消费后再添加
     */
    @Test
    public void arrayBlockingQueue() throws InterruptedException {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
        // 循环向队列中添加元素
        for(int i = 0; i < 20; i++){
            queue.put(i);
            System.out.println("向队列中添加值：" + i);
        }
    }

    /**
     * 基于链表有界阻塞队列，队列容量为10，当把初始化10去除，那就变成了无界队列
     * 当队列满了时，会阻塞住，等待队列消费后再添加
     */
    @Test
    public void linkedBlockingQueue() throws InterruptedException {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
        // 循环向队列中添加元素
        for(int i = 0; i < 20; i++){
            queue.put(i);
            System.out.println("向队列中添加值：" + i);
        }
    }

    /**
     * 同步移交队列
     * 没有储存元素，如果没有消费，则一直卡在等待消费
     */
    @Test
    public void synchronousQueue() {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        // 插入值
        new Thread(() -> {
            try{
                queue.put(1);
                System.out.println("插入成功");
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }).start();
        // 删除值
        new Thread(() -> {
            try{
                queue.take();
                System.out.println("删除成功");
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }).start();
    }

}
