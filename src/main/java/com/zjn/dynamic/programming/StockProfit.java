package com.zjn.dynamic.programming;


/**
 * 股票买卖问题
 * dp[n][k][0/1]
 * 状态   天数n    允许买卖的次数k    当前的状态（持有/没有持有）0/1
 * 选择   买入   卖出    无操作
 *
 * 状态转移方程
 * 当前未持有     前一天未买入，今天未操作      前一天持有，今天卖出
 * dp[n][k][0] = Math.max(dp[n - 1][k][0], dp[n - 1][k][1] + prices[n])
 * 当前持有       前一天持有                   前一天未持有，今天买入
 * dp[n][k][1] = Math.max(dp[n - 1][k][1], dp[n - 1][k - 1][0] - prices[n])
 *
 * base case
 * 第-1天，收益为0
 * dp[-1][][0] = 0
 * 第-1天持有不可能，算法要求一个最大值，所以初始化为最小值
 * dp[-1][][1] = Integer.MIN_VALUE
 * 允许操作次数是0，收益是0
 * dp[][0][0] = 0
 * 允许操作次数是0且持有不可能，算法要求一个最大值，所以初始化为最小值
 * dp[][0][1] = Integer.MIN_VALUE
 *
 */
public class StockProfit {

    /**
     * 121 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第i 个元素prices[i] 表示一支给定股票第 i 天的价格。
     *
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     *
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0
     */


    /**
     * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
     * dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i])
     *             = max(dp[i-1][1][1], -prices[i])
     * 解释：k = 0 的 base case，所以 dp[i-1][0][0] = 0。
     *
     * 现在发现 k 都是 1，不会改变，即 k 对状态转移已经没有影响了。
     * 可以进行进一步化简去掉所有 k：
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], -prices[i])
     */
    int dp[][][];
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int k = 1;
        dp = new int[n][k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                if (i == 0) {
                    dp[0][j][0] = 0;
                    dp[0][j][1] = -prices[i];
                    continue;
                }
                if (j == 1) {
                    dp[i][0][0] = 0;
                    dp[i][0][1] = -prices[i];
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][k][0];
    }

    // 空间复杂度优化
    public int maxProfit1(int[] prices) {
        int n = prices.length;
        int i_1 = Integer.MIN_VALUE;
        int i_0 = 0;

        for (int i = 0; i < n; i++) {
            i_0 = Math.max(i_0, i_1 + prices[i]);
            i_1 = Math.max(i_1, -prices[i]);// 这里本应该是 dp[i - 1][j - 1][0] - prices[i]，这里j相当于是1，所以dp[i - 1][0][0] = 0
        }
        return i_0;
    }


    /**
     * 122 买卖股票的最佳时机II
     * 给定一个数组 prices ，其中prices[i] 表示股票第 i 天的价格。
     *
     * 在每一天，你可能会决定购买和/或出售股票。你在任何时候最多只能持有 一股 股票。你也可以购买它，然后在 同一天 出售。
     * 返回 你能获得的 最大 利润。
     *
     *
     */

    /**
     *
     * dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i])
     * dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i])
     *
     * 这里k是无限大，则 k相当于 k-1
     *
     * dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])
     * dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i])
     *
     */
    public int maxProfit_122(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[n - 1][0];
    }

    // 空间优化
    public int maxProfit_122_1(int[] prices) {

        int n = prices.length;
        int i_1 = Integer.MIN_VALUE;
        int i_0 = 0;

        for (int i = 0; i < n; i++) {
            i_0 = Math.max(i_0, i_1 + prices[i]);
            i_1 = Math.max(i_1, i_0 - prices[i]);
        }
        return i_0;
    }


    /**
     * 123 买卖股票的最佳时机III
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     */
    public int maxProfit_123(int[] prices) {
        int n = prices.length;
        int k = 2;
        int[][][] dp = new int[n][k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                if (i == 0) {
                    dp[0][j][0] = 0;
                    dp[0][j][1] = -prices[i];
                    continue;
                }
                if (j == 1) {
                    dp[i][0][0] = 0;
                    dp[i][0][1] = -prices[i];
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][k][0];
    }

    // 状态转移方程：
    // dp[i][2][0] = max(dp[i-1][2][0], dp[i-1][2][1] + prices[i])
    // dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i])
    // dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
    // dp[i][1][1] = max(dp[i-1][1][1], -prices[i])
    int maxProfit_k_123_2(int[] prices) {
        // base case
        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
        int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
        for (int price : prices) {
            dp_i20 = Math.max(dp_i20, dp_i21 + price);
            dp_i21 = Math.max(dp_i21, dp_i10 - price);
            dp_i10 = Math.max(dp_i10, dp_i11 + price);
            dp_i11 = Math.max(dp_i11, -price);
        }
        return dp_i20;
    }


    /**
     * 309  股票买卖最佳时机 -- 包含冷冻期
     * 给定一个整数数组prices，其中第prices[i]表示第i天的股票价格 。​
     *
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     *
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i])
     * dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i])
     *
     * 这里k不限制次数，相当于无穷大  卖出后需要冷却一天,就是买入要基于前两天的数据
     *
     * dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i])
     * dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i])
     *
     */
    public int maxProfit_309(int[] prices) {

        int n = prices.length;
        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            if (i == 1) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], - prices[i]);
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);

        }
        return dp[n - 1][0];
    }

    // 空间复杂度优化
    public int maxProfit_309_1(int[] prices) {
        int n = prices.length;

        int i_0 = 0;
        int i_1 = -prices[0];
        int i_2 = 0;
        for (int i = 1; i < n; i++) {
            int temp = i_0;
            i_0 = Math.max(i_0, i_1 + prices[i]);
            i_1 = Math.max(i_1, i_2 - prices[i]);
            i_2 = temp;
        }
        return i_0;
    }


    /**
     * 714 买卖股票的最佳时机-- 含手续费
     * 给定一个整数数组prices，其中 prices[i]表示第i天的股票价格 ；整数fee 代表了交易股票的手续费用。
     *
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     *
     * 返回获得利润的最大值。
     *
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     *
     * dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i])
     * dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i])
     *
     * k无限
     * 这里在卖出时减去手续费用    ps  买入减去也是可以的
     * dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee)
     * dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i])
     *
     */
    public int maxProfit(int[] prices, int fee) {

        int n = prices.length;

        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }

            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    // 空间优化
    public int maxProfit_1(int[] prices, int fee) {

        int n = prices.length;

        int i_0 = 0;
        int i_1 = -prices[0];
        for (int i = 0; i < n; i++) {
            i_0 = Math.max(i_0, i_1 + prices[i] - fee);
            i_1 = Math.max(i_1, i_0 - prices[i]);
        }
        return i_0;
    }


    /**
     * 188 买卖股票的最佳时机-- k次
     * 给定一个整数数组prices ，它的第 i 个元素prices[i] 是一支给定的股票在第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i])
     * dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i])
     *
     */
    public int maxProfit(int k, int[] prices) {

        int n = prices.length;
        if (n <= 0) return 0;

        if (k > n / 2) {
            // 交易次数 k 没有限制的情况
            return maxProfit_122(prices);
        }

        int[][][] dp = new int[n][k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int j = k; j > 0; j--) {

                if (i == 0) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                if (j == 1) {
                    dp[i][0][0] = 0;
                    // 这个不要也行，因为下面并没有用到
                    dp[i][0][1] = -prices[i];
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][k][0];
    }

}
