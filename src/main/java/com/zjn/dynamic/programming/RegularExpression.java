package com.zjn.dynamic.programming;

import java.util.HashMap;
import java.util.Map;

/**
 * 10 正则表达式
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 *1 <= s.length<= 20
 * 1 <= p.length<= 30
 * s只包含从a-z的小写字母。
 * p只包含从a-z的小写字母，以及字符.和*。
 * 保证每次出现字符* 时，前面都匹配到有效的字符
 *
 */
public class RegularExpression {

    Map<String, Boolean> memo;

    public boolean isMatch(String s, String p) {
        memo = new HashMap<>();
        return isMatch(s, 0, p, 0);
    }


    public boolean isMatch(String s, int i, String p, int j) {
        int sl = s.length();
        int pl = p.length();

        if (j == pl) {
            if (i == sl) return true;
            return false;
        }
        // 这里需要看j后面的 * 是否是成对出现的
        if (i == sl) {
            int remain = pl - j;
            // 剩余的元素不是偶数，肯定是匹配不上的
            if (remain % 2 != 0) return false;

//            int ll = 0;
//            for (int r = j; r < pl; r++) {
//                if ("*".charAt(0) == p.charAt(r)) ll ++;
//            }
//            // 剩余元素的格式必须是  x*x*x*
//            if (ll == remain / 2) return true;
//            return false;

            // 检查是否为 x*y*z* 这种形式
            for (int r = j + 1; r < pl; r += 2) {
                if ("*".charAt(0) != p.charAt(r)) {
                    return false;
                }
            }
            return true;
        }

        String key = i + "," + j;
        if (memo.containsKey(key)) return memo.get(key);

        boolean res;
        // 比较 s[i] 和 p[j]
        if (s.charAt(i) == p.charAt(j) || ".".charAt(0) == p.charAt(j)) {

            // 这里有两种情况，x*可能是重复0次或者多次     ab 和 abc* 是重复0次的情况   bbb 和 b* 是重复多次
            if (j < pl - 1 && "*".charAt(0) == p.charAt(j + 1)) {
                // 重复0次，即 s的位置不变，j向后移动两位，继续和s[i]匹配
                // 重复多次，即s的位置向后移动，j不变，继续匹配
                res = isMatch(s, i, p, j + 2) || isMatch(s, i + 1, p, j);
            } else {
            // 这里只有一种情况，就是匹配上了，都向后移动位置
                res = isMatch(s, i + 1, p, j + 1);
            }

        } else {

            // 这里只有匹配0次的情况
            if (j < pl - 1 && "*".charAt(0) == p.charAt(j + 1)) {
                res = isMatch(s, i, p, j + 2);
            } else {
                // 这里只能是不匹配
                res = false;
            }
        }
        memo.put(key, res);
        return res;

    }






}
