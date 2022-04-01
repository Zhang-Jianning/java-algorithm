package com.zjn.technical;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * 常数时间插入、删除和获取随机元素
 *
 *实现RandomizedSet 类：
 *
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 *
 * 示例：
 *
 * 输入
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * 输出
 * [null, true, false, true, 2, true, false, 2]
 *
 * 解释
 * RandomizedSet randomizedSet = new RandomizedSet();
 * randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
 * randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
 * randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
 * randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
 * randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
 * randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
 * randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
 *
 * 提示：
 *
 * -231 <= val <= 231 - 1
 * 最多调用 insert、remove 和 getRandom 函数 2 * 105 次
 * 在调用 getRandom 方法时，数据结构中 至少存在一个 元素
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1
 *
 */
public class RandomizedSet {

    private Vector<Integer> nums;
    private Map<Integer, Integer> map;

    public RandomizedSet() {
        map = new HashMap();
        nums = new Vector();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        int length = nums.size();
        map.put(val, length);
        nums.add(val);
        return true;
    }


    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        Integer index = map.get(val);
        Integer last = nums.get(nums.size() - 1);
        nums.set(index, last);
        nums.removeElementAt(nums.size() - 1);
        map.put(last, index);
        map.remove(val);
        return true;
    }


    public int getRandom() {
        return nums.get(new Random().nextInt(nums.size()));
    }

    public static void main(String[] args) {
//        RandomizedSet randomizedSet = new RandomizedSet();
//        boolean insert = randomizedSet.insert(1);
//        System.out.println(insert);
//        System.out.println(randomizedSet.map + "---" + randomizedSet.nums);
//        boolean insert1 = randomizedSet.insert(2);
//        System.out.println(insert1);
//        System.out.println(randomizedSet.map + "---" + randomizedSet.nums);
//        randomizedSet.insert(3);
//
//        boolean remove = randomizedSet.remove(1);
//        System.out.println(remove);
//        System.out.println(randomizedSet.map + "---" + randomizedSet.nums);
//
//        System.out.println(randomizedSet.getRandom());


//        Vector<Integer> vector = new Vector<>();
//        vector.add(4);
//        vector.add(1);
//        vector.add(2);
//        Solution solution = new Solution(6, vector);
//        System.out.println(solution.indexMap);
//        System.out.println(solution.pick());

        int[] ints = {1, 3, 2, 1};
        RandomSolution randomSolution = new RandomSolution(ints);
//        System.out.println(Arrays.toString(randomSolution.preSum));

        for (int i = 0; i < 10; i++) {
            System.out.println(randomSolution.pickIndex());
        }

    }


    /**
     * 给你输入一个正整数 N，代表左闭右开区间 [0,N)，再给你输入一个数组 blacklist，其中包含一些「黑名单数字」，且 blacklist 中的数字都是区间 [0,N) 中的数字。
     * class Solution {
     * public:
     *     // 构造函数，输入参数
     *     Solution(int N, vector<int>& blacklist) {}
     *
     *     // 在区间 [0,N) 中等概率随机选取一个元素并返回
     *     // 这个元素不能是 blacklist 中的元素
     *     int pick() {}
     * };
     *
     * pick 函数中应该尽可能少调用随机数生成函数 rand()
     *
     *  核心思想是把 黑名单元素映射到非黑名单的index上
     */
    static class Solution{

        private int size;
        private Map<Integer, Integer> indexMap;


        public Solution(int N, Vector<Integer> blackList) {
            this.size = N - blackList.size();
            indexMap = new HashMap<>();
            int lastIndex = N - 1;

            Map<Integer, Integer> map = blackList.stream().collect(Collectors.toMap(v -> v, v -> 0, (a, b) -> a));

            for (Integer integer : blackList) {

                // 如果 blacklist 中的黑名单数字本身就存在区间 [size - 1, N) 中，那么就没必要在 mapping 中建立映射
                if (integer >= size) {
                    continue;
                }

                // 使用map效率高
//                while (blackList.contains(lastIndex)) {
//                    lastIndex --;
//                }
                // 我们将黑名单中的 b 映射到 last，但是我们能确定 last 不在 blacklist 中吗
                // 在对 mapping[b] 赋值时，要保证 last 一定不在 blacklist 中
                while (map.containsKey(lastIndex)) {
                    lastIndex --;
                }

                System.out.println(integer + "  " + lastIndex);
                indexMap.put(integer, lastIndex);
                lastIndex --;

            }

        }

        /**
         * 在区间 [0,N) 中等概率随机选取一个元素并返回
         */
        int pick() {
            // 随机选取一个索引 即 val
            int val = new Random().nextInt(size);
            // 这个索引命中了黑名单，
            // 需要被映射到其他位置
            if (indexMap.containsKey(val)) {
                return indexMap.get(val);
            }
            return val;
        }


    }


    /**
     * 给你一个 下标从 0 开始 的正整数数组w ，其中w[i] 代表第 i 个下标的权重。
     *
     * 请你实现一个函数pickIndex，它可以 随机地 从范围 [0, w.length - 1] 内（含 0 和 w.length - 1）选出并返回一个下标。选取下标 i的 概率 为 w[i] / sum(w) 。
     *
     * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3)= 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3)= 0.75（即，75%）。
     *
     * 示例 1：
     *
     * 输入：
     * ["Solution","pickIndex"]
     * [[[1]],[]]
     * 输出：
     * [null,0]
     * 解释：
     * Solution solution = new Solution([1]);
     * solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。
     * 示例 2：
     *
     * 输入：
     * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
     * [[[1,3]],[],[],[],[],[]]
     * 输出：
     * [null,1,1,1,1,0]
     * 解释：
     * Solution solution = new Solution([1, 3]);
     * solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
     * solution.pickIndex(); // 返回 1
     * solution.pickIndex(); // 返回 1
     * solution.pickIndex(); // 返回 1
     * solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。
     *
     * 由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
     * [null,1,1,1,1,0]
     * [null,1,1,1,1,1]
     * [null,1,1,1,0,0]
     * [null,1,1,1,0,1]
     * [null,1,0,1,0,0]
     * ......
     * 诸若此类
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/random-pick-with-weight
     *
     *
     * 提示：
     *
     * 1 <= w.length <= 104
     * 1 <= w[i] <= 105
     * pickIndex 将被调用不超过 104 次
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/random-pick-with-weight
     */

    static class RandomSolution{

        private int[] preSum;
        private Random random = new Random();


        public RandomSolution(int[] nums) {
            preSum = new int[nums.length + 1];
            preSum[0] = 0;
            for (int i = 1; i <= nums.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }

        }



        public int pickIndex() {

            // 随机角标
            // 不取0，并且要包含最后一个元素
            int target = random.nextInt(preSum[preSum.length - 1]) + 1;

            System.out.println("target:" + target);

            int left = 0, right = preSum.length - 1;

            while (left <= right) {

                int mid = left + (right - left) / 2;

                if (preSum[mid] == target) {
                    right = mid - 1;
                } else if (preSum[mid] > target) {
                    right = mid - 1;
                } else if (preSum[mid] < target) {
                    left = mid + 1;
                }
            }

            // 额外的 -1 是因为preSum 的索引偏移了一位，还原为权重数组 w 的索引
            return left - 1;


        }





    }



}
