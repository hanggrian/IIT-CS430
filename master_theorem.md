# [Master theorem](https://brilliant.org/wiki/master-theorem/)

The **master theorem** provides a solution to [recurrence relations](https://brilliant.org/wiki/recurrence-relations/)
of the form

$$T(n) = aT(\frac{n}{b}) + f(n) ,$$

for constants $a \ge 1$ and $b > 1$ with $f$ asymptotically positive. Such
recurrences occur frequently in the runtime analysis of many commonly
encountered algorithms.

## Introduction

Many algorithms have a runtime of the form

$$T(n) = aT(\frac{n}{b}) + f(n) ,$$

where $n$ is the size of the input and $a$ $(\ge 1)$ and $b$ $(> 1)$ are
constants with $f$ symptotically positive. For instance, one can show that
runtime of the [merge sort](https://brilliant.org/wiki/merge/) algorithm
satisfies

$$T(n) = 2T(\frac{n}{2}) + n .$$

Similarly, traversing a [binary tree](https://brilliant.org/wiki/binary-tree/)
takes time

$$T(n) = 2T(\frac{n}{2}) + O(1) .$$

By comparing $\log_b a$ to the asymptotic behavior of $f(n)$, the **master
theorem** provides a solution to many frequently seen recurrences.

## Statement of the Master Theorem

First, consider an algorithm with a recurrence of the form

$$T(n) = aT(\frac{n}{b}) ,$$

where $a$ represents the number of children each node has, and the runtime of
each of the three initial nodes is the runtime of $T(\frac{n}{b})$. The tree has
a depth of $\log_b n$ and depth $i$ contains $a^i$ nodes. So there
are $a^{\log_b n} = n^{\log_b a}$ leaves, and hence the [runtime](https://brilliant.org/wiki/big-o-notation/)
is $\Theta(n^{\log_b a})$.

![](https://ds055uzetaobb.cloudfront.net/brioche/uploads/it0XGmnWcb-mastertheorem.png?width=2400)

Intuitively, the master theorem argues that if an asymptotically positive
function $f$ is added to the recurrence so that one instead has

$$T(n) = aT(\frac{n}{b}) + f(n) ,$$

it is possible to determine the asymptotic form of $T$ based on relative
comparison between $f$ and $n^{\log_b a}$.

> ##### THEOREM
>
> ### Master Theorem
>
> Given a recurrence of the form
>
> $$T(n) = aT(\frac{n}{b}) + f(n) ,$$
>
> for constants $a \ge 1$ and $b > 1$ with $f$ asymptotically positive, the
  following statements are true:
> - **Case 1.** If $f(n) = O(n^{\log_b a-\epsilon})$ for some $\epsilon > 0$,
    then $T(n) = \Theta(n^{\log_b a})$.
> - **Case 2.** If $f(n) = \Theta(n^{\log_b a)}$,
    then $T(n) = \Theta(n^{\log_b a} \log n)$
> - **Case 3.** If $f(n) = \Omega(n^{\log_b a+\epsilon})$ for
    some $\epsilon > 0$ (and $af(\frac{n}{b}) \le cf(n)$ for some $c < 1$ for
    all $n$ sufficiently large), then $T(n) = \Theta(f(n))$

Simply put, if $f(n)$ is polynomially smaller than $n^{\log_b a}$,
then $n^{\log_b a}$ dominates, and the runtime is $\Theta(n^{\log_b a})$.
If $f(n)$ is instead polynomially larger than $n^{\log_b a}$, then $f(n)$
dominates, and the runtime is $\Theta(f(n))$. Finally, if $f(n)$
and $n^{\log_b a}$ are asymptotically the same,
then $T(n) = \Theta(n^{\log_b a} \log n)$

Note that the master theorem does not provide a solution for all $f$. In
particular, if $f$ is smaller or larger than $n^{\log_b a}$ by less than a
polynomial factor, then none of the three cases are satisfied. For instance,
consider the recurrence

$$T(n) = 3T(\frac{n}{3}) + n \log n.$$

In this case, $n^{\log_b a} = n$. While $f$ is asymptotically larger than $n$,
it is larger only by a logarithmic factor; it is not the case
that $f(n) = O(n^{\log_b a-\epsilon})$ for some $\epsilon > 0$. Therefore, the
master theorem makes no claim about the solution to this recurrence.

## Examples

As mentioned in the introduction, the mergesort algorithm has runtime

$$T(n) = 2T(\frac{n}{2}) + n ,$$

$n^{\log_b a} = n$ and $f(n) = n$, so **case 2** of the master theorem
gives $T(n) = \Theta(n^{}\log_b a \log n) = \Theta(n \log n)$.

Similarly, as mentioned before, traversing a binary tree takes time

$$T(n) = 2T(\frac{n}{2}) + O(1) ,$$

$n^{\log_b a} = n$, which is asymptotically larger than a constant factor, so
**case 1** of the master theorem gives $T(n) = 2T(\frac{n}{2}) + O(1) = \Theta(n)$.

> ##### Example
>
> Consider the recurrence
>
> $$T(n) = 9T(\frac{n}{3}) + n .$$
>
> In this case, $n^{\log_b a} = n^2$ and $f(n) = n$. Since $f(n)$ is
  polynomially smaller than $n^{\log_b a}$, **case 1** of the master theorem implies
  that $T(n) = \Theta(n^2)$.

> ##### Example
>
> Consider the recurrence
>
> $$T(n) = 27T(\frac{n}{3}) + n^3 .$$
>
> In this case, $n^{\log_b a} = n^3$ and $f(n) = n^3$. Since $f(n)$ is
  asymptotically the same as $n^{\log_b a}$, **case 2** of the master theorem
  implies that $T(n) = \Theta(n^3 \log n)$.

> ##### Example
>
> Consider the recurrence
>
> $$T(n) = 2T(\frac{n}{2}) + n^2 .$$
>
> In this case, $n^{\log_b a} = n$ and $f(n) = n^2$. Since $f(n)$ is
  asymptotically larger than $n^{\log_b a}$ but by less than a polynomial
  factor. Therefore, the master theorem makes no claim about the solution to the
  recurrence.
