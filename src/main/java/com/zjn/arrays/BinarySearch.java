package com.zjn.arrays;

/**
 * 二分查找
 */
public class BinarySearch {



    public static int search(int[] nums, int target) {

        return search(nums, target, 0, nums.length - 1);

    }

    public static int search(int[] nums, int target, int start, int end) {

        if (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                return search(nums, target, mid + 1, end);
            } else {
                return search(nums, target, start, mid - 1);
            }
        }
        return -1;
    }



    // 二分查找
    public static int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }


    // 寻找左侧边界  // 左闭右闭
    public static int binarySearchLeft(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }
    // // 寻找左侧边界  左闭右开
    public static int binarySearchLeft2(int[] nums, int target) {
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }



    // // 寻找右侧边界 左闭右闭
    public static int binarySearchRight(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }

        if (left == 0 || nums[left - 1] != target) {
            return -1;
        }
        return left - 1;
//        if (right < 0 || nums[right] != target) {
//            return -1;
//        }
//        return right;

    }

    // 寻找右侧边界
    public static int binarySearchRight2(int[] nums, int target) {
        int left = 0, right = nums.length;

        while(left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
            System.out.println(left + "  " + right);
        }

//        if (left == 0 || nums[left - 1] != target) {
//            return -1;
//        }
//        return left - 1;

        if (right == 0 || nums[right - 1] != target) {
            return -1;
        }
        return right - 1;

    }


    /**
     * 珂珂喜欢吃香蕉，这里有 n 堆香蕉，第i堆中有 piles[i] 根香蕉。警卫已经离开了，将在H小时后回来
     * 珂珂可以决定他吃香蕉的速度K（单位：根/小时）。每个小时，他会选择一堆香蕉，从中吃掉K根，如果这堆香蕉少于K根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉
     * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉
     * 返回她可以在H小时内吃掉所有香蕉的最小速度K（k为整数）
     * 珂珂每小时最多只能吃一堆香蕉，如果吃不完的话留到下一小时再吃；如果吃完了这一堆还有胃口，也只会等到下一小时才会吃下一堆
     * 1 <= piles[i] <= 10^9
     * @param piles H
     * 确定x, f(x), target分别是什么 ？ 二分搜索的本质就是在搜索自变量。
     *  f(x) 单调，f(x) == target 时计算最大的x
     *
     */

    // 速度为x，需要f(x)小时吃完   f(x) 随x的增加单调递减
    static int f(int[] piles, int x) {

        int hour = 0;
        for (int pile : piles) {
            hour += (pile / x);
            if (pile % x != 0) {
                hour ++;
            }
        }
        return hour;
    }

    public static int minEatingSpeed(int[] piles, int H) {

        // 此时不存在
        if (piles.length > H) {
            return -1;
        }

        int left = 1;
        int right = 100;

        while(left < right) {
            int mid = left + (right - left) / 2;

            if (f(piles, mid) == H) {
                right = mid;
            } else if (f(piles, mid) < H) {
                right = mid;
            } else if (f(piles, mid) > H) {
                left = mid + 1;
            }
        }
        return left;

    }


    /**
     * 传送带上的包裹必须在d天内从一个港口运送到另一个港口
     * 传送带上的第i个包裹的重量为 wights[i] ，每一天我们都会按给出重量的顺序往传送带上装载包裹，我们装载的重量不会超过船的最大运载重量
     * 返回能在d天鹅你讲传送带上的所有包裹送达的船的最低运载能力
     */

    // 定义：当运载能力为 x 时，需要 f(x) 天运完所有货物
    static int ff(int[] weights, int k) {
        int result = 0;
        int a = 0;
        for (int weight : weights) {
            a += weight;
            if (a > k) {
                a = weight;
                result ++;
            }
        }
        if (a != 0) {
            result ++;
        }
        return result;
    }

    static int fff(int[] weights, int k) {
        int result = 0;
        for (int i = 0; i < weights.length; i++) {
            int cap = k;
            while (i < weights.length) {
                if (cap >= weights[i]) {
                    cap --;
                    i ++;
                } else {
                    break;
                }
            }
            result ++;

        }
        return result;
    }




    public static int mix(int[] weights, int D) {

        int left = 0;
        int right = 0;

        // 最小值是数组中的最大值， 最大值是所有元素和
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }

        while (left < right){
            int mid = left + (right -left) / 2;

            if (ff(weights, mid) == D) {
                right = mid;
            } else if (ff(weights, mid) > D) {
                left = mid + 1;
            } else if (ff(weights, mid) < D) {
                right = mid;
            }
        }
        return left;

    }


    /**
     * 给定一个非负整数数组和一个整数m，你需要将这个数组分成m个非空的连续子数组，设计一个算法使得这m个子数组各自和的最大值最小
     * 数组长度n满足以下条件： 1 <= n <= 1000   1 <= m <= min(50,n)
     *
     *  tips : 面试做算法题的时候，题目一般都会要求算法的时间复杂度，如果你发现 O(NlogN) 这样存在对数的复杂度，一般都要往二分查找的方向上靠，这也算是个小套路。
     * @param nums 整数数组
     * @param m m
     *
     *          假设 nums 元素个数为 N，元素和为 S，则 split 函数的复杂度为 O(N)，二分查找的复杂度为 O(logS)，所以算法的总时间复杂度为 O(N*logS)
     */



    int splitArray(int[] nums, int m) {

        int left = 0;
        int right = 0;
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }

        while(left < right) {
            int mid = left + (right - left) / 2;

            if (f1(nums, mid) == m) {
                right = mid;
            } else if (f1(nums, mid) > m) {
                left = mid + 1;
            } else if (f1(nums, mid) < m) {
                right = mid;
            }

        }

        return left;

    }



    // k是最大和，返回值是需要分成几个数组
    int f1(int[] nums, int k) {


        int res = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum >= k) {
                res ++;
                sum = num;
                if (sum == k) {
                    sum = 0;
                }
            }
        }
        if (sum != 0) {
            res ++;
        }

        return res;
    }









    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7,8,9,10};
        int search = mix(nums, 4);
        System.out.println(search);
    }




}
