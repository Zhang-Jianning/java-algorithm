package com.zjn.technical;

import java.util.HashSet;
import java.util.Set;

/**
 * 391 完美矩形
 * 给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi] 表示一个坐标轴平行的矩形。这个矩形的左下顶点是 (xi, yi) ，右上顶点是 (ai, bi) 。
 * 如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ；否则，返回 false 。
 */
public class PerfectRectangle {

    // 面积 和 顶点的判断方式
    public boolean isRectangleCover(int[][] rectangles) {
        // 记录顶点
        Set<String> set = new HashSet<>();
        // 左下角坐标初始化为最大值，因为求的是min，右上角初始化为最小值，求的是max
        int X1 = Integer.MAX_VALUE, Y1 = Integer.MAX_VALUE, X2 = Integer.MIN_VALUE, Y2 = Integer.MIN_VALUE;
        // 实际面积
        int actualArea = 0;
        for (int[] rectangle : rectangles) {
            int x1 = rectangle[0];
            int y1 = rectangle[1];
            int x2 = rectangle[2];
            int y2 = rectangle[3];
            actualArea += (x2 - x1) * (y2 - y1);
            // 记录所有的顶点
            int[] ld = new int[]{x1, y1};
            int[] lu = new int[]{x1, y2};
            int[] rd = new int[]{x2, y1};
            int[] ru = new int[]{x2, y2};
            // 当某一个点同时是 2 个或者 4 个小矩形的顶点时，该点最终不是顶点；当某一个点同时是 1 个或者 3 个小矩形的顶点时，该点最终是一个顶点
            // 记录所有的顶点，如果顶点已经存在，就删除  即偶数个的顶点会删除，只会记录奇数个的顶点
            for (int[] p : new int[][]{ld, lu, rd, ru}) {
                String sp = p[0] + "," + p[1];
                if (set.contains(sp)) {
                    set.remove(sp);
                } else {
                    set.add(sp);
                }
            }

            // 假设是完美矩形，拿到顶点
            // 左下
            X1 = Math.min(x1, X1);
            Y1 = Math.min(y1, Y1);
            // 右上
            X2 = Math.max(X2, x2);
            Y2 = Math.max(Y2, y2);

        }

        // 完美矩形的面积
        int expectedArea = (X2 - X1) * (Y2 - Y1);
        // 面积不想等就不可能是完美矩形
        if (actualArea != expectedArea) return false;
        // 只能是4个顶点
        if (set.size() != 4) return false;
        // 完美矩形的4个顶点
        int[] ld = new int[]{X1, Y1};
        int[] lu = new int[]{X1, Y2};
        int[] rd = new int[]{X2, Y1};
        int[] ru = new int[]{X2, Y2};
        for (int[] p : new int[][]{ld, lu, rd, ru}) {
            String sp = p[0] + "," + p[1];
            if (!set.contains(sp)) return false;
        }
        return true;
    }

}



