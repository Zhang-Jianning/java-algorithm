package com.zjn.technical;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 392 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 进阶：
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 */
public class Subsequence {

    // 使用双指针判定
    public boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            char c = t.charAt(j);
            if (c == s.charAt(i)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }



    // 另一种解法
    public boolean isSubsequence_1(String s, String t) {
        boolean[] subsequence = isSubsequence(new String[]{s}, t);
        return subsequence[0];
    }
    // 进阶
    public boolean[] isSubsequence(String[] sArr, String t) {

        // 记录所有字符出现的位置
        ArrayList<Integer>[] index = new ArrayList[256];
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (index[c] == null) {
                index[c] = new ArrayList<>();
            }
            index[c].add(i);
        }

        boolean[] res = new boolean[sArr.length];
        for (int m = 0; m < sArr.length; m++) {
            String s = sArr[m];
            int flag = 0;
            boolean b = true;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                ArrayList<Integer> position = index[c];
                if (position == null) {
                    b = false;
                    break;
                }
                // 二分查找最左侧的边界
                int left = 0, right = position.size() - 1;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    Integer p = position.get(mid);
                    if (p >= flag) {
                        right = mid - 1;
                    } else if (p < flag) {
                        left = mid + 1;
                    }
                }
                // left是size，说明没有找到
                if (left >= position.size()) {
                    b = false;
                    break;
                }
                // 更新此时t上指针的位置， left是position的index，根据index拿到对应的位置
                flag = position.get(left) + 1;
            }
            res[m] = b;

        }
        return res;
    }



}
