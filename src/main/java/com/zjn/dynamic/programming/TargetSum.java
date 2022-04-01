package com.zjn.dynamic.programming;

import java.util.HashMap;
import java.util.Map;

/**
 * 494  目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加'+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 */
public class TargetSum {


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
     *
     *
     *
     *
     *  def backtrack(nums, i):
     *     if i == len(nums):
     *         if 达到 target:
     *             result += 1
     *         return
     *
     *     for op in { +1, -1 }:
     *         选择 op * nums[i]
     *         # 穷举 nums[i + 1] 的选择
     *         backtrack(nums, i + 1)
     *         撤销选择
     */



    int sum;
    public int findTargetSumWays(int[] nums, int target) {
        if (nums.length == 0) return 0;
        find(nums, target, 0);
        return sum;
    }


    private void find(int[] nums, int target, int n) {
        if (n == nums.length) {
            if (target == 0) sum ++;
            return;
        }

        target += nums[n];
        find(nums, target, n + 1);
        target -= nums[n];

        target -= nums[n];
        find(nums, target, n + 1);
        target += nums[n];

    }



    // 备忘录优化

    Map<String, Integer> memo = new HashMap<>();
    public int findTargetSumWays1(int[] nums, int target) {
        if (nums.length == 0) return 0;
        return find1(nums, target, 0);
    }

    public int find1(int[] nums, int target, int n) {
        if (n == nums.length) {
            if (target == 0) {
                return 1;
            }
            return 0;
        }
        // 把它俩转成字符串才能作为哈希表的键
        String key = n + "," + target;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int re = find1(nums, target + nums[n], n + 1) + find1(nums, target - nums[n], n + 1);
        memo.put(key, re);
        return re;
    }


    /**
     *  动态规划解法
     *  首先，如果我们把 nums 划分成两个子集 A 和 B，分别代表分配 + 的数和分配 - 的数，那么他们和 target 存在如下关系：
     *
     * sum(A) - sum(B) = target
     * sum(A) = target + sum(B)
     * sum(A) + sum(A) = target + sum(B) + sum(A)
     * 2 * sum(A) = target + sum(nums)
     *
     *综上，可以推出 sum(A) = (target + sum(nums)) / 2，也就是把原问题转化成：nums 中存在几个子集 A，使得 A 中元素的和为 (target + sum(nums)) / 2？
     *
     */

    int findTargetSumWays2(int[] nums, int target) {

        if (nums.length == 0) return 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < Math.abs(target) || (sum + target) % 2 == 1) {
            return 0;
        }
        return subsets(nums, (sum + target) / 2);

    }

    int subsets(int[] nums, int sum) {
        int n = nums.length;
        int[][] dp = new int[n + 1][sum + 1];
        // base case
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                // 判断 背包容量 是否充足
                // 空间充足
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                } else {
                    // 空间不足
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][sum];

    }


    // 空间压缩，将二维数组压缩成一维数组
    int subsets1(int[] nums, int sum) {
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = sum; j >= 0; j--) {
                if (j >= nums[i - 1]) {
                    dp[j] = dp[j] + dp[j - nums[i - 1]];
                } else {
                    dp[j] = dp[j];
                }
            }
        }
        return dp[sum];
    }

}
