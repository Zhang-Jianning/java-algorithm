package com.zjn.technical;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 855 考场就座
 * 在考场里，一排有N个座位，分别编号为0, 1, 2, ..., N-1。
 *
 * 当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。如果有多个这样的座位，他会坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 0 号座位上。)
 *
 * 返回ExamRoom(int N)类，它有两个公开的函数：其中，函数ExamRoom.seat()会返回一个int（整型数据），代表学生坐的位置；函数ExamRoom.leave(int p)代表坐在座位 p 上的学生现在离开了考场。每次调用ExamRoom.leave(p)时都保证有学生坐在座位p上。
 *
 */
public class ExamRoom {
    // 考生分割开的线段用长度为2的数组表示

    // 记录所有分割开的线段
    private TreeSet<int[]> treeSet;
    // 存储以p开头的线段
    private Map<Integer, int[]> startMap;
    // 存储以p为结尾的线段
    private Map<Integer, int[]> endMap;
    // 座位数
    private int N;


    public ExamRoom(int n) {
        this.N = n;
        treeSet = new TreeSet<int[]>((a, b) -> {
            int A = distance(a);
            int B = distance(b);
            if (A == B) {
                // 最大距离相等，让索引小的靠后（因为set取的是last数据，last是最大的），可以获取到编号最小的座位
                return b[0] - a[0];
            }
            // 距离小的在前
            return A - B;
        });
        // 初始化treeSet
        treeSet.add(new int[]{-1, n});
        startMap = new HashMap<>();
        endMap = new HashMap<>();
    }

    public int seat() {
        // 拿到线段最长的
        int[] longest = treeSet.last();
        int start = longest[0];
        int end = longest[1];
        // 判断是否是初始化数据，是否是第一次进入
        int seat;
        if (start == -1) {
            seat = 0;
        } else if (end == N) {
            seat = N - 1;
        } else {
            // 取中间
            seat = (end - start) / 2 + start;
        }
        // 将线段分成两份，存储两份线段
        remInterval(longest);
        addInterval(new int[]{start, seat});
        addInterval(new int[]{seat, end});
        return seat;
    }

    public void leave(int p) {
        // p前面的线段
        int[] beforeInterval = endMap.get(p);
        // p后面的线段
        int[] afterInterval = startMap.get(p);

        remInterval(beforeInterval);
        remInterval(afterInterval);
        // 合并成一个线段
        addInterval(new int[]{beforeInterval[0], afterInterval[1]});


    }

    // 添加一个线段
    private void addInterval(int[] arr) {
        treeSet.add(arr);
        startMap.put(arr[0], arr);
        endMap.put(arr[1], arr);
    }

    // 移除一个线段
    private void remInterval(int[] arr) {
        treeSet.remove(arr);
        startMap.remove(arr[0]);
        endMap.remove(arr[1]);
    }

    // 这里要算线段中点到两端的距离
    private int distance(int[] arr) {
        int start = arr[0];
        int end = arr[1];
        if (start == -1) {
            return end;
        }
        if (end == N) {
            return N - 1 - start;
        }
        return (end - start) / 2;
    }


    public static void main(String[] args) {
        ExamRoom examRoom = new ExamRoom(10);
        examRoom.seat();
        examRoom.seat();
        examRoom.seat();
        examRoom.seat();
        examRoom.leave(4);
    }
}
