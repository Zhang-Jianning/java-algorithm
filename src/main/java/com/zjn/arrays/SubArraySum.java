package com.zjn.arrays;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定一个整数数组和一个整数k，找到该数组中和为k的连续的子数组的个数
 */
public class SubArraySum {


    int subSum(int[] nums, int k) {

        int n = nums.length;

        int[] subSumArray = new int[n + 1];
        subSumArray[0] = 0;

        for (int i = 0; i < n; i++) {
            subSumArray[i + 1] = subSumArray[i] + nums[i];
        }

        int res = 0;
        for (int i = 1; i < subSumArray.length; i++) {
            for (int j = 0; j < i; j++) {
                if (subSumArray[i] - subSumArray[j] == k) {
                    res ++;
                }
            }
        }

        return res;
    }



    static int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // map：前缀和 -> 该前缀和出现的次数
        HashMap<Integer, Integer>
                preSum = new HashMap<>();
        // base case
        preSum.put(0, 1);

        int ans = 0, sum0_i = 0;
        for (int i = 0; i < n; i++) {
            sum0_i += nums[i];
            // 这是我们想找的前缀和 nums[0..j]
            int sum0_j = sum0_i - k;
            // 如果前面有这个前缀和，则直接更新答案
            if (preSum.containsKey(sum0_j))
                ans += preSum.get(sum0_j);
            // 把前缀和 nums[0..i] 加入并记录出现次数
            preSum.put(sum0_i,
                    preSum.getOrDefault(sum0_i, 0) + 1);
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = {0, 0, 1, -1};
        System.out.println(subarraySum(nums, 0));

//        NumArray numArray = new NumArray(nums);
//        int i = numArray.sumRange(1, 2);
//        System.out.println(i);
//        numArray.scoreCount();

//        int[][] mat = {{0,1},{2,3},{4,5}};
//
//        NumMatrix numMatrix = new NumMatrix(mat);
//        int i = numMatrix.sumRegion(0, 0, 1, 1);
//        System.out.println(i);


    }


    // 数字矩阵
    static class NumMatrix{
        // preSum[i][j] 记录矩阵 [0, 0, i, j] 的元素和
        private int[][] sumMatrix;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            sumMatrix = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    // sumMatrix[1][1] 记录的是 matrix[0][0]的值
                    sumMatrix[i][j] = sumMatrix[i][j - 1] + sumMatrix[i - 1][j] - sumMatrix[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
            }
        }

        // [x1][y1]是左上角  [x2][y2]是右下角
        public int sumRegion(int x1, int y1, int x2, int y2) {
            return sumMatrix[x2 + 1][y2 + 1] - sumMatrix[x1][y2 + 1] - sumMatrix[x2 + 1][y1] + sumMatrix[x1][y1];
        }


    }



    // 整数数组
    static class NumArray{

        private int[] array;

        public NumArray(int[] nums) {
            array = new int[nums.length + 1];
            array[0] = 0;
            for (int i = 0; i < nums.length; i++) {
                array[i + 1] = nums[i] + array[i];
            }
        }


        // 返回i、j范围内元素的总和，包含i、j
        public int sumRange(int i, int j) {
            return array[j + 1] - array[i];
        }




        public void scoreCount() {
            // 存储着所有同学的分数
            int[] scores = {0,1,2,3,4,5,5,5,10,10};
            // 试卷满分 100 分
            int[] count = new int[10 + 1];
            for (int score : scores) {
                // 该分数出现的次数
//                count[score] = count[score] + 1;
                count[score]++;
            }
            System.out.println(Arrays.toString(count));

//            for (int i = 0; i < count.length - 1; i++) {
//                count[i + 1] = count[i + 1] + count[i];
//            }
            for (int i = 1; i < count.length; i++)
                count[i] = count[i] + count[i-1];
            System.out.println(Arrays.toString(count));

            // 获取start至end得分的数量
            int end = 10, start = 5;
            int i = count[end] - count[start - 1];
            System.out.println(i);

        }





    }






}
