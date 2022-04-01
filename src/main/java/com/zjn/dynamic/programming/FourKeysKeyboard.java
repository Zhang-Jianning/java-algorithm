package com.zjn.dynamic.programming;


import java.util.HashMap;
import java.util.Map;

/**
 * 651 四键键盘
 * 1、A 键：在屏幕上打印一个 A。
 *
 * 2、Ctrl-A 键：选中整个屏幕。
 *
 * 3、Ctrl-C 键：复制选中的区域到缓冲区。
 *
 * 4、Ctrl-V 键：将缓冲区的内容输入到光标所在的屏幕上。
 * 这就和我们平时使用的全选复制粘贴功能完全相同嘛，只不过题目把 Ctrl 的组合键视为了一个键。现在要求你只能进行 N 次操作，请你计算屏幕上最多能显示多少个 A？
 */
public class FourKeysKeyboard {

    static int count;
    static Map<String, Integer> map;
    static int maxA(int N) {
        map = new HashMap<>();
        return dp(N, 0, 0);
    }

    /**
     *
     * @param n 操作次数
     * @param aAmount 屏幕上现有的a数量
     * @param copy 剪贴板中a的数量
     *  return a的数量
     */
    public static int dp(int n, int aAmount, int copy) {
        count ++;
       if (n <= 0) return aAmount;
       String key = n + "," + aAmount + "," + copy;
       if (map.containsKey(key)) return map.get(key);
        // 选择直接输入a
       int a = dp(n - 1, aAmount + 1, copy);
       // Ctrl-A Ctrl-C 一起操作，减少两步， 剪贴板上的数量变成屏幕上的数量
       int b = dp(n - 2, aAmount, aAmount);
        // Ctrl-V ,a的数量增加copy
       int c = dp(n - 1, aAmount + copy, copy);

       int res = Math.max(a, Math.max(b, c));
       map.put(key, res);
       return res;
    }




    // 另一种解法， 动态规划另一种状态转移方程
    // 这里基于一个事实
    // 要使a最多，当n比较小时，一直重复a是最多的， 当n比较大是，是 aa..Ctrl-A Ctrl-C Ctrl-V Ctrl-V...Ctrl-A Ctrl-C Ctrl-V
    static int maxA1(int N) {
        //操作N次，对应的a出现的最大次数
        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            // 选择直接按 a
            dp[i] = dp[i - 1] + 1;
            // 这里是选择使用三连，for循环结束后可以拿出所有使用 Ctrl-A Ctrl-C Ctrl-V的最大值
            for (int j = 2; j < i; j++) {
                // 这里j-2是 Ctrl-A Ctrl-C的起始位置，当前操作是 Ctrl-V， i-j+1是Ctrl-V操作次数
                dp[i] = Math.max(dp[i], dp[j - 2] * (i - j + 1));
            }
        }
        return dp[N];
    }














    public static void main(String[] args) {
        System.out.println(maxA(20));
    }



}
