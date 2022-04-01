package com.zjn.technical;

/**
 * 11 盛最多水的容器
 * 给定一个长度为 n 的整数数组height。有n条垂线，第 i 条线的两个端点是(i, 0)和(i, height[i])。
 *
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 *
 * 返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 *
 */
public class MaxArea{

    public int maxArea(int[] height) {

        int length = height.length;

        int l = 0, r = length - 1;
        int res = 0;
        while (l < r) {
            // 当前位置容量
            int area = Math.min(height[l], height[r]) * (r - l);
            // 取最大的
            res = Math.max(res, area);
            // 移动指针  移动较低的位置，因为面积是由较低的位置决定的
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return res;
    }

}
