package com.zjn.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combine {

    /**
     * 77 组合
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     *
     * 你可以按 任何顺序 返回答案。
     *
     * 元素无重不可复选
     */


    List<List<Integer>> res;
    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        backtrack(n, k, list, 1);
        return res;
    }

    // 根节点是第0层，第 n 层的所有节点就是大小为 n 的所有组合
    public void backtrack(int n, int k, List<Integer> list, int index) {
        // 手机第k层
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i <= n; i++) {
            // 选择index位置元素
            list.add(i);
            // 下一个位置  通过 index 参数控制树枝的遍历，避免产生重复的子集
            backtrack(n, k, list, i + 1);
            // 撤销选择
            list.remove(list.size() - 1);
        }
    }


    /**
     * 40 组合总和 II
     * 给定一个候选人编号的集合candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
     *
     * candidates中的每个数字在每个组合中只能使用一次。
     *
     * 注意：解集不能包含重复的组合。
     *
     * 元素可重不可重复选择
     *
     */
    List<List<Integer>> combination;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        combination = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        Arrays.sort(candidates);
        backtrack(candidates, target, list, 0, 0);
        return combination;
    }

    private void backtrack(int[] candidates, int target, List<Integer> list, int sum, int index) {

        if (sum == target) {
            combination.add(new ArrayList<>(list));
            return;
        }
        // 超出target就不必在往下查找了
        if (sum > target) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {

            // 有重复元素，需要剪枝
            if (i > index && candidates[i] == candidates[i - 1]) continue;
            // 做选择
            list.add(candidates[i]);
            sum += candidates[i];
            // 从i+1继续寻找
            backtrack(candidates, target, list, sum, i + 1);
            // 撤销选择
            list.remove(list.size() - 1);
            sum -= candidates[i];
        }

    }


    /**
     * 39 组合总和
     * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，找出candidates中可以使数字和为目标数target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     *
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     *
     * 对于给定的输入，保证和为target 的不同组合数少于 150 个。
     *
     *
     * 这里元素可重复使用只需要进入下一次回溯时index保持不变即可，但是循环不能从0开始，必须从index开始，从0开始结果会重复
     */
    List<List<Integer>> combinationSum;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        combinationSum = new ArrayList<>();

        List<Integer> list = new ArrayList<>();

        backtrack_1(candidates, target, list, 0, 0);
        return combinationSum;

    }


    private void backtrack_1(int[] candidates, int target, List<Integer> list, int index, int sum) {

        if (sum == target) {
            combinationSum.add(new ArrayList<>(list));
            return;
        }
        if (sum > target) return;

        for (int i = index; i < candidates.length; i++) {

            list.add(candidates[i]);
            sum += candidates[i];
            // 让每个元素被重复使用，我只要把 i + 1 改成 i 即可
            backtrack_1(candidates, target, list, i, sum);

            list.remove(list.size() - 1);
            sum -= candidates[i];

        }


    }


}
