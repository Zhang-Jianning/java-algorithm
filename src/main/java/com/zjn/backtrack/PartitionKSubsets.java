package com.zjn.backtrack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 698 划分为k个相等的子集
 *
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 *
 * 1 <= k <= len(nums) <= 16
 */
public class PartitionKSubsets {


    // 这里是站在数字的角度，遍历桶，选择数字是否进入桶中
    // k 个桶（集合），记录每个桶装的数字之和
    int[] bucket;
    public boolean canPartitionKSubsets(int[] nums, int k) {

        if (k > nums.length) return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) return false;

        bucket = new int[k];
        int target = sum / k;
        // 这里是一个优化点，对 nums 数组排序，把大的数字排在前面，那么大的数字会先被分配到 bucket 中，对于之后的数字，bucket[i] + nums[index] 会更大，更容易触发剪枝的 if 条件。
        //升序后交换顺序来降序
        Arrays.sort(nums);
        for (int i = 0, j = nums.length - 1; i <= j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return traverse(nums, 0, k, target);
    }


    // 站在数字的角度，选择是否进每个桶
    private boolean traverse(int[] nums, int index, int k, int target) {
        if (index == nums.length) {
            // 判断桶中元素是否符合要求
            for (int i : bucket) {
                if (i != target) return false;
            }
            return true;
        }
        // 遍历所有的桶进行选择
        for (int i = 0; i < k; i++) {
            // 超出限制的直接剪枝
            if (bucket[i] + nums[index] > target) continue;
            // 选择装入桶中
            bucket[i] += nums[index];
            boolean b = traverse(nums, index + 1, k, target);
            if (b) return true;
            // 撤销选择
            bucket[i] -= nums[index];
            // 另一个剪枝？ 这里撤销选择后，如果是0，就是没找到合适   没理解？？？？
//            if (bucket[i] == 0) break;
        }
        // 走到这里说明本轮循环都没有满足的
        return false;
    }




    // 这里是站在桶的角度，遍历所有数字，是否将数字装入桶中，并装满
    public boolean canPartitionKSubsets_1(int[] nums, int k) {
        if (k > nums.length) return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) return false;
        int target = sum / k;
        boolean[] used = new boolean[nums.length];
        return backtrack(nums, 0, used, target, k, 0);

    }

    /**
     *
     * @param nums 数组
     * @param index 开始遍历数组的位置
     * @param used 元素是否装入桶中
     * @param target 桶中需要累计的元素和
     * @param k 第k个桶
     * @param bucket 桶中元素和
     * @return boolean
     */
    Map<String, Boolean> memo = new HashMap();
    private boolean backtrack(int[] nums, int index, boolean[] used, int target, int k, int bucket) {

        // 不需要判断index，如果达到上限不会走for循环
//        if (index == nums.length) {}
        if (k == 0) {
            return true;
        }

        // 当桶中的元素装满时，可能会出现重复的情况
        // 元素 1  4  2  3   元素 1  4  2  3
        // 桶   1  1  2  2   桶   2  2  1  1
        // 这种情况下used数组的值是一样的，而且是重复的遍历，可以剪枝
        String key = Arrays.toString(used);
        if (bucket == target) {
            boolean b = backtrack(nums, 0, used, target, k - 1, 0);
            memo.put(key, b);
        }
        // 备忘录
        if (memo.get(key) != null) return memo.get(key);

        // 从index开始，选择下一个可以装入桶中的元素
        for (int i = index; i < nums.length; i++) {
            // 已经被装入桶中，剪枝
            if (used[i]) continue;
            // 超过target
            if (bucket + nums[i] > target) continue;
            // 选择装入桶中
            used[i] = true;
            // 累计和
            bucket += nums[i];
            // 这里递归，是要把i加入桶中后所有情况都遍历出来  穷举所有可能的组合，然后看是否能找出和为 target
            boolean b = backtrack(nums, i + 1, used, target, k, bucket);
            // 有满足条件的直接return
            if (b) return true;
            // 撤销选择
            used[i] = false;
            bucket -= nums[i];
        }
        // 所有的选择都不满足
        return false;
    }



    public boolean canPartitionKSubsets_2(int[] nums, int k) {
        if (k > nums.length) return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) return false;
        int target = sum / k;
        int used = 0;
        return backtrack_1(nums, 0, used, target, k, 0);

    }

    Map<Integer, Boolean> memo_1 = new HashMap();
    private boolean backtrack_1(int[] nums, int index, int used, int target, int k, int bucket) {

        // 不需要判断index，如果达到上限不会走for循环
//        if (index == nums.length) {}
        if (k == 0) {
            return true;
        }

        // 当桶中的元素装满时，可能会出现重复的情况
        // 元素 1  4  2  3   元素 1  4  2  3
        // 桶   1  1  2  2   桶   2  2  1  1
        // 这种情况下used数组的值是一样的，而且是重复的遍历，可以剪枝

        if (bucket == target) {
            boolean b = backtrack_1(nums, 0, used, target, k - 1, 0);
            memo_1.put(used, b);
        }
        // 备忘录
        if (memo_1.get(used) != null) return memo_1.get(used);

        // 从index开始，选择下一个可以装入桶中的元素
        for (int i = index; i < nums.length; i++) {
            // 已经被装入桶中，剪枝\
            // 这里只和1做与运算，其他位置都是和0做与运算
            if (((used >> i) & 1) == 1) continue;
            // 超过target
            if (bucket + nums[i] > target) continue;
            // 选择装入桶中
            used |= 1 << i;
            // 累计和
            bucket += nums[i];
            // 这里递归，是要把i加入桶中后所有情况都遍历出来  穷举所有可能的组合，然后看是否能找出和为 target
            boolean b = backtrack_1(nums, i + 1, used, target, k, bucket);
            // 有满足条件的直接return
            if (b) return true;
            // 撤销选择
            used ^= 1 << i;
            bucket -= nums[i];
        }
        // 所有的选择都不满足
        return false;
    }
}
