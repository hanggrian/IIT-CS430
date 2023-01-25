package com.hendraanggrian.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyTest {
  @Test
  public void test() {
    assertEquals("yo!", new MyApp().toString());
  }
}
