package com.zjn.linked;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SinglyLinkedList<T> {

    private LinkNode<T> first;

    private LinkNode<T> last;

    private int size = 0;


    // 后继节点
    private LinkNode<T> successor;


    public void reverse() {
        if (this.first == null || this.last == null) {
            return;
        }
        // 获取当前头节点
        LinkNode<T> oldHead = first;
        // 传入头节点进行反转
        LinkNode<T> newLast = this.reverse4Traverse(first);
//        LinkNode<T> newLast = this.reverse(first);
        // 最新的头节点赋值
        first = newLast;
        // 对尾节点赋值
        last = oldHead;

    }

    public void reverse(int m, int n) {
        if (this.first == null || this.last == null) {
            return;
        }
        // 获取当前头节点
        LinkNode<T> oldHead = first;
        // 传入头节点进行反转
        LinkNode<T> newLast = this.reverseM2N(first, m, n);
    }

    public void reverse(int n) {
        if (this.first == null || this.last == null) {
            return;
        }

        // 传入头节点进行反转
        LinkNode<T> newLast = this.reverseN(first, n);
        first = newLast;
    }



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
     * 递归反转链表
     * @param head 头节点
     * @return 返回反转后的头节点
     */
    private LinkNode<T> reverse(LinkNode<T> head) {
        // 没有next节点了，不需要反转
        if (head.next == null) {
            return head;
        }

        // 递归，返回最后一个元素
        LinkNode<T> last = reverse(head.next);
        // 将当先元素的下一个元素 next指向当前元素，实现反转
        head.next.next = head;
        // 断开当前元素的next指针
        head.next = null;
        return last;
    }


    /**
     * 反转前N个节点
     * @param head 头节点
     * @param n n
     * @return 最新的头节点
     */
    private LinkNode<T> reverseN(LinkNode<T> head, int n) {

        if (n == 1) {
            // 记录n+1个节点
            successor = head.next;
            return head;
        }
        // 反转 head.next为起点的前n-1个节点
        LinkNode<T> last = reverseN(head.next, n - 1);
        // 指针反转
        head.next.next = head;
        // 新的为节点指向最后的节点
        head.next = successor;

        return last;
    }

    private LinkNode<T> reverseM2N(LinkNode<T> head, int m, int n) {
        // 当m为1时，直接反转前n个节点
        if (m == 1) {
            return reverseN(head, n);
        }
        // 反转 next的 m-1 到 n-1   直到找到某个m对应=next为1的位置
        LinkNode<T> last = reverseM2N(head.next, m - 1, n - 1);
        head.next = last;

        return head;
    }


    /**
     * 遍历反转链表
     * @param head 头节点
     * @return 返回发转后的头节点
     */
    private LinkNode<T> reverse4Traverse(LinkNode<T> head) {
        if (head.next == null) {
            return head;
        }

        LinkNode<T> next = head.next;
        head.next = null;
        LinkNode<T> nextNext ;
        while (true) {

            nextNext = next.next;
            next.next = head;

            if (nextNext == null) {
                break;
            }
            head = next;
            next = nextNext;
        }
        return next;
    }


    private LinkNode<T> reverseM2N4Traverse(LinkNode<T> head, int m, int n) {

        if (m == n) {
            return head;
        }
        LinkNode<T> newHead = head;
        LinkNode<T> mNode = null;
        LinkNode<T> nNodeNext = null;
        for (int i = 1; i <= n; i++) {

            if (m == i) {
                mNode = newHead;
            }
            if (n == i) {
                nNodeNext = newHead.next;
                newHead.next = null;
                break;
            }
            newHead = newHead.next;
        }

        LinkNode<T> last = reverse4Traverse(mNode);

        mNode.next = last;

        LinkNode<T> next = last.next;
        while (true) {
            if (next == null) {
                next.next = nNodeNext;
                break;
            }
            next = next.next;
        }


        return head;
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
        int a = 1;
        int b = 2;
        int c = 3;
        int d = 4;
        int e = 5;

        SinglyLinkedList list = new SinglyLinkedList<Integer>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        list.add(e);

        list.traversal();
//        list.reverse();
//        list.reverse(3);
        list.reverse(2, 4);
        list.traversal();

    }

}
