package com.zjn.dynamic.programming;

import java.util.Arrays;

/**
 * 64 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 */
public class MinPathSum {

    // 自顶向下
    public static int[][] memo;
    public static int minPathSum(int[][] grid) {
        memo = new int[grid.length][grid[0].length];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        return minPath(grid, 0 , 0);
    }


    private static int minPath(int[][] grid, int i, int j) {
        if (i == grid.length || j == grid[0].length) return Integer.MAX_VALUE;
        if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];
        if (memo[i][j] != -1) return memo[i][j];

        memo[i][j] = grid[i][j] +  Math.min(minPath(grid, i, j + 1), minPath(grid, i + 1, j));

        return memo[i][j];
    }


    // 换一种写法  // 自顶向下
    public int[][] memo1;
    public int minPathSum1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        memo1 = new int[m][n];
        for (int[] ints : memo1) {
            Arrays.fill(ints, -1);
        }
        return minPath1(grid, m - 1, n - 1);

    }

    public int minPath1(int[][] grid, int i, int j) {
        if (i < 0 || j < 0) return Integer.MAX_VALUE;
        if (i == 0 && j == 0) return grid[0][0];
        if (memo1[i][j] != -1) return memo1[i][j];
        memo1[i][j] = grid[i][j] + Math.min(minPath1(grid, i, j - 1), minPath1(grid, i - 1, j));
        return memo1[i][j];

    }


    // 自底向上
    public int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int dp[][] = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }


    // 空间压缩
    public int minPathSum3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int dp[] = new int[n];
        dp[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[i] = grid[0][i] + dp[i - 1];
        }
        int sum = grid[0][0];
        for (int i = 1; i < m; i++) {
            sum += grid[i][0];
            for (int j = 1; j < n; j++) {
                int a = 0;
                if (j == 1) {
                    a = sum;
                } else {
                    a = dp[j - 1];
                }
                dp[j] = grid[i][j] + Math.min(dp[j], a);
            }
        }
        return dp[n - 1];
    }

}
