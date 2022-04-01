package com.zjn.dynamic.programming;

import java.util.Arrays;

/**
 * 最长递增子序列（Longest Increasing Subsequence，简写 LIS）
 *
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 *  进阶：
 *
 *  你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 *
 *
 * 注意「子序列」和「子串」这两个名词的区别，子串一定是连续的，而子序列不一定是连续的
 *
 */
public class LIS {


    // 动态规划解法
    public int lengthOfLIS(int[] nums) {
        if (nums.length <= 1) return nums.length;

        // // 定义：dp[i] 表示以 nums[i] 这个数结尾的最长递增子序列的长度
        int[] dp = new int[nums.length];

        // base case：dp 数组全都初始化为 1
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] <= nums[j]) continue;
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        int res = 0;
        for (int i : dp) {
            res = Math.max(res, i);
        }
        return res;
    }


    // 二分查找解法   https://labuladong.gitee.io/algo/3/23/72/
    public int lengthOfLIS1(int[] nums) {

        // 记录堆顶元素
        int[] arr = new int[nums.length];

        // // 牌堆数初始化为 0
        int piles = 0;

        for (int i = 0; i < nums.length; i++) {
            int left = 0, right = piles;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (arr[mid] == nums[i]) {
                    right = mid;
                } else if (arr[mid] < nums[i]) {
                    left = mid + 1;
                } else if (arr[mid] > nums[i]) {
                    right = mid;
                }
            }
            // 没找到合适的堆，即 arr中的元素全部小于 nums[i] ，则 left的值最终会是piles
            if (left == piles) piles++;
            arr[left] = nums[i];
        }
        // 牌堆数就是 LIS 长度
        return piles;
    }






}
