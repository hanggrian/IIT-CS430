package com.example;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class CellTowerPlacementTest {
  @Test
  public void minLocations() {
    assertThat(CellTowerPlacement.minLocations(1, 2, 3, 4, 5)).asList()
      .containsExactly(2, 5).inOrder();
    assertThat(CellTowerPlacement.minLocations(2, 4, 5, 6, 7, 9, 11, 12)).asList()
      .containsExactly(3, 6, 10, 13).inOrder();
    assertThat(CellTowerPlacement.minLocations(0, 2, 4, 6, 8, 10)).asList()
      .containsExactly(1, 5, 9).inOrder();
    assertThat(CellTowerPlacement.minLocations(1, 3, 5, 7, 9)).asList()
      .containsExactly(2, 6, 10).inOrder();
  }

  @Test
  public void minLocationsReversed() {
    assertThat(CellTowerPlacement.minLocationsReversed(1, 2, 3, 4, 5)).asList()
      .containsExactly(1, 4).inOrder();
    assertThat(CellTowerPlacement.minLocationsReversed(2, 4, 5, 6, 7, 9, 11, 12)).asList()
      .containsExactly(1, 5, 8, 11).inOrder();
    assertThat(CellTowerPlacement.minLocationsReversed(0, 2, 4, 6, 8, 10)).asList()
      .containsExactly(1, 5, 9).inOrder();
    assertThat(CellTowerPlacement.minLocationsReversed(1, 3, 5, 7, 9)).asList()
      .containsExactly(0, 4, 8).inOrder();
  }
}
