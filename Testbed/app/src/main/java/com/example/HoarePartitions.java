package com.example;

class HoarePartitions {
  private HoarePartitions() {
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
        Utils.swap(A, i, j);
      } else {
        return j;
      }
    }
  }
}
