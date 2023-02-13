<!-- LaTeX for markdown-pdf -->
<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
<script type="text/x-mathjax-config"> MathJax.Hub.Config({ tex2jax: { inlineMath: [['$', '$']]}, messageStyle: "none" });</script>

# CS430: [Homework 1](https://github.com/hendraanggrian/IIT-CS430/raw/assets/CS430HW1.pdf)

## 1.

### 1a. Use pseudocode to specify a brute-force algorithm that determines when given as input a sequence of $n$ positive integers whether there are two distinct terms of the sequence that have as sum a third term. The algorithm should loop through all triples of terms of the sequence, checking whether the sum of the first two terms equals the third.

In a brute-force approach, simply create 3 pointers that gradually moves forward
covering all posibilities.

```java
boolean hasTuple(int[] A) {
  for (int i = 0; i < A.length - 2; i++) {
    for (int j = i + 1; j < A.length - 1; j++) {
      for (int k = j + 1; k < A.length; k++) {
        // because the input is unsorted, manually find the highest value and compare the rest
        int max = Math.max(A[i], Math.max(A[j], A[k]));
        if (A[i] + A[j] + A[k] - max == max) {
          return true;
        }
      }
    }
  }
  return false;
}
```

### 1b. Give a big-O estimate for the complexity of the brute-force algorithm part 1a.

**The big-O estimate is $O(n^3)$**, because we're searching for permutations of
3 in $n$.

### 1c. Devise a more efficient algorithm for solving the problem that first sorts the input sequence and then checks for each pair of terms whether their sum is in the sequence.

A more efficient solution is to sort the input first, allowing the comparison to
be executed in 1 pass.

```java
boolean hasTuple(int[] A) {
  Arrays.sort(A);
  for (int i = 0; i < A.length - 2; i++) {
    if (A[i] + A[i + 1] == A[i + 2]) {
      return true;
    }
  }
  return false;
}
```

### 1d. Give a big-O estimate for the complexity of this algorithm. Is it more efficient than the brute-force algorithm?

**The big-O estimate is $O(n^2)$**, assuming that `Arrays.sort` function
operates at **$O(n)$**. It is only slightly better than brute-force approach.
But also could have been worse if worse-performing sorting method is being used,
like *bubble sort*.

## 2. Prove, by induction on $k$, that level $k$ of a binary tree has less than or equal to $2k$ nodes (root level has $k = 0$).

Prove that the height of binary tree is $T(k) \le 2^k$:

$$
\begin{array}{rcl}
  T(k+1) &\le& 2T(k) \quad \text{because to increment the height of the tree, one must
  add the maximum of 2 leaf nodes} \\
  T(k+1) &\le& 2 . 2^k \qquad \text{swapping the values from the assumption} \\
  T(k+1) &\le& 2^{k+1} \qquad \text{\textbf{therefore true}, because the equation is balanced to the assumption}
\end{array}
$$

## 3. Use definition of big O to prove or disprove.

### 3a. Is $2^(n+1) ?=? O(2^n)$

$$
\begin{array}{rcl}
  2^{n+1} &=& O(2^n) \\
  2^n . 2^1 &\le& c2^n \qquad \text{divide all by $2^n$} \\
  2^1 &\le& c \qquad\quad \text{\textbf{therefore true}, because $c\gt0$}
\end{array}
$$

### 3b. Is $2^(2n) ?=? O(2^n)$

$$
\begin{array}{rcl}
  2^{2n} &=& O(2^n) \\
  2^n . 2^n &\le& c2^n \qquad \text{divide all by $2^n$} \\
  2^n &\le& c \qquad\quad \text{\textbf{therefore false}, because $c$ and $n$ is unbounded}
\end{array}
$$

## 4. The following routine takes as input a list of $n$ numbers, and returns the first value of $i$ for which $L[i] < L[i-1]$, or $n$ if no such number exists.

```c
int firstDecrease(int * L, int n) {
  for (int i = 2; i <= n && L[i] >= L[i - 1]; i++) { }
  return i;
}
```

### 4a. What is the big-O runtime for the routine, measured as a function of its return value $i$?

Looping $n$ times without inner loop would indicate that **the big-O is
$O(n)$**.

### 4b. If the numbers are chosen independently at random, then the probability that `firstDecrease(L) returns i` is $(i-1)/i!$, except for the special case of $i = n+1$ for which the probability is $1/n!$ Use this fact to write an expression for the expected value returned by the algorithm. (Your answer can be expressed as a sum, it does not have to be solved in closed form. Do not use O-notation.)

$$T(n) = \frac12! + \ldots + \frac{n-1}n! + \ldots + \frac1n!$$

### 4c. What is the big-O average case running time of the routine? Hint: Simplify the previous summation until you see a common taylor series.

I have limited understanding of point 2b, **my guess would be $O(n)$**.

## 5. Consider the following program and recursive function.

