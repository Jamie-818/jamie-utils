package com.jamie.pattern.singleton;

/**
 * 懒汉单例模式：在被客户端首次调用的时候才创建唯一实例
 * 添加双重检查锁机制的懒汉模式能确保线程安全
 * @author jamie
 * @date 20.11.2 15:59
 */
public class LazyDoubleCheckSingleton {

    private volatile static LazyDoubleCheckSingleton instance;

    /** 构造函数私有化确保不会被外部调用创建 */
    private LazyDoubleCheckSingleton() {}

    public static LazyDoubleCheckSingleton getInstance() {
        //第一次检测
        if(instance == null){
            //同步
            synchronized(LazyDoubleCheckSingleton.class){
                if(instance == null){
                    //memory = allocate(); //1.分配对象内存空间
                    //instance(memory);    //2.初始化对象
                    //instance = memory;   //3.设置instance指向刚分配的内存地址，此时instance！=null
                    instance = new LazyDoubleCheckSingleton();
                }

            }
        }
        return instance;
    }

}

