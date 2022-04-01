package com.zjn.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51 N 皇后
 * n皇后问题 研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的n皇后问题 的解决方案。
 *
 * 每一种解法包含一个不同的n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * PS：皇后可以攻击同一行、同一列、左上左下右上右下四个方向的任意单位。
 */
public class NQueens {

    static List<List<String>> res;
    static int nx;
    public static List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        nx = n;
        char[][] board = new char[n][n];
        for (char[] ch : board) {
            Arrays.fill(ch, '.');
        }
        solve(board, 0);
        return res;
    }

    private static void solve(char[][] board, int row) {
        int n = nx;
        if (row == n) {
            List<String> list = new ArrayList<>();
            for (char[] ch : board) {
                list.add(new String(ch));
            }
            res.add(list);
            return;
        }

        for (int col = 0; col < n; col++) {
            // 排除无效的数据
            boolean valid = isValid(row, col, board);
            if (!valid) continue;
            // 做选择
            board[row][col] = 'Q';
            // 下个决策
            System.out.println(row + " " + col);
            solve(board, row + 1);
            // 撤销选择
            board[row][col] = '.';
        }
    }

    //因为皇后是一行一行从上往下放的，所以左下方，右下方和正下方不用检查（还没放皇后）；因为一行只会放一个皇后，所以每行不用检查。也就是最后只用检查上面，左上，右上三个方向。
    private static boolean isValid(int row, int col, char[][] board) {
        int n = nx;
        // 同一行
        for (int i = 0; i < n; i++) {
            if (board[row][i] == 'Q') return false;
        }
        // 同一列
        for (int i = 0; i < n; i++) {
            if (board[i][col] == 'Q') return false;
        }

        // 检查左上
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        // 检查右上
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    public static void main(String[] args) {
        solveNQueens(3);
    }

}
