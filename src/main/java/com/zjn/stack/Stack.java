package com.zjn.stack;

import java.util.EmptyStackException;
import java.util.Vector;

public class Stack<E> extends Vector<E> {

    // 添加元素
    public E push(E item) {
        addElement(item);

        return item;
    }

    // 弹出元素

    /**
     * 移除栈顶元素并返回
     * @return
     */
    public synchronized E pop() {
        E       obj;
        int     len = size();

        obj = peek();
        removeElementAt(len - 1);

        return obj;
    }

    /**
     * 查看栈顶元素，不移除
     */
    public synchronized E peek() {
        int     len = size();

        if (len == 0)
            throw new EmptyStackException();
        return elementAt(len - 1);
    }

    // 是否空
    public boolean empty() {
        return size() == 0;
    }

    // 栈顶元素
    public E top() {
        return peek();
    }

    public synchronized int search(Object o) {
        int i = lastIndexOf(o);

        if (i >= 0) {
            return size() - i;
        }
        return -1;
    }

}
