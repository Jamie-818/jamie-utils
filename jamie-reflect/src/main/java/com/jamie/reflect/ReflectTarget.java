package com.jamie.reflect;

public class ReflectTarget extends ReflectTargetOrigin {

    //---------构造函数-----------

    /**
     * 默认的带参数构造函数
     * @param str
     * @return
     */
    ReflectTarget(String str) {
        System.out.println("(默认)的构造方法 s = " + str);
    }

    /**
     * 无参构造函数
     * @return
     */
    public ReflectTarget() {
        System.out.println("调用了公有的无参构造方法 。。。");
    }

    /**
     * 有一个参数的构造函数
     * @param name
     * @return
     */
    public ReflectTarget(char name) {
        System.out.println("调用了带有一个参数的构造方法，参数值为 " + name);
    }

    /**
     * 有多个参数的构造函数
     * @param name  string入参
     * @param index int入参
     */
    public ReflectTarget(String name, int index) {
        System.out.println("调用了带有多个参数的构造方法，参数值为【目标名】： " + name + " 【序号】" + index);
    }

    /**
     * 受保护的构造函数
     * @param n boolean入参
     */
    protected ReflectTarget(boolean n) {
        System.out.println("受保护的构造方法 n :" + n);
    }

    /**
     * 私有的构造函数
     * @param index 入参index
     */
    private ReflectTarget(int index) {
        System.out.println("私有的构造方法 序号：" + index);
    }

    //**************字段*******************

    /** 公有权限字段 */
    public String name;

    /** 父子权限字段 */
    protected int index;

    /** 默认权限字段 */
    char type;

    /** 私有权限字段 */
    private String targetInfo;

    @Override
    public String toString() {
        return "ReflectTarget [name="
                + name
                + ", index="
                + index
                + ", type="
                + type
                + ", targetInfo="
                + targetInfo
                + "]";
    }

    //***************成员方法***************//
    public void show1(String s) {
        System.out.println("调用了公有的，String参数的show1(): s = " + s);
    }

    protected void show2() {
        System.out.println("调用了受保护的，无参的show2()");
    }

    void show3() {
        System.out.println("调用了默认的，无参的show3()");
    }

    private String show4(int index) {
        System.out.println("调用了私有的，并且有返回值的，int参数的show4(): index = " + index);
        return "show4result";
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //第一种方式获取Class对象 Object->getClass()
        ReflectTarget reflectTarget = new ReflectTarget();
        Class<?> reflectTargetClass1 = reflectTarget.getClass();
        System.out.println("1st : " + reflectTargetClass1.getName());
        //第二种方式获取Class对象 任何数据类型(包括基本数据类型)都有一个"静态"的class属性
        Class<?> reflectTargetClass2 = ReflectTarget.class;
        System.out.println("2nd: " + reflectTargetClass2.getName());
        //判断第一种方式获取的class对象和第二种方式获取的是否是同一个
        System.out.println(reflectTargetClass1 == reflectTargetClass2);
        //第三种方式来获取Class对象 通过Class类的静态方法:forName(String className)  *常用*
        Class<?> reflectTargetClass3 = Class.forName("com.jamie.reflect.ReflectTarget");
        System.out.println("3rd: " + reflectTargetClass3.getName());
        System.out.println(reflectTargetClass2 == reflectTargetClass3);
    }

}
