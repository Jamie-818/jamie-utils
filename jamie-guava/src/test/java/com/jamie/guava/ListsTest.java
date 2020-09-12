package com.jamie.guava;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Lists 使用
 * @author jamie
 * @date 2020/9/12 11:58
 */
public class ListsTest {

    /**
     * Lists 工具类的常用方法：
     * 反转 / 拆分
     */
    private static final List<Integer> LIST = Lists.newArrayList(1, 2, 3, 4, 5, 6);

    /**
     * 拆分：按条件拆分集合
     */
    @Test
    public void partition() {
        List<List<Integer>> partition = Lists.partition(LIST, 3);
        System.out.println(JSON.toJSONString(partition));
    }

    /**
     * 反转：将集合元素进行反转排序
     */
    @Test
    public void reverse() {
        System.out.println(JSON.toJSONString(LIST));
        List<Integer> reverse = Lists.reverse(LIST);
        System.out.println(JSON.toJSONString(reverse));

    }

}
