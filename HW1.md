<!--
LaTeX for markdown-pdf
-->
<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
<script type="text/x-mathjax-config"> MathJax.Hub.Config({ tex2jax: {inlineMath: [['$', '$']]}, messageStyle: "none" });</script>

# [HW1](https://github.com/hendraanggrian/IIT-CS430/raw/assets/CS430HW1.pdf)

## 1. Find a Tuple

### a. Brute Force

If I understand this correctly, create a function to return true if a tuple
where $n_1 + n_2 = n_3$ is found. Simply create 3 pointers that gradually moves
forward covering all posibilities.

```java
boolean hasTuple(int[] nums) {
  for (int i = 0; i < nums.length - 2; i++) {
    for (int j = i + 1; j < nums.length - 1; j++) {
      for (int k = j + 1; k < nums.length; k++) {
        // because the input is unsorted, manually find the highest value and compare the rest
        int max = Math.max(nums[i], Math.max(nums[j], nums[k]));
        if (nums[i] + nums[j] + nums[k] - max == max) {
          return true;
        }
      }
    }
  }
  return false;
}
```

### b. Big-O of Brute Force

**The big-O estimate is $O(n^3)$**, because we're searching for permutations of
3 in $n$.

### c. Sort First

```java
boolean hasTuple(int[] nums) {
  Arrays.sort(nums);
  for (int i = 0; i < nums.length - 2; i++) {
    // because the input is sorted, max is always the third index
    if (nums[i] + nums[i + 1] == nums[i + 2]) {
      return true;
    }
  }
  return false;
}
```

### d. Big-O of Sort First

**The big-O estimate is $O(n^2)$**, assuming that `Arrays.sort` function
operates at **$O(n)$**. It is only slightly better than brute-force approach.
But also could have been worse if worse-performing sorting method is being used,
like *bubble sort*.

## 2. Prove by Induction

## 3. Prove Big-O

### a. 2^(n+1) ?=? O(2^n)

- $2^{n + 1} = O(2^n)$
- $2^n . 2^1 \le c2^n$, divide all by $2^n$.
- **$2^1 \le c$, therefore yes**, because $c\gt0$

### b. 2^(2n) ?=? O(2^n)

- $2 ^ {2n} = O(2^n)$
- $2^n . 2^n \le c2^n$, divide all by $2^n$.
- **$2^n \le c$, therefore no**, because $c$ and $n$ is unbounded.

## 4.

### Big-O Runtime

$T(n) = ai$

### If Numbers are Random

- $\displaystyle\sum_{i=2}^{n+1} T()$

### Big-0 Average

## 5. Permutations

### a. Describe the Program

```c
void Z(int A[], int n, int k) {
  if (k == n - 1) {
    for (int i = 0; i < n; i++) {
      cout << A[i] << " ";
    }
    cout << endl;
  } else {
    for (int i = k; i < n; i++) {
      swap(A[i], A[k]);
      Z(A, n, k + 1);
      swap(A[i], A[k]);
    }
  }
}
```

This is a 2-pointer recursive function with conditions:
- In base case, print the result of this recursion leaf node when the left
  pointer reaches right.
- In recursive case, iterate every possibility of combination by temporarily
  swapping values by index of array.

```c
void main() {
  int A[3] = {1, 2, 3};
  int n = 3;
  Z(A, n, 0);
}
```

Print all combinations of `A`, each has a length of `n`.

```
1 2 3
1 3 2
2 1 3
2 3 1
3 2 1
3 1 2
```

Based on such characteristics and result, I believe this is a function to
**print permutations of an array of integer**.

### b. Give a Recurrence Equation

| Case | Equation |
| --- | --- |
| Base case ($n = 1$) | **$T(1) = O(1)$** |
| Recursive case ($n > 1$) | **$T(n) = n . T(n - 1) + O(n)$** |

Where $O(1)$ is print and $O(n)$ is swap.

### c. Solve the Recurrence Equation

Using the previous example where `n` is 3.

- $T(3) = 3 . T(2) + O(3)$
- $T(2) = 2 . T(1) + O(2)$
- $T(1) = O(1)$

## 6. Inversions

### a. List 5 Inversions

The input is array of `[2, 3, 8, 6, 1]`. We start the 2 pointers on the right:

| No | Pair | $i < j$ | $A[i] > A[j]$ |
| ---: | --- | :---: | :---: |
| 1 | **`[4, 5]`** | $4 < 5$ | $6 > 1$ |
| 2 | **`[3, 5]`** | $3 < 5$ | $8 > 1$ |
| 3 | **`[2, 5]`** | $2 < 5$ | $3 > 1$ |
| 4 | **`[1, 5]`** | $1 < 5$ | $2 > 1$ |
| 5 | **`[3, 4]`** | $3 < 4$ | $8 > 6$ |

### b. Array with the Most Inversions

Using the previous example of starting from the right, array with the most
inversions are reversed order.

- $T(3) = 3 . T(2)$
- $T(2) = 2 . T(1)$
- $T(1) = 1$

In such case, **the equation is $\frac{n . (n - 1)}{2}$**.

### c. Relationship with Insertion Sort

### d. Algorithm of Inversions in Permutation

## 7. Big-O Bounds of Substitution or Recursion

### a. T(n) = T(n-1) + n

By expansion:

- $T(3) = T(2) + 3$
- $T(2) = T(1) + 2$
- $T(1) = 1$

As is the case previously, the equation is $\frac{n . (n - 1)}{2}$, and
**the big-O is $O(n^2)$**.

### b. T(n) = T(n/4) + T(n/2) + n^2

Using Recursion Tree:

- $n^2$
  - $(n / 4)^2$
    - $(\frac{n / 4}{4})^2 = (n / 16)^2$
      - ...
      - ...
    - $(\frac{n / 4}{2})^2 = (n / 8)^2$
      - ...
      - ...
  - $(n / 2)^2$
    - $(\frac{n / 2}{4})^2 = (n / 8)^2$
      - ...
      - ...
    - $(\frac{n / 2}{2})^2 = (n / 4)^2$
      - ...
      - ...

Calculating by level:

- $1n^2$
- $\frac{5}{16}n^2$
- $\frac{5 . 5}{16 . 16}n^2 = \frac{25}{256}n^2$
- Dropping the lower bound, we get **$O(n^2)$**.
