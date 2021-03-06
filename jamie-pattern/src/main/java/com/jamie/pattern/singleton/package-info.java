/**
 * 单例模式 Singleton Pattern
 * 确保一个类只有一个实例，并对外提供统一访问方式
 * - 饿汉模式：类被加载的时候就立即初始化并创建唯一实例
 * - 懒汉模式：在被客户端首次调用的时候才创建唯一实例
 * - 添加双重检查锁机制的懒汉模式能确保线程安全
 * - 装备了枚举的饿汉模式能抵御反射与序列化的进攻，满足容器需求
 */
package com.jamie.pattern.singleton;
