# IIT CS430

Spring 2023 Introduction to Algorithms at Illinois Tech.

## [Sorting Algorithms](https://big-o.io/algorithms/)

| Sorting | Best ($\Omega$) | Average ($\Theta$) | Worst ($O$) |
| --- | --- | --- | --- |
| **Comparison** | | | |
| Bubble Sort | $\color{green} n$ | $\color{red} n^2$ | $\color{red} n^2$ |
| Cycle Sort | $\color{red} n^2$ | $\color{red} n^2$ | $\color{red} n^2$|
| Heapsort | $\color{orange} n \log(n)$ | $\color{orange} n \log(n)$ | $\color{orange} n \log(n)$ |
| Insertion Sort | $\color{green} n$ | $\color{red} n^2$ | $\color{red} n^2$ |
| Merge Sort | $\color{orange} n \log(n)$ | $\color{orange} n \log(n)$ | $\color{orange} n \log(n)$ |
| Quicksort | $\color{orange} n \log(n)$ | $\color{orange} n \log(n)$ | $\color{red} n^2$ |
| Selection Sort | $\color{red} n^2$ | $\color{red} n^2$ | $\color{red} n^2$ |
| Shellsort | $\color{orange} n \log(n)$ | $\color{OrangeRed} n (\log(n))^2$ | $\color{OrangeRed} n (\log(n))^2$ |
| **Non-Comparison** | | | |
| Radix Sort | $n k$ | $n k$ | $n k$ |

## Theories

| Theory | Equation | Where |
| --- | --- | --- |
| Expected values | $E(X) = \displaystyle \sum_{i=1}^{n} x_i . p_i$ | |
| Taylor series | $\displaystyle \sum_{n=0}^{\infty} \frac{f^{(n)}(a)}{n!} (x-a)^n$ | $a$ is real or complex number.<br>$f^{(n)}(a)$ is $n$-th derivative of $f$ evaluated at the point $a$. |

### Master Theorem

| # | Case | Constant | Equation |
| ---: | --- | --- | --- |
| | **Base** | | |
| 1 | $f(n) = O(n^{\log_b a-e})$ | $e>0$ | $T(n) = \Theta(n^{\log_b a})$ |
| 2 | $f(n) = O(n^{\log_b a})$ | | $T(n) = \Theta(n^{\log_b a} \log(n))$ |
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

<!-- hotfix: KaTeX -->
<!-- https://github.com/yzane/vscode-markdown-pdf/issues/21/ -->
<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
<script type="text/x-mathjax-config">MathJax.Hub.Config({ tex2jax: { inlineMath: [['$', '$']]}, messageStyle: 'none' });</script>
