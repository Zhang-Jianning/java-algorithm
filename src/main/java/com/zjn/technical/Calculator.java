package com.zjn.technical;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 实现计算器
 *
 * 224 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。  只有加减括号
 *
 * 227  只有加减乘除 没有括号
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *
 * 整数除法仅保留整数部分。
 *
 * 你可以假设给定的表达式总是有效的。所有中间结果将在[-231, 231- 1] 的范围内。
 *
 * 772 加减乘除和括号
 *
 * 这里是通解
 */
public class Calculator {


    public int calculator(String s) {
        Queue<Character> queue = new LinkedList();
        // 加入队列
        for (int i = 0; i < s.length(); i++) {
            queue.offer(s.charAt(i));
        }
        int calculator = calculator(queue);
        return calculator;
    }



    public int calculator(Queue<Character> queue) {
        // 存储分离出来的数字
        Stack<Integer> stack = new Stack();
        int num = 0;
        // 符号  这里默认第一个是 +号
        int sign = '+';
        // 遍历
        while (!queue.isEmpty()) {
            // 取出队列首个元素
            Character item = queue.poll();
            // 考虑括号问题，如果是括号，采用递归，减小问题范围
            if (item == '(') {
                num = calculator(queue);
            }
            // 判断是否是数字
            if (isDigit(item)) {
                // 将字符串转化为数字
//                System.out.println(num);
                num = 10 * num + (item - '0');
                System.out.println(num);
            }
            // 不是数字，并排除空格，或者 队列空（代表这是最后一个元素）
            // 括号也会走进来,左括号是从递归返回后更新num，这里再计算， 右括号是递归中进来，计算括号中最好一个数字
            if ((!isDigit(item) && item != ' ') || queue.isEmpty()) {
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        Integer multiplier = stack.pop();
                        stack.push(multiplier * num);
                        break;
                    case '/':
                        Integer divisor = stack.pop();
                        stack.push(divisor / num);
                        break;
                }
                // 更新符号
                sign = item;
                // num重置
                num = 0;
            }
            // 匹配到右括号，推出结束递归
            if (item == ')') {
                break;
            }
        }
        return sum(stack);
    }


    // 判断是否是数字，这里因为只有一个字符传进来，所以可以直接使用这个方法， 否则推荐采用正则"^[-\\+]?[\\d]*$"
    private boolean isDigit(Character c) {
        return Character.isDigit(c);
    }

    private int sum(Stack<Integer> stack) {
        int sum = 0;
        for (Integer integer : stack) {
            sum += integer;
        }
        return sum;
    }



}
