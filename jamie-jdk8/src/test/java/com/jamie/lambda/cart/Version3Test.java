package com.jamie.lambda.cart;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

/**
 * 支持通过商品类型或总结来过滤商品
 * @author jamie
 * @date 2020/9/9 23:21
 */
public class Version3Test {

    @Test
    public void filterSkus() {
        List<Sku> cartSkuList = CartService.getCartSkuList();
        // 查找购物车中图书类商品集合
        List<Sku> skus = CartService.filterSkus(cartSkuList, SkuCategoryEnum.BOOKS, 0.00, true);
        System.out.println(JSON.toJSONString(skus, true));
        List<Sku> skus2 = CartService.filterSkus(cartSkuList, SkuCategoryEnum.BOOKS, 2699.00, false);
        System.out.println(JSON.toJSONString(skus2, true));
    }

}