```c
#include <iostream.h>
void Z(int[], int, int);
void swap(int&, int&);

void main() {
  int A[3] = {1, 2, 3};
  int n = 3;
  Z(A, n, 0);
}

void swap(int &x, int &y) {
  int temp;
  temp = x;
  x = y;
  y = temp;
}

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

### 5a. Demonstrate the execution, show the output, and explain what the program accomplishes.

Function `Z` has 2 pointers with conditions:
- In base case, print the result of this recursion leaf node when the left
  pointer reaches right.
- In recursive case, iterate every possibility of combination by temporarily
  swapping values by index of array.

Function `main` starts the recursive reaction, printing all combinations of $A$,
each has a length of $n$. The result:

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

### 5b. Give a recurrence equation describing the worst-case behavior of the program.

| Case | Equation |
| --- | --- |
| Base case ($n = 1$) | **$T(1) = O(1)$** |
| Recursive case ($n > 1$) | **$T(n) = n . T(n-1) + O(n)$** |

Where $O(1)$ is print and $O(n)$ is swap.

### 5c. Solve the recurrence equation.

Using the previous example where $n = 3$.

$$
\begin{array}{rcl}
  T(1) &=& O(1) \qquad\qquad\quad \text{is the base case} \\
  T(2) &=& 2 . T(1) + O(2) \quad \text{start of the recursive case} \\
  T(3) &=& 3 . T(2) + O(3) \quad \text{and so on...}
\end{array}
$$

## 6. Let $A[1 \ldots n]$ be an array of $n$ distinct numbers. If $i < j$ and $A[i] > A[j]$, then the pair $(i,j)$ is called an inversion of $A$.

$A$ is an array of integers with unique values. An inversion of array $A$ is a
pair of $i$ and $j$ where $i < j$ and $A[i] > A[j]$.

### 6a. List the five inversions of the array $\{2,3,8,6,1\}$.

We start the 2 pointers on the right:

| No | Pair | $i < j$ | $A[i] > A[j]$ |
| ---: | --- | :---: | :---: |
| 1 | $\textbf{[4, 5]}$ | $4 < 5$ | $6 > 1$ |
| 2 | $\textbf{[3, 5]}$ | $3 < 5$ | $8 > 1$ |
| 3 | $\textbf{[2, 5]}$ | $2 < 5$ | $3 > 1$ |
| 4 | $\textbf{[1, 5]}$ | $1 < 5$ | $2 > 1$ |
| 5 | $\textbf{[3, 4]}$ | $3 < 4$ | $8 > 6$ |

### 6b. What array with elements from the set $\{1,2,\ldots,n\}$ has the most inversions? How many does it have?

Using the previous example of starting from the right, array with the most
inversions are reversed order.

$$
\begin{array}{rcl}
  T(1) &=& 1 \\
  T(2) &=& 2 . T(1) \\
  T(3) &=& 3 . T(2) \quad \text{and so on...}
\end{array}
$$

Looking at the pattern, **its inversion can be interpreted as
$\frac{n.(n-1)}{2}$**.

### 6c. What is the relationship between the running time of insertion sort and the number of inversions in the input array? Justify your answer.

Inversion search draw some similarities from *insertion sort*, **specifically on
values comparison and loop times**. For one, the value of right pointer need to
be lower than the left. Also, the complexity grows as the input increases.

### 6d. Give an algorithm that determines the number of inversions in any permutation on $n$ elements in $\theta(n \log n)$ worst-case time. (Hint: Modify merge sort.)

```java
int countInversions(int[] A, int p, int r) {
  if (p >= r) {
    return 0;
  }
  int mid = (p + r) / 2;
  int left = countInversions(A, p, mid);
  int right = countInversions(A, mid + 1, r);
  return left + right + mergeSortAndCountInversions(A, p, mid, r);
}

int mergeSortAndCountInversions(int[] A, int p, int q, int r) {
  // split into sub-arrays
  int[] left = new int[q - p + 1];
  int[] right = new int[r - q];
  for (int i = 0; i < left.length; ++i) {
    left[i] = A[p + i];
  }
  for (int j = 0; j < right.length; ++j) {
    right[j] = A[q + 1 + j];
  }
  // merge sub-arrays
  int i = 0;
  int j = 0;
  int k = p;
  int inversions = 0;
  while (i < left.length && j < right.length) {
    if (left[i] <= right[j]) {
      A[k] = left[i];
      i++;
    } else {
      inversions += left.length - i + 1;
      A[k] = right[j];
      j++;
    }
    k++;
  }
  return inversions;
}
```

## 7. Give big-O bounds for $T(n)$ in each of the following recurrences. (Substitution or Recursion Tree)

### 7a. $T(n) = T(n-1) + n$

By expansion:

$$
\begin{array}{rcl}
  T(1) &=& 1 \qquad\qquad \text{is the base case} \\
  T(2) &=& T(1) + 2 \quad \text{start of the recursive case} \\
  T(3) &=& T(2) + 3 \quad \text{and so on...}
\end{array}
$$

As is the case previously, the equation is $\frac{n.(n-1)}{2}$, and **the
big-O is $O(n^2)$**.

### 7b. $T(n) = T(n/4) + T(n/2) + n^2$

Using Recursion Tree:

- $n^2 = (n/4)^2 + (n/2)^2$
  - $(\frac{n}{4})^2 = (\frac{n}{4} / 4)^2 + (\frac{n}{4} / 2)^2$
    - $(\frac{n}{16})^2 = \ldots$
    - $(\frac{n}{8})^2 = \ldots$
  - $(\frac{n}{2})^2 = (\frac{n}{2} / 4)^2 + (\frac{n}{2} / 2)^2$
    - $(\frac{n}{8})^2 = \ldots$
    - $(\frac{n}{4})^2 = \ldots$

Calculating by level:

$$
\begin{array}{rcl}
  1n^2 &=& (1.\frac{5}{16})n^2 \\
  \frac{5}{16}n^2 &=& (\frac{5.5}{16.16})n^2 \\
  \frac{25}{256}n^2 &=& \ldots \\
  &&\text{Dropping the lower term, \textbf{we get}} \ O(n^2)
\end{array}
$$
