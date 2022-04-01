package com.zjn.dynamic.programming;

/**
 * 174 地下城游戏
 *
 * 输入一个存储着整数的二维数组 grid，如果 grid[i][j] > 0，说明这个格子装着血瓶，经过它可以增加对应的生命值；如果 grid[i][j] == 0，则这是一个空格子，经过它不会发生任何事情；如果 grid[i][j] < 0，说明这个格子有怪物，经过它会损失对应的生命值。
 *
 * 现在你是一名骑士，将会出现在最上角，公主被困在最右下角，你只能向右和向下移动，请问你初始至少需要多少生命值才能成功救出公主？
 *
 * 换句话说，就是问你至少需要多少初始生命值，能够让骑士从最左上角移动到最右下角，且任何时候生命值都要大于 0。
 */
public class MinimumHP {

    int[][] memo;
    public int calculateMinimumHP(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        memo = new int[m][n];
        // 这里只能从右下角反推到左上角，  如果从左上角开始，推算不出来最终的选择
        return find(grid, 0, 0);
    }


    // 函数定义 从 grid[i][j] 到达终点（右下角）所需的最少生命值是 dp(grid, i, j)。
    public int find(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i >= m || j >= n) {
            return Integer.MAX_VALUE;
        }
        if (i == m - 1 && j == n - 1) {
            // 这里相当于 用 1 - grid[m - 1][n - 1] 和 下方的逻辑一致的
            return grid[i][j] >= 0 ? 1 : -grid[i][j] + 1;
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int res = Math.min(find(grid, i + 1, j), find(grid, i, j + 1)) - grid[i][j];
        memo[i][j] = res > 0 ? res : 1;
        return memo[i][j];

    }



    // 自底向上
    public int calculateMinimumHP1(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            dp[m][i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i <= m; i++) {
            dp[i][n] = Integer.MAX_VALUE;
        }
        dp[m - 1][n - 1] = grid[m - 1][n - 1] > 0 ? 1 : -grid[m - 1][n - 1] + 1;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) continue;
                int res = Math.min(dp[i + 1][j], dp[i][j + 1]) - grid[i][j];
                dp[i][j] = res > 0 ? res : 1;
            }
        }
        return dp[0][0];
    }




    // 空间压缩
    public int calculateMinimumHP2(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[n - 1] = grid[m - 1][n - 1] > 0 ? 1 : -grid[m - 1][n - 1] + 1;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) continue;
                int res = Math.min(dp[j], dp[j + 1]) - grid[i][j];
                dp[j] = res > 0 ? res : 1;
            }
        }
        return dp[0];
    }


}
