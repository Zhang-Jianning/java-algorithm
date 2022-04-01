package com.zjn.stack;

import java.util.Vector;

// 从栈顶到栈底的元素是严格递增（or递减）
public class MonotonicStack {

    /**
     * 给你一个数组 nums，请你返回一个等长的结果数组，结果数组中对应索引存储着下一个更大元素，如果没有更大的元素，就存 -1。
     * @param nums 数组
     * @return 数组
     */
    public static Vector<Integer> nextGreaterElement(Vector<Integer> nums) {
        // 存储结果
        Vector<Integer> res = new Vector<>();
        res.setSize(nums.size());
        // 栈
        Stack<Integer> stack = new Stack<>();

        for (int i = nums.size() - 1; i >= 0; i--) {

            // 栈顶元素小于当前元素，则删除栈顶元素，这个值不会用到
            while(!stack.empty() && stack.top() < nums.get(i)) {
                stack.pop();
            }
            // 结果，如果栈是空的，则后面的所有元素都小于该元素，否则获取栈顶元素
            // 栈中的元素从栈顶开始，是从小到大，因为while把所有小于的元素剔除了
            res.setElementAt(stack.empty() ? -1 : stack.top(), i);
            stack.push(nums.get(i));
        }
        return res;
    }


    /**
     * 给你一个数组 T，这个数组存放的是近几天的天气气温，你返回一个等长的数组，计算：对于每一天，你还要至少等多少天才能等到一个更暖和的气温；如果等不到那一天，填 0。
     */
    public static Vector<Integer> dailyTemperatures(Vector<Integer> nums) {
        // 存储结果
        Vector<Integer> res = new Vector<>();
        res.setSize(nums.size());
        // 栈
        Stack<Integer> stack = new Stack<>();

        for (int i = nums.size() - 1; i >= 0; i--) {

            // 栈顶元素小于当前元素，则删除栈顶元素，这个值不会用到
            while(!stack.empty() && nums.get(stack.top()) < nums.get(i)) {
                stack.pop();
            }
            // 结果，如果栈是空的，则后面的所有元素都小于该元素，否则获取栈顶元素
            // 栈中的元素从栈顶开始，是从小到大，因为while把所有小于的元素剔除了
            res.setElementAt(stack.empty() ? 0 : stack.top() - i, i);
            stack.push(i);
        }
        return res;
    }



    /**
     * 输入一个数组 [2,1,2,4,3]，你返回数组 [4,2,4,-1,4]。拥有了环形属性，最后一个元素 3 绕了一圈后找到了比自己大的元素 4。
     * 一般是通过 % 运算符求模（余数），来获得环形特效：
     * int[] arr = {1,2,3,4,5};
     * int n = arr.length, index = 0;
     * while (true) {
     *     print(arr[index % n]);
     *     index++;
     * }
     *
     * 对于这种需求，常用套路就是将数组长度翻倍：
     * 我们可以不用构造新数组，而是利用循环数组的技巧来模拟数组长度翻倍的效果
     */
    public static Vector<Integer> nextGreaterElements(Vector<Integer> nums) {
        int n = nums.size();
        Vector<Integer> res = new Vector<>();
        res.setSize(n);
        // 栈
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * n - 1; i >= 0; i--) {
            // 栈顶元素小于当前元素，则删除栈顶元素，这个值不会用到  索引要求模
            while (!stack.empty() && stack.top() < nums.get(i % n)) {
                stack.pop();
            }

            res.setElementAt(stack.empty() ? -1 : stack.top(), i % n);
            stack.push(nums.get(i % n));

        }
        return res;

    }




    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(2);
        vector.add(1);
        vector.add(2);
        vector.add(4);
        vector.add(3);
        Vector<Integer> vector1 = nextGreaterElements(vector);
        System.out.println(vector1);

    }

}
