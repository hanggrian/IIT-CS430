package com.example;

class Permutations {
  static void print(int[] nums) {
    print(nums, nums.length, 0);
  }

  static void print(int[] nums, int n, int k) {
    if (k == n - 1) {
      for (int i = 0; i < n; i++) {
        System.out.print(nums[i] + " ");
      }
      System.out.println();
    } else {
      for (int i = k; i < n; i++) {
        swap(nums, i, k);
        print(nums, n, k + 1);
        swap(nums, i, k);
      }
    }
  }

  private static void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}
