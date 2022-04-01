package com.zjn.technical;

/**
 * 43 字符串相乘
 * 给定两个以字符串形式表示的非负整数num1和num2，返回num1和num2的乘积，它们的乘积也表示为字符串形式。
 *
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 *
 */
public class Multiply {



    public static String multiply(String num1, String num2) {

        int n1 = num1.length();
        int n2 = num2.length();

        // 最多n1 + n2 - 1位数     最大的位置是 i+j+1
        int[] arr = new int[n1 + n2];
        // 遍历每一位数字，计算乘积，在数组中累加
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                // 计算乘积  注意这里是 char，不是int
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int index1 = i + j;
                int index2 = i + j + 1;
                int sum = mul + arr[index2];
                // mul一定是个两位数，加上个位数也一定是两位数，这里处理两位数的个位数
                arr[index2] = sum % 10;
                // 处理sum的十位数, 这里可能会超过9，比如11，12， 但是这个在下次的个位数相加会处理掉
                arr[index1] += sum / 10;
                if (arr[index1] > 9) System.out.println("+++++" + arr[index1]);
            }
        }

        int index = 0;
        // 找到第一个元素不为0的，才是真正的答案
        while (index < arr.length && arr[index] == 0) {
            index++;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = index; i < arr.length; i++) {
            stringBuilder.append(arr[i]);
        }
        // 考虑结果为0的情况
        return stringBuilder.length() == 0 ? "0" : stringBuilder.toString();
    }


    public static void main(String[] args) {
        String a = "24556798082335534435";
        String b = "293443943795";
        multiply(a, b);
    }


}
