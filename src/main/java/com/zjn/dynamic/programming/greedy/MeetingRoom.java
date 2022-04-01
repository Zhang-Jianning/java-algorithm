package com.zjn.dynamic.programming.greedy;

import java.util.Arrays;

/**
 * 253 会议室
 * 给你输入若干形如 [begin, end] 的区间，代表若干会议的开始时间和结束时间，请你计算至少需要申请多少间会议室。
 * int minMeetingRooms(int[][] meetings);
 * 比如给你输入 meetings = [[0,30],[5,10],[15,20]]，算法应该返回 2，因为后两个会议和第一个会议时间是冲突的，至少申请两个会议室才能让所有会议顺利进行。
 */
public class MeetingRoom {

    // 这里是利用差分数组
    static int minMeetingRooms(int[][] meetings) {
        int max = 0;
        for (int[] meeting : meetings) {
            for (int i : meeting) {
                max = Math.max(max, i);
            }
        }
        // 这里构建一个差分数组，利用差分数组，来计算出某个点的最多重叠区间
        // 默认所有的数组元素都是0，构建的差分数组也都是0
        int[] diff = new int[max + 1];

        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];
            // 这里+1是为了保证某个整点比如[0,5][5,10]可以公用会议室
            diff[start + 1] += 1;
            if (end >= diff.length) {
                continue;
            }
            // 这里是end而不是end+1是为了保证某个整点比如[0,5][5,10]可以公用会议室
            diff[end] -= 1;
        }

        // 还原出原数组
        int[] result = new int[max + 1];
        result[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            result[i] = result[i - 1] + diff[i];
        }
        // 获取最大值，即可获得某个时间点的重叠次数
        int res = 0;
        for (int i : result) {
            res = Math.max(res, i);
        }
        return res;
    }


    // 优化解法  差分数组的衍生解法
    static int minMeetingRooms_1(int[][] meetings) {

        int n = meetings.length;
        int[] start = new int[n];
        int[] end = new int[n];

        // 把入参中的每个区间开始和结束时间分别存储
        for (int i = 0; i < n; i++) {
            start[i] = meetings[i][0];
            end[i] = meetings[i][1];
        }
        // 对区间进行投影，就相当于对每个区间的起点和终点分别进行排序
        // 排序后所有的开始和结束时间点准确的对应到了横轴上
        Arrays.sort(start);
        Arrays.sort(end);

        int res = 0;
        int count = 0;
        // 双指针，分别指向数组中目前扫面到的位置
        int i = 0, j = 0;
        while (i < n && j < n) {
            if (start[i] < end[j]) {
                count++;
                i++;
            } else {
                count--;
                j++;
            }
            res = Math.max(res, count);
        }
        return res;
    }




    static int minMeetingRooms_2(int[][] meetings) {
        int n = meetings.length;
        int[] begin = new int[n];
        int[] end = new int[n];
        for(int i = 0; i < n; i++) {
            begin[i] = meetings[i][0];
            end[i] = meetings[i][1];
        }
        Arrays.sort(begin);
        Arrays.sort(end);

        // 扫描过程中的计数器
        int count = 0;
        // 双指针技巧
        int res = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (begin[i] < end[j]) {
                // 扫描到一个红点
                count++;
                i++;
            } else {
                // 扫描到一个绿点
                count--;
                j++;
            }
            // 记录扫描过程中的最大值
            res = Math.max(res, count);
        }

        return res;
    }


    public static void main(String[] args) {
        int [][] a = {{0,30},{5,10},{15,20}};
        System.out.println(minMeetingRooms(a));
    }


}
