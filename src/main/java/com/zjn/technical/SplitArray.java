package com.zjn.technical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 695 分割数组为连续子序列
 *
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度至少为 3 的子序列，其中每个子序列都由连续整数组成。
 *
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 *
 */
public class SplitArray {



    public boolean isPossible(int[] nums) {

        // 记录数字出现的频率
        Map<Integer, Integer> freq = new HashMap<>();
        // 记录数字需要出现的次数
        Map<Integer, Integer> need = new HashMap<>();
        // 初始化数字出现的次数
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        for (int num : nums) {
            // 如果频次为0，说明已经被使用了，则跳过
            if (freq.get(num) == 0) continue;

            // 先判断是否能接在其他序列后面
            if (need.containsKey(num) && need.get(num) > 0) {
                freq.put(num, freq.get(num) - 1);
                // num 需要出现的次数减一
                need.put(num, need.get(num) - 1);
                need.put(num + 1, need.getOrDefault(num + 1, 0) + 1);

            // 新开序列
            } else if (freq.containsKey(num) && freq.get(num) > 0 && freq.containsKey(num + 1) && freq.get(num + 1) > 0 && freq.containsKey(num + 2) && freq.get(num + 2) > 0) {
                // 将 v 作为开头，新建一个长度为 3 的子序列 [v,v+1,v+2]
                freq.put(num, freq.get(num) - 1);
                freq.put(num + 1, freq.get(num + 1) - 1);
                freq.put(num + 2, freq.get(num + 2) - 1);
                need.put(num + 3, need.getOrDefault(num + 3, 0) + 1);
            } else {
                return false;
            }
        }
        return true;
    }


    // 打印出所有的情况
    public static boolean isPossible_1(int[] nums) {

        // 记录数字出现的频率
        Map<Integer, Integer> freq = new HashMap<>();
        // 记录数字需要出现的次数
        Map<Integer, List<List<Integer>>> need = new HashMap<>();
        // 初始化数字出现的次数
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        for (int num : nums) {
            // 如果频次为0，说明已经被使用了，则跳过
            if (freq.get(num) == 0) continue;

            // 先判断是否能接在其他序列后面
            if (need.containsKey(num) && need.get(num).size() > 0) {
                freq.put(num, freq.get(num) - 1);
                // num 需要出现的次数减一
                List<List<Integer>> lists = need.get(num);
                List<Integer> one = lists.get(0);
                lists.remove(0);
                List<List<Integer>> next = need.getOrDefault(num + 1, new ArrayList<>());
                one.add(num);
                next.add(one);
                need.put(num + 1, next);

                // 新开序列
            } else if (freq.containsKey(num) && freq.get(num) > 0 && freq.containsKey(num + 1) && freq.get(num + 1) > 0 && freq.containsKey(num + 2) && freq.get(num + 2) > 0) {
                // 将 v 作为开头，新建一个长度为 3 的子序列 [v,v+1,v+2]
                freq.put(num, freq.get(num) - 1);
                freq.put(num + 1, freq.get(num + 1) - 1);
                freq.put(num + 2, freq.get(num + 2) - 1);

                // 记录list
                List<Integer> list = new ArrayList<>();
                list.add(num);
                list.add(num + 1);
                list.add(num + 2);
                List<List<Integer>> orDefault = need.getOrDefault(num + 3, new ArrayList<>());
                orDefault.add(list);
                need.put(num + 3, orDefault);
            } else {
                return false;
            }
        }

        for (List<List<Integer>> value : need.values()) {
            for (List<Integer> integers : value) {
                System.out.println(integers);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] n = new int[]{1,2,3,3,4,4,5,6,7};
        boolean possible_1 = isPossible_1(n);
        System.out.println(possible_1);
    }
}
