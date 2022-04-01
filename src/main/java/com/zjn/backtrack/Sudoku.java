package com.zjn.backtrack;

import com.zjn.stack.Stack;

import java.util.Arrays;

/**
 * 37 解数独
 * 编写一个程序，通过填充空格来解决数独问题。
 *
 * 数独的解法需 遵循如下规则：
 *
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用'.'表示。
 *
 */
public class Sudoku {


    static int m = 9;
    static int n = 9;
    public static void solveSudoku(char[][] board) {
        solve(board, 0, 0);
    }


    // 返回 boolean 可以在找到唯一解之后理解返回，不用再把其他的遍历完
    private static boolean solve(char[][] board, int i, int j) {
        // base case 到了最后一行，说明已经w完成
        if (i == m) return true;
        // 切换到下一行
        if (j == n) {
            return solve(board, i + 1, 0);
        }
        if (board[i][j] != '.') {
            return solve(board, i, j + 1);
        }

        for (char ch = '1'; ch <= '9'; ch++) {
            // 剪枝
            if (!isValid(board, i, j, ch)) continue;

            board[i][j] = ch;
            // 如果找到一个可行解，立即结束
            // 相当于剪枝，不用接着循环其他的数据
            if (solve(board, i, j + 1)) return true;

            board[i][j] = '.';
        }
        return false;
    }

    private static boolean isValid(char[][] board, int i, int j, char c) {
        for (int a = 0; a < 9; a++) {
            // 整行不能有重复的
            if (board[i][a] == c) return false;
            // 整列不能有重复的
            if (board[a][j] == c) return false;
            // 3 x 3 中不能有重复的
            if (board[(i / 3) * 3 + a / 3][(j / 3) * 3 + a % 3] == c) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        char[][] ch = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        solveSudoku(ch);
        for (char[] chars : ch) {
            System.out.println(Arrays.toString(chars));

        }
    }


}
