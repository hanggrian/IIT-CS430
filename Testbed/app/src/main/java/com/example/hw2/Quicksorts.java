package com.example.hw2;

import static com.example.hw2.Partitions.partition;

class Quicksorts {
  private Quicksorts() {
  }

  static void quicksort(int[] A) {
    quicksort(A, 0, A.length - 1);
  }

  static void quicksort(int[] A, int p, int r) {
    if (p < r) {
      int q = partition(A, p, r);
      quicksort(A, p, q - 1);
      quicksort(A, q + 1, r);
    }
  }

  static void quicksortTailRecursive(int[] A) {
    quicksortTailRecursive(A, 0, A.length - 1);
  }

  static void quicksortTailRecursive(int[] A, int p, int r) {
    while (p < r) {
      int q = partition(A, p, r);
      quicksortTailRecursive(A, p, q - 1);
      p = q + 1;
    }
  }

  static void quicksortTailRecursiveModified(int[] A) {
    quicksortTailRecursiveModified(A, 0, A.length - 1);
  }

  static void quicksortTailRecursiveModified(int[] A, int p, int r) {
    while (p < r) {
      int q = partition(A, p, r);
      if (q < p + (r - p) / 2) {
        quicksortTailRecursiveModified(A, p, q - 1);
        p = q + 1;
      } else {
        quicksortTailRecursiveModified(A, q + 1, r);
        r = q - 1;
      }
    }
  }
}
