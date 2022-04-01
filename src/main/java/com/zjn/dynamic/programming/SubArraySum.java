package com.zjn.dynamic.programming;


/**
 * 53. 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组 是数组中的一个连续部分。
 */
public class SubArraySum {


    public int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;
        int n = nums.length;
        //定义：dp[i] 记录以 nums[i] 为结尾的「最大子数组和」
        int[] ints = new int[n];

//        for (int i = 0; i < n; i++) {
//            ints[i] = nums[i];
//        }
        ints[0] = nums[0];
        for (int i = 1; i < n; i++) {
            ints[i] = Math.max(nums[i], ints[i - 1] + nums[i]);
        }
        int res = Integer.MIN_VALUE;
        for (int anInt : ints) {
            res = Math.max(res, anInt);
        }
        return res;
    }


    // 空间压缩
    public int maxSubArray1(int[] nums) {
        if (nums.length == 0) return 0;
        int n = nums.length;
        int a = nums[0];
        // 这里最大和不能使用 Integer.MIN_VALUE， 否则会把nums[0]是最大和的情况漏掉
        int res = a;
        for (int i = 1; i < n; i++) {
            a = Math.max(nums[i], a + nums[i]);
            res = Math.max(res, a);
        }

        return res;
    }


}
