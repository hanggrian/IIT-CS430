# CS430: [Homework 2](https://github.com/hendraanggrian/IIT-CS430/raw/assets/CS430HW2.pdf)

## [1.](https://github.com/hendraanggrian/IIT-CS430/raw/main/Testbed/app/src/main/java/com/example/hw2/Quicksorts.java) Show in detail that the running time to sort in increasing order using `QUICKSORT` is $T(n^2)$ when the array A contains distinct elements and is initially sorted in decreasing order.

| Sorting Algorithm | Best | Average | Worst |
| --- | :---: | :---: | :---: |
| Quicksort | $\Omega(n \log n)$ | $\Theta(n \log n)$ | $O(n^2)$ |

Quicksort picks a random pivot and divides the collection accordingly. For the
most optimal performance, these divisions should be sized proportionally.
Ideally, the pivot should be the median of a sorted collection, as opposed to
the smallest or the largest.

**Therefore, the running time of quicksort on a sorted array depends on how the
pivot is selected.**

| Case | Pivot |
| --- | --- |
| Best | $A[n/2]$ |
| Worst | $A[1]$ or $A[n]$ |
| Average | Random |

## 2. Stack depth for `QUICKSORT` - The `QUICKSORT` algorithm of Section 7.1 contains two recursive calls to itself. After the call to `PARTITION`, the left sub array is recursively sorted and then the right subarray is recursively sorted. The second recursive call in `QUICKSORT` is not really necessary; it can be avoided by using an iterative control structure. Good compilers provide this technique, called tail recursion. Consider the following version of quick sort, which simulates tail recursion.

```java
void quicksort2(int[] A, int p, int r) {
  while (p < r) {
    int q = partition(A, p, r);
    quicksort2(A, p, q - 1);
    p = q + 1;
  }
}
```

### a. Argue that `quicksort2(A, 1, A.length)` correctly sorts the array $A$.

Consider a regular `quicksort` algorithm below. It traverses with 2 recursive
calls that are stopped by `if` control flow, `quicksort2` however uses `while`
loop and single recursive call.

```java
void quicksort(int[] A, int p, int r) {
  if (p < r) {
    int q = partition(A, p, r);
    quicksort(A, p, q - 1);
    quicksort(A, q + 1, r);
  }
}
```

By comparison, `quicksort2` has the same iteration process and parameter values.
**Therefore, `quicksort2` correctly sorts the array.**

### b. Compilers usually execute recursive procedures by using a stack that contains pertinent information, including the parameter values, for each recursive call. The information for the most recent call is at the top of the stack, and the information for the initial call is at the bottom. When a procedure is invoked, its information is pushed onto the stack; when it terminates, its information is popped. Since we assume that array parameters are represented by pointers, the information for each procedure call on the stack requires $O(1)$ stack space. The stack depth is the maximum amount of stack space used at any time during a computation. Describe a scenario in which the stack depth of `quicksort2` is $\Theta(n)$ on an $n$-element input array.

### c. Modify the code for `quicksort2` so that the worst-case stack depth is $\Theta(\log n)$. Maintain the $O(n \log n)$ expected running time of the algorithm.

## [3.](https://github.com/hendraanggrian/IIT-CS430/raw/main/Testbed/app/src/main/java/com/example/hw2/Partitions.java) Hoare Partition Correctness - The version of `PARTITION` given in chapter 7 is not the original partitioning algorithm. Here is the original partition algorithm, which is due to *T. Hoare*:

```java
int hoarePartition(int[] A, int p, int r) {
  int x = A[p];
  int i = p - 1;
  int j = r + 1;

  while (true) {
    do {
      j--;
    } while (A[j] < x);
    do {
      i++;
    } while (A[i] > x);
    if (i < j) {
      swap(A, i, j);
    } else {
      return j;
    }
  }
}
```

### a. Demonstrate the operation of `hoarePartition` on the array $A = \{13,19,9,5,12,8,7,4,11,2,6,21\}$, showing the values of the array and auxiliary values after each iteration of the while-loop.

