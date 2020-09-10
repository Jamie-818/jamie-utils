package com.jamie.jdk8.stream;

import com.alibaba.fastjson.JSON;
import com.jamie.jdk8.lambda.cart.CartService;
import com.jamie.jdk8.lambda.cart.Sku;
import com.jamie.jdk8.lambda.cart.SkuCategoryEnum;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * 对比：原始集合操作与Stream集合操作
 * 1 想看看购物车中都有什么商品
 * 2 图书类商品都给买
 * 3 其余的商品中买两件最贵的
 * 4 只需要两件商品的名称和总价
 * @author jamie
 * @date 2020/9/10 11:20
 */
public class StreamVsTest {

    /**
     * 以原始集合操作实现需求
     */
    @Test
    public void oldCartHandle() {
        // 1 打印所有商品
        List<Sku> cartSkuList = CartService.getCartSkuList();
        for(Sku sku: cartSkuList){
            System.out.println(JSON.toJSONString(sku, true));
        }
        // 2 图书类过滤掉
        List<Sku> notBooksSkuList = new ArrayList<>();
        for(Sku sku: cartSkuList){
            if(!SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory())){
                notBooksSkuList.add(sku);
            }
        }
        System.out.println(JSON.toJSONString(notBooksSkuList, true));
        // 3 其余的商品中买两件最贵的
        // 3.1 排序
        notBooksSkuList.sort((sku1, sku2) -> {
            if(sku1.getTotalPrice() > sku2.getTotalPrice()){
                return -1;
            }else if(sku1.getTotalPrice() < sku2.getTotalPrice()){
                return 1;
            }
            return 0;
        });
        // 3.2 取最高价格两件
        List<Sku> top2SkuList = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            top2SkuList.add(notBooksSkuList.get(i));
        }
        System.out.println(JSON.toJSONString(top2SkuList, true));
        // 4 只需要两件商品的名称和总价

        double money = 0.0;
        ArrayList<String> resultSkuNameList = new ArrayList<>();
        for(Sku sku: top2SkuList){
            // 4.1 获取总价
            money += sku.getTotalPrice();
            // 4.2 获取名称
            resultSkuNameList.add(sku.getSkuName());
        }
        System.out.println(JSON.toJSONString(resultSkuNameList, true));
        System.out.println(JSON.toJSONString("商品总价：" + money, true));

    }

    /**
     * 以Stream操作实现需求
     */
    @Test
    public void newCartHandle() {
        AtomicReference<Double> money = new AtomicReference<>(0.0);
        List<Sku> cartSkuList = CartService.getCartSkuList();
        List<String> resultSkuNameList = cartSkuList.stream()
                                                    // 1 打印所有商品
                                                    .peek(sku -> System.out.println(JSON.toJSONString(sku, true)))
                                                    // 2 图书类过滤掉
                                                    .filter(sku -> !SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory()))
                                                    // 再打印
                                                    .peek(sku -> System.out.println(JSON.toJSONString(sku, true)))
                                                    // 3 其余的商品中买两件最贵的
                                                    // 3.1 排序
                                                    .sorted(Comparator.comparing(Sku::getTotalPrice)
                                                                      .reversed())
                                                    // 3.2 取最高价格两件
                                                    .limit(2)
                                                    // 4 只需要两件商品的名称和总价
                                                    // 4.1 获取总价
                                                    .peek(sku -> money.set(money.get() + sku.getTotalPrice()))
                                                    // 4.2 获取名称
                                                    .map(Sku::getSkuName)
                                                    // 收集结果
                                                    .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(resultSkuNameList, true));
        System.out.println(JSON.toJSONString("商品总价：" + money, true));
    }

}
