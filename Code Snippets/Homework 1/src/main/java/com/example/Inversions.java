package com.example;

import java.util.ArrayList;
import java.util.List;

public class Inversions {
  static List<List<Integer>> getInversions(int[] nums) {
    List<List<Integer>> inversions = new ArrayList<>();
    for (int j = nums.length - 1; j >= 1; j--) {
      for (int i = j - 1; i >= 0; i--) {
        if (nums[i] > nums[j]) {
          List<Integer> pair = new ArrayList<>();
          // starting index is 1
          pair.add(i + 1);
          pair.add(j + 1);
          inversions.add(pair);
        }
      }
    }
    return inversions;
  }

  // TODO: still not stable
  static int countInversions(int[] nums) {
    return countInversions(nums, 0, nums.length - 1);
  }

  // TODO: still not stable
  static int countInversions(int[] nums, int left, int right) {
    if (left >= right) {
      return 0;
    }
    int mid = (left + right) / 2;
    int leftInversions = countInversions(nums, left, mid);
    int rightInversions = countInversions(nums, mid + 1, right);
    return leftInversions + rightInversions + mergeSortAndCountInversions(nums, left, mid, right);
  }

  // TODO: still not stable
  private static int mergeSortAndCountInversions(int[] nums, int left, int mid, int right) {
    // split into sub-arrays
    int[] numsLeft = new int[mid - left + 1];
    int[] numsRight = new int[right - mid];
    for (int i = 0; i < numsLeft.length; ++i) {
      numsLeft[i] = nums[left + i];
    }
    for (int j = 0; j < numsRight.length; ++j) {
      numsRight[j] = nums[mid + 1 + j];
    }
    // merge sub-arrays
    int i = 0;
    int j = 0;
    int k = left;
    int inversions = 0;
    while (i < numsLeft.length && j < numsRight.length) {
      if (numsLeft[i] <= numsRight[j]) {
        nums[k] = numsLeft[i];
        i++;
      } else {
        inversions += numsLeft.length - i + 1;
        nums[k] = numsRight[j];
        j++;
      }
      k++;
    }
    return inversions;
  }
}
