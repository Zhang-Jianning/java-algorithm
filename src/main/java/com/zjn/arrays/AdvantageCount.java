package com.zjn.arrays;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 给你输入两个长度相等的数组 nums1 和 nums2，请你重新组织 nums1 中元素的位置，使得 nums1 的「优势」最大化。
 *
 * 如果 nums1[i] > nums2[i]，就是说 nums1 在索引 i 上对 nums2[i] 有「优势」。优势最大化也就是说让你重新组织 nums1，尽可能多的让 nums1[i] > nums2[i]。
 */
public class AdvantageCount {

    // 两个数组排序后，相同位置的元素直接比较大小就可以 ，比得过直接拿出元素，比不过挑最小的元素顶上
    public static int[] advantage(int[] nums1, int[] nums2) {

        int n = nums1.length;
        // 最大堆
        Queue queue = new PriorityQueue<int []>((a, b) -> b[1] - a[1]);
        // 升序
        Arrays.sort(nums1);

        // 将nums2数据的元素便利，将数据角标和对应的数据中的值放入队列  从大到小
        for (int i = 0; i < nums2.length; i++) {
            queue.offer(new int[]{i, nums2[i]});
        }

        int[] result = new int[n];
        // 双指针定位nums1的元素位置
        int left = 0, right = n - 1;
        for (int i = 0; i < n; i++) {
            int[] poll = (int[]) queue.poll();
            // 拿出nums2的最大元素和元素对应index
            int index = poll[0], maxVal = poll[1];
            // nums1 大，则直接用该值即可 即为最优解，不需要看下一个值，因为最终 数组1 比 数组2 的元素大的数量相等的
            if (nums1[right] > maxVal) {
                result[index] = nums1[right];
                right --;
            } else {
                // 拿出最小值去匹配
                result[index] = nums1[left];
                left ++;
            }
        }

        return result;
    }


    public static void main(String[] args) {
        int[] ints = {30, 34, 8, 32};
        int[] nums2 = {13,25,31,11};
        int[] advantage = advantage(ints, nums2);
        System.out.println(Arrays.toString(advantage));
    }


}
