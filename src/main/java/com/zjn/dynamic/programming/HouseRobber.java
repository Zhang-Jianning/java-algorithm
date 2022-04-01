package com.zjn.dynamic.programming;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HouseRobber {

    /**
     * 198 打家劫舍
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     */
    int[] memo;
    public int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dp(nums, 0);
    }

    // 自顶向下
    public int dp(int[] nums, int i) {
        if (i >= nums.length) return 0;
        if (memo[i] != -1) return memo[i];
        int res = Math.max(dp(nums, i + 2) + nums[i], dp(nums, i + 1));
        memo[i] = res;
        return res;
    }


    // 自底向上
    public int rob1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 2];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 2] + nums[i], dp[i + 1]);
        }
        return dp[0];
    }

    // 空间优化
    public int rob2(int[] nums) {
        int n = nums.length;
        int i_2 = 0;
        int i_1 = 0;
        int res = 0;
        for (int i = n - 1; i >= 0; i--) {
            res = Math.max(i_2 + nums[i], i_1);
            i_2 = i_1;
            i_1 = res;
        }
        return res;
    }


    /**
     *
     * 213  打家劫舍2
     *
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     *
     */
    public int rob3(int[] nums) {
        // 需要对只有单个元素的情况判断
        if (nums.length == 1) return nums[0];
        int a = rob3_0(nums, 0, nums.length - 2);
        int b = rob3_0(nums, 1, nums.length - 1);
        return Math.max(a, b);
    }

    private int rob3_0(int[] nums, int start, int end) {
        int i_2 = 0;
        int i_1 = 0;
        int res = 0;
        for (int i = start; i <= end; i++) {
            res = Math.max(i_2 + nums[i], i_1);
            i_2 = i_1;
            i_1 = res;
        }
        return res;
    }




    Map<TreeNode, Integer> map;
    public int rob4(TreeNode root) {
        map = new HashMap<>();
        return rob4_0(root);
    }

    public int rob4_0(TreeNode root) {
        if (root == null) return 0;
         if (map.containsKey(root)) return map.get(root);
        // 打劫
        int a = root.val
                        + (root.left == null ? 0 : rob4_0(root.left.left) + rob4_0(root.left.right))
                        + (root.right == null ? 0 : rob4_0(root.right.left) + rob4_0(root.right.right));
        // 不打劫
        int b = root == null ? 0 : (rob4_0(root.left) + rob4_0(root.right));

        int res = Math.max(a, b);
        map.put(root, res);
        return res;
    }




    // 另一种思路
    public int rob5(TreeNode root) {
        int[] ints = rob5_0(root);
        return Math.max(ints[0], ints[1]);
    }

    // 返回数组， 0 代表抢的最大金额  1代表不抢的最大金额
    public int[] rob5_0(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int[] left = rob5_0(root.left);
        int[] right = rob5_0(root.right);
        // 抢
        int todo = root.val + left[1] + right[1];
        int not_todo = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{todo, not_todo};
    }



public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


}
