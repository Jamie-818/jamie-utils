package com.jamie.lambda.cart;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

/**
 * 根据传入商品类型参数，找出购物车中同种商品类型的商品列表
 * @author jamie
 * @date 2020/9/9 23:21
 */
public class Version2Test {

    @Test
    public void filterSkusByCategory() {
        List<Sku> cartSkuList = CartService.getCartSkuList();
        // 查找购物车中图书类商品集合
        List<Sku> skus = CartService.filterSkusByCategory(cartSkuList, SkuCategoryEnum.BOOKS);
        System.out.println(JSON.toJSONString(skus, true));
    }

}
