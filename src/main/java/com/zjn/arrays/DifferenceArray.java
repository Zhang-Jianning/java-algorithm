package com.zjn.arrays;

public class DifferenceArray {

    // 差分数组
    private int[] diff;

    // 构建差分数组
    public DifferenceArray(int[] arr) {
        diff = new int[arr.length];
        diff[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            diff[i] = arr[i] - arr[i - 1];
        }
    }


    /* 给闭区间 [i,j] 增加 val（可以是负数）*/
    public void increment(int i, int j, int val) {

        diff[i] += val;
        if (j + 1 < diff.length) {
            diff[j + 1] -= val;
        }
    }

    // 根据差分数组获取原数组
    public int[] result() {
        int[] result = new int[diff.length];
        result[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            result[i] = diff[i] + result[i - 1];
        }
        return result;

    }


    /**
     * 假设有一个长度为n的数组，初始情况下所有的数字均为0，你将会被给出k个更新的操作，其中，每个操作会被表示为一个三元数组 ：【startIndex, endIndex, inc】， 你需要
     * 将子数组A【startIndex...endIndex】（包括startIndex和endIndex）增加inc
     * 请返回k次操作后的数组
     */

    int[] getModArray(int length, int[][] updates) {
        // 初始化数组，默认是为0
        int[] ints = new int[length];
        DifferenceArray differenceArray = new DifferenceArray(ints);

        for (int[] update : updates) {
            differenceArray.increment(update[0], update[1], update[2]);
        }
        return differenceArray.result();

    }


    /**
     * 有n个航班，他们分别从1到n编号
     * 有一份航班预订表，表中第 i 条预定记录 bookings[i] = [i, j, k] 意味着从i到j每个航班预定了k个座位
     * 返回一个长度为n的数组，按航班表好顺序返回每个航班上预定的座位数
     */
    int[] corpFlightBookings(int[][] bookings, int n) {
        // nums 初始化为全 0
        int[] ints = new int[n];
        DifferenceArray differenceArray = new DifferenceArray(ints);
        for (int[] booking : bookings) {
            // 因为n是从1开始 1-n -> 0- n-1   对应的三元组[i, j, k] 区间是[i - 1, j - 1]
            differenceArray.increment(booking[0] - 1, booking[1] - 1, booking[2]);
        }

        return differenceArray.result();
    }


    /**
     * 你是一个开公交车的司机，公交车的最大载客量为 capacity，沿途要经过若干车站，给你一份乘客行程表 int[][] trips，
     * 其中 trips[i] = [num, start, end] 代表着有 num 个旅客要从站点 start 上车，到站点 end 下车，
     * 请你计算是否能够一次把所有旅客运送完毕（不能超过最大载客量 capacity）。
     * 0 <= trips[i][1] < trips[i][2] <= 1000  即 0 <= start < end <= 1000
     */

    boolean carPooling(int[][] trips, int capacity) {
        // 构建1001个  区间是0-1000，即 length：1001
        int[] ints = new int[1001];
        DifferenceArray differenceArray = new DifferenceArray(ints);
        for (int[] trip : trips) {
            differenceArray.increment(trip[1], trip[2], trip[0]);
        }
        int[] result = differenceArray.result();
        for (int i : result) {
            if (i > capacity) {
                return false;
            }
        }

        return true;
    }









}
