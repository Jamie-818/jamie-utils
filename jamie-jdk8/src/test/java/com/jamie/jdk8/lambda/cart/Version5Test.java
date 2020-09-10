package com.jamie.jdk8.lambda.cart;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

/**
 * 根据不同的Sku判断标准，对Sku列表进行过滤,用接口匿名内部类
 * @author jamie
 * @date 2020/9/9 23:22
 */
public class Version5Test {

    @Test
    public void filterSkus() {
        List<Sku> cartSkuList = CartService.getCartSkuList();
        // 查找购物车中图书类商品集合
        List<Sku> totalPrice = CartService.filterSkus(cartSkuList, new SkuPredicate() {
            @Override
            public boolean test(Sku sku) {
                return sku.getTotalPrice() > 2000;
            }
        });
        System.out.println(JSON.toJSONString(totalPrice, true));
        List<Sku> book = CartService.filterSkus(cartSkuList, new SkuPredicate() {
            @Override
            public boolean test(Sku sku) {
                return SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory());
            }
        });
        System.out.println(JSON.toJSONString(book, true));
    }

}
