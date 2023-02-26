package com.example;

class Permutations {
  private Permutations() {
  }

  static void print(int[] A) {
    print(A, A.length, 0);
  }

  static void print(int[] A, int n, int k) {
    if (k == n - 1) {
      for (int i = 0; i < n; i++) {
        System.out.print(A[i] + " ");
      }
      System.out.println();
    } else {
      for (int i = k; i < n; i++) {
        Utils.swap(A, i, k);
        print(A, n, k + 1);
        Utils.swap(A, i, k);
      }
    }
  }
}
