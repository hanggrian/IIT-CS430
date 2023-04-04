<!-- hotfix: KaTeX -->
<!-- https://github.com/yzane/vscode-markdown-pdf/issues/21/ -->
<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
<script type="text/x-mathjax-config">MathJax.Hub.Config({ tex2jax: { inlineMath: [['$', '$']] }, messageStyle: 'none' });</script>

# [Homework 4](https://github.com/hendraanggrian/IIT-CS430/blob/assets/assignments/hw4.pdf)

## Problem 1

> Sally is hosting an Internet auction to sell at most $n$ widgets and maximize
  her income from the sale. She receives $m$ bids, each of the form "I want
  exactly $k_i$ widgets for $d_i$ dollars," for $i = 1,2,\ldots,m$. (you can
  assume each $k_i$ and each $d_i$ are integers) Characterize her optimization
  problem similar to a problem we covered in class. Explain the algorithm to
  solve her problem and prove it is optimal. How does the solution change if
  each bidder is willing to accept partial lots (an integer amount less
  than $k_i$ widgets)?

## Problem 2

> We consider the problem of placing towers along a straight road, so that every
  building on the road receives cellular service. Assume that a building
  receives cellular service if it is within one mile of a tower

### 2A

> Devise an algorithm that uses the minimum number of towers possible to provide
  cell service to $d$ buildings located at positions $x_1,x_2,\ldots,x_d$ from
  the start of the road.

### 2B

> Prove that the algorithm you devised produces an optimal solution, that is,
  that it uses the fewest towers possible to provide cellular service to all
  buildings.

## Problem 3

> An array cost[] consists of integers. $cost_i$ is the cost to climb from
  step $i$. Once $cost_i$ is paid, you can choose 1 or 2 steps to take forward.
  Initially, you can start from either step 0 or step 1. Please design an
  algorithm to return the minimal cost to arrive at the top of the stairs, which
  is step $n$.
>
> For example:
>
> ```
> cost[]=(1,100,1,1,1,90,1,1,80,1)
> The minimal cost is 6.
> Solution:
> Start from step 0;
> Pay 1, and take 2 steps, reaches step2;
> Pay 1, and take 2 steps, reaches step4;
> Pay 1, and take 2 steps, reaches step6;
> Pay 1, and take 1 step, reaches step7;
> Pay 1, and take 2 steps, reaches step9;
> Pay 1, and take 1 step, reaches the top.
> Total cost: 6
