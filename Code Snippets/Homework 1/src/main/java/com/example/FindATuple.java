package com.example;

import java.util.Arrays;

class FindATuple {
  static boolean hasTupleBruteForce(int[] nums) {
    for (int i = 0; i < nums.length - 2; i++) {
      for (int j = i + 1; j < nums.length - 1; j++) {
        for (int k = j + 1; k < nums.length; k++) {
          // because the input is unsorted, manually find the highest value and compare the rest
          int max = Math.max(nums[i], Math.max(nums[j], nums[k]));
          if (nums[i] + nums[j] + nums[k] - max == max) {
            return true;
          }
        }
      }
    }
    return false;
  }

  static boolean hasTupleSorted(int[] nums) {
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 2; i++) {
      if (nums[i] + nums[i + 1] == nums[i + 2]) {
        return true;
      }
    }
    return false;
  }
}
