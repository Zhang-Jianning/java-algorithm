package com.zjn.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 46 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 *
 * 如果这里再给一个入参，算元素个数为k的排列
 * void backtrack(int[] nums, int k) {
 *     // base case，到达第 k 层
 *     if (track.size() == k) {
 *         // 第 k 层节点的值就是大小为 k 的排列
 *         res.add(new LinkedList(track));
 *         return;
 *     }
 *
 *     // 回溯算法标准框架
 *     for (int i = 0; i < nums.length; i++) {
 *         // ...
 *         backtrack(nums, k);
 *         // ...
 *     }
 * }
 *
 * 元素无重不可复选
 *
 */
public class Permute {


    List<List<Integer>> result;

    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();
        // 记录结果子集
        List<Integer> item = new ArrayList<>();
        // 标记是否选择过选择
        boolean[] booleans = new boolean[nums.length];
        find(nums, item, booleans);
        return result;

    }


    private void find(int[] nums, List<Integer> item, boolean[] booleans) {
        // 达到结束条件，添加到结果集，return
        if (item.size() == nums.length) {
            result.add(new ArrayList<>(item));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 已经选择过就跳过
            if (booleans[i]) continue;
            // 做选择
            item.add(nums[i]);
            // 标记选择
            booleans[i] = true;
            find(nums, item, booleans);
            // 撤销选择
            booleans[i] = false;
            // 移除选择
            item.remove(item.size() - 1);
        }
    }


    /**
     * 47 全排列II
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     */
    List<List<Integer>> res;
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        // 记录结果子集
        List<Integer> item = new ArrayList<>();
        // 标记是否选择过选择
        boolean[] booleans = new boolean[nums.length];
        Arrays.sort(nums);
        find_1(nums, item, booleans);
        return res;

    }

    private void find_1(int[] nums, List<Integer> item, boolean[] booleans) {
        // 达到结束条件，添加到结果集，return
        if (item.size() == nums.length) {
            res.add(new ArrayList<>(item));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 已经选择过就跳过
            if (booleans[i]) continue;
            // 这里再加一个条件，用来剪枝
            // 如果booleans[i - 1] == false说明nums[i - 1] 已经遍历完了，并且撤销选择后走到这里进行遍历，如果当前数和上一个数一样，那得到的结果就是重复的
            if (i > 0 && nums[i] == nums[i - 1] && booleans[i - 1] == false) continue;

            // 做选择
            item.add(nums[i]);
            // 标记选择
            booleans[i] = true;
            find_1(nums, item, booleans);
            // 撤销选择
            booleans[i] = false;
            // 移除选择
            item.remove(item.size() - 1);
        }
    }

    /**
     * 元素无重可复选 全排列
     */
    List<List<Integer>> dupRes;
    public List<List<Integer>> permuteWithDup(int[] nums) {

        dupRes = new ArrayList<>();

        List<Integer> list = new ArrayList<>();
        backtrack(nums, list);
        return dupRes;
    }

    private void backtrack(int[] nums, List<Integer> list) {
        if (list.size() == nums.length) {
            dupRes.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);

            backtrack(nums, list);

            list.remove(list.size() - 1);
        }

    }


}
