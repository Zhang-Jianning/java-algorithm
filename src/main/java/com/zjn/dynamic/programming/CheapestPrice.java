package com.zjn.dynamic.programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 787 k站中转内最便宜的航班
 *
 * 有 n 个城市通过一些航班连接。给你一个数组flights ，其中flights[i] = [fromi, toi, pricei] ，表示该航班都从城市 fromi 开始，以价格 pricei 抵达 toi。
 *
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k站中转的路线，使得从 src 到 dst 的 价格最便宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。
 *
 *
 * 1 <= n <= 100
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * flights[i].length == 3
 * 0 <= fromi, toi < n
 * fromi != toi
 * 1 <= pricei <= 104
 * 航班没有重复，且不存在自环
 * 0 <= src, dst, k < n
 * src != dst
 *
 */
public class CheapestPrice {

    Map<Integer, List<Integer[]>> nodes;
    int start;
    int[][] memo;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        start = src;
        nodes = new HashMap<>();
        // n个如果没有0，需要用n+1个位置，k这里是下面会++，所以是+2
        memo = new int[n + 1][k + 2];
        for (int[] ints : memo) {
            Arrays.fill(ints, -666);
        }

        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];
            Integer[] arr = {from, price};
            nodes.putIfAbsent(to, new ArrayList<>());
            nodes.get(to).add(arr);
        }
        // 将中转站个数转化成边的条数
        return find(dst, k + 1);

    }

    // 定义：从 src 出发，k 步之内到达 dst 的最短路径权重
    public int find(int dst, int k) {
        if (dst == start) return 0;
        if (k <= 0) return -1;
        if (memo[dst][k] != -666) return memo[dst][k];
        int res = Integer.MAX_VALUE;
        List<Integer[]> items = nodes.get(dst);
        if (items != null) {
            for (Integer[] item : items) {
                Integer start = item[0];
                Integer price = item[1];
                int subProblem = find(start, k - 1);
                if (subProblem != -1) {
                    res = Math.min(res, subProblem + price);
                }
            }
        }
        memo[dst][k] = res == Integer.MAX_VALUE ? -1 : res;
        return memo[dst][k];
    }




}
