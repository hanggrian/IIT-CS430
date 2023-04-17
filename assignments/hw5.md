<!-- hotfix: KaTeX -->
<!-- https://github.com/yzane/vscode-markdown-pdf/issues/21/ -->
<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
<script type="text/x-mathjax-config">MathJax.Hub.Config({ tex2jax: { inlineMath: [['$', '$']] }, messageStyle: 'none' });</script>

# [Homework 5](https://github.com/hendraanggrian/IIT-CS430/blob/assets/assignments/hw5.pdf)

## Problem 1

> Refer to the weighted directed graph below and adopt the Bellman-Ford to find
  shortest paths sourced from **vertex $\bf 0$** to all other vertices. Answer the
  following questions.
>
> ![Figure 1](https://github.com/hendraanggrian/IIT-CS430/raw/assets/assignments/hw5_fig1.jpg)

### Subproblem 1A

> Present the adjacent matrix.

### Subproblem 1B

> Demonstrate the procedure of shortest paths disclosure in the following table
  by adopting Bellman-Ford algorithm. Use row $1$ and $2$ as examples. You may add
  more rows when necessary.
>
> | $Round --k$ | $Dist^k[0]$ | $Dist^k[1]$ | $Dist^k[2]$ | $Dist^k[3]$ | $Dist^k[4]$ | $Dist^k[5]$ | $Dist^k[6]$ |
> | --- | --- | --- | --- | --- | --- | --- | --- |
> | 1 | 0 | 6 | 5 | 5 | $\infin$ | $\infin$ | $\infin$ |
> | 2 | 0 | 3 | 3 | 5 | 5 | 4 | $\infin$ |
> | 3 | | | | | | | |
> | 4 | | | | | | | |
> | 5 | | | | | | | |
> | 6 | | | | | | | |

### Subproblem 1C

> Implement the function to find all shortest paths from $0$ to other vertices by
  Bellman-Ford. Your implementation should print the shortest paths. (Preferred
  Java or C++). Run your code and present the shortest paths found by your
  implementation.

## Problem 2

> Argue that if all edge weights of an undirected graph are positive, then any
  subset of edges that connects all vertices and has minimum total weight must
  be a tree.

## Problem 3

> Given an unweighted undirected graph $G$, and a vertex pair $u$ and $v$,
  please give out a pseudo code returning $T$ if $v$ is reachable from $u$.
  Otherwise return $F$. Analyze the time complexity of your algorithm.

## Problem 4

> Some students enroll in some events in athletics. Below shows students names
  and their enrolled events.
>
> | Name | Events |
> | --- | --- |
> | Zach Williams | $A\ B\ E$ |
> | Jennifer Hopkins | $C\ D$ |
> | Ivan Green | $C\ E\ F$ |
> | Douglas May | $D\ F\ A$ |
> | Katherine Nojwoi | $B\ F$ |
>
> But some events may be held with a time conflict. To find out if each student
  could succeed participating in all events he/she enrolls in, we refer to the
  graph $G$ below. In $G$, if two events have no time conflict, they are
  connected by an edge. Give out a pseudo code to return $T$ if a student’s
  enrolled events have no conflict and $F$ otherwise.
>
> ![Figure 2](https://github.com/hendraanggrian/IIT-CS430/raw/assets/assignments/hw5_fig2.jpg)
