package com.example;

class Quicksort {
  private Quicksort() {
  }

  static void quicksort(int[] A) {
    quicksort(A, 0, A.length - 1);
  }

  static void quicksort(int[] A, int p, int r) {
    while (p < r) {
      int q = partition(A, p, r);
      quicksort(A, p, q - 1);
      p = q + 1;
    }
  }

  static void hoareQuicksort(int[] A) {
    hoareQuicksort(A, 0, A.length - 1);
  }

  static void hoareQuicksort(int[] A, int p, int r) {
    while (p < r) {
      int q = hoarePartition(A, p, r);
      hoareQuicksort(A, p, q - 1);
      p = q + 1;
    }
  }

  private static int partition(int[] A, int p, int r) {
    int pivot = A[r];
    int i = (p - 1);

    for (int j = p; j < r; j++) {
      if (A[j] <= pivot) {
        i++;

        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
      }
    }

    int temp = A[i + 1];
    A[i + 1] = A[r];
    A[r] = temp;

    return i + 1;
  }

  private static int hoarePartition(int[] A, int p, int r) {
    int x = A[p];
    int i = p - 1;
    int j = r + 1;

    while (true) {
      do {
        j -= 1;
      } while (A[j] < x);
      do {
        i += 1;
      } while (A[i] > x);
      if (i < j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
      } else {
        return j;
      }
    }
  }
}
