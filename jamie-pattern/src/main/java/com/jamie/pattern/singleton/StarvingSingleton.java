package com.jamie.pattern.singleton;

/**
 * 饿汉单例模式
 * 在加载类的时候已经实例化了，然后把构造函数私有化，提供获取该实例的方法
 * @author jamie
 * @date 20.11.2 15:41
 */
public class StarvingSingleton {

    /** 把对象以常量形式创建，实例化的时候创建该对象 */
    private static final StarvingSingleton STARVING_SINGLETON = new StarvingSingleton();

    /** 构造函数私有化确保不会被外部调用创建 */
    private StarvingSingleton() { }

    public static StarvingSingleton getInstance() {
        return STARVING_SINGLETON;
    }

}

