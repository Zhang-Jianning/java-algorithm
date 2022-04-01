package com.zjn.dynamic.programming;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 514 自由之路
 *
 * 电子游戏“辐射4”中，任务 “通向自由” 要求玩家到达名为 “Freedom Trail Ring” 的金属表盘，并使用表盘拼写特定关键词才能开门。
 *
 * 给定一个字符串ring，表示刻在外环上的编码；给定另一个字符串key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
 *
 * 最初，ring的第一个字符与 12:00 方向对齐。您需要顺时针或逆时针旋转 ring 以使key的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完key中的所有字符。
 *
 * 旋转ring拼出 key 字符key[i]的阶段中：
 *
 * 您可以将ring顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串ring的一个字符与 12:00 方向对齐，并且这个字符必须等于字符key[i] 。
 * 如果字符key[i]已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作1 步。按完之后，您可以开始拼写key的下一个字符（下一阶段）, 直至完成所有拼写。
 *
 *
 */
public class RotateSteps {

    // 记录 ring 中所有相同字符出现的index
    public Map<Character, List<Integer>> map;
    // 备忘录
    public int[][] memo;

    public int findRotateSteps(String ring, String key) {
        int m = ring.length();
        int n = key.length();
        memo = new int[m][n];
        char[] chars = ring.toCharArray();
        map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            map.putIfAbsent(chars[i], new ArrayList<>());
            map.get(chars[i]).add(i);
        }
        return dp(ring, 0, key, 0);
    }

    // 指针指向 ring 的 i 位置元素，寻找key中的j元素，需要的最小操作数
    public int dp(String ring, int i, String key, int j) {
        // j到达终点，没有元素，也不需要寻找了
        if (j == key.length()) return 0;
        if (memo[i][j] != 0) return memo[i][j];
        // 拿到待寻找元素的多个index，循环找到最小的一个操作数字
        char c = key.charAt(j);
        List<Integer> items = map.get(c);
        int length = ring.length();
        int res = Integer.MAX_VALUE;
        for (Integer item : items) {
            // 这里圆盘指针有两种转向，selectOne和selectTwo分别是两种转向需要的操作数
            int selectOne = Math.abs(item - i);
            int selectTwo = length - selectOne;
            int len = Math.min(selectOne, selectTwo);
            int subProblem = dp(ring, item, key, j + 1);
            // 指针转向操作者 + button的1次 + 子问题的操作数
            res = Math.min(res, len + 1 + subProblem);
        }
        memo[i][j] = res;
        return memo[i][j];

    }



}
