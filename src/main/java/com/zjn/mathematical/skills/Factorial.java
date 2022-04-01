package com.zjn.mathematical.skills;

/**
 * 阶乘
 */
public class Factorial {


    /**
     * 172 阶乘后的0
     * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
     *
     * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
     *
     * 末尾有0，则一定是2和5相乘，即求n可以分解成多少个2和5，但是只要是偶数都可以分解成2，所以又转化为可以分解成多少个5
     *
     * 首先5的倍数可以提供1个5
     * 其次5*5的倍数可以额外提供一个5
     * 再其次5*5*5的倍数可以再额外提供一个5
     * ········
     *
     */
    public int trailingZeroes(int n) {
        int res = 0;
        long divisor = 5;
        while (divisor <= n) {
            res += n / divisor;
            divisor *= 5;
        }
        return res;
    }

    // 简写
    public int trailingZeroes_1(int n) {
        int res = 0;
        for (int d = n; d / 5 > 0; d /= 5) {
            res += d / 5;
        }
        return res;
    }


    /**
     * 793 阶乘函数后k个0
     * f(x)是x!末尾是 0 的数量。回想一下x! = 1 * 2 * 3 * ... * x，且 0! = 1。
     *
     * 例如，f(3) = 0，因为 3! = 6 的末尾没有 0 ；而 f(11) = 2，因为 11!= 39916800 末端有 2 个 0 。
     * 给定k，找出返回能满足 f(x) = k的非负整数 x的数量。
     *
     *  0 <= k <= 10^9
     */
    public int preimageSizeFZF(int k) {

        long le = leftVal(k);
        long ri = rightVal(k);
        return (int)(ri - le + 1);
    }


    // 求有k个0的最小值
    private long leftVal(int k) {
        long left = 0, right = Long.MAX_VALUE;
        while (left < right) {
            long mid = left + (right - left) / 2;
            long tar = trailingZeroes(mid);
            if (tar < k) {
                left = mid + 1;
            } else if (tar > k) {
                right = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // 求有k个0的最大值
    private long rightVal(int k) {
        long left = 0, right = Long.MAX_VALUE;
        while (left < right) {
            long mid = left + (right - left) / 2;
            long tar = trailingZeroes(mid);
            if (tar < k) {
                left = mid + 1;
            } else if (tar > k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // 最后是left = mid + 1
        return left - 1;
    }


    public long trailingZeroes(long n) {
        long res = 0;
        for (long d = n; d / 5 > 0; d /= 5) {
            res += d / 5;
        }
        return res;
    }


    // 上下限无限小的解法，
    // 上下限的推测可以查看 https://leetcode-cn.com/problems/preimage-size-of-factorial-zeroes-function/solution/c-ji-jian-er-fen-jie-fa-chao-guo-100-by-op07g/
    public int preimageSizeFZF_1(long k) {
        return (int)val(k);
    }


    private long val(long k) {
        long left = 4 * k, right = 5 * k + 1;
        while (left < right) {
            long mid = left + (right - left) / 2;
            long tar = trailingZeroes(mid);
            if (tar < k) {
                left = mid + 1;
            } else if (tar > k) {
                right = mid;
            } else {
                return 5;
            }
        }
        return 0;
    }




}
