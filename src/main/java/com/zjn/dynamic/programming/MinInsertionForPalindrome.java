package com.zjn.dynamic.programming;

/**
 * 1312  字符串成为回文串的最小插入次数
 *
 * 给你一个字符串s，每一次操作你都可以在字符串的任意位置插入任意字符。
 *
 * 请你返回让s成为回文串的最少操作次数。
 *
 * 「回文串」是正读和反读都相同的字符串。
 *
 *
 */
public class MinInsertionForPalindrome {


    public int minInsertions(String s) {
        int n = s.length();

        // 表示[i,j]插入的最小次数
        // base case 就是i==j时值为0
        int[][] dp = new int[n][n];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    // [i + 1][j] 和 [i][j - 1] 先取最小的，然后在[i + 1][j]右侧插入[i] 或者在[i][j - 1]左侧插入[j]
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[0][n - 1];
    }


    // 空间优化

    public int minInsertions_1(String s) {
        int n = s.length();
        // 表示[i,j]插入的最小次数
        // base case 就是i==j时值为0
        int[] dp = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            int pre = 0;
            for (int j = i + 1; j < n; j++) {
                int temp = dp[j];
                if (s.charAt(i) == s.charAt(j)) {
                    dp[j] = pre;
                } else {
                    // dp[i + 1][j] 在这里 就是dp[j]
                    dp[j] = Math.min(dp[j], dp[j - 1]) + 1;
                }
                pre = temp;
            }
        }
        return dp[n - 1];
    }




}
