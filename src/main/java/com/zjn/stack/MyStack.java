package com.zjn.stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 队列实现栈
 */
public class MyStack {

    private Queue<Integer> queue = new LinkedList();

    private int top_elem = 0;


    /** 添加元素到栈顶 */
    public void push(int x) {
        queue.offer(x);
        top_elem = x;
    }

    /** 返回栈顶元素 */
    public int top() {
        return top_elem;
    }

    /** 删除栈顶的元素并返回 */
    public int pop() {

        int size = queue.size();
        while (size > 1) {
            if (size == 2) {
                top_elem = queue.peek();
            }
            queue.offer(queue.poll());
            size --;
        }

        return queue.poll();
    }


    /** 判断栈是否为空 */
    public boolean isEmpty() {
        return queue.isEmpty();
    }





}
