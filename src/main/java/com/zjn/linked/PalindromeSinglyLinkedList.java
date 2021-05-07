package com.zjn.linked;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PalindromeSinglyLinkedList<T> {

    private LinkNode<T> first;

    private LinkNode<T> last;

    private int size = 0;



    public void add(T t) {
        if (t == null) {
            return;
        }
        LinkNode<T> tail = new LinkNode<T>(t, null);
        LinkNode<T> l = last;
        last = tail;
        if (l == null) {
            first = tail;
        } else {
            l.next = tail;
        }
        size ++;

    }


    public void traversal() {
        LinkNode<T> x = first;
        for (int i = 0; i < size; i++) {
            if (x == null) {
                continue;
            }
            System.out.println(x.val);
            x = x.next;
        }
        System.out.println("first:" + first.val);
        System.out.println("last:" + last.val);
        System.out.println("==============");

    }

    /**
     * 获取字符串s中以 l，r为中心的回文字符串 偶数字符串，有两个中心
     * @param s s
     * @param l m中心
     * @param r n中心
     * @return 回文字符串
     */
    public String palindrome(String s, int l, int r) {

        char[] chars = s.toCharArray();
        while(l >= 0 && r < s.length() && chars[l] == chars[r]) {
            // 中心向两边展开
            l --; r ++;
        }
        return s.substring(l + 1, r);

    }

    /**
     * 判断字符串是否是回文串
     * @param s s
     * @return boolean
     */
    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int l = 0, r = s.length() - 1;
        while(l < r) {
            if (chars[l] != chars[r]) {
                return false;
            }
            l ++; r --;
        }
        return true;
    }


    /**
     * 给定头结点，判断链表中 val 是否是回文
     * @param head 头节点
     * @return 是否是回文
     */
    public boolean isPalindrome(LinkNode<T> head) {
        // 取出最左侧的元素
//        first = head;

        return isPalindrome2(head);
    }

    /**
     * 递归判断单向链表是否是回文串
     * 时间复杂度 O(n)  空间复杂度 O(n)
     * @param head
     * @return
     */
    public boolean isPalindrome2(LinkNode<T> head) {

        if (head == null) {
            return true;
        }
        boolean res = isPalindrome2(head.next);

        res = res && (first.val == head.val);

        first = first.next;
        return res;
    }




    public LinkNode<T> reverse(LinkNode<T> head) {
        if (head == null) {
            return null;
        }
        LinkNode<T> pre = null, cur = head, next = head;
        while (next != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    /**
     * 时间复杂度 O(n)  空间复杂度 O(1)
     * @param head
     * @return
     */
    public boolean isPalindrome3(LinkNode<T> head) {
        // 根据快慢指针获取中心位置
        LinkNode<T> pre = head, af = head;
        while (af != null && af.next != null) {
            pre = pre.next;
            af = af.next.next;
        }
        if (af != null) {
            pre = pre.next;
        }

        LinkNode<T> newPre = this.reverse(pre);

        while(newPre != null) {
            if (!head.val.equals(newPre.val)) {
                return false;
            }
            head = head.next;
            newPre = newPre.next;
        }
        return true;
    }



    @ToString
    static class LinkNode<T> {

        T val;

        LinkNode<T> next;

        LinkNode(T value, LinkNode<T> node) {
            this.val = value;
            this.next = node;
        }
    }


    public static void main(String[] args) {

        PalindromeSinglyLinkedList<String> list = new PalindromeSinglyLinkedList<String>();
//        String dd = "abcdedcba";
//        String palindrome = list.palindrome(dd, 4, 4);
//        System.out.println(palindrome);
//
//        boolean isPalindrome = list.isPalindrome(dd);
//        System.out.println(isPalindrome);

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("e");
        list.add("e");
        list.add("d");
        list.add("c");
        list.add("b");
        list.add("a");

        boolean isPalindrome = list.isPalindrome3(list.first);
        System.out.println(isPalindrome);

    }

}
