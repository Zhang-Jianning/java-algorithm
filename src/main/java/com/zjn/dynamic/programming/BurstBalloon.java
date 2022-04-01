package com.zjn.dynamic.programming;

import java.util.ArrayList;
import java.util.List;

/**
 * 312 戳气球
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组nums中。
 *
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。这里的 i - 1 和 i + 1 代表和i相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 *
 * 求所能获得硬币的最大数量。
 *
 */
public class BurstBalloon {


    int res = Integer.MIN_VALUE;

    // 对于最值问题，毫无列外， 就是穷举出所有可能，选择最优解
    // 这里先使用回溯算法的思想，穷举，选择最优解
    public int maxCoins(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        backTrack(list, 0);
        return res;
    }

/**
 * 回溯算法框架
 * def backtrack(路径, 选择列表):
 *     if 满足结束条件:
 *         result.add(路径)
 *         return
 *
 *     for 选择 in 选择列表:
 *         做选择
 *         backtrack(路径, 选择列表)
 *         撤销选择
 */
    public void backTrack(List<Integer> nums, int score) {
        int ll = nums.size();
        if(ll == 0) res = Math.max(res, score);

        for (int i = 0; i < ll; i++) {
            int iB;
            int iA;
            if (i == 0) {
                iB = 1;
            } else {
                iB = nums.get(i - 1);
            }
            if (i == ll - 1) {
                iA = 1;
            } else {
                iA = nums.get(i + 1);
            }
            Integer temp = nums.get(i);
            int point = iB * temp * iA;
            nums.remove(i);
            backTrack(nums, point + score);
            nums.add(i, temp);
        }

    }


    /**
     * 动态规划解法
     */
    public int maxCoins1(int[] nums) {
        int ll = nums.length;

        // 首先把 i - 1或 i + 1 超出了数组的边界，当作是一个数字为 1 的气球
        int[] points = new int[ll + 2];
        points[0] = 1;
        points[ll + 1] = 1;
        for (int i = 0; i < ll; i++) {
            points[i + 1] = nums[i];
        }

        // 定义 戳破（i,j）中所有气球的最大值    0<=i<=ll + 1;
        int[][] dp = new int[ll + 2][ll + 2];

        for (int i = points.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < points.length; j++) {
                // 假设（i,j）中最后一个被戳破的气球是k, 则此时 k的左侧是i，k的右侧是j
                // 这里是在做选择   i < k < j
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + points[i] * points[k] * points[j]);
                }
            }
        }

        return dp[0][ll + 1];
    }






}
