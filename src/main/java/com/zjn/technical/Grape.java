package com.zjn.technical;

import java.util.Arrays;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/14c0359fb77a48319f0122ec175c9ada
 *
 * 有三种葡萄，每种分别有 a,b,c颗。有三个人，第一个人只吃第\text 1,21,2种葡萄，第二个人只吃第\text 2,32,3种葡萄，第三个人只吃第\text 1,31,3种葡萄。
 * 适当安排三个人使得吃完所有的葡萄,并且且三个人中吃的最多的那个人吃得尽量少。
 */
public class Grape {

    private long solution(long a, long b, long c) {
        // 找到最大的

        long[] nums = new long[]{a, b, c};
        Arrays.sort(nums);

        long sum = a + b + c;

        // 不能平分，一个人吃少的两堆，剩下两个平分最多的，向上取整
        if ((nums[0] + nums[1]) * 2 < nums[2]) {
            return (nums[2] + 1) / 2;
        }
        // 可以平分，向上取整
        return (sum + 2) / 2;
    }




}
