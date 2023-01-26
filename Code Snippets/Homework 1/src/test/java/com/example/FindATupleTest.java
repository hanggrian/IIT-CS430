package com.example;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FindATupleTest {
  @Test
  public void hasTupleForced() {
    assertTrue(FindATuple.hasTupleForced(new int[]{2, 72, 21, 100, 19})); // 2 + 19 = 21
    assertFalse(FindATuple.hasTupleForced(new int[]{2, 72, 21, 100, 18}));
  }

  @Test
  public void hasTupleSorted() {
    assertTrue(FindATuple.hasTupleSorted(new int[]{2, 72, 21, 100, 19})); // 2 + 19 = 21
    assertFalse(FindATuple.hasTupleSorted(new int[]{2, 72, 21, 100, 18}));
  }
}
