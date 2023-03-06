# Homework 2

## 1. Assume the priority queue also must support the update `DELETE(A,i)`, where $i$ gives you the location in the data strucure where element $i$ is located. In a binary heap, that would be $A[i]$. Write pseudocode to achieve `DELETE(A,i)` in $O(\log n)$ time for binary heaps, where $n$ is the size of the heap. Justify the running time.

```
delete(A, i):
  last = A.length - 1
  r = A[i]
  A[i] = A[last--]
  parent = i / 2

  # filter A[i] up or down accordingly
  if (i == 1 || A[parent] < A[i])
    heapFilterDown(i) # move node A[k] down the tree
  else
    heapFilterUp(i) # move node A[k] up the tree
```

Time complexity analysis: in `delete()` function, we are mainly performing two
operations:

1. Replacing $i$-th node with rightmost node in constant time complexity $O(1)$.
2. Heapify-up/down: from the function, its clear that maximum number of swaps
  possible in these functions is equal to the height of heap which is of the
  order of $O(\log n)$.

**So, overall complexity is $\bf O(\log n) + O(1) =O(\log n)$**.

## 2. Give an algorithm with worst-case running time of $O(k \log k)$ to print the $k$ biggest elements in a $n$-element max-heap (binary heap stored in an array) without modifying the max-heap (or if you do modify it, restore it to the input heap within the given time bound). For example, if the max-heap is $A=\{100,88,44,66,77,11,22,5,55\}$ and $k=5$, the output could be $100,88,66,77,55$ (the order in which the algorithm prints does not matter). Do not assume $n = O(k)$. Here $n$ can be much larger than $k$. A correctness proof is not needed (but your pseudocode must be correct). You can use additional data structures. Argue the running time.

```
kthBiggest(A):
  m = 2 ^ k - 1
  B = Array(m)
  for i in range(0, m - 1)
    B[i] = A[i]

  buildMaxHeap(A)
  for i in range(1, k)
    heapExtractMax(B)
    maxHeapify(B)
```

Contruct a new max heap using the front $2^k - 1$​​​​​​​ elements from the given array
(which is a max heap) Note the height of the new max heap is $k$. The maximum
element is present in the root node. We extract the maximum element from the
heap and convert it back to max heap. This step has a complexity $O(\log k)$.
**Since the previous step is performed $k$ times, hence total time complexity
$\bf k . O(\log k) = O(k \log k)$**.

## 3. Using only the definition of a binary search tree, show that if a node in a binary search tree has two children, then its successor has no left child and that its predecessor has no right child. Here all the keys in the binary search tree are distinct. The successor of a node $j$ is defined as follows: if the node $j$ has the biggest key, the successor is NIL; otherwise the successor is the node $i$ that has a key that is bigger than the key of $j$ and such that no other node has key between the key of $i$ and the key of $j$.

### Given

If a node in a binary search tree has two children, then its sucessor has no
left child & its predecessor has no right child.

### Solutions

Successor of a node is the leftmost node in the right subtree. Therefore the
successor would not be having a left child anymore.

Similarly, Predecessor of a
node is the rightmost node in the left subtree. Therefore the predecessor would
not be having the right child.

| Predecessor | Sucessor |
| --- | --- |
| ![](img/hw2_2_1.png) | ![](img/hw2_2_2.png) |

## 4. Suppose that a node $x$ is inserted into a red-black tree with RB-INSERT and then is immediately deleted with RB-DELETE. Is the resulting red-black tree the same as the initial red-black tree? Justify your answer.

Consider an example.

| Initial | Insert 2 | Delete 2 |
| --- | --- | --- |
| ![](img/hw2_3_1.jpg) | ![](img/hw2_3_2.jpg) | ![](img/hw2_3_3.jpg) |

**Therefore true, it is clear that after insertion and deletion operation the
result red-black tree is not same as the initial red-black tree**.

## 5. Suppose you are given two $n$-element sorted sequences $A$ and $B$, each representing a set (none has duplicate entries). Describe an $O(n)$-time method for computing a sequence representing the set $A\B$ (with no duplicates; note: the difference of sets $A$ and $B$ consists of those elements in $A$ and not in $B$). You do not have to argue correctness (but, obviously, your method must be correct), but must justify the running time.

```
union(A, B, m, n):
  L = List()

  i, j = 0
  while (i < m) and (j < m)
    if A[i] < B[j]
      add(L, A[i++])
    else if A[i] > B[j]
      add(L, A[j++])
    else
      add(L, A[i++])
      add(L, A[j++])

  # print remaining
  while i < m
    add(L, A[i++])
  while j < n
    add(L, A[j++])

  return L
```

## Extra 1. Present an algorithm with running time O(n) for the following problem: Given array $A[1 \ldots n]$, find entries $1 \le i \le j$ n such that $A[j] − A[i]$ is maximum among all such pairs $i < j$. As an example, if $A=\{5,9,1,4,7,2\}$, output $i=3, j=5$. Make sure to include pseudocode, correctness and running time arguments. A $O(n \log n)$-time algorithm is worth partial credit. An $O(n^2)$-time algorithm is trivial and only gives half of score if complete.

> Hint: Dynamic programming.

```
maxDifference(A):
  if A.length == 1
    return 0
  lowest = A[0]
  result = MAX_INTEGER
  for i in range(1, A.length)
    lowest = min(lowest, A[i])
    result = max(result, A[i] - lowest)
  return result
```

## Extra 2.

## Extra 3

## Extra 4.

<!-- hotfix: KaTeX -->
<!-- https://github.com/yzane/vscode-markdown-pdf/issues/21/ -->
<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
<script type="text/x-mathjax-config">MathJax.Hub.Config({ tex2jax: { inlineMath: [['$', '$']]}, messageStyle: 'none' });</script>
