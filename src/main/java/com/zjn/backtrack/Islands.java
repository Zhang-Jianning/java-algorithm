package com.zjn.backtrack;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 岛屿问题
 */
public class Islands {


    /**
     * 200 岛屿数量
     * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     *
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     *
     * 此外，你可以假设该网格的四条边均被水包围。
     *
     */
    // 上下左右
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    int m;
    int n;
    public int numIslands(char[][] grid) {

        m = grid.length;
        n = grid[0].length;

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count ++;
                    floodFill(grid, i, j);
                }
            }
        }
        return count;
    }

    // 将和i、j相连的填充为0
    private void floodFill(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        if (grid[i][j] == '0') return;
        // 淹没ij
        grid[i][j] = '0';
        // 填充上下左右
        for (int[] dir : dirs) {
            floodFill(grid, i + dir[0], j + dir[1]);
        }
    }


    /**
     * 1254 统计封闭岛屿的数量
     * 二维矩阵 grid由 0（土地）和 1（水）组成。岛是由最大的4个方向连通的 0组成的群，封闭岛是一个完全 由1包围（左、上、右、下）的岛。
     *
     * 请返回 封闭岛屿 的数目。
     *  这里就是挨着边的不算封闭岛屿
     */
    public int closedIsland(int[][] grid) {

        m = grid.length;
        n = grid[0].length;

        for (int i = 0; i < m; i++) {
            // 淹没左边
            floodFill(grid, i, 0);
            // 淹没右边
            floodFill(grid, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            // 淹没上边
            floodFill(grid, 0, j);
            // 淹没下边
            floodFill(grid, m - 1, j);
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    count ++;
                    floodFill(grid, i, j);
                }
            }
        }
        return count;
    }

    private void floodFill(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= m || j >= n) return;
        if (grid[i][j] == 1) return;
        grid[i][j] = 1;

        for (int[] dir : dirs) {
            floodFill(grid, i + dir[0], j + dir[1]);
        }
    }


    /**
     * 1020 飞地的数量
     * 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
     *
     * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
     *
     * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
     *
     *
     */
    public int numEnclaves(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        for (int i = 0; i < m; i++) {
            // 淹没左边
            floodFill_1(grid, i, 0);
            // 淹没右边
            floodFill_1(grid, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            // 淹没上边
            floodFill_1(grid, 0, j);
            // 淹没下边
            floodFill_1(grid, m - 1, j);
        }

        int count = 0;
        // 淹没边沿的陆地后，看一下剩下的数量
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    count ++;
                }
            }
        }
        return count;

    }

    private void floodFill_1(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= m || j >= n) return;
        if (grid[i][j] == 0) return;
        grid[i][j] = 0;

        for (int[] dir : dirs) {
            floodFill_1(grid, i + dir[0], j + dir[1]);
        }
    }


    /**
     * 695 岛屿的最大面积
     * 给你一个大小为 m x n 的二进制矩阵 grid 。
     *
     * 岛屿是由一些相邻的1(代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设grid 的四个边缘都被 0（代表水）包围着。
     *
     * 岛屿的面积是岛上值为 1 的单元格的数目。
     *
     * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
     *
     *
     */
    public int maxAreaOfIsland(int[][] grid) {

        m = grid.length;
        n = grid[0].length;

        int area = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    area = Math.max(area, floodFill_2(grid, i, j));
                }

            }
        }
        return area;
    }

    private int floodFill_2(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return 0;
        }
        if (grid[i][j] == 0) return 0;
        grid[i][j] = 0;
        // 当前i、j是第一个
        int area = 1;
        for (int[] dir : dirs) {
            area += floodFill_2(grid, i + dir[0], j + dir[1]);
        }
        return area;
    }


    /**
     * 1905 统计子岛屿
     * 给你两个m x n的二进制矩阵grid1 和grid2，它们只包含0（表示水域）和 1（表示陆地）。一个 岛屿是由 四个方向（水平或者竖直）上相邻的1组成的区域。任何矩阵以外的区域都视为水域。
     *
     * 如果 grid2的一个岛屿，被 grid1的一个岛屿完全 包含，也就是说 grid2中该岛屿的每一个格子都被 grid1中同一个岛屿完全包含，那么我们称 grid2中的这个岛屿为 子岛屿。
     *
     * 请你返回 grid2中 子岛屿的 数目。
     *
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {

        m = grid1.length;
        n = grid1[0].length;

        // 先把grid2中的非grid1的岛屿剔除
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // grid2中是陆地，grid1中是海水，说明这块不是grid1的子集，直接把这块填充为海水
                if (grid2[i][j] == 1 && grid1[i][j] == 0) {
                    floodFill_3(grid2, i, j);
                }
            }
        }
        int res = 0;
        // 再次遍历，这时grid2中都是grid1的子岛屿
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // grid2中是陆地，grid1中是海水，说明这块不是grid1的子集，直接把这块填充为海水
                if (grid2[i][j] == 1) {
                    res ++;
                    floodFill_3(grid2, i, j);
                }
            }
        }
        return res;
    }

    private void floodFill_3(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= m || j >= n) return;
        if (grid[i][j] == 0) return;
        grid[i][j] = 0;

        for (int[] dir : dirs) {
            floodFill_3(grid, i + dir[0], j + dir[1]);
        }

    }


    /**
     * 694 不同的岛屿数量
     *
     *输入一个二维矩阵，0 表示海水，1 表示陆地，这次让你计算 不同的 (distinct) 岛屿数量
     */
    int numDistinctIslands(int[][] grid) {

        m = grid.length;
        n = grid[0].length;
        Set<String> res = new HashSet();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder builder = new StringBuilder();
                    floodFill_4(grid, i, j, 666, builder);
                    res.add(builder.toString());
                }
            }
        }
        return res.size();
    }


    private void floodFill_4(int[][] grid, int i, int j, int flag, StringBuilder builder) {

        if (i < 0 || j < 0 || i >= m || j >= n) return;
        if (grid[i][j] == 0) return;
        grid[i][j] = 0;
        builder.append(flag).append(",");
        //上
        floodFill_4(grid, i - 1, j, 1, builder);
        // 下
        floodFill_4(grid, i + 1, j, 2, builder);
        // 左
        floodFill_4(grid, i, j - 1, 3, builder);
        // 右
        floodFill_4(grid, i, j + 1, 4, builder);
        builder.append(-flag).append(",");
    }

}
