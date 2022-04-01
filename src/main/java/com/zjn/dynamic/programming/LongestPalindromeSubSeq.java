package com.zjn.dynamic.programming;

/**
 * 516 最长回文子序列
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 *
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 *
 * 1 <= s.length <= 1000
 * s 仅由小写英文字母组成
 */
public class LongestPalindromeSubSeq {



    public int longSubSeq(String s) {
        int n = s.length();
        if (n <= 1) return n;

        int[][] dp = new int[n][n];
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = 1;
        }
        //   下-》上   左-》右
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }

        // 另一种遍历方式   左-》右  下-》上
//        for (int j = 1; j < s.length(); j++) {
//            for (int i = j - 1; i >= 0; i--) {
//                if (s.charAt(i) == s.charAt(j)) {
//                    dp[i][j] = dp[i + 1][j - 1] + 2;
//                } else {
//                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
//                }
//            }
//        }

        return dp[0][n-1];

    }


    // 空间压缩  使用一维数组
    public int longSubSeq1(String s) {
        int n = s.length();
        if (n <= 1) return n;
        int[] dp = new int[n];

        // base case   i == j 时最长回文子序列是1
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }

        for (int i = n - 2; i >= 0; i--) {
            // 这里 pre 记录的是 dp[i+1][j-1]
            int pre = 0;
            for (int j = i + 1; j < n; j++) {
                int temp = dp[j];
                if (s.charAt(i) == s.charAt(j)) {
                    dp[j] = pre + 2;
                } else {
                    // 这里的dp[j - 1]代表的是 dp[i][j-1]    dp[j]代表的是dp[i+1][j]
                    dp[j] = Math.max(dp[j - 1], dp[j]);
                }
                pre = temp;
            }
        }
        return dp[n - 1];
    }



}
