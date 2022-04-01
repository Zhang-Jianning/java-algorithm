package com.zjn.backtrack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 22 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */

public class GenerateParenthesis {


    /**
     * 这个解法基于两个事实
     *
     * 1、一个「合法」括号组合的左括号数量一定等于右括号数量，这个很好理解。
     *
     * 2、对于一个「合法」的括号字符串组合 p，必然对于任何 0 <= i < len(p) 都有：子串 p[0..i] 中左括号的数量都大于或等于右括号的数量。
     *
     */
    List<String> res;
    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        LinkedList<String> list = new LinkedList();
        generate(list, n, n);
        return res;
    }


    // 可用的左括号数量为 left 个，可用的右括号数量为 rgiht 个
    private void generate(LinkedList<String> list, int leftCount, int rightCount) {
        if (leftCount > rightCount) return;
        if (leftCount < 0 || rightCount < 0) return;

        // 当所有括号都恰好用完时，得到一个合法的括号组合
        if (leftCount == 0 && rightCount == 0) {
            StringBuilder builder = new StringBuilder();
            for (String s : list) {
                builder.append(s);
            }
            res.add(builder.toString());
            return;
        }
        // 选择左括号
        list.add("(");
        // leftCount - 1
        generate(list, leftCount - 1, rightCount);
        // 撤销选择
        list.removeLast();

        // 选择右括号
        list.add(")");
        // rightCount - 1
        generate(list, leftCount, rightCount - 1);
        // 撤销选择
        list.removeLast();
    }


}
