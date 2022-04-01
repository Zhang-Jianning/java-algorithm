package com.zjn.stack;
// 去重
public class DuplicateRemoval {


    /**
     * 316  给一个仅包含小写字母的字符串，请你去除字符串中重复的字母，使得每个字母只出现一次，需要保证返回结果的字典序最小
     * （要求不能打乱其他字符串的相对位置）
     *
     * 1081  返回 s 字典序最小的子序列，该子序列包含 s 的所有不同字符，且只包含一次。
     */

    private static String removeDup(String s) {


        char[] chars = s.toCharArray();
        int[] nums = new int[256];
        for (char aChar : chars) {
            nums[aChar] ++;
        }

        // 存放去重的结果
        Stack<Character> stack = new Stack<>();
        // 布尔数组初始值为 false，记录栈中是否存在某个字符
        // 输入字符均为 ASCII 字符，所以大小 256 够用了
        boolean[] arr = new boolean[256];
        for (char aChar : chars) {
            // 计数减去
            nums[aChar] --;
            if (arr[aChar]) {
                continue;
            }
            while(!stack.empty() && stack.peek() > aChar) {
                // 如果后面已经没有该元素，这里不能移除
                if (nums[stack.peek()] == 0) {
                    break;
                }
                // 后面还有该元素，移除元素，清除标记
                arr[stack.pop()] = false;
            }
            // 若不存在，则插入栈顶并标记为存在
            stack.push(aChar);
            arr[aChar] = true;

        }

        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.empty()) {
            stringBuilder.append(stack.pop());
        }
        // 栈中元素插入顺序是反的，需要 reverse 一下
        return stringBuilder.reverse().toString();


    }


    public static void main(String[] args) {

        String s = "cbaaabcdbcssabc";
        System.out.println(removeDup(s));

    }

}
