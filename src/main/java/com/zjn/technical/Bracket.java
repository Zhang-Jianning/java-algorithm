package com.zjn.technical;

import java.util.Stack;

public class Bracket {


    /**
     * 20 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
     *
     * 有效字符串需满足：
     *
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     *
     */
    public boolean isValid(String s) {
        // 使用栈存储
        Stack<Character> stack = new Stack();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar == '(' || aChar == '[' || aChar == '{') {
                stack.push(aChar);
            } else {
                // 判断上一个括号和当前字符是否匹配
                if (!stack.isEmpty() && stack.peek() == convert(aChar)) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        // 必须成对抵消
        return stack.isEmpty();
    }


    /**
     * 921 使括号有效的最少添加
     * 只有满足下面几点之一，括号字符串才是有效的：
     *
     * 它是一个空字符串，或者
     * 它可以被写成AB（A与B连接）, 其中A 和B都是有效字符串，或者
     * 它可以被写作(A)，其中A是有效字符串。
     * 给定一个括号字符串 s ，移动N次，你就可以在字符串的任何位置插入一个括号。
     *
     * 例如，如果 s = "()))" ，你可以插入一个开始括号为 "(()))" 或结束括号为 "())))" 。
     * 返回 为使结果字符串 s 有效而必须添加的最少括号数。
     *
     */
    public int minAddToMakeValid(String s) {
        // 左括号插入次数
        int leftNeed = 0;
        // 右括号插入次数
        int rightNeed = 0;

        for (int i = 0; i < s.length(); i++) {

            // 需要一个右括号
            if (s.charAt(i) == '(') {
                rightNeed++;
            }
            if (s.charAt(i) == ')') {
                // 右括号需求减一
                rightNeed--;
                if (rightNeed == -1) {
                    leftNeed++;
                    rightNeed = 0;
                }
            }
        }
        return leftNeed + rightNeed;
    }


    /**
     * 1541 平衡括号字符串的最小插入次数
     * 给你一个括号字符串s，它只包含字符'(' 和')'。一个括号字符串被称为平衡的当它满足：
     *
     * 任何左括号'('必须对应两个连续的右括号'))'。
     * 左括号'('必须在对应的连续两个右括号'))'之前。
     * 比方说"())"，"())(())))" 和"(())())))"都是平衡的，")()"，"()))" 和"(()))"都是不平衡的。
     *
     * 你可以在任意位置插入字符 '(' 和 ')' 使字符串平衡。
     *
     * 请你返回让 s平衡的最少插入次数。
     *
     */
    public int minInsertions(String s) {
        // 需要操作的次数
        int res = 0;
        // 对右括号的需求次数
        int rightNeed = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // 一个左括号对应两个右括号
                rightNeed += 2;
                // 如果此时右括号不是偶数，说明需要插入一个右括号
                if (rightNeed % 2 == 1) {
                    res++;
                    rightNeed -= 1;
                }
            }

            if (s.charAt(i) == ')') {
                rightNeed--;
                // 这里说明需要插入一个左括号和需要一个右括号
                if (rightNeed == -1) {
                    res++;
                    rightNeed = 1;
                }
            }
        }
        return res + rightNeed;
    }




    private char convert(char c) {
        if (c == ')') return '(';
        if (c == ']') return '[';
        return '{';
    }



}
