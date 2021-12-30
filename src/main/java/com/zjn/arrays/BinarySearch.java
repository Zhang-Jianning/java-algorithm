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
    // 左闭右开
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



    // 左闭右闭
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




    public static void main(String[] args) {
        int[] nums = {1,22,33,44,55,55};
        int search = binarySearchRight2(nums, 55);
        System.out.println(search);
    }




}
