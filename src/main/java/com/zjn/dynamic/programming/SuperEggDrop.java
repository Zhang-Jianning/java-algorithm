package com.zjn.dynamic.programming;

import java.util.HashMap;
import java.util.Map;

/**
 * 884 你面前有一栋从 1 到N共N层的楼，然后给你K个鸡蛋（K至少为 1）。现在确定这栋楼存在楼层0 <= F <= N，在这层楼将鸡蛋扔下去，鸡蛋恰好没摔碎（高于F的楼层都会碎，低于F的楼层都不会碎）。现在问你，最坏情况下，你至少要扔几次鸡蛋，才能确定这个楼层F呢？
 */
public class SuperEggDrop {


    Map<String, Integer> memo;
    public int superEggDrop(int k, int n) {
        memo = new HashMap<>();
        return dp(k, n);

    }


    public int dp(int k, int n) {
        if (n == 0) return 0;
        if (k == 1) return n;
        String key = k + "," + n;
        if (memo.containsKey(key)) return memo.get(key);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {

            res = Math.min(res, Math.max(dp(k - 1, i - 1),
                                            dp(k, n - i))
                                            + 1);
        }
        memo.put(key, res);
        return memo.get(key);
    }


    // 配合使用二分查找法解决，快速定位准确的楼层
    static Map<String, Integer> memo1;
    public static int superEggDrop1(int k, int n) {
        memo1 = new HashMap<>();
        return dp1(k, n);

    }


    public static int dp1(int k, int n) {
        // 鸡蛋个数1，只能线性遍历，最坏的结果是n
        if (k == 1) return n;
        if (n == 0) return 0;
        String key = k + "," + n;
        if (memo1.containsKey(key)) return memo1.get(key);

        int res = Integer.MAX_VALUE;
        int left = 1, right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int broken = dp1(k - 1, mid - 1);
            int notBroken = dp1(k, n - mid);
            // 这里并不一定能走到相等的点，例如 输入 2，2, 所以每次都需要去取最大值中的最小值
            if (broken > notBroken) {
                right = mid - 1;
                res = Math.min(res, broken + 1);
            }else{
                left = mid + 1;
                res = Math.min(res, notBroken + 1);
            }
        }
        memo1.put(key, res);
        return res;
    }


    public static void main(String[] args) {
        superEggDrop1(2, 2);
    }



    // 换一种定义
    // 复杂度 O(KN)
    public static int superEggDrop2(int k, int n) {

        // dp[k][m] 代表 k层扔鸡蛋，最多扔m次，最坏情况下，最多测试的楼层高度
        int[][] dp = new int[k + 1][n + 1];

        // 这里我只要知道，m取什么值时，dp对应的数值和给定的楼层相等，即可确认此时的m是所求的值
        int m = 0;
        while(dp[k][m] < n) {
            m ++;
            for (int i = 1; i <= k; i++) {
                // 无论你在哪层楼扔鸡蛋，鸡蛋只可能摔碎或者没摔碎，碎了的话就测楼下，没碎的话就测楼上
                // 无论你上楼还是下楼，总的楼层数 = 楼上的楼层数 + 楼下的楼层数 + 1（当前这层楼）
                dp[i][m] = dp[i - 1][m - 1] + dp[i][m - 1] + 1;
            }
        }
        return m;

    }


}
