package com.jamie.jdk8.lambda.cart;

/**
 * Sku选择规则接口
 * @author jamie
 * @date 2020/9/9 23:10
 */
@FunctionalInterface
public interface SkuPredicate {

    /**
     * 选择判断标准
     * @param sku
     * @return boolean
     */
    boolean test(Sku sku);

}
