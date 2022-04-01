package com.zjn.stack;

import java.util.HashMap;

/**
 * class FreqStack {
 *      最大频率栈
 *     // 在栈中加入一个元素 val
 *     public void push(int val) {}
 *
 *     // 从栈中删除并返回出现频率最高的元素
 *     // 如果频率最高的元素不止一个，
 *     // 则返回最近添加的那个元素
 *     public int pop() {}
 * }
  */

public class MaxFreqStack {

    // 元素对应的频次
    private HashMap<Integer, Integer> vf ;

    // 频率对应的所有元素   在栈中，并且可以pop出最近添加的元素
    private HashMap<Integer, Stack<Integer>> fs;

    // 最大的频次
    private int maxFreq;


    public MaxFreqStack() {
        this.vf = new HashMap<>();
        this.fs = new HashMap<>();
        this.maxFreq = 0;
    }

    public void push(int val) {
        // 获取当前频率，没有默认频率是0+1
        int freq = vf.getOrDefault(val, 0) + 1;
        // 更新val对应的频率
        vf.put(val, freq);
        // 如果栈不存在，给默认值
        fs.putIfAbsent(freq, new Stack<>());
        // 维护该频率下的栈数据     // 下面的频次不需要移除该元素，pop时也不需要在下面频次添加该元素
        fs.get(freq).push(val);
        // 维护最大值
        maxFreq = Math.max(maxFreq, freq);
    }

    public int pop() {
        // 得到最高频率最近放入的元素
        int v = fs.get(this.maxFreq).pop();
        // 获取当前频率
        int freq = vf.get(v) - 1;
        // 更新频率
        vf.put(v, freq);
        // 如果最高频率对应的栈是空的，则维护 maxFreq
        if (fs.get(this.maxFreq).empty()) {
            this.maxFreq --;
        }
        return v;
    }






}
