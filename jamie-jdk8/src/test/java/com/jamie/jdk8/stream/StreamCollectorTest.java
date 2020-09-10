package com.jamie.jdk8.stream;

import com.alibaba.fastjson.JSON;
import com.jamie.jdk8.lambda.cart.CartService;
import com.jamie.jdk8.lambda.cart.Sku;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 常见预定义收集器使用
 * @author jamie
 */
public class StreamCollectorTest {

    List<Sku> list;

    @Before
    public void init() {
        list = CartService.getCartSkuList();
    }

    /**
     * 集合收集器 toList ：将流转成list集合
     */
    @Test
    public void toList() {
        List<Sku> collect = list.stream()
                                .filter(sku -> sku.getTotalPrice() > 100)
                                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));
    }

    /**
     * 分组收集器 group ：将流按照指定条件分组成 Map<分组条件，结果合集>
     */
    @Test
    public void group() {
        // Map<分组条件，结果合集>
        Map<Object, List<Sku>> collect = list.stream()
                                             .collect(Collectors.groupingBy(Sku::getSkuCategory));
        System.out.println(JSON.toJSONString(collect, true));
    }

    /**
     * 分区收集器 partition ：将流按照指定条件分区成 Map<Boolean，结果合集>
     */
    @Test
    public void partition() {
        // Map<Boolean，结果合集>
        Map<Boolean, List<Sku>> collect = list.stream()
                                              .collect(Collectors.partitioningBy(sku -> sku.getTotalPrice() > 100));
        System.out.println(JSON.toJSONString(collect, true));
    }

}
