package com.jamie.pattern.singleton;

/**
 * 枚举饿汉单例模式
 * 因为枚举的对象不能通过反射去创建，所以利用反射，也不能直接新创建一个对象
 * @author jamie
 * @date 20.11.2 18:47
 */
public class EnumStarvingSingleton {

    private EnumStarvingSingleton() {}

    public static EnumStarvingSingleton getInstance() {
        return ContainerHolder.HOLDER.instance;
    }

    private enum ContainerHolder {
        HOLDER;

        private EnumStarvingSingleton instance;

        /** 枚举的构造函数 */
        ContainerHolder() {
            instance = new EnumStarvingSingleton();
        }
    }

}
