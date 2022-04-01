package com.zjn.technical;

/**
 * 42 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */
public class Trap {

    // 暴力解法
    public int trap(int[] height) {

        int length = height.length;

        int res = 0;
        for (int i = 1; i < length; i++) {
            // 计算右侧最高的柱子
            int rMax = 0;
            for (int j = i; j < length; j++) {
                rMax = Math.max(rMax, height[j]);
            }
            // 寻找左侧最高的柱子
            int lMax = 0;
            for (int j = i; j >= 0; j--) {
                lMax = Math.max(lMax, height[j]);
            }

            int c = Math.min(rMax, lMax) - height[i];
            res += c;
        }
        return res;
    }


    // 备忘录解法
    // 备忘录
    public int trap_1(int[] height) {

        int length = height.length;
        // 记录左侧最高
        int[] lMax = new int[length];
        // 记录右侧最高
        int[] rMax = new int[length];
        // 初始化
        lMax[0] = height[0];
        rMax[length - 1] = height[length - 1];

        // 填充左侧最高柱子
        for (int i = 1; i < length; i++) {
            lMax[i] = Math.max(lMax[i - 1], height[i]);
        }

        // 填充右侧最高柱子
        for (int i = length - 2; i >= 0; i--) {
            rMax[i] = Math.max(rMax[i + 1], height[i]);
        }

        int res = 0;
        for (int i = 1; i < length - 1; i++) {
            res += Math.min(lMax[i], rMax[i]) - height[i];
        }
        return res;
    }


    // 双指针解法
    public int trap_2(int[] height) {
        int length = height.length;

        int lMax = 0;
        int rMax = 0;

        int l = 0, r = length - 1;
        int res = 0;
        while (l < r) {
            lMax = Math.max(lMax, height[l]);
            rMax = Math.max(rMax, height[r]);
            // 这种解法可行是因为我要知道左右高度中最低的即可
            if (lMax < rMax) {
                res += lMax - height[l];
                l++;
            } else {
                res += rMax - height[r];
                r--;
            }
        }
        return res;
    }


}
