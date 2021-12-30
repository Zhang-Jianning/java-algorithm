package com.zjn.stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 单调队列  「单调队列」这个数据结构可以解决滑动窗口相关的问题
 */
public class MonotonicQueue {

    //队列
    private LinkedList<Integer> que = new LinkedList();


    /**
     * 队尾添加元素
     * @param element element
     */
    public void push(Integer element) {
        // 如果last小于当前元素，则移除last，该队列最终 对首->队尾 是 从大到小的
        while(!que.isEmpty() && que.getLast() < element) {
            que.pollLast();
        }
        que.addLast(element);
    }


    /**
     * 从队头移除 element 元素
     */
    public void poll(Integer element) {
        // 如果队首元素是 element，则删除该元素  有可能想删除的**队头**元素被压缩 pop 了，所以这里判断是否相等
        if (que.getFirst() == element) {
            que.pollFirst();
        }
    }

    /**
     * 获取最大的元素
     */
    public Integer max() {
        return que.getFirst();
    }



    /**
     * 给你输入一个数组 nums 和一个正整数 k，有一个大小为 k 的窗口在 nums 上从左至右滑动，请你输出每次窗口中 k 个元素的最大值
     * int[] maxSlidingWindow(int[] nums, int k);
     */

    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue queue = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                // 添加前k-1个元素
                queue.push(nums[i]);
            } else {
                // 进入第一个滑动窗口，元素添加到队列
                queue.push(nums[i]);
                // 获取当前最大的元素
                res.add(queue.max());
                // 将滑动窗口中的第一个元素删除，再进行后续操作
                queue.poll(nums[i - k + 1]);
            }
        }

        int[] resArray = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            resArray[i] = res.get(i);
        }
        return resArray;
    }



}
