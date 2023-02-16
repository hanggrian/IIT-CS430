package com.example.hw2;

import static com.example.hw2.Partitions.partition;

class Quicksorts {
  private Quicksorts() {
  }

  static void quicksort2(int[] A) {
    quicksort2(A, 0, A.length - 1);
  }

  static void quicksort2(int[] A, int p, int r) {
    while (p < r) {
      int q = partition(A, p, r);
      quicksort2(A, p, q - 1);
      p = q + 1;
    }
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
}
