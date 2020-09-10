package com.jamie.lambda.cart;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;

/**
 * 最基础的判断 找出购物车中所有电子产品
 * @author jamie
 * @date 2020/9/9 23:21
 */
public class Version1Test {

    @Test
    public void filterElectronicsSkus() {
        List<Sku> cartSkuList = CartService.getCartSkuList();
        // 查找购物车中数码类商品
        List<Sku> skus = CartService.filterElectronicsSkus(cartSkuList);
        System.out.println(JSON.toJSONString(skus, true));
    }

}
