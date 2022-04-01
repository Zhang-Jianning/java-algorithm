package com.zjn.dynamic.programming;

import java.util.Arrays;

/**
 * 斐波那契数列
 *（通常用F(n) 表示）形成的序列称为 斐波那契数列 。该数列由0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *
 * F(0) = 0，F(1)= 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定n ，请计算 F(n) 。
 *
 *
 *
 *
 * 首先使用递归方法进行暴力求解的框架写起来, 以此确定转移方程, 然后加上数组, 再将其修改为自底向上的代码逻辑.
 *
 * 明确 base case
 * 明确「状态」
 * 明确「选择」
 * 定义 dp 数组/函数的含义。
 */
public class FibonacciSequence {

    // 暴力解法  O(2^n)
    public int fib(int n) {
        if (n == 0 || n == 1) return n;
        return fib(n - 1) + fib(n - 2);
    }


    // 添加备忘录   自顶向下  O(n)
    public int fib2(int n) {
        int[] memo = new int[n + 1];
        return helper(n, memo);
    }
    private int helper(int n, int[] memo) {
        if (n == 0 || n == 1) return n;
        if (memo[n] != 0) return memo[n];
        memo[n] = helper(n - 1, memo) + helper(n - 2, memo);
        return memo[n];
    }

    // db数组迭代解法  自底向上  O(n)
    public int fib3(int n) {
        if (n == 0) return 0;
        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 状态转移方程
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 空间复杂度优化  O(n)  空间复杂度 O(1)
    public int fib4(int n) {
        if (n == 0) return 0;
        int dp_i_1 = 0, dp_i_2 = 1;
        for (int i = 2; i <= n; i++) {
            int dp_i = dp_i_1 + dp_i_2;
            dp_i_1 = dp_i_2;
            dp_i_2 = dp_i;
        }
        return dp_i_2;
    }


    /**
     * 凑硬币
     * 给你 k 种面值的硬币，面值分别为 c1, c2 ... ck，每种硬币的数量无限，再给一个总金额 amount，问你最少需要几枚硬币凑出这个金额，如果不可能凑出，算法返回 -1 。算法的函数签名如下：
     * // coins 中是可选硬币面值，amount 是目标金额
     * int coinChange(int[] coins, int amount);
     */

    public int coinChange(int[] coins, int amount) {
        return dp(coins, amount);
    }

    // 确定base case   确定状态    确定状态的转变   确定dp函数的定义：输入一个目标金额 n，返回凑出目标金额 n 的最少硬币数量。
   private int dp(int[] coins, int amount) {
       if (amount < 0) return -1;
       if (amount == 0) return 0;
       int res = Integer.MAX_VALUE;
       for (int coin : coins) {
           int m = dp(coins, amount - coin);
           if (m == -1) continue;
           res = Math.min(res, m + 1);
       }
       return res == Integer.MAX_VALUE ? -1 : res;
   }


   // 带备忘录
   public int coinChange1(int[] coins, int amount) {
       int[] arr = new int[amount + 1];
        return dp1(coins, amount, arr);
   }

    private int dp1(int[] coins, int amount, int[] arr) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        if (arr[amount] != 0) return arr[amount];
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {

            int m = dp1(coins, amount - coin, arr);
            if (m == -1) continue;
            res = Math.min(res, m + 1);
        }
        arr[amount] = res == Integer.MAX_VALUE ? -1 : res;
        return arr[amount];
    }



    // dp 数组的迭代解法

    public int coinChange2(int[] coins, int amount) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];

    }


    /**
     * 518 零钱兑换II
     * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
     *
     * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
     *
     * 假设每一种面额的硬币有无限个。
     *
     * 题目数据保证结果符合 32 位带符号整数。
     *
     */
    public int change(int amount, int[] coins) {
        int n = coins.length;
        // 定义，只使用coins中的前i个硬币面值（无限个数），有几种方法可以凑出总金额
        int[][] dp = new int[n + 1][amount + 1];
        // base case
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j >= coins[i - 1]) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][amount];
    }

    public int change1(int amount, int[] coins) {
        int n = coins.length;
        // 定义，只使用coins中的前i个硬币面值（无限个数），有几种方法可以凑出总金额
        int[] dp = new int[amount + 1];
        // base case
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j >= coins[i - 1]) {
                    dp[j] = dp[j] + dp[j - coins[i - 1]];
                } else {
                    dp[j] = dp[j];
                }
            }
        }
        return dp[amount];
    }


}
