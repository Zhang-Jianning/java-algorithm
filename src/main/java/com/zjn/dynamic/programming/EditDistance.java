package com.zjn.dynamic.programming;

public class EditDistance {
    // 备忘录   自顶向下
    int[][] memo;
    public int minDistance(String word1, String word2) {
        int i = word1.length();
        int j = word2.length();
        memo = new int[i + 1][j + 1];
        return dp(i - 1, j - 1, word1, word2);
    }

    public int dp(int i, int j, String word1, String word2) {
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;
        if (memo[i][j] != 0) return memo[i][j];
        if (word1.charAt(i) == word2.charAt(j)) {
            memo[i][j] = dp(i - 1, j - 1, word1, word2);
        } else {
            memo[i][j] = min(dp(i, j - 1, word1, word2) + 1, dp(i - 1, j, word1, word2) + 1, dp(i - 1, j - 1, word1, word2) + 1);
        }
        return memo[i][j];
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }


    // dp table  自底向上
    public int minDistance1(String word1, String word2) {

        int i = word1.length();
        int j = word2.length();

        // dp[i-1][j-1] 存储 s1[0..i] 和 s2[0..j] 的最小编辑距离
        int[][] dp = new int[i + 1][j + 1];

        for (int k = 0; k <= i; k++) {
            dp[k][0] = k;
        }

        for (int g = 0; g <= j; g++) {
            dp[0][g] = g;
        }

        for (int m = 1; m <= i; m++) {
            for (int n = 1; n <= j; n++) {
                if (word1.charAt(m - 1) == word2.charAt(n - 1)) {
                    dp[m][n] = dp[m - 1][n - 1];
                } else {
                    dp[m][n] = min(dp[m][n - 1] + 1, dp[m - 1][n] + 1, dp[m - 1][n - 1] + 1);
                }
            }
        }

        return dp[i][j];
    }






}
