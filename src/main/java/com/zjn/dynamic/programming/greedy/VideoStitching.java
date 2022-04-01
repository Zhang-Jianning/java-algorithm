package com.zjn.dynamic.programming.greedy;

import sun.nio.cs.ext.MacHebrew;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1024 视频拼接
 * 你将会获得一系列视频片段，这些片段来自于一项持续时长为time秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
 *
 * 使用数组clips 描述所有的视频片段，其中 clips[i] = [starti, endi] 表示：某个视频片段开始于starti并于endi结束。
 *
 * 甚至可以对这些片段自由地再剪辑：
 *
 * 例如，片段[0, 7]可以剪切成[0, 1] +[1, 3] + [3, 7]三部分。
 * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, time]）。返回所需片段的最小数目，如果无法完成该任务，则返回-1 。
 *
 */
public class VideoStitching {

    public static int videoStitching(int[][] clips, int time) {

        if (time == 0) return 0;
        if (clips.length == 0) {
            if (time == 0) return 0;
            if (time > 0) return -1;
        }

        Arrays.sort(clips, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[]b) {
                // 先按照开始时间升序，开始时间相等，则按照结束时间降序
                if (a[0] < b[0]) {
                    return -1;
                } else if (a[0] > b[0]) {
                    return 1;
                } else {
                    return a[1] < b[1] ? 1 : a[1] == b[1] ? 0 : -1;
                }
            }
        });
        for (int[] clip : clips) {
            System.out.println(Arrays.toString(clip));
        }
        int n = clips.length;
        int end = 0;
        int nextEnd = 0;
        int count = 0;
        int i = 0;
        while (i < n && clips[i][0] <= end) {
            // 这里使用贪心策略寻找下一个片段，前提是 clips[i][0]要小于当前end，才可以拼接
            while (i < n && clips[i][0] <= end) {
                nextEnd = Math.max(nextEnd, clips[i][1]);
                i++;
            }
            end = nextEnd;
            count ++;
            if (end >= time) return count;
        }
        return -1;
    }

}
