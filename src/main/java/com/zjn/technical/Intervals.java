package com.zjn.technical;


import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

// 区间问题
public class Intervals {


    /**
     * 1288 删除覆盖区间
     *
     * 给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
     *
     * 只有当c <= a且b <= d时，我们才认为区间[a,b) 被区间[c,d) 覆盖。
     *
     * 在完成所有删除操作后，请你返回列表中剩余区间的数目。
     *
     * removeCoveredIntervals
     */
    public int removeCoveredIntervals(int[][] intervals) {
        // 先按照第一个元素升序，在按照第二个元素降序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o2[1] - o1[1];
                return o1[0] - o2[0];
            }
        });
        int n = intervals.length;
        int res = 0;
        int left = intervals[0][0];
        int right = intervals[0][1];
        for (int i = 1; i < n; i++) {
            int[] interval = intervals[i];
            int start = interval[0];
            int end = interval[1];
            // 覆盖了
            if (start >= left && end <= right) {
                res++;
            } else {
                // 没有覆盖, 更新区间
                right = end;
                left = start;
            }
        }
        return n - res;
    }


    /**
     * 56 合并区间
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     *
     */
    public int[][] merge(int[][] intervals) {

        // 先升序，后降序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int length = intervals.length;

        int[][] res = new int[length][2];
        int idx = -1;
        for (int[] interval : intervals) {
            // 不相交   start > end
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 相交，更新
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }


    /**
     *
     * 986 区间列表的交集
     *
     * 给定两个由一些 闭区间 组成的列表，firstList 和 secondList ，其中 firstList[i] = [starti, endi] 而secondList[j] = [startj, endj] 。每个区间列表都是成对 不相交 的，并且 已经排序 。
     *
     * 返回这 两个区间列表的交集 。
     *
     * 形式上，闭区间[a, b]（其中a <= b）表示实数x的集合，而a <= x <= b 。
     *
     * 两个闭区间的 交集 是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3] 。
     *
     */

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

        int fl = firstList.length;
        int sl = secondList.length;
        List<int[]> re = new LinkedList<>();
        // 记录两个数组的位置
        int i = 0;
        int j = 0;
        while (i < fl && j < sl) {
            int[] fArr = firstList[i];
            int[] sArr = secondList[j];
            // 相交
            if (fArr[0] <= sArr[1] && fArr[1] >= sArr[0]) {
                int left = Math.max(fArr[0], sArr[0]);
                int right = Math.min(fArr[1], sArr[1]);
                re.add(new int[]{left, right});
            }

            if (fArr[1] < sArr[1]) {
                i++;
            } else {
                j++;
            }
        }
        return re.toArray(new int[0][0]);
    }


    // 另一种写法
    public int[][] intervalIntersection_1(int[][] firstList, int[][] secondList) {

        int fl = firstList.length;
        int sl = secondList.length;
        List<int[]> re = new LinkedList<>();
        // 记录两个数组的位置
        int i = 0;
        int j = 0;
        while (i < fl && j < sl) {
            int[] fArr = firstList[i];
            int[] sArr = secondList[j];
            int left = Math.max(fArr[0], sArr[0]);
            int right = Math.min(fArr[1], sArr[1]);
            // 相交
            if (left <= right) {
                re.add(new int[]{left, right});
            }

            if (fArr[1] < sArr[1]) {
                i++;
            } else {
                j++;
            }
        }
        return re.toArray(new int[0][0]);
    }

}
