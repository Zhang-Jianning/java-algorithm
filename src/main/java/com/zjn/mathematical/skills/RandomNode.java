package com.zjn.mathematical.skills;

import java.util.Random;

public class RandomNode {


    /**
     * 382 链表随机节点
     * 给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。
     *
     * 实现 Solution 类：
     *
     * Solution(ListNode head) 使用整数数组初始化对象。
     * int getRandom() 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。
     *
     *
     */

    ListNode head;
    public RandomNode(ListNode head) {
        this.head = head;
    }

    public int getRandom() {
        Random random = new Random();
        int res = 0;
        int i = 0;
        ListNode p = head;
        // 当前遍历到第i个元素，第i个元素获取的概率 是 1/i,后续元素遍历i不被获取的概率是 1- 1/(i+n),后续遍历相当于减小概率
        // https://labuladong.github.io/algo/images/%e6%b0%b4%e5%a1%98%e6%8a%bd%e6%a0%b7/formula1.png
        while (p != null) {
            i++;
            if (0 == random.nextInt(i)) {
                res = p.val;
            }
            p = p.next;
        }
        return res;
    }



    /* 返回链表中 k 个随机节点的值 */
    int[] getRandom(ListNode head, int k) {

        Random r = new Random();
        int[] res = new int[k];

        ListNode p = head;
        for (int i = 0; i < k; i++) {
            res[i] = p.val;
            p = p.next;
        }

        int i = k;
        // 遍历时到第i个元素，第i个元素被选中的概率 k/i, 后续遍历i不被选到的概率是 1 - k/(i+n) * 1/k
        while (p != null) {
            i++;
            int j = r.nextInt(i);
            // j<k的概率是 k/i
            if (j < k) {
                res[j] = p.val;
            }
            p = p.next;
        }
        return res;
    }



    class ListNode {
        int val;
        ListNode next;
         ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    /**
     * 398 随机数索引
     * 给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。
     *
     * 注意：
     * 数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。
     *
     */

    int[] nums;
    public RandomNode(int[] nums) {
        this.nums = nums;
    }

    public int pick(int target) {

        Random r = new Random();

        int res = 0;

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]) {
                count++;
                if (0 == r.nextInt(count)) {
                    res = i;
                }
            }
        }
        return res;
    }









}
