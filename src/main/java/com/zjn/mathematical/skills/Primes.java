package com.zjn.mathematical.skills;

import java.util.Arrays;

/**
 * 204 计算质数
 * 给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。
 *
 * 一个数如果只能被 1 和它本身整除，那么这个数就是质数
 */
public class Primes {

    // 筛选法
    public int countPrimes(int n) {

        boolean[] arr = new boolean[n];
        Arrays.fill(arr, true);
        for (int i = 2; i * i < n; i++) {
            if (arr[i]) {
                for (int j = i * i; j < n; j += i) {
                    arr[j] = false;
                }
            }
        }
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (arr[i]) res++;
        }
        return res;
    }

    // 判断是否是质数
    private boolean isPrime(int n) {
        // 从2开始，
        for (int i = 2; i * i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
