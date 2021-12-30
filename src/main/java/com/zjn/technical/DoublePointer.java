package com.zjn.technical;

/**
 * 双指针技巧
 * 快慢指针  链表环
 * 左右指针  二分查找
 */
public class DoublePointer<T> {


    /**
     * 判定链表中是否含有环
     */
    boolean hasCycle(LinkNode<T> head) {
        // 指针指向头节点
        LinkNode fast = head, slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 已知链表中含有环，返回这个环的起始位置
     * head到相遇点距离为k，则fast走了2k，slow走了k，且k是环的n倍，设起始点到相遇点距离为m，则head到起始点距离为k-m，如果从相遇点继续前进 k - m 步，也恰好到达环起点
     * 甭管 fast 在环里到底转了几圈，反正走 k 步可以到相遇点，那走 k - m 步一定就是走到环起点了
     */
    LinkNode startPosition4Cycle(LinkNode<T> head) {

        LinkNode fast = head, slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == head) break;
        }
        // fast 为 null 或者 .next 为 null， 说明不存在环
        if (fast == null || fast.next == null) {
            return null;
        }

        slow = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;

    }



    /**
     * 寻找链表中点
     * 没有环的链表,使用快慢指针找中点
     */
    LinkNode middleNode(LinkNode head) {

        LinkNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    /**
     * 删除链表的倒数第 n 个元素,并且返回链表的头节点
     */
    LinkNode removeNthFromEnd (LinkNode head, int n) {

        LinkNode fast = head, slow = head;
        // 先走 n 步
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        // fast为null，则head就是倒数第n个节点，删除后的头节点是head.next
        if (fast == null) {
            return head.next;
        }

        // fast走到链表尾部，此时slow是在倒数n的位置
        // 这里判断fast.next != null是为了让fast少走一步，找到倒数第n+1个位置，方便删除倒数第n个元素
        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // 这里slow是倒数第n+1个位置
        slow.next = slow.next.next;
        return head;
    }


    /**
     * 给定一个升序排序的有序数组，找到两个数是他们相加只和等于目标数
     * 返回两个下标值index1和index2，其中index1必须小于index2
     * 说明：
     *     1.下标值index1和index2不是从0开始的
     *     2.可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素
     */

    int[] twoSum(int[] nums, int t) {
        int left = 0, right = nums.length - 1;
        //index1必须小于index2
        while (left < right) {
            int i = nums[left] + nums[right];
            if (i == t) {
                return new int[]{left + 1, right + 1};
            } else if (i > t) {
                right --;
            } else if (i < t) {
                left ++;
            }
        }
        return new int[]{-1, -1};
    }


    /**
     * 反转数组
     */
    void reverseChar(char[] arr) {
        int left = 0, right = arr.length;

        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left ++;
            right --;
        }
    }




    class LinkNode<T>{

        T val;
        LinkNode<T> next;

        public LinkNode(T val, LinkNode<T> node) {
            this.val = val;
            this.next = node;
        }



    }




}
