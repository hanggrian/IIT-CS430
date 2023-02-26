[View questions](https://github.com/hendraanggrian/IIT-CS430/raw/assets/CS430HW3.pdf)
/ [homepage](https://github.com/hendraanggrian/IIT-CS430/)

# CS430: Homework 3

## 1. What is the largest possible number of internal (key) nodes in a red-black tree with black-height $k$ (measured from root)? What is the smallest possible number?

## 2. A team of biologists keeps information about DNA structures in a balanced binary search tree (i.e. AVL, red/black, etc) using as key the specific weight (an integer) of the structure. The biologists routinely ask questions of the type: "Are there any structures in the tree with specific weight between $a$ and $b$ (inclusive)?" and they hope to get an answer as soon as possible. Design an efficient algorithm that, given integers $a$ and $b$, returns true if there exists a key $x$ in the tree such that $a \le x \le b$, and false if no such key exists in the tree. Describe your algorithm in pseudocode. What is the time complexity of your algorithm?

## 3. Suppose that we can augment a red-black tree of $n$ nodes with an additional attribute at each node, the size, which is the number of (internal) nodes in the subtree rooted at $x$. That is, `size(x)` and it is the number of internal nodes in the subtree rooted at $x$. Also, the size at a node $x$ can be computed using only the information stored in $x$, `left(x)`, and `right(x)`, and that we can maintain “size” at all nodes during insertion and deletion in $O(\log n)$ time. In particular, we can maintain size so that `insert(x)` and `delete(x)` are supported in $O(\log n)$ time. Write a new function on a red-black tree, `count(D, x)`, which returns the number of elements larger than $x$ in the red-black tree $D$. Your function should work even if $x$ is not in the red-black tree. Show your function runs in $O(\log n)$ time.

## 4. $x$ is an integer. All nodes in a BST T are primes. Design an algorithm to check $x$’s primality. If $x$ is a prime and its key exists in the BTS already, return $1$; if $x$ is a prime but its key does not exist in the BTS, insert it into the BST; if $x$ is not a prime, return $0$.

<!-- hotfix: KaTeX -->
<!-- https://github.com/yzane/vscode-markdown-pdf/issues/21/ -->
<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
<script type="text/x-mathjax-config">MathJax.Hub.Config({ tex2jax: { inlineMath: [['$', '$']]}, messageStyle: 'none' });</script>
