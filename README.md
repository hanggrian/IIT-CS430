# IIT CS430

Spring 2023 Introduction to Algorithms at Illinois Tech.

## [Sorting Algorithm](https://en.wikipedia.org/wiki/Sorting_algorithm)

| Sorting | Best $\bf \Omega$ | Average $\bf \Theta$ | Worst $\bf O$ | Memory | Stable |
| --- | :---: | :---: | :---: | :---: | :---: |
| **Comparison** | | | |
| Quicksort | $\color{green} n \log n$ | $\color{green} n \log n$ | $\color{red} n^2$ | $\color{orange} \log n$ | $\color{red} \textsf{✗}$ |
| Merge Sort | $\color{green} n \log n$ | $\color{green} n \log n$ | $\color{green} n \log n$ | $\color{red} n$ | $\color{green} \checkmark$ |
| In-place Merge Sort | | | $\color{orange} n \log^2 n$ | $\color{green} 1$ | $\color{green} \checkmark$ |
| Heapsort | $\color{green} n \log n$ | $\color{green} n \log n$ | $\color{green} n \log n$ | $\color{green} 1$ | $\color{red} \textsf{✗}$ |
| Insertion Sort | $\color{green} n$ | $\color{red} n^2$ | $\color{red} n^2$ | $\color{green} 1$ | $\color{green} \checkmark$ |
| Selection Sort | $\color{red} n^2$ | $\color{red} n^2$ | $\color{red} n^2$ | $\color{green} 1$ | $\color{red} \textsf{✗}$ |
| Bubble Sort | $\color{green} n$ | $\color{red} n^2$ | $\color{red} n^2$ | $\color{green} 1$ | $\color{green} \checkmark$ |
| **Non-Comparison** | | | |
| Bucket Sort (uniform keys) | | $\color{green} n+k$ | $\color{red} n^2 \cdot k$ | $n \cdot k$ | $\color{green} \checkmark$ |
| Bucket Sort (integer keys) | | $\color{green} n+r$ | $\color{green} n+r$ | $n+r$ | $\color{green} \checkmark$ |
| Counting Sort | | $\color{green} n+r$ | $\color{green} n+r$ | $n+r$ | $\color{green} \checkmark$ |
| LSD Radix Sort | $\color{green} n$ | $\color{green} n \cdot \frac{k}{d}$ | $\color{green} n \cdot \frac{k}{d}$ | $n + 2^d$ | $\color{green} \checkmark$ |
| MSD Radix Sort | | $\color{green} n \cdot \frac{k}{d}$ | $\color{green} n \cdot \frac{k}{d}$ | $n + 2^d$ | $\color{green} \checkmark$ |
| MSD Radix Sort (in-place) | | $\color{green} n \cdot \frac{k}{1}$ | $\color{green} n \cdot \frac{k}{1}$ | $2^1$ | $\color{red} \textsf{✗}$ |

## Theories

| Theory | Equation | Where |
| --- | --- | --- |
| Expected values | $E(X) = \displaystyle \sum_{i=1}^{n} x_i . p_i$ | |
| Taylor series | $\displaystyle \sum_{n=0}^{\infty} \frac{f^{(n)}(a)}{n!} (x-a)^n$ | $a$ is real or complex number.<br>$f^{(n)}(a)$ is $n$-th derivative of $f$ evaluated at the point $a$. |

### Master Theorem

| # | Case | Where | Equation |
| ---: | --- | --- | --- |
| | **Base** | | |
| 1 | $f(n) = O(n^{\log_b a-e})$ | $e>0$ | $T(n) = \Theta(n^{\log_b a})$ |
| 2 | $f(n) = O(n^{\log_b a})$ | | $T(n) = \Theta(n^{\log_b a} \log n)$ |
| 3 | $f(n) = O(n^{\log_b a-e})$<br>$a . f(\frac{n}{b}) \le c . f(n)$ | $e>0$<br>$c < 1$ | $T(n) = \Theta(f(n))$ |
| | **Extended** | | |
| 1 | $a . f(\frac{n}{b}) = c . f(n)$ | $c<1$ | $T(n) = \Theta(f(n))$ |
| 2 | $a . f(\frac{n}{b}) = c . f(n)$ | $c>1$ | $T(n) = \Theta(n^{\log_b a})$ |
| 3 | $a . f(\frac{n}{b}) = f(n)$ | | $T(n) = \Theta(f(n) \log_b n)$ |

#### Decision Tree

- If $a > b^k$, then $T(n) = \Theta(n^{\log^a_b})$.
- If $a = b^k$
  - If $p > -1$, then $T(n) = \Theta(n^{\log^a_b} \log^{p+1} n)$
  - If $p = -1$, then $T(n) = \Theta(n^{\log^a_b} \log \log n)$
  - If $p < -1$, then $T(n) = \Theta(n^{\log^a_b})$
- If $a < b^k$
  - If $p \ge 0$, then $T(n) = \Theta(n^k \log^p n)$
  - If $p < 0$, then $T(n) = O(n^k)$

## Trees

<!-- hotfix: KaTeX -->
<!-- https://github.com/yzane/vscode-markdown-pdf/issues/21/ -->
<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
<script type="text/x-mathjax-config">MathJax.Hub.Config({ tex2jax: { inlineMath: [['$', '$']]}, messageStyle: 'none' });</script>
