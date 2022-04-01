package com.zjn.dynamic.programming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1143 最长公共子序列
 * 给定两个字符串text1 和text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 *
 * 一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 */
public class LongestCommonSubsequence {


    private int[][] memo;

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1.length() == 0 || text2.length() == 0) return 0;
        memo = new int[text1.length()][text2.length()];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        return dp(text1, 0, text2, 0);
    }

    // 自顶向下
    // 定义：计算 s1[i..] 和 s2[j..] 的最长公共子序列长度
    private int dp(String text1, int i, String text2, int j) {
        if (i == text1.length() || j == text2.length()) return 0;
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // 从单个字符串出发开始考虑有哪些比较与动作
        if (text1.charAt(i) == text2.charAt(j)) {

            memo[i][j] =  1 + dp(text1, i + 1, text2, j + 1);
        } else {
            // 有三种情况，i不再序列中，j不再序列中，j i 都不在序列中
            // 但是这里可以不考虑i j都不在序列中的情况，因为这里是求最大值，第一种和第二种肯定最大值是大于等于第三种的
            memo[i][j] =  Math.max(dp(text1, i + 1, text2, j), dp(text1, i, text2, j + 1));
        }
        return memo[i][j];
    }


    // 自底向上解法
    public int longestCommonSubsequence1(String text1, String text2) {
        if (text1.length() == 0 || text2.length() == 0) return 0;
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 2][n + 2];

        for (int i = m; i > 0; i--) {
            for (int j = n; j > 0; j--) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        return dp[1][1];
    }


    // 自底向上解法
    public int longestCommonSubsequence2(String text1, String text2) {
        if (text1.length() == 0 || text2.length() == 0) return 0;
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    // 空间压缩
    public int longestCommonSubsequence3(String text1, String text2) {
        if (text1.length() == 0 || text2.length() == 0) return 0;
        int m = text1.length();
        int n = text2.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            // 记录[i - 1][j - 1]
            int w = 0;
            for (int j = 1; j <= n; j++) {
                // 记录 [i - 1][j]
                int temp = dp[j];
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[j] = 1 + w;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                w = temp;
            }
        }
        return dp[n];
    }


    /**
     * 583 两个字符串的删除操作
     * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
     *
     * 每步 可以删除任意一个字符串中的一个字符。
     */
    public int[][] memo1;
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        memo1 = new int[m][n];
        for (int[] ints : memo1) {
            Arrays.fill(ints, -1);
        }

        return minDistance(word1, word2, m - 1, n - 1);

    }

    private int minDistance(String word1, String word2, int i, int j) {
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;
        if (memo1[i][j] != -1) return memo1[i][j];

        if (word1.charAt(i) == word2.charAt(j)) {
            memo1[i][j] = minDistance(word1, word2, i - 1, j - 1);
        } else {
            memo1[i][j] = Math.min(minDistance(word1, word2, i - 1, j) + 1, minDistance(word1, word2, i, j - 1) + 1);
        }
        return memo1[i][j];
    }


    /**
     * 这里求最小步数也可以转化为求最大公共子序列
     */
    public int minDistance1(String word1, String word2) {
        int i = longestCommonSubsequence3(word1, word2);
        return word1.length() - i + word2.length() - i;
    }


    /**
     * 712. 两个字符串的最小ASCII删除和
     *给定两个字符串s1 和 s2，返回 使两个字符串相等所需删除字符的 ASCII 值的最小和 。
     */

    public int[][] ints;
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        ints = new int[m][n];
        for (int[] anInt : ints) {
            Arrays.fill(anInt,-1);
        }
        return minimumDeleteSum(s1, s2, m - 1, n - 1);
    }

    public int minimumDeleteSum(String s1, String s2, int i, int j) {
        int res = 0;
        if (i == -1) {
            for (int n = j; n >= 0; n--) {
                res += s2.charAt(n);
            }
            return res;
        }
        if (j == -1) {
            for (int n = i; n >= 0; n--) {
                res += s1.charAt(n);
            }
            return res;
        }
        if (ints[i][j] != -1) return ints[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            ints[i][j] = minimumDeleteSum(s1, s2, i - 1, j - 1);
        } else {
            ints[i][j] = Math.min(minimumDeleteSum(s1, s2, i - 1, j) + s1.charAt(i), minimumDeleteSum(s1, s2, i, j - 1) + s2.charAt(j));
        }
        return ints[i][j];
    }



}
