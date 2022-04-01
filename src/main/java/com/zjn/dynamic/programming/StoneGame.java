package com.zjn.dynamic.programming;

/**
 * 877 石子游戏
 *你和你的朋友面前有一排石头堆，用一个数组 piles 表示，piles[i] 表示第 i 堆石子有多少个。你们轮流拿石头，一次拿一堆，但是只能拿走最左边或者最右边的石头堆。所有石头被拿完后，谁拥有的石头多，谁获胜。
 *
 * 石头的堆数可以是任意正整数，石头的总数也可以是任意正整数，这样就能打破先手必胜的局面了。比如有三堆石头 piles = [1, 100, 3]，先手不管拿 1 还是 3，能够决定胜负的 100 都会被后手拿走，后手会获胜。
 *
 * 假设两人都很聪明，请你设计一个算法，返回先手和后手的最后得分（石头总数）之差。
 *
 *
 *
 * 博弈游戏的核心思路就是 在二维 dp 的基础上使用元组分别存储两个人的博弈结果
 */
public class StoneGame {

    public boolean stoneGame(int[] piles) {
        return amountDifference(piles) > 0;
    }

    // 三维存储结果   dp[i][j][0]存储先手最高分  dp[i][j][1]存储后手最高分
//    dp[i][j][0] = x 表示，对于 piles[i...j] 这部分石头堆，先手能获得的最高分数为 x。
//    dp[i][j][1] = y 表示，对于 piles[i...j] 这部分石头堆，后手能获得的最高分数为 y。
    int[][][] dp;
    public int amountDifference(int[] piles) {

        int n = piles.length;
        dp = new int[n][n][2];
        // base case
        for (int i = 0; i < n; i++) {
            // 这里初始化先手的得分，当i j 相等，只有先手可以得分，后手得分为0
            dp[i][i][0] = piles[i];
            dp[i][i][1] = 0;
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 先手选择左边
                int left = piles[i] + dp[i + 1][j][1];
                // 先手选择右边
                int right = piles[j] + dp[i][j - 1][1];

                // 先手选择最大值，后手选择下一步的最大值
                if (left > right) {
                    dp[i][j][0] = left;
                    // 如果先手选择左边，则后手可以选择下一步的先手
                    dp[i][j][1] = dp[i + 1][j][0];
                } else {
                    dp[i][j][0] = right;
                    // 如果先手选择右边，则后手可以选择下一步的先手
                    dp[i][j][1] = dp[i][j - 1][0];
                }
            }
        }

        return dp[0][n - 1][0] - dp[0][n - 1][1];
    }

    // 另一种解法
    // 先手必胜
    // 作为第一个拿石头的人，你可以控制自己拿到所有偶数堆，或者所有的奇数堆。你可以在第一步就观察好，奇数堆的石头总数多，还是偶数堆的石头总数多，然后步步为营
    public boolean stoneGame_1(int[] piles) {
        return true;
    }
}
