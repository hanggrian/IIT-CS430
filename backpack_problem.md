# [Backpack problem](https://brilliant.org/wiki/backpack-problem/)

The **backpack problem** (also known as the "Knapsack problem") is a widely
known combinatorial optimization problem in computer science. In this wiki, you
will learn how to solve the knapsack problem using dynamic programming.

## Introduction

The backpack problem can be stated as follows:

> ### Definition
>
> Given a set of different items, each one with an associated value and weight,
  determine which items you should pick in order to maximize the value of the
  items without surpassing the capacity of your backpack.

Concretely, imagine we have the following set of valued items and the given
backpack.

> ### Example
>
> Suppose you have a set of 5 items:
>
> | | 1st Item | 2nd Item | 3rd Item | 4th Item | 5th Item
> --- | --- | --- | --- | --- | ---
> Value | $\$5$ | $\$10$ | $\$3$ | $\$2$ | $\$3$
> Weight | $4\ kg$ | $8\ kg$ | $3\ kg$ | $5\ kg$ | $2\ kg$
>
> If your backpack's weight limit is $10\ kg$, what is the optimal solution?
  That is, which items should you take with you?
>
> In this case, the solution is clear. One would take the second and the last
  items, obtaining a value of $\$13$ while meeting the weight limit exactly. We
  achieve the maximum possible value without violating the problem's constraint.

However, evaluating all possibilities is very unpractical in general, so we
would like to know if there is a better way to approach this problem. In fact,
there is, and we will see an algorithm in the next section.

