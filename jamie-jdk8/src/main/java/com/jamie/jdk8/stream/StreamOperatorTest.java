package com.jamie.jdk8.stream;

import com.alibaba.fastjson.JSON;
import com.jamie.jdk8.lambda.cart.CartService;
import com.jamie.jdk8.lambda.cart.Sku;
import com.jamie.jdk8.lambda.cart.SkuCategoryEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * 演示流的各种操作
 * @author jamie
 * @date 2020/9/10 13:07
 */
public class StreamOperatorTest {

    List<Sku> list;

    @Before
    public void init() {
        list = CartService.getCartSkuList();
    }

    /**
     * stream filter 使用：过滤掉不符合断言判断的数据
     */
    @Test
    public void filter() {
        list.stream()
            .filter(sku -> SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory()))
            .forEach(item -> System.out.println(JSON.toJSONString(item)));
    }

    /**
     * stream map 使用：将一个元素转换成另一个元素
     * 这里将一个对象集合转换成一个String集合
     */
    @Test
    public void map() {
        list.stream()
            .map(Sku::getSkuName)
            .forEach(item -> System.out.println(JSON.toJSONString(item)));
    }

    /**
     * stream flatMap 使用：将一个对象转换成一个流的操作
     * 这里将一个对象集合的每个对象名称转成一个一个字符输出
     */
    @Test
    public void flatMap() {
        list.stream()
            .flatMap(sku -> Arrays.stream(sku.getSkuName()
                                             .split("")))
            .forEach(item -> System.out.println(JSON.toJSONString(item)));
    }

    /**
     * stream peek 使用：中间操作，
     * 和forEach的区别就是他执行后不会销毁流元素，流还可以使用
     * 这里和forEach会交替执行,但是如果在peek和forEach中间插入一个有状态的中间操作，例如排序，就不会交替执行，而会顺序执行
     * 这里将一个对象集合的每个对象名称输出
     */
    @Test
    public void peek() {
        list.stream()
            .peek(sku -> System.out.println(sku.getSkuName()))
            .forEach(item -> System.out.println(JSON.toJSONString(item)));
    }

    /**
     * stream sorted 使用：进行排序，默认是顺序从小到大,如果需要倒序，则添加reversed()，也可以自定义排序规则
     */
    @Test
    public void sorted() {
        list.stream()
            .sorted(Comparator.comparing(Sku::getTotalPrice)
                              .reversed())
            .forEach(item -> System.out.println(JSON.toJSONString(item)));
    }

    /**
     * stream distinct 使用：去重，对相同的元素进行去重，有状态操作
     */
    @Test
    public void distinct() {
        list.stream()
            .map(Sku::getSkuCategory)
            .distinct()
            .forEach(item -> System.out.println(JSON.toJSONString(item)));
    }

    /**
     * stream skip 使用：跳过数据，有状态操作
     * 这里排序后跳过前3条数据
     */
    @Test
    public void skip() {
        list.stream()
            .sorted(Comparator.comparing(Sku::getTotalPrice))
            .skip(3)
            .forEach(item -> System.out.println(JSON.toJSONString(item)));
    }

    /**
     * stream limit 使用：截取数据，有状态操作
     * 这里截取排序后前3条数据
     */
    @Test
    public void limit() {
        list.stream()
            .sorted(Comparator.comparing(Sku::getTotalPrice))
            .limit(3)
            .forEach(item -> System.out.println(JSON.toJSONString(item)));
    }

    //  终端操作

    /**
     * stream allMatch 使用： 短路终端操作，所有元素匹配，返回true，只要遍历到有一个数据不满足，马上返回false
     * 这里判断是否有sku的总价不大于1000
     */
    @Test
    public void allMatch() {
        boolean match = list.stream()
                            .peek(sku -> System.out.println(JSON.toJSONString(sku)))
                            .allMatch(sku -> sku.getTotalPrice() > 10000);
        System.out.println(match);
    }

    /**
     * stream anyMatch 使用： 短路终端操作，任何元素匹配，返回true，只要遍历到有一个数据满足，马上返回true
     * 这里判断是否有sku的总价大于100
     */
    @Test
    public void anyMatch() {
        boolean match = list.stream()
                            .peek(sku -> System.out.println(JSON.toJSONString(sku)))
                            .anyMatch(sku -> sku.getTotalPrice() > 100);
        System.out.println(match);
    }

    /**
     * stream noneMatch 使用： 短路终端操作，任何元素都不匹配，返回true，只要匹配上一个，就马上返回false
     * 这里判断是否所有sku的总价大于10000
     */
    @Test
    public void noneMatch() {
        boolean match = list.stream()
                            .peek(sku -> System.out.println(JSON.toJSONString(sku)))
                            .noneMatch(sku -> sku.getTotalPrice() > 10000);
        System.out.println(match);
    }

    /**
     * stream findFirst 使用： 短路终端操作，返回第一个值的Optional，通过get获取
     */
    @Test
    public void findFirst() {
        Optional<Sku> first = list.stream()
                                  .sorted(Comparator.comparing(Sku::getTotalPrice))
                                  .findFirst();
        System.out.println(JSON.toJSONString(first.get()));
    }

    /**
     * stream findAny 使用： 短路终端操作，找到任何一个元素，如果是并行操作，findAny会随机找到一个，但是如果是串行操作，则和findFirst一样找到第一个
     */
    @Test
    public void findAny() {
        Optional<Sku> any = list.stream()
                                .sorted(Comparator.comparing(Sku::getTotalPrice))
                                .findAny();
        System.out.println(JSON.toJSONString(any.get()));
    }

    /**
     * stream max 使用： 非短路终端操作，查找指定条件中最大的对象（值）
     */
    @Test
    public void max() {
        OptionalDouble max = list.stream()
                                 // 获取总价集合
                                 .mapToDouble(Sku::getTotalPrice)
                                 .max();
        System.out.println(JSON.toJSONString(max));
    }

    /**
     * stream min 使用： 非短路终端操作，查找指定条件中最小的对象（值）
     */
    @Test
    public void min() {
        OptionalDouble min = list.stream()
                                 // 获取总价集合
                                 .mapToDouble(Sku::getTotalPrice)
                                 .min();
        System.out.println(JSON.toJSONString(min));
    }

    /**
     * stream count 使用： 非短路终端操作，获取元素总数
     */
    @Test
    public void count() {
        long count = list.stream()
                         // 获取总价集合
                         .mapToDouble(Sku::getTotalPrice)
                         .count();
        System.out.println(JSON.toJSONString(count));
    }

}
