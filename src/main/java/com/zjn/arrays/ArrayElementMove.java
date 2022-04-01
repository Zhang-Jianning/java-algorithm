package com.zjn.arrays;

import lombok.ToString;

public class ArrayElementMove {

    /**
     * 26. 删除有序数组中的重复项
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int slow = 0, fast = 1;
        while(fast < nums.length) {
            // 如果slow和fast元素不一样，则让slow前进并修改slow位置的元素
            if (nums[slow] != nums[fast]) {
                slow ++;
                // 如果位置一样，其实是不需要重新赋值的      不加判断数组中所有的元素会重新赋值一次
                if (fast - slow > 0) {
                    nums[slow] = nums[fast];
                }
            }
            fast ++;

        }
        // slow 是index，返回长度
        return slow + 1;
    }


    /**
     * 83  给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
     * 提示：
     * 链表中节点数目在范围 [0, 300] 内
     * -100 <= Node.val <= 100
     * 题目数据保证链表已经按升序 排列
     */
    public LinkNode<Integer> removeDuplicates4LinkNode(LinkNode<Integer> head) {
        if (head == null) return null;

        LinkNode<Integer> slow = head, fast = head.next;

        while(fast != null) {

            if (slow.val != fast.val) {
                if (slow.next.val != fast.val) {
                    slow.next = fast;
                }
                slow = slow.next;
            }
            fast = fast.next;
        }
        slow.next = null;
        return head;
    }


    public LinkNode deleteDuplicates(LinkNode head) {
        LinkNode cur = head;
        while(cur != null && cur.next != null) {
            if(cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    /**
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     *
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     */

    public int removeElement(int[] nums, int val) {

        if (nums.length == 0) return 0;

        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow ++;
            }
            fast ++;
        }
        return slow;
    }

    public int removeElement2(int[] nums, int val) {

        int index = 0;
        for (int num : nums) {
            if (num != val) {
                nums[index] = num;
                index ++;
            }
        }
        return index;
    }


    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     */
    public void moveZeroes(int[] nums) {

        if (nums.length == 0) return;
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow ++;
            }
            fast ++;
        }

        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }

    }

    public void moveZeroes2(int[] nums) {

        if (nums == null || nums.length == 0) return;

        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                // 同一个位置就不必交换了
                if (slow < fast) {
                    nums[slow] = nums[fast];
                    nums[fast] = 0;
                }
                slow ++;
            }
            fast ++;
        }

    }



    @ToString
    static class LinkNode<T> {

        T val;

        ArrayElementMove.LinkNode<T> next;

        LinkNode(T value, ArrayElementMove.LinkNode<T> node) {
            this.val = value;
            this.next = node;
        }
    }

}
