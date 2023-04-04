# [Dynamic Programming](https://brilliant.org/wiki/problem-solving-dynamic-programming/)

**Dynamic programming** refers to a problem-solving approach, in which we
precompute and store simpler, similar subproblems, in order to build up the
solution to a complex problem. It is similar to [recursion](https://brilliant.org/wiki/recursion/),
in which calculating the base cases allows us to inductively determine the final
value. This bottom-up approach works well when the new value depends only on
previously calculated values.

An important property of a problem that is being solved through dynamic
programming is that it should have overlapping subproblems. This is what
distinguishes DP from [divide and conquer](https://brilliant.org/wiki/divide-and-conquer/)
in which storing the simpler values isn't necessary.

To show how powerful the technique can be, here are some of the most famous
problems commonly approached through dynamic programming:

1. [**Backpack Problem**](backpack_problem.md): Given a set
  of treasures with known values and weights, which of them should you pick to
  maximize your profit whilst not damaging your backpack which has a fixed capacity?
2. [Egg Dropping](https://brilliant.org/wiki/egg-dropping/): What is the best way to drop $n$ eggs from
  an $m$-floored building to figure out the lowest height from which the eggs
  when dropped crack?
3. Longest Common Subsequence: Given two sequences, which is the longest
  subsequence common to both of them?
4. [Subset Sum Problem](https://brilliant.org/discussions/thread/balance-it-if-you-possibly-can/):
  Given a set and a value $n$, is there a subset the sum of whose elements
  is $n$?
5. [Fibonacci Numbers](https://brilliant.org/wiki/fast-fibonacci-transform/): Is
  there a better way to compute Fibonacci numbers than plain recursion?

In a contest environment, dynamic programming almost always comes up (and often
in a surprising way, no matter how familiar the contestant is with it).

## Motivational example: Change of coins

> What is the minimum number of coins of values $v_1,v_2,v_3,\ldots,v_n$
  required to amount a total of $V$?
>
> You may use a denomination more than once.

### Optiomal substructure

The most important aspect of this problem that encourages us to solve this
through dynamic programming is that it can be simplified to smaller subproblems.

Let $f(N)$ represent the minimum number of coins required for a value of $N$.

Visualize $f(N)$ as a stack of coins. What is the coin at the top of the stack?
It could be any of $v_1,v_2,v_3,\ldots,v_n$. In case it were $v_1$, the rest of
the stack would amount to $N - v_1$; or if it were $v_2$, the rest of the stack
would amount to $N - v_2$ and so on.

How do we decide which is it? Sure enough, we do not know yet. We need to see
which of them minimizes the number of coins required.

Going by the above argument, we could state the problem as follows:

$$f(V) = min(\{ 1+f(V-v_1), 1+f(V-v_2), \ldots, 1+f(V-v_n)\}) .$$

__**Why is there a $+1$?**__

### Overlapping subproblems

It is easy to see that the subproblems could be overlapping.

> ##### EXAMPLE
>
> For example, if we are trying to make a stack of $11 using $1, $2, and $5, our
  look-upo pattern would be like this:
>
> $$
> \begin{array}{rcl}
>   f(11) &=& min(\{ 1+f(10), 1+f(9), 1+f(6) \}) \\
>   &=& min(\{ 1 + min(\{ 1+f(9), 1+f(8), 1+f(5) \}), 1+f(9), 1+f(6) \})
> \end{array} .
> $$
>
> Clearly enough, we'll need to use the value of $f(9)$ several times.

One of the most important aspects of optimizing our algorithms is that we do not
recompute these values. To do this, we compute and store all the values of $f$
from 1 onwards for potential future use.

### Edge cases

The recursion has to bottom out somewhere, in other words, at a known value from
which it can start.

For this problem, we need to take care of two things:

- Zero: It is clear enough that $f(0) = 0$ since we do not require any coins at
  all to make a stack amounting to $0$.
- *Negative* and *Unreachable Values*: One way of dealing with such values is to
  mark them with a sentinel value so that our code deals with them in a special
  way. A good choice of a sentinel is $\infty$, since the minimum value between a reachable value and $\infty$ could never be infinity.

### The Algorithm

Let's sum up the ideas and see how we could implement this as an actual
algorithm:

```python
# V = the value we want, v=the list of available denomenations
def coinsChange(V,v):
  dpTable = [float("inf")]*(V+1)
  dpTable[0] = 0
  for i in xrange(1,V+1):
    for vi in v:
      if (i - vi) >= 0:
          dpTable[i] = min(dpTable[i],1+dpTable[i-vi])
  return dpTable[V]
```

## Recursion with memoization

We have claimed that naive recursion is a bad way to solve problems with
overlapping subproblems. Why is that? Mainly because of all the recomputations
involved.

Another way to avoid this problem is to compute the data first time and store it
as we go, in a top-down fashion.

> ##### EXAMPLE
>
> Let's look at how one could potentially solve the previous coin change problem
  in the memoization way.
>
> ```python
> def coinsChange(V,v):
>   memo = {}
>   def Change(V):
>     if V in memo:
>       return memo[V]
>     if V == 0:
>       return 0
>     if V < 0:
>       return float("inf")
>     memo[V] = min([1+Change(V-vi) for vi in v])
>       return memo[V]
>   return Change(V)

### Dynamic programming vs recursion with caching

| Dynamic Programming | Recursion with Caching |
| --- | --- |
| Faster if many sub-problems are visited as there is no overhead from recursive calls | Intuitive approach |
| The complexity of the program is easier to see | Computes only those subproblems which are necessary |

## Bidimensional dynamic programming: Example

> ##### TRY IT YOURSELF
>
> There are $k$ types of brackets each with its own opening bracket and closing
  bracket. We assume that the first pair is denoted by the numbers $1$
  and $k+1$, the second by $2$ and $k+2$, and so on. Thus the opening brackets
  are denoted by $1,2,\ldots,k$, and the corresponding closing brackets are
  denoted by $k+1,k+2,\ldots,2k$, respectively.
>
> Some sequences with elements from $1,2,\ldots,2k$ form well-bracketed
  sequences while others don't. A sequence is well-bracketed if we can match or
  pair up opening brackets of the same type in such a way that the following
  holds:
>
> 1. Every bracket is paired up.
> 2. In each matched pair, the opening bracket occurs before the closing bracket.
> 3. For a matched pair, any other matched pair lies either completely between
  them or outside them.
>
> In this problem, you are given a sequence of brackets of
  length $N:B[1],\ldots,B[N]$, where each $B[i]$ is one of the brackets. You are
  also given an array of values: $V[1],\ldots,V[N]$.
>
> Among all the subsequences in the Values array, such that the corresponding
  bracket subsequence in the B Array is a well-bracketed sequence, you need to
  find the maximum sum.
>
> **Task**: Solve the above problem for [this](https://gist.githubusercontent.com/anonymous/ddd8ee38e7924d933f93/raw/753c999b818556e8263c93654becaea3cf96bf31/2_7.in) input.
>
> ### Input format
>
> One line, which contains $(2 \times N + 2)$ space separate integers. The first
  integer denotes $N$. The next integer is $k$. THe next $N$ integers
  are $V[1],\ldots,V[N]$. The last $N$ integers are $B[1],\ldots,B[N]$.
>
> ### Constraints
>
> - $1 \le k \le 7$
> - $-10^6 \le V[i] \le 10^6$, for all $i$
> - $1 \le B[i] \le 2k$, for all $i$
>
> ### Illustrated examples
>
> For the examples discussed here, let us assume that $k=2$. The
  sequence $1,1,3$ is not well-bracketed as one of the two $1$'s cannot be
  paired. The sequence $3,1,3,1$ is not well-bracketed as there is no way to
  match the second $1$ to a closing bracket occurring after it. The
  sequence $1,2,3,4$ is not well-bracketed as the matched pair $2,4$ is neither
  completely between the matched pair $1,3$ nor completely outside of it. That
  is, the matched pairs cannot overlap. The sequence $1,2,4,3,1,3$ is
  well-bracketed. We match the first $1$ with the first $3$, the $2$ with
  the $4$, and the second $1$ with the second $3$, satisfying all the $3$
  conditions. If you rewrite these sequences using [, {, ], } instead
  of $1,2,3,4$ respectively, this will be quite clear.
>
> Suppose $N=6$, $k=3$, and the values of $V$ and $B$ are as follows:
>
> ![dptablebrackets](https://ds055uzetaobb.cloudfront.net/brioche/uploads/uLf6mxoF00-dptablebrackets.jpg?width=1200)
>
> Then, the brackets in positions $1,3$ form a well-bracketed sequence $(1,4)$
  and the sum of the values in these positions is $2 (4 + (-2) =2)$. The
  brackets in positions $1,3,4,5$ form a well-bracketed sequence $(1,4,2,5)$ and
  the sum of the values in these positions is $4$. Finally, the brackets in
  positions $2,4,5,6$ form a well-bracketed sequence $(3,2,5,6)$ and the sum of
  the values in these positions is $13$. The sum of the values in
  positions $1,2,5,6$ is $16$ but the brackets in these positions $(1,3,5,6)$ do
  not form a well-bracketed sequence. You can check the best sum from positions
  whose brackets form a well-bracketed sequence is $13$.

We'll try to solve this problem with the help of a dynamic program, in which the
state, or the parameters that describe the problem, consist of two variables.

First, we set up a two-dimensional array `dp[start][end]` where each entry
solves the indicated problem for the part of the sequence between `start`
and `end` inclusive.

We'll try to think what happens when we run across a new `end` value, and need
to solve the new problem in terms of the previously solved subproblems. Here are
all the possibilities:

- When `end <= start`, there are no valid subsequences.
- When `b[end] <= k`, i.e, the last entry is an open bracket, no valid
  subsequence can end with it. Effectively, the result is the same if we hadn't
  included the last entry at all.
- When `b[end] > k`, i.e, the last entry is a closing bracket, one has to find
  the best match for it, or simply ignore it, whichever maximizes the sum.

Can you use these ideas to solve the problem?

## Example: Maximum paths

Very often, dynamic programming helps solve problems that ask us to find the
most profitable (or least costly) path in an implicit graph setting. Let us try
to illustrate this with an example.

> ##### EXAMPLE
>
> You are supposed to start at the top of a number triangle and chose your
  passage all the way down by selecting between the numbers below you to the
  immediate left or right.
>
> Your goal is to maximize the sum of the elements lying in your path.
>
> For example, in the triangle below, the red path maximizes the sum.
>
> ![capture](https://ds055uzetaobb.cloudfront.net/brioche/uploads/dduTz67NXT-capture.PNG?width=1200)

To see the **optimal substructures** and the **overlapping subproblems**, notice
that everytime we make a move from the top to the bottom right or the bottom
left, we are still left with smaller number triangle, much like this:

We could break each of the sub-problems in a similar way until we reach an edge-case at the bottom:

In this case, the solution is `a + max(b,c)`.

A bottom-up dynamic programming solution is to allocate a number triangle that
stores the maximum reachable sum if we were to start from that position. It is
easy to compute the number triangles from the bottom row onward using the fact
that the

```
best from this point=this point+max(best from the left, best from the right).
```

> Let me demonstrate this principle through the iterations.
>
> Iteration 1:
>
> ```
> 8 5 9 3
> ```
>
> Iteration 2:
>
> ```
>   10  13  15
> 8    5    9    3
> ```
>
> Iteration 3:
>
> ```
>      20   19
>   10   13   15
> 8     5    9    3
> ```
>
> Iteration 4:
>
> ```
>         23
>     20    19
>   10   13   15
> 8     5    9    3
> ```
>
> So, the effective best we could do from the top is 23, which is our answer.
