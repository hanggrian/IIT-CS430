package com.example;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class QuicksortTest {
  @Test
  public void quicksort() {
    int[] A = new int[]{4, 1, 3, 9, 7};
    Quicksort.quicksort(A);
    assertThat(A).asList().containsExactly(1, 3, 4, 7, 9);

    A = new int[]{2, 1, 6, 10, 4, 1, 3, 9, 7};
    Quicksort.quicksort(A);
    assertThat(A).asList().containsExactly(1, 1, 2, 3, 4, 6, 7, 9, 10);
  }

  @Test
  public void hoareQuicksort() {
    int[] A = new int[]{4, 1, 3, 9, 7};
    Quicksort.hoareQuicksort(A);
    assertThat(A).asList().containsExactly(1, 3, 4, 7, 9);

    A = new int[]{2, 1, 6, 10, 4, 1, 3, 9, 7};
    Quicksort.hoareQuicksort(A);
    assertThat(A).asList().containsExactly(1, 1, 2, 3, 4, 6, 7, 9, 10);
  }
}
