package com.zjn.mathematical.skills;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

/**
 * 372 超级次方
 * 你的任务是计算 ab 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
 *
 * 模幂运算
 * (a * b) % k = (a % k)(b % k) % k
 */
public class SuperPow {

    int base = 1337;
    public int superPow(int a, int[] b) {
        Stack<Integer> stack = new Stack();
        for (int i : b) {
            stack.push(i);
        }
        return superPow(a, stack);
    }
    // https://labuladong.github.io/algo/images/superPower/formula1.png
    public int superPow(int a, Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return 1;
        }
        Integer pop = stack.pop();
        int pow1 = myPow(a, pop);
        int pow2 = myPow(superPow(a, stack), 10);
        return (pow1 * pow2) % base;

    }

    private int myPow(int a, int n) {
        a %= base;
        int res = 1;
        for (int i = 0; i < n; i++) {
            res *= a;
            res %= base;
        }
        return res;
    }


    // 高效幂运算
    // https://labuladong.github.io/algo/images/superPower/formula2.png
    private int myPow_1(int a, int n) {
        if (n == 0) return 1;
        a %= base;
        if (n % 2 == 0) {
            // 偶数
            int pow = myPow_1(a, n / 2);
            return (pow * pow) % base;
        } else {
            return (myPow_1(a, n - 1) * a) % base;

        }
    }


}
