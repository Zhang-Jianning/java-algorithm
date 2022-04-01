package com.zjn.technical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TwoSum {

    /**
     * 给你一个数组和一个整数 target，可以保证数组中存在两个数的和为 target，请你返回这两个数的索引
     */


    int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int find = target - nums[i];
            if (map.get(find) != null && map.get(find) != i) {
                return new int[]{i, map.get(find)};
            }
        }
        return new int[]{-1, -1};

    }


    /**
     * 实现两个api
     * class TwoSum {
     *     // 向数据结构中添加一个数 number
     *     public void add(int number);
     *     // 寻找当前数据结构中是否存在两个数的和为 value
     *     public boolean find(int value);
     * }
     */

    Map<Integer, Integer> freq = new HashMap<>();
    // 添加元素,记录频次
    public void add(int number) {
        freq.put(number, freq.getOrDefault(number, 0) + 1);
    }
    // 是否存在符合要求的元素
    public boolean find(int value) {
        for (Integer integer : freq.keySet()) {
            int tar = value - integer;
            if (tar != integer && freq.get(tar) > 0) {
                return true;
            } else if (tar == integer && freq.get(tar) > 1) {
                return true;
            }
        }
        return false;
    }


    /**
     * 对于频繁使用 find 方法的场景 的优化
     */
    Set<Integer> sum = new HashSet<>();
    List<Integer> nums = new ArrayList();
    // 添加元素,记录频次
    public void add1(int number) {
        nums.add(number);
        for (Integer integer : nums) {
            sum.add(integer + number);
        }
    }
    // 是否存在符合要求的元素
    public boolean find1(int value) {
        return sum.contains(value);
    }


    /**
     * 给定 有序 的数组和一个整数 target，可以保证数组中存在两个数的和为 target，请你返回这两个数的索引
     */

    public int[] findByOrderlyArr(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left ++;
            } else if (sum > target) {
                right --;
            }
        }
        return new int[]{-1, -1};
    }

}
