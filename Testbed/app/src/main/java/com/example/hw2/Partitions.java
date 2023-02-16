package com.example.hw2;

import static com.example.Utils.swap;

class Partitions {
  private Partitions() {
  }

  static int partition(int[] A, int p, int r) {
    int x = A[r];
    int i = p - 1;

    for (int j = p; j < r; j++) {
      if (A[j] <= x) {
        i++;
        swap(A, i, j);
      }
    }
    swap(A, i + 1, r);
    return i + 1;
  }

  static int hoarePartition(int[] A, int p, int r) {
    int x = A[p];
    int i = p - 1;
    int j = r + 1;

    while (true) {
      do {
        j--;
      } while (A[j] > x);
      do {
        i++;
      } while (A[i] < x);
      if (i < j) {
        swap(A, i, j);
      } else {
        return j;
      }
    }
  }
}
