package com.example.graph;

import org.junit.Test;

import static com.example.graph.MazeGraphs.REACHABLE;
import static com.example.graph.MazeGraphs.UNREACHABLE;
import static org.junit.Assert.assertEquals;

public class MazeGraphsTest {
  @Test
  public void getMinMoves() {
    char[][] maze = new char[10][6];
    for (int i = 0; i < maze.length; i++) {
      for (int j = 0; i < maze[i].length; i++) {
        maze[i][j] = REACHABLE;
      }
    }
    maze[0][4] = UNREACHABLE;
    maze[0][6] = UNREACHABLE;
    maze[0][7] = UNREACHABLE;
    maze[1][1] = UNREACHABLE;
    maze[1][3] = UNREACHABLE;
    maze[1][5] = UNREACHABLE;
    maze[2][0] = UNREACHABLE;
    maze[2][1] = UNREACHABLE;
    maze[2][2] = UNREACHABLE;
    maze[2][3] = UNREACHABLE;
    maze[2][4] = UNREACHABLE;
    maze[2][9] = UNREACHABLE;
    maze[3][0] = UNREACHABLE;
    maze[3][1] = UNREACHABLE;
    maze[4][0] = UNREACHABLE;
    maze[4][5] = UNREACHABLE;
    maze[4][9] = UNREACHABLE;
    maze[5][8] = UNREACHABLE;
    maze[5][9] = UNREACHABLE;
    assertEquals(5, MazeGraphs.getMinMoves(maze, 2, 3, 6, 2));
  }
}