| # | $j$ | $i$ | $A$ | Note |
| ---: | :---: | :---: | :---: | --- |
| 0 | $12$ | $1$ | $\{13, 19, 9, 5, 12, 8, 7, 4, 11, 2, 6, 21\}$ | $x = A[1] = 13$. |
| 1 | $11$ | $1$ | $\{\mathbf{6}, 19, 9, 5, 12, 8, 7, 4, 11, 2, \mathbf{13}, 21\}$ | |
| 2 | $10$ | $2$ | $\{6, \mathbf{2}, 9, 5, 12, 8, 7, 4, 11, \mathbf{19}, 13, 21\}$ | |
| 3 | $9$ | $10$ | $\{6, 2, 9, 5, 12, 8, 7, 4, 11, 19, 13, 21\}$ | **Not swapping, returns $\mathbf{j}$.** |

> The next three questions ask you to give a careful argument that the procedure `hoarePartition` is correct. Prove the following:

### b. The indices $i$ and $j$ are such that we never access an element of $A$ outside the subarray $A[p \ldots r]$.

Look at the variable assignments `i = p - 1` and `j = r + 1`, also the
statements `j--` and `i++` in do-while loops. They explain that $i$ and $j$ are
set from $p$ and $r$ respectively and only move closer gradually until they
intersect. **Therefore $\mathbf{p \le i < j \le r}$ or
$\mathbf{A[p \ldots i \ldots j \ldots r]}$.**

### c. When `hoarePartition` terminates, it returns a value $j$ such that $p \le j < r$.

The function terminates when $i$ and $j$ intersect, making sure that they are
always in the range of $p$ and $r$. **Therefore $\mathbf{p \le j < r}$ is
true.**

### d. Every element of $A[p \ldots j]$ is less than or equal to every element of $A[j+1 \ldots r]$ when `hoarePartition` terminates.

During termination, the function returns $j$, the index of the pivot.
**Therefore $\mathbf{A[p \ldots j] \le x \le A[j+1 \ldots r]}$.**

## 4. What are the minimum and maximum numbers of elements in a heap of height $h$?

| Data Structure | # of Nodes | Notes |
| --- | :---: | --- |
| Almost Complete Binary Tree | $2^0 + 2^1 + 2^2 + \ldots + 2^{h-1} + l = 2^h$ | Where $l$ is the number of nodes in the last level. |
| Complete Binary Tree | $2^0 + 2^1 + 2^2 + \ldots + 2^h = 2^{h+1}-1$ | |

A heap data structure is almost complete to a complete binary tree. **For a
minimum number of elements, the tree should be almost complete with the last
level having just 1 node. Oppositely, the tree should be complete for a maximum
number of nodes.**

## [5.](https://github.com/hendraanggrian/IIT-CS430/raw/main/Testbed/app/src/main/java/com/example/hw2/MaxHeapifies.java) The code for `maxHeapify` is quite efficient in terms of constant factors, except possibly for the recursive call in line 10, which might cause some compilers to produce inefficient code. Write an efficient `maxHeapify` that uses an iterative control construct (a loop) instead of recursion.

```java
void maxHeapify(int[] A, int i) {
  while (true) {
    int l = left(i);
    int r = right(i);
    int max;
    if (l <= A.length && A[l] > A[i]) {
      max = l;
    } else {
      max = i;
    }
    if (r <= A.length && A[r] > A[max]) {
      max = r;
    }
    if (max == i) {
      break;
    }
    swap(A, i, max);
    i = max;
  }
}
```

## 6. What is the smallest possible depth of a leaf in a decision tree for a comparison sort? Explain why in detail.

For the lowest possible stack depth in any sorting algorithm, the input should
already be sorted. With no sorting process, any comparison sorting algorithm
still needs to perform comparisons. **Therefore $\mathbf{n-1}$, because every
comparison checks 2 variables.**
