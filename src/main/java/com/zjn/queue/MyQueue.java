package com.zjn.queue;

import com.zjn.stack.Stack;

/**
 * 栈实现队列
 */
public class MyQueue {

    // 两个栈实现队列
    private Stack<Integer> s1, s2;

    public MyQueue() {
        s1 = new Stack<Integer>();
        s2 = new Stack<Integer>();
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
        if (s2.empty()) {
            while (!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        return s2.top();
    }


    // 队列是否为空
    public boolean iSEmpty() {
        return s1.empty() && s2.empty();
    }






}
