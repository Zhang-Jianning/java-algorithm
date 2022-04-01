package com.zjn.technical;

import java.util.LinkedList;
import java.util.List;

/**
 * 969 煎饼排序
 * 给你一个整数数组 arr ，请使用 煎饼翻转 完成对数组的排序。
 *
 * 一次煎饼翻转的执行过程如下：
 *
 * 选择一个整数 k ，1 <= k <= arr.length
 * 反转子数组 arr[0...k-1]（下标从 0 开始）
 * 例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。
 *
 * 以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在10 * arr.length 范围内的有效答案都将被判断为正确。
 *
 */
public class PancakeSort {

    List<Integer> res;
    public List<Integer> pancakeSort(int[] arr) {
        res = new LinkedList<>();
        sort(arr, arr.length);
        return res;
    }

    // 将前n个中最大的放到第n个位置
    private void sort(int[] arr, int n) {

        if (n == 1) return;
        // 先找到前n个中最大的一个
        int maxIndex = 0;
        int maxItem = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] > maxItem) {
                maxIndex = i;
                maxItem = arr[i];
            }
        }

        // 将最大的反转到最上面
        reverse(arr, 0, maxIndex);
        res.add(maxIndex + 1);
        // 再次反转，把最大的反转到最底层
        reverse(arr, 0, n - 1);
        res.add(n);
        // 递归调用,将前n-1个中的最大的再次放到最下层
        sort(arr, n - 1);

    }

    // 反转
    private void reverse(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }


    }

}
