package com.zjn.dynamic.programming;

import java.util.Arrays;

/**
 * 931 下降路径最小和
 * 给你一个 n x n 的 方形 整数数组matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
 *
 * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
 *
 * matrix 是 n x n 的二维数组，其中 1 <= n <= 100；对于二维数组中的元素，有 -100 <= matrix[i][j] <= 100
 */
public class MinFallingPath {


    public static int minFallingPathSum(int[][] matrix) {
        // dp 数组，记录从length-1到该位置的最短路径
        int[][] dp = new int[matrix.length][matrix[0].length];
        // dp数组初始值不再题目范围中即可
        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(dp[i], 99999);
        }
        // base case   执行到最底层是，需要直接返回该位置对应的值
        for (int i = 0; i < dp[0].length; i++) {
            dp[matrix.length - 1][i] = matrix[matrix.length - 1][i];
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < matrix[0].length; i++) {
            res = Math.min(res, dp(matrix, 0, i, dp));
        }
        return res;

    }


    public static  int dp(int[][] matrix, int i, int j, int[][] arr) {
        // 这里返回的特殊值会在 min这里拿最小值
        if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length) return 99999;
        // base case是执行到最底层就不需要往下执行了，直接返回值，这个值在上层的for循环中填充了
        if (arr[i][j] != 99999) return arr[i][j];
        // 当前位置的值加上另外3个中的最小值，即为最短路径
        arr[i][j] = matrix[i][j] + min(dp(matrix, i + 1, j, arr), dp(matrix, i + 1, j + 1, arr), dp(matrix, i + 1, j - 1, arr));

        return arr[i][j];

    }



    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }


    public static void main(String[] args) {
        int[][] arr = {{2,1,3},{6,5,4},{7,8,9}};
        minFallingPathSum(arr);
    }


}
