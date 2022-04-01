package com.zjn.dynamic.programming;


import java.util.Arrays;
import java.util.Comparator;

/**
 * 354 . 俄罗斯套娃信封嵌套问题
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 *
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 *
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 *
 * 注意：不允许旋转信封。
 *
 */
public class EnvelopeNested {

    public int maxEnvelopes(int[][] envelopes) {

        /**
         * public static int compare(int x, int y) {
         *         return (x < y) ? -1 : ((x == y) ? 0 : 1);
         * }
         */
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
            }
        });


        int[] ints = new int[envelopes.length];

        for (int i = 0; i < envelopes.length; i++) {
            ints[i] = envelopes[i][1];
        }

        return LIS(ints);

    }


    private int LIS(int[] nums) {

        if (nums.length <= 1) return nums.length;

        int[] ints = new int[nums.length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = 1;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] <= nums[j]) continue;
                ints[i] = Math.max(ints[i], ints[j] + 1);
            }
        }
        int res = 0;
        for (int anInt : ints) {
            res = Math.max(res, anInt);
        }

        return res;
    }



    private int LISByB(int[] nums) {
        if (nums.length <= 1) return nums.length;
        // 堆顶元素
        int[] ints = new int[nums.length];
        int piles = 0;
        for (int num : nums) {
            int left = 0, right = piles;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (ints[mid] == num) {
                    right = mid;
                } else if (ints[mid] > num) {
                    right = mid;
                } else if (ints[mid] < num) {
                    left = mid + 1;
                }
            }
            if (left == piles) piles ++;
            ints[left] = num;
        }
        return piles;

    }


}
