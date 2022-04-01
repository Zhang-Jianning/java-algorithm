package com.zjn.dynamic.programming.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class EraseOverlapIntervals {


    /**
     * 435 无重叠区间
     * 给定一个区间的集合intervals，其中 intervals[i] = [starti, endi]。返回 需要移除区间的最小数量，使剩余区间互不重叠。
     * 这里[1,2][2,3]不算重叠
     */
    // 这里的贪心思想是先选择结束时间最早的，提出有交集的数据，然后选择下一个
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        // (x < y) ? -1 : ((x == y) ? 0 : 1)
        // 这里讲所有二阶数组按照结束位置从小到大排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 这里可能有溢出问题，不能直接相加相减
                return o1[1] < o2[1] ? -1 : o1[1] == o2[1] ? 0 : 1;
            }
        });

        int res = 1;
        int x = intervals[0][1];
        for (int[] interval : intervals) {

            int startTime = interval[0];
            if (startTime < x) continue;
            res ++;
            x = interval[1];

        }

        // 这个算法返回的是移除区间的最小数量， res是不相交的最大数量
        return intervals.length - res;
    }


    /**
     * 452 用最少的箭引爆气球
     * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
     *
     * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
     *
     * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
     *
     *    ****这里相邻的也算重叠****
     *    核心思想和上述方法一致，都是寻找不重叠的最大的区间数量，就需要几支箭
     */
    public int findMinArrowShots(int[][] points) {

        if (points.length == 0) return 0;

        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 结束位置升序排列   这里要注意溢出，不能直接相加相减
                return o1[1] < o2[1] ? -1 : o1[1] == o2[1] ? 0 : 1;
            }
        });

        // 最开始的end坐标
        int x = points[0][1];
        int res = 1;
        for (int[] point : points) {
            // 开始位置和x相交，说明是重叠的，跳过，否则不重叠区间+1，更新x
            if (point[0] <= x) continue;
            res ++;
            x = point[1];
        }

        return res;
    }

}
