# Classworks

## Statistic Algorithm

Assume that are given a "black-box" (i.e., you do not have the source code)
procedure `MEDIAN` that takes as parameters an array $A$ and subarray indices
$p$ and $r$, and returns the value of the median element of $A[p \ldots r]$ in
$O(n)$ time in the worst case. Give a simple, linear-time algorithm that uses
this procedure `MEDIAN` to find the $i$-th smallest element. Write the
recurrence relation for your algorithm and show the solution is linear growth.

### Solution

Given `MEDIAN`, here is a linear-time algorithm `SELECT` for finding $i$-th
smallest element in $A$. This algorithm uses the deterministic `PARTITION`
algorithm that was modified to take an element to partition around as an input
parameter.

```
SELECT(A, p, r, i)
  if (p == r)
    return A[p]
  x = MEDIAN(a, p, r)
  q = PARTITION(x)
  k = q - p + 1
  if (i == k)
    return A[q]
  else if (i < k)
    return SELECT(A, p, q - 1, i)
    else return SELECT(A, q + 1, r, i - k)
```

Because $x$ is the median of $A[p \ldots r]$, each of the subarrays
$A[p \ldots q - 1]$ and $A[q + 1 \ldots r]$ has at most half the number of
elements of $A$. **The recurrence for the worst-case running time of `SELECT`
is $\mathbf{T(n) = T(n/2) + O(n)}$ (by master method.)**

## Recursive Insertion Sort

Rewrite the Insertion with recursion and analyze the complexity accordingly.

### Solution

#### A. Pseudocode

```
// initial call Isort(A, 1, n)
Isort(A, p, r) {
  if (p < r) {
    Isort(A, p, r - 1)
    temp = A[r]
    i = r - 1
    while (i>=p && temp < A[i]) {
      A[i+1] = A[i]
      i--
    } 
    A[i+1] = temp
  }
}
```

#### B. Recurrence

$$
\begin{array}{rcl}
  T(n) &=& T(n-1) + O(n-1) + O(1) \quad \text{if n>1} \\
  T(1) &=& O(1)
\end{array}
$$

#### C. Recurrence Equation

$$
\begin{array}{rcl}
  T(n) &=& T(n-1) + O(n-1) + O(1) \quad \text{use iteration} \\
  T(n) &=& [O(n-1) + O(1)] + T(n-1) \\
  T(n) &=& [O(n-1) + O(1)] + [O(n-2) + O(1)] + T(n-2) \\
  T(n) &=& [O(n-1) + O(1)] + [O(n-2) + O(1)] + [O(n-3) + O(1)] + T(n-3) \\
  T(n) &=& [O(n-1) + O(1)] + [O(n-2) + O(1)] + [O(n-3) + O(1)] + \ldots + T(1) \\
  && \text{n-1 terms total} \\
  T(n) &=& summation, (i=1 to n-1) [O(n-i) + O(1)] = O(n^2)
\end{array}
$$
