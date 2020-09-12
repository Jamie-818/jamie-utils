package com.jamie.guava;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * Sets 使用
 * @author jamie
 * @date 2020/9/11 10:31
 */
public class SetsTest {

    /**
     * Sets 工具类的常用方法：
     * 并集 / 交集 / 差集 / 分解集合中的所有子集 / 求两个集合的笛卡尔积

     */

    private static final Set<Integer> set1 = Sets.newHashSet(1, 2, 3, 4);

    private static final Set<Integer> set2 = Sets.newHashSet(3, 4, 5, 6);

    /**
     * 并集
     */
    @Test
    public void union() {
        Sets.SetView<Integer> union = Sets.union(set1, set2);
        System.out.println(union);
    }

    /**
     * 交集
     */
    @Test
    public void intersection() {
        Sets.SetView<Integer> intersection = Sets.intersection(set1, set2);
        System.out.println(intersection);
    }

    /**
     * 差集：如果元素属于A而且不属于B
     */
    @Test
    public void difference() {
        //  正常差集：如果元素属于A而且不属于B
        Sets.SetView<Integer> difference = Sets.difference(set1, set2);
        System.out.println(difference);
        // 相对差集：属于A而且不属于B 或者 属于B而且不属于A
        Sets.SetView<Integer> symmetricDifference = Sets.symmetricDifference(set1, set2);
        System.out.println(symmetricDifference);
    }

    /**
     * 拆分：拆分所有子集
     */
    @Test
    public void powerSet() {
        Set<Set<Integer>> sets = Sets.powerSet(set1);
        System.out.println(JSON.toJSONString(sets));
    }

    /**
     * 笛卡尔积：计算两个集合笛卡尔积
     */
    @Test
    public void cartesianProduct() {
        Set<List<Integer>> lists = Sets.cartesianProduct(set1, set2);
        System.out.println(JSON.toJSONString(lists));
    }

}
