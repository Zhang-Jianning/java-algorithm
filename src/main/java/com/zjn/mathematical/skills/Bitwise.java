package com.zjn.mathematical.skills;

/**
 *
 */
public class Bitwise {


    public static void main(String[] args) {

        // 大写字母 和 空格 的与运算，可以将大写字母变成小写字母
        char ch = 'A' | ' ';

        // 小写字母和下划线的与运算可以将小写字母变成大写字母
        char c = 'a' & '_';

        // 利用异或操作 ^ 和空格进行英文字符大小写互换
        char cc = 'd' ^ ' ';
        char dd = 'E' ^ ' ';

        System.out.println(ch);
        System.out.println(c);
        System.out.println(cc);
        System.out.println(dd);


        // 利用异或判断两个数字时候是否 异号

        int x = 2, y = -4;
        boolean b = (x ^ y) < 0;
        x = 2; y = 2;
        boolean bb = (x ^ y) < 0;
        System.out.println(b);
        System.out.println(bb);



        // 不用临时变量交换两个数

        int m = 3, n = 8;
        m ^= n;
        n ^= m;
        m ^= n;
        System.out.println(m + "  " + n);


        /**
         * 注意：   反码和取反是不一样的操作
         * 正数原反补码相同，负数的反码为原码除符号位以外取反
         *
         * 按位取反操作符~是对一个数的存储态(补码)进行取反，包含符号位
         *
         * 负数的反码 为 负数原码除符号位以外取反
         * 负数取反 先将初始数值转换成二进制数，再取得二进制数的补码，之后对补码的每一位取反
         *
         * 在计算机中，运算都是通过补码进行计算的
         */

        // 正数的 原码、反码、补码都是本身
        // 负数在计算机中的表示计算方法有两种，一是正数 取反 + 1 二是 负数原码除符号位取反 + 1
        // 正数 取反（正数取反并不是反码，正数的反码是本身，这里是能说是正数取反的结果） +1 得到 负数的补码

        // 这里是加1   ～是取反，包含符号位， - 是取反（包含符号位）后+1
        int u = 3;
        u = -~u;
        System.out.println(u);


        // 这里是 -1， -u为 u取反+1， 最后在取反
        u = ~-u;
        System.out.println(u);

        // ~1 的值是 -2       1开头的二进制取反 ，符号位是保留的
        System.out.println(~1);


        // n & (n - 1) 可以用来消除n二进制中的最后一个1
        int w = 9;
        int ww = w & (w - 1);
        System.out.println(ww);

        System.out.println(Integer.toBinaryString(4));
        System.out.println(Integer.toBinaryString(-4));

        System.out.println(Integer.toBinaryString(~4));
        System.out.println(Integer.toBinaryString(-~4));

        System.out.println(~4);

    }


    /**
     * 191 位1的个数
     * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
     */
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            // 消除最后一位1，并计数
            n = n & (n - 1);
            res++;
        }
        return res;
    }


    /**
     * 231 2的幂
     * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
     *
     * 如果存在一个整数 x 使得n == 2x ，则认为 n 是 2 的幂次方。
     *
     */

    // 如果是2的幂次，则二进制中只有一个 1
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }


    /**
     * 136 只出现一次的数字
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * 说明：
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     */

    // a^a=0  a^0=a;
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }


    /**
     *  268 丢失的数字
     *
     *给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
     *
     * 数组排个序，然后遍历一遍
     * HashSet 把数组里出现的数字都储存下来，再遍历 [0,n] 之间的数字
     * 等差数列求和公式。
     */
    // 等差数列求和公式。 求和公式：(首项 + 末项) * 项数 / 2
    public int missingNumber(int[] nums) {
        int n = nums.length;
        long sum = (0 + n) * (n + 1) / 2;

        for (int num : nums) {
            sum -= num;
        }
        return (int)sum;
    }

    // 异或的技巧，索引和元素异或
    public int missingNumber_1(int[] nums) {
        int n = nums.length;
        int res = 0;
        // 数组的长度是n，数组中有n个元素，元素是 0-n，有n + 1个元素，遍历时没有遍历到n这个元素，这里先和n异或
        // 相当于数组中多了一个长度，元素是0
        res ^= n;
        for (int i = 0; i < n; i++) {
            res ^= i ^ nums[i];
        }
        return res;
    }
}
