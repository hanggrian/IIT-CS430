package com.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SallyWidgetAuctionTest {
  @Test
  public void maxProfitGreedy() {
    assertEquals(36, SallyWidgetAuction.maxProfitGreedy(
      new int[]{1, 3, 5, 6, 7},
      new int[]{1, 8, 18, 22, 28},
      10));
    assertEquals(12, SallyWidgetAuction.maxProfitGreedy(
      new int[]{1, 2, 1, 12, 4},
      new int[]{1, 1, 2, 4, 10},
      5));
  }

  @Test
  public void maxProfitKnapsackRecursive() {
    assertEquals(36, SallyWidgetAuction.maxProfitKnapsackRecursive(
      new int[]{1, 3, 5, 6, 7},
      new int[]{1, 8, 18, 22, 28},
      10));
    assertEquals(12, SallyWidgetAuction.maxProfitKnapsackRecursive(
      new int[]{1, 2, 1, 12, 4},
      new int[]{1, 1, 2, 4, 10},
      5));
  }
}