> ##### TRY IT YOURSELF
>
> ![](https://ds055uzetaobb.cloudfront.net/brioche/solvable/e8a12d519a.2d5128d7e4.VIbrEI.jpg?width=250)
>
> Gina is traveling with Tom into the desert, and she'll be carrying all of
  their food. She can carry a maximum of $9.5\ kg$, and has $5500\ cm^3$ of
  space to carry supplies in her bag. Gina can pick from the following
  collection of supplies:
>
> Food item - Weight / Volume / Calories
>
> - granola bars - $240\ g / 400\ cm / 900\ calories$
> - potato chips - $135\ g / 400\ cm / 650\ calories$
> - beef jerky - $2800\ g / 1500\ cm / 5000\ calories$
> - almonds - $410\ g / 410\ cm / 950\ calories$
> - apples - $182\ g / 190\ cm / 95\ calories$
>
> What is the largest number of calories she can bring with them, given her
  constraints?
>
> **Note**: Gina can bring as many as she wants of each of the above items.
​
## The pseudo-code

This problem can be solved using simple recursion and a two-dimensional array.

To begin, we should find a convenient representation for our problem and
carefully define it. We can say that we have a set of $n$ items, each item has
value $v_i$ and weight $w_i$, and our bag has a total weight limit of $W$.

Now we construct an $n \times W$ table:

![](https://ds055uzetaobb.cloudfront.net/brioche/uploads/JSeeekcYlt-table1.png?width=1200)

Each cell in the table has value $t[i,j]$, where $i$ represents the $i^{th}$ row
and $j$ represents the $j^{th}$ column.

$t[i,j]$ is the maximum value we can get using any combination of the set of the
first $i$ items of the list without exceeding a certain weight $j$. With this,
we can already identify a recursion for our table:

> ##### DEFINITION
>
> Recursion for the knapsack problem:
>
> $$(missing equation)$$

Let's try to interpret this recursion:

Suppose you have a certain value $t[i-1,j]$ in your table, which means that the
maximum value you can get using any combination of the first $i-1$ items of your
list and the sum of the weight of each item does not exceed $j$. If we can do
this, it's evident that we can do the same using the first $i$ items of our
list. So we find the first case of our recursion, which is pretty
straightforward:

$$t[i,j] = t[i-1,j] \quad \text{if} \quad w_i > j$$

And how does the second case of the recursion work?

Given $w_i \le j$, we can say that $t[i,j] = t[i-1,j-w_i] + v_i$ because if we
can get a certain value $t[i-1,j-w_i]$ using the first $i-1$ items of the list,
we can also add the $i^{th}$ item of the list to the backpack and it will not
exceed the current limit $j$, because before we get the $i^{th}$ item, the
current weight is $j-w_i$, so if we add the $i^{th}$ item the current weight
will become $j-w_i+w_i=j$, so we maintain the current weight equal to the weight
limit and the new value will be $t[i-1,j-w_i]$ plus the value of the item $v_i$,
then $t[i,j]$ becomes $t[i-1,j-w_i] + v_i$. Nevertheless, this is not always the
best option. If the value of $t[i-1,j]$ is bigger, we will use this value
instead of $t[i-1,j-w_i]$. Remember $t[i,j]$ only computer the maximum value, so
we will always choose the best option.

Finally, the `max()` function evaluates what's the best option (i.e. it finds
the greatest value).

To find the greatest possible value, we just have to get $t[n,W]$ (i.e. the
maximum value possible using the $n$ items of our list while the total wight is
less than the capacity $W$ of our bag).

> ##### THEOREM
>
> ### Complexity of the knapsack problem
>
> In this problem, we employ a matrix of height $n$ and width $W$, and our
  algorithm goes through each cell once, which makes $n \times W$ operations in
  total. Hence the complexity of the knapsack problem $O(n \times W)$.

```python
# n: number of items
# v[i]: value of the i-th item, w[i]: weight of the i-th item
# W = backpack capacity

knapsack(n,v,w,W)
  for j=0 to j=W
    t[0, j]=0
  for i=1 to n
    for j=0 to W
      if w[i]>j
        t[i, j]=t[i - 1, j]
      else
        t[i, j] = max(t[i - 1, j], t[i - 1, j - w[i]]+v[i])
    return t[n][W]
```

Now we will solve the first example:

> ##### EXAMPLE
>
> You are given a set of five items $I_1,I_2,I_3,I_4,I_5$ and each has a
  corresponding value and weight inside brackets $[v_i,w_i]$:
>
> $$I_1 = [5,4], \quad I_2 = [10,8], \quad I_3 = [3,3], \quad I_4 = [2,5], \quad I_5 = [3,2]$$
>
> What's the maximum possible value you can get, knowing that the knapsack's
  weight limit is $10$?
>
> Let's start filling up our table:
>
> We know that all the cells in the first row are zero, hence the following
  table:
>
> ![table2](https://ds055uzetaobb.cloudfront.net/brioche/uploads/hTfsHHoUFp-table2.png?width=1200)
>
> Using the recursion, we know that $t[1,0] = t[1,1] = t[1,2] = t[1,3] = 0$. But
  when $j=4$, we have $t[1,j] = t[1,4] = 5$. This happens because at this point
  $w[i] \le j$, the weight of the item is $w[i] = 4$ and $j = 4$, so it
  satisfies the condition for the second case of the recursion. We know that
>
> $$t[i-1,j] = t[0,4] = 0$$
>
> and that
>
> $$t[i-1, j-w[i]] + v_i = t[0,0] + v_i = 0 + 5 = 5$$
>
> Hence, the maximum here is $5$, and then $t[i,j] = t[1,4] = 5$. Doing the same
  for $j=5$ to $j=10$, we will also get $5$, so our table becomes as follows:
>
> ![table3](https://ds055uzetaobb.cloudfront.net/brioche/uploads/RBj2Dk3GAG-table3.png?width=1200)
>
> Now, $i=2$. We will keep doing the same process. Doing the calculations, we
  will see that $t[2,j]=0$ from $j=0$ to $j=3$ and $t[2,j] = 5$ from $j=4$
  to $j=7$. When $j=8$, we have $w[i] \le j$, so we will have to analyze both
  cases:
>
> $$t[i-1,j] = t[1,8] = 5$$
>
> and
>
> $$t[i-1, j-w[i]] + v_i = t[1,0] + v_i = 0 + 10 = 10$$
>
> The second option is the best one, so
>
> $$t[i,j] = t[2,8] = 10 .$$
>
> Doing the same until we finish the row, we get
>
> ![table4](https://ds055uzetaobb.cloudfront.net/brioche/uploads/RyCckpB2KO-table4.png?width=1200)
>
> Just keep using the recursion to fill up the third and the fourth rows until
  you get to the last one:
>
> ![table5](https://ds055uzetaobb.cloudfront.net/brioche/uploads/V4ns1jLzf8-table5.png?width=1200)
>
> When you reach $t[5,9]$, you will see
>
> $$t[i-1,j] = t[4,9] = 10$$
>
> and
>
> $$t[i-1,j-w[i]] + v_i = t[4,7] + v_i = 8 + 3 = 11 .$$
>
> For the last cell
>
> $$t[i-1,j] = t[4,10] = 10$$
>
> and
>
> $$t[i-1,j-w[i]] + v_i = t[4,8] + v_i = 10 + 3 = 13 .$$
>
> Therefore $t[i,j] = t[5,10] = 13 .$
>
> This is our complete table:
>
> ![table6](https://ds055uzetaobb.cloudfront.net/brioche/uploads/88pJ2vfhUH-table6.png?width=1200)
>
> The maximum value we can get is $t[n,W] = t[5,10] = 13$, which can be achieved
  using the second and the last items. By doing this, the total weight will
  be $10$ (it's equal to the capacity, which is $10$) and the total value will
  be $13$.
>
> Here's a sample code you can use to solve the problem:
>
> ```cpp
> #include <iostream>
> #include <cstring>
>
> /*Knapsack problem*/
>
> //W=Backpack capacity
> //w[i]=i-th item's weight
> //v[i]=i-th item's value
>
> //Compares two values and returns the bigger one
> int maximum(int a,int b){
>   if(a>=b) return a;
>   else return b;
> }
>
> using namespace std;
>
> int main()
> {
>   int W, v[100],w[100];
>   int i,j,n;
>   int table[100][10000];
>
>   //Reads W and the number of items n
>   cin>>W>>n;
>
>   //Reads the weight and the value of each item
>   for(i=1;i<=n;i++){
>     cin>>w[i]>>v[i];
>   }
>
>   //Make every single cell in the first row equal to zero
>   for(j=0;j<=W;j++) table[0][j]=0;
>
>   // Fills the table
>   for(i=1;i<=n;i++)
>     for(j=0;j<=W;j++){
>       //First case of the recursion
>       if(w[i]>j) table[i][j]=table[i-1][j];
>       //Second case
>       else table[i][j]=maximum(table[i-1][j],table[i-1][j-w[i]] + v[i]);
>     }
>
>   cout<<"\n";
>
>   //Shows the table
>   for(i=0;i<=n;i++){
>     cout<<"\n";
>       for(j=0;j<=W;j++){
>         cout<<table[i][j]<<" ";
>     }
>   }
>
>   //Prints the answer
>   cout<<"\nMaximum value:\n";
>
>   cout<<table[n][W];
>
>   cout<<"\n\n";
>
>   return 0;
> }
> ```
