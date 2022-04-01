package com.zjn.backtrack;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {

    /**
     * 78 子集
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     *
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集
     *
     * 元素无重不可复选
     *
     *
     */
    List<List<Integer>> res;
    public List<List<Integer>> subsets(int[] nums) {

        res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        backtrack(nums, list, 0);
        return res;
    }


    public void backtrack(int[] nums, List<Integer> list, int index) {
        // 每次进来都是一个结果集
        res.add(new ArrayList<>(list));

        // 这里使用index，不能回头从0开始，否则就有重复了
        for (int i = index; i < nums.length; i++) {
            // 做选择
            list.add(nums[i]);
            // 继续
            backtrack(nums, list, i + 1);
            // 撤销选择
            list.remove(list.size() - 1);
        }
    }


    /**
     *
     * 90 子集II
     *
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     *
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     *
     */


    List<List<Integer>> result;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        result = new ArrayList<>();
        List<Integer> list = new ArrayList();
        Arrays.sort(nums);
        backtrack_1(nums, list, 0);
        return result;
    }


    private void backtrack_1(int[] nums, List<Integer> list, int index) {

        result.add(new ArrayList<>(list));

        for (int i = index; i < nums.length; i++) {

            // 这里判断是要大于 index，而不是大于0，如果是大于0，剪枝会多剪（把含有相等元素的子集都剪掉了），这里只需要剪枝 本层，值相同的相邻树枝只遍历第一条
            if (i > index && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);

            backtrack_1(nums, list, i + 1);

            list.remove(list.size() - 1);

        }

    }



}
