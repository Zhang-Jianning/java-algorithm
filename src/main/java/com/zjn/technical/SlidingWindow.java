package com.zjn.technical;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 滑动窗口
 */
public class SlidingWindow {


    /**
     * 给定字符串s，t，在字符串s中找出包含t所有字母的最小子串
     * @return  如果s中不存在这样的子串，则返回空字符串""
     *          如果s中存在这样的子串。我们保证他是唯一的答案
     */
    static String midWindow(String s, String t) {

        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        for (char c : tArray) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        // 窗口
        int left = 0, right = 0;
        // 记录窗口中包含need元素的个数
        int valid = 0;

        // 记录满足条件子串的起始索引和长度
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {

            char c = sArray[right];
            right ++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    valid ++;
                }
            }

            while(valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char d = sArray[left];
                left++;

                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        valid --;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }


    /**
     * 给定两个字符串s1和s2，写一个函数来判断s2是否包含s1的排列 即 第一个字符串的排列之一是第二个字符串的子串
     */

    public static boolean checkInclusion (String s, String t) {
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        for (char c : tArray) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        while (right < s.length()) {
            char c = sArray[right];
            right ++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid ++;
                }
            }
            // 长度相等就需要移动left，因为这里要的是完全匹配的子串，不是最小子串
            while (right - left >= t.length()) {

                if (valid == need.size()) {
                    return true;
                }
                char d = sArray[left];
                left ++;
                if (need.containsKey(d)) {
                    if (need.get(d) == window.get(d)) {
                        valid --;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return false;
    }


    /**
     * 给定一个字符串s和一个非空字符串p，找到s中所有是p的字母异位词的子串，返回这些子串的起始索引   字符串只包含小写英文字母，并且字符串s和p的长度都不超过20100
     *   字母异位词指字母相同，排序不同的字符串
     *   不考虑答案输出的顺序
     */

    public static Vector<Integer> findAnagrams(String s, String p) {

        char[] sArray = s.toCharArray();
        char[] pArray = p.toCharArray();
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        for (char c : pArray) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        Vector<Integer> vector = new Vector<>();
        int left = 0, right = 0;
        int valid = 0;

        while (right < s.length()) {
            char c = sArray[right];
            right ++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    valid ++;
                }
            }

            while(right - left >= p.length()) {
                if (valid == need.size()) {
                    vector.add(left);
                }
                char d = sArray[left];
                left ++;
                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        valid --;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return vector;

    }


    /**
     * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度
     */

    public static int length4LongestSubstring(String s) {

        Map<Character, Integer> windowMap = new HashMap();

        char[] sArray = s.toCharArray();

        int left = 0, right = 0;
        int size = 1;
        while (right < s.length()) {
            char c = sArray[right];
            right ++;
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
            while (windowMap.get(c) > 1) {
                char d = sArray[left];
                left ++;
                windowMap.put(d, windowMap.get(d) - 1);
            }
            // 更新最大长度
            size = Math.max(size, right - left);
            System.out.println("right" + right + "left" + left);
        }

        return size;
    }














    public static void main(String[] args) {

        String s = "abcdefgdf,sndf,sfffs,mdf,mf m ";
        String t = "fsfsf";
        boolean b = checkInclusion(s, t);
        System.out.println(b);

    }


}
