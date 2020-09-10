package com.jamie.lambda.cart;

/**
 * 对Sku的商品总价是否超过2000作为判断标准
 * @author jamie
 * @date 2020/9/9 23:15
 */
public class SkuTotalPricePredicate implements SkuPredicate {

    @Override
    public boolean test(Sku sku) {
        return sku.getTotalPrice() > 2000;
    }

}
