package com.zjn.bfs;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 752 打开转盘锁
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 *
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 *
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 *
 */
public class OpenLock {


    public int openLock(String[] deadends, String target) {
        // 队列
        Queue<String> queue = new LinkedList();
        // 是否已经走过了
        Set<String> visited = new HashSet<>();

        Set<String> dead = new HashSet<>();
        for (String deadend : deadends) {
            dead.add(deadend);
        }
        // 初始元素
        queue.offer("0000");
        visited.add("0000");
        int step = 0;
        while (!queue.isEmpty()) {

            int size = queue.size();
            for (int i = 0; i < size; i++) {

                String poll = queue.poll();
                if (dead.contains(poll)) {
                    continue;
                }
                if (poll.equals(target)) {
                    return step;
                }

                // 获取8中可能的情况，加入到队列中
                for (int j = 0; j < 4; j++) {
                    String ps = plusOne(poll, j);
                    if (!visited.contains(ps)) {
                        queue.offer(ps);
                        visited.add(ps);
                    }
                    String ms = minusOne(poll, j);
                    if (!visited.contains(ms)) {
                        queue.offer(ms);
                        visited.add(ms);
                    }
                }
            }

            step ++;
        }
        return -1;
    }

    // 向上拨动一次
    private static String plusOne(String s, int j) {
        char[] chars = s.toCharArray();
        if (chars[j] == '9') {
            chars[j] = '0';
        } else {
            chars[j] += 1;
        }
        return new String(chars);
    }

    // 向下拨动一次
    private static String minusOne(String s, int j) {
        char[] chars = s.toCharArray();
        if (chars[j] == '0') {
            chars[j] = '9';
        } else {
            chars[j] -= 1;
        }
        return new String(chars);
    }



    // 双向BFS优化      使用条件  1.知道target  2.可以从target往回找
    public static int openLock_1(String[] deadends, String target) {

        Set<String> dead = new HashSet<>();
        for (String deadend : deadends) {
            dead.add(deadend);
        }
        Set<String> visited = new HashSet<>();
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        // 开始节点
        q1.add("0000");
        q2.add(target);
        int step = 0;

        while (!q1.isEmpty() && !q2.isEmpty()) {

            // 优化点   优先扩算数据少的
            if (q1.size() > q2.size()) {
                Set<String> t = q1;
                q1 = q2;
                q2 = t;
            }
            // 存储扩散的节点
            Set<String> temp = new HashSet<>();
            for (String s : q1) {
                if (dead.contains(s)) continue;
                // 相交说明了已经找到了最短路径
                if (q2.contains(s)) return step;
                visited.add(s);
                for (int j = 0; j < 4; j++) {
                    String ps = plusOne(s, j);
                    if (!visited.contains(ps)) {
                        temp.add(ps);
                    }
                    String ms = minusOne(s, j);
                    if (!visited.contains(ms)) {
                        temp.add(ms);
                    }
                    System.out.println(ps + "   " + ms);
                }
            }
            // 步数增加
            step ++;
            // 这里temp相当于q1，交换后下一轮扩散的q2，轮循扩散
            q1 = q2;
            q2 = temp;
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] s = {"8888"};
        System.out.println(openLock_1(s, "0009"));

    }



}
