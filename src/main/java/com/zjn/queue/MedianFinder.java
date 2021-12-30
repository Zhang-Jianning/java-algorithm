package com.zjn.queue;

import java.util.PriorityQueue;

/**
 * 数据流中的中位数
 */
public class MedianFinder {

    // 小顶堆  存储大元素
    private PriorityQueue<Integer> large;
    // 大顶堆  存储小元素
    private PriorityQueue<Integer> small;


    public MedianFinder() {
        // 小顶堆
        large = new PriorityQueue();
        // 大顶堆
        small = new PriorityQueue<Integer>((a, b) ->  {return b - a;});
    }


    public void addNum(int num) {

        if (small.size() >= large.size()) {
            small.offer(num);
            large.offer(small.poll());
        } else {
            large.offer(num);
            small.offer(large.poll());
        }

    }

    // 计算当前添加的所有数字的中位数
    public double findMedian(){
        // 从元素多的队列中peek第一个元素，偶数去两个元素平均值
        if (large.size() > small.size()) {
            return large.peek();
        } else if (large.size() < small.size()) {
            return small.peek();
        }
        return (large.peek() + small.peek()) / 2.0;

    }




}
