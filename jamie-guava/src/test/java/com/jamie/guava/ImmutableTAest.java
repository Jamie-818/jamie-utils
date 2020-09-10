package com.jamie.guava;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 不可变集合用法
 * @author jamie
 */
public class ImmutableTAest {

    /**
     * 测试：使用 unmodifiableList
     * 1. 把集合变成不可变集合
     * 2. 对不可变集合进行修改时，会触发异常
     */
    @Test
    public void unmodifiableList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        // 把list变成不可变list，避免函数把数据改变
        List<Integer> newList = Collections.unmodifiableList(list);
        removeList(newList);
        System.out.println(newList);
    }

    /**
     * 对集合进行删除操作
     * @param list 集合
     */
    public static void removeList(List<Integer> list) {
        list.remove(0);
    }

    /**
     * guava构造不可变集合的三种方式
     */
    @Test
    public void immutable() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        // 方式一：使用copyOf，通过已经存在的集合
        ImmutableSet<Integer> integers = ImmutableSet.copyOf(list);
        // 方式二：通过初始值，直接创建不可变集合
        ImmutableSet<Integer> of = ImmutableSet.of(1, 2, 3);
        //方式三：以builder形式创建
        ImmutableSet<Object> build = ImmutableSet.builder()
                                                 .add(1)
                                                 .addAll(Sets.newHashSet(2, 3))
                                                 .add(4)
                                                 .build();

    }

}

