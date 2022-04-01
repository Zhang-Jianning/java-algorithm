package com.zjn.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 773  滑动谜题
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用0来表示.
 *
 * 一次移动定义为选择0与一个相邻的数字（上下左右）进行交换.
 *
 * 最终当板board的结果是[[1,2,3],[4,5,0]]谜板被解开。
 *
 * 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 *
 *
 */
public class SlidingPuzzle {

    public static int slidingPuzzle(int[][] board) {

        Queue<String> queue = new LinkedList();
        // 标记是否走过
        Set<String> visited = new HashSet<>();

        // 结果转为字符串
        String end = "123450";
        // 入参也转为字符串，借助数组
        // 将6个元素 每个元素相邻的位置用数组标记出来，是扩散的方式
        int[][] neighbor = {{1,3},{0,2,4},{1,5},{0,4},{1,3,5},{2,4}};

        StringBuilder builder = new StringBuilder();
        for (int[] ints : board) {
            for (int anInt : ints) {
                builder.append(anInt);
            }
        }
        String start = builder.toString();
        // 开始位置和已读
        queue.offer(start);
        visited.add(start);

        System.out.println(start);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                if (poll.equals(end)) {
                    return step;
                }
                // 找到0的位置，然后将和该位置相邻的位置交换，放入队列
                int idx = 0;
                for (; poll.charAt(idx) != '0'; idx++);
                for (int position : neighbor[idx]) {
                    String swap = swap(poll, idx, position);
                    if (!visited.contains(swap)) {
                        queue.offer(swap);
                        visited.add(swap);
                    }
                }
            }
            step++;
        }
        return -1;
    }


    private static String swap(String s, int i, int j) {

        char[] chars = s.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }


    public static void main(String[] args) {
        int[][] i = {{1,2,3},{4,0,5}};
        System.out.println(slidingPuzzle(i));
    }

}
