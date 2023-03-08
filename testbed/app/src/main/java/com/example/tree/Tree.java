package com.example.tree;

import java.util.Arrays;

public class Tree {
  public int value;
  public Tree left;
  public Tree right;

  public Tree() {
  }

  public Tree(int value) {
    this.value = value;
  }

  public Tree(int value, Tree left, Tree right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  public static Tree newBinarySearchTree(int... values) {
    Arrays.sort(values);
    Tree root = null;
    Tree current;
    for (int value : values) {
      if (root == null) {
        root = new Tree(value);
      } else {
        current = root;
        while (true) {
          if (value < current.value) {
            if (current.left == null) {
              current.left = new Tree(value);
              break;
            } else {
              current = current.left;
            }
          } else {
            if (current.right == null) {
              current.right = new Tree(value);
              break;
            } else {
              current = current.right;
            }
          }
        }
      }
    }
    return root;
  }
}
