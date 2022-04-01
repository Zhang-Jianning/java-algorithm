package com.zjn.dynamic.programming.greedy;

/**
 * 134 加油站
 * 在一条环路上有 n个加油站，其中第 i个加油站有汽油gas[i]升。
 *
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1个加油站需要消耗汽油cost[i]升。你从其中的一个加油站出发，开始时油箱为空。
 *
 * 给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 *
 */
public class CompleteCircuit {


    // 暴力解法，这里分别以每个点为起点，遍历所有情况，看时候可以走到终点
    public int canCompleteCircuit(int[] gas, int[] cost) {

        int n = gas.length;
        int start = -1;
        for (int i = 0; i < n; i++) {
            // 以i为起点，th为当前的汽油量
            int th = 0;
            for (int j = 0; j < n; j++) {
                int step = (i + j) % n;
                th = th + gas[step] - cost[step];
                if (th < 0) {
                    break;
                }
                if (j == n - 1) {
                    start = i;
                    return start;
                }
            }
        }

        return start;
    }




    // 这里是计算sum，拿到sum最低点，如果有解，最低点就是开始的点   时间复杂度 O(n)
    public int canCompleteCircuit_1(int[] gas, int[] cost) {

        int n = gas.length;
        int sum = 0;
        int minSum = 0;
        // 这里初始位置为0，如果后续startIndex没有更新，说明0就是最低点
        int startIndex = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + gas[i] - cost[i];
            if (sum < minSum) {
                minSum = sum;
                // 这里cost[i]是从i到i+1消耗的油量，则开始点 应该是i+1
                startIndex = i + 1;
            }
        }
        if (sum < 0) return -1;
        return startIndex == n ? 0 : startIndex;
    }



    public int canCompleteCircuit_2(int[] gas, int[] cost) {

        int n = gas.length;
        // 先拿到总数，判断时候有解
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + gas[i] - cost[i];
        }
        if (sum < 0) return -1;

        // 这里基于一个事实 如果选择站点 i 作为起点「恰好」无法走到站点 j，那么 i 和 j 中间的任意站点 k 都不可能作为起点。
        int start = 0;
        int tank = 0;
        for (int i = 0; i < n; i++) {
            tank = tank + gas[i] - cost[i];
            // 这里无法从start走到i，说明start-i都不是起点，重置tank和start
            if (tank < 0) {
                tank = 0;
                start = i + 1;
            }
        }

        return start == n ? 0 : start;
    }
}
