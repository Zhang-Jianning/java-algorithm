package com.zjn.dynamic.programming.greedy;


import java.util.Arrays;

public class JumpGame {

    /**
     * 55 跳跃游戏
     *
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     *
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 判断你是否能够到达最后一个下标。
     *
     *
     *
     *
     * 每一步都计算一下从当前位置最远能够跳到哪里，然后和一个全局最优的最远位置 maxLen 做对比，通过每一步的最优解，更新全局最优解，这就是贪心。
     */
    public boolean canJump(int[] nums) {

        int n = nums.length;
        int maxLen = 0;

        // 计算是否能跳到最后一个下标，这里不计算最后一个
        for (int i = 0; i < n - 1; i++) {
            // 取所有跳跃中最远的一个
            maxLen = Math.max(maxLen, i + nums[i]);
            // 此时说明跳到了一个元素为0的位置
            if (maxLen <= i) {
                return false;
            }
        }
        return maxLen >= n - 1;
    }


    /**
     * 45 跳跃游戏2
     * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
     *
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     *
     * 假设你总是可以到达数组的最后一个位置。
     *
     *
     */

    int[] memo;
    public int jump(int[] nums) {
        int n = nums.length;
        memo = new int[n];
        // 最大的步数是n - 1
        Arrays.fill(memo, n);
        return dp(nums, 0);
    }


    public int dp(int[] nums, int k) {
        int n = nums.length;
        if (k >= n - 1) return 0;

        if (memo[k] != n) return memo[k];
        // 如果step是0，则memo[k]是默认值n
        int step = nums[k];
        // 穷举每一个选择
        // 计算每一个子问题的结果
        for (int i = 1; i <= step; i++) {
            memo[k] = Math.min(memo[k], dp(nums, k + i) + 1);
        }
        return memo[k];
    }



    // 贪心思路   最优解法
    public int jump_1(int[] nums) {
        int n = nums.length;
        int maxLen = 0;
        // 第x次跳跃的上限
        int end = 0;
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            maxLen = Math.max(maxLen, nums[i] + i);
            // 达到本次跳跃的上限后需要再次跳跃，更新本次跳跃最多跳跃的距离
            if (end == i) {
                count++;
                end = maxLen;
            }
        }
        return count;
    }






}
