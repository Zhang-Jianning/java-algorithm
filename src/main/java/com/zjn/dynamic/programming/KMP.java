package com.zjn.dynamic.programming;

/**
 * KMP 算法（Knuth-Morris-Pratt 算法）是一个著名的字符串匹配算法
 */
public class KMP {

    // dp[i][j]表示当前的状态是i，当前匹配到的字符是j，则状态转移到 dp[i][j]
    private int[][] dp;
    private String pat;

    public KMP(String pat) {
        this.pat = pat;
        int n = pat.length();
        // 256个字符
        // dp[状态][字符] = 下个状态
        dp = new int[n][256];
        // base case
        // 只有pat[0]字符可以进行状态推进，其余字符时，状态重启（状态就要回退或者原地不动）
        dp[0][pat.charAt(0)] = 1;

        // 影子状态   是从pat中匹配 pat[1..end] ，与当前j拥有最长的相同前缀
        int X = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 256; j++) {
                //txt 中匹配 pat
                if (pat.charAt(i) == j) {
                    //只有字符串相等，才能状态推进
                    dp[i][j] = i + 1;
                } else {
                    // 状态重启 并且要根据 X 获取当前最近的 状态重启 状态
                    dp[i][j] = dp[X][j];
                }
            }
            X = dp[X][pat.charAt(i)];
        }
    }

    public int search(String txt) {

        int n = txt.length();
        int l = dp.length;

        // 初始化状态
        int j = 0;
        for (int i = 0; i < n; i++) {
            // 当前状态，当前字符对应的下一个状态
            j = dp[j][txt.charAt(i)];
            // 到达最终状态
            if (j == l) return i - l + 1;
        }
        return -1;

    }


    /**
     * 28 strStr
     * 实现strStr()函数。
     *
     * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。
     *
     * 说明：
     *
     * 当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     *
     * 对于本题而言，当needle是空字符串时我们应当返回 0 。这与 C 语言的strstr()以及 Java 的indexOf()定义相符。
     *
     *
     */

    public int strStr(String haystack, String needle) {

        int m = haystack.length();
        int n = needle.length();
        if (n == 0) return 0;

        for (int i = 0; i <= m - n; i++) {
            int l = 0;
            for (int j = 0; j < n; j++) {
                // 不匹配就跳出，重新开始匹配
                if (needle.charAt(j) != haystack.charAt(i + j)) {
                    break;
                }
                l ++;
            }
            if (l == n) return i;
        }
        return -1;
    }


    public static void main(String[] args) {
        KMP kmp = new KMP("abab");
        int re = kmp.search("babacbababc");
        System.out.println(re);
    }






}
