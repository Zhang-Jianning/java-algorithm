package com.zjn.queue;

import com.zjn.stack.Stack;

/**
 * 栈实现队列
 */
public class MyQueue {

    // 两个栈实现队列
    private Stack s1, s2;

    public MyQueue() {
        s1 = new Stack();
        s2 = new Stack();
    }



    // 从队尾插入元素
    public void push(int x) {
        s1.push(x);
    }


    // 从队列头部移除元素并返回
    public int pop() {
        peek();
        return s2.pop();
    }


    // 返回队头元素
    public int peek() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.top();
    }


    // 队列是否为空
    public boolean iSEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }






}
