package com.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClimbingStairTest {
  @Test
  public void minCost() {
    assertEquals(6, ClimbingStair.minCost(new int[]{1, 100, 1, 1, 1, 90, 1, 1, 80, 1}));

    assertEquals(0, ClimbingStair.minCost(new int[]{0, 0, 0, 0}));
    assertEquals(1, ClimbingStair.minCost(new int[]{0, 1, 1, 0}));
    assertEquals(0, ClimbingStair.minCost(new int[]{1, 0, 0, 1}));

    assertEquals(15, ClimbingStair.minCost(new int[]{10, 15, 20}));
    assertEquals(6, ClimbingStair.minCost(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
  }
}
