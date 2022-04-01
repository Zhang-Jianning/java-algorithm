package com.zjn.technical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 241 为运算符表达式设计优先级
 * 给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
 */


// 算法题利用了分治思想，以每个运算符作为分割点，把复杂问题分解成小的子问题，递归求解子问题，然后再通过子问题的结果计算出原问题的结果。
public class DiffWaysToCompute {

    // 备忘录
    Map<String, List<Integer>> memo = new HashMap<>();

    // 分治思想，不考虑整体，考虑一个运算符时因该怎么拆分，怎么合并
    // 明确函数的定义 ： 该运算符所有可能的结果
    public List<Integer> diffWaysToCompute(String expression) {

        if (memo.containsKey(expression)) {
            return memo.get(expression);
        }

        int n = expression.length();
        List res = new LinkedList();

        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            if (!(c == '-' || c == '+' || c == '*')) {
                continue;
            }
            // 递归计算符号左侧元素可能的所有结果
            List<Integer> left = diffWaysToCompute(expression.substring(0, i));
            // 递归计算符号右侧元素可能的所有结果
            List<Integer> right = diffWaysToCompute(expression.substring(i + 1));

            // 处理结果
            for (Integer le : left) {
                for (Integer ri : right) {
                    if (c == '-') {
                        res.add(le - ri);
                    } else if (c == '+') {
                        res.add(le + ri);
                    } else {
                        res.add(le * ri);
                    }
                }
            }
        }
        // base case
        // 说明没有运算符，只有数字，直接加入结果
        if (res.isEmpty()) {
            res.add(Integer.parseInt(expression));
        }
        memo.put(expression, res);
        return res;
    }


}
