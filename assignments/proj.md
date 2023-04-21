<!-- hotfix: KaTeX -->
<!-- https://github.com/yzane/vscode-markdown-pdf/issues/21/ -->
<script type="text/javascript" src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
<script type="text/x-mathjax-config">MathJax.Hub.Config({ tex2jax: { inlineMath: [['$', '$']] }, messageStyle: 'none' });</script>

# [Project](https://github.com/hendraanggrian/IIT-CS430/blob/assets/assignments/proj.pdf): Cheapest Merchandise

> ## Instructions
>
> CS430 project creates an application program. This project can be done on an
  individual or a group basis. The number of a group SHOULD NOT exceed 3. In
  expected circumstances you are fulfilling the programming in your own group
  with a strong relation to the contents of this course. Given any input, your
  system must show the output with necessary explanation. Note that you must
  implement all functions yourself and must not rely on any language libraries.
>
> ## Outcomes
>
> - Source code as a compressed package.
> - `input.txt`, `output.txt`.
> - A concise report analyzing the algorithm design, running on some test cases
    and performances with all group members' names on it.
> - An auxiliary document clarifying the running environment.
> - All the above materials should be submitted in groups (**1 submission PER
    GROUP**).
>
> ## Evaluation
>
> | Topic | Points |
> | --- | ---: |
> | Functional correctness | 10 |
> | Runnability and robust | 3 |
> | Friendly UI | 2 |
> | Report writing | 5 |
>
> ## Tasks
>
> **Most profitable purchase**.
>
> **Problem description**: each merchandise for sale has a ticket price. For
  example, one silk flower for $\$2$ and a vase for $\$5$. But stores always
  provide some promotions for bulk purchase or combinations. For
  example, $3\textsf{ silk}$ flowers for $\$5$
  or $2\textsf{ vases}$ + $1\textsf{ silk flower}$ for $\$10$. Design and
  implement a program to find out the minimal payment for a purchase.
>
> **Input**: two `.txt` files.
>
> 1. `input.txt`. This file contains the purchase intention and organized as:
>     - line1: the number of merchandises to buy.
>     - line2: identifier/code of the first merchandise; amount; ticket price.
>     - line3: identifier/code of the first merchandise; amount; ticket price.
>     - ...
> 2. `output.txt`. This file addresses the promotion information and oragized
      as:
>     - line1: the number of the promotions that are in effect presently;
>     - line2: the number of merchandise types; id1; amount1; id2; amount2; ...
>       promotion price
>     - line3: the number of merchandise types; id1; amount1; id2; amount2; ...
>       promotion price
> 3. `promotions.txt` and `price.txt` will be provided by your instructor.
>
> **Output**: one `.txt` file.
>
> `output.txt`. This file contains the purchase plan and the optimal amount to
  pay.
>
> **Example**:
>
> ```
> input.txt    promotions.txt    output.txt
> 2            2                 7 2 2
> 7 3 2        1 7 3 5           7 1 8 2 10
> 8 2 5        2 7 1 8 2 10      14
> ```

![App preview.](https://github.com/hendraanggrian/IIT-CS430/raw/assets/cheapest-merchandise/preview.png)

A 3-columns windowed multi-platform desktop app.

- [DMG](https://github.com/hendraanggrian/IIT-CS430/releases/download/proj/cheapest_merchandise-1.0.dmg)
  &ndash; macOS moutable with one-file application inside.
- [EXE](https://github.com/hendraanggrian/IIT-CS430/releases/download/proj/cheapest_merchandise-1.0.exe)
  &ndash; Windows installer, select directory to install to.
- [JAR](https://github.com/hendraanggrian/IIT-CS430/releases/download/proj/cheapest_merchandise-1.0.jar)
  &ndash; JRE executable that requires version 17+.

## Tech stack

- *JavaFX* GUI:
  - Reactive interface, texts are updated real-time with `Observable`.
  - Handles file import/export with native picker.
- *Gradle* project management tool:
  - Essentially Maven with minimalistic syntax.
- *Kotlin* language:
  - Essentially Java with minimalistic syntax.
- *Google* libraries:
  - [Guava](https://github.com/google/guava/): Critical for its `Multimap`,
    which is a `Map` with duplicate keys allowed.
  - [Truth](https://github.com/google/truth/): Comprehensive testing for
    collection.

The library use is expansive, but I believe does not violate the code of conduct
that states:

> Note that you must implement all functions yourself and must not rely on any
  language libraries.

Because none of these are particularly mathematics-focused.

## Data structures

### Price

![Price data structure.](https://github.com/hendraanggrian/IIT-CS430/raw/assets/cheapest-merchandise/dsa_data_prices.png)

Each price is stored in `HashMap`, a standard hash table. We can also store this
information in an `ArrayList` or `HashSet`, but getting a price can be $O(n)$
because we are searching price by `id` instead of collection index.

```kotlin
data class Price(
    val id: Int,
    override var amount: Int,
    override val price: Double
) : Merchandise {

    override fun toString(): String = "$id $amount ${price.formatted}"
}
```

### Promotion

![Promotions data structure.](https://github.com/hendraanggrian/IIT-CS430/raw/assets/cheapest-merchandise/dsa_data_promotions.png)

Each promotion is stored in `TreeMultimap`, which is a hash table with features
such as:

- **Ordered keys**: Highest saving is at the bottom, useful for greedy
  algorithm.
- **Duplicate keys**: With native `TreeMap`, inserting duplicate key will
  override the value. This cannot stand, because some promotions may have the
  same saving but completely different items.

```kotlin
data class Promotion(
    val items: List<Item>,
    val price: Double
) : Comparable<Promotion> {

    data class Item(
        val id: Int,
        override val amount: Int,
        override val price: Double
    ) : Merchandise

    override fun toString(): String =
        "${items.joinToString(" ") { "${it.id} ${it.amount}" }} ${price.formatted}"

    override fun compareTo(other: Promotion): Int = price.compareTo(other.price)
}
```

### Parsing

This is how the text are parsed into JVM objects.

```kotlin
fun parse(prices: String, promotions: String): String {
    check(prices.isNotBlank()) { "Empty input." }
    check(promotions.isNotBlank()) { "Empty promotions." }

    val priceMap = mutableMapOf<Int, Price>()
    val promotionMultimap = TreeMultimap.create<Double, Promotion>()
    prices.forEachLine {
        val parts = it.split(' ')
        check(parts.size == 3) { "Invalid price for '$it'" }
        priceMap += parts[0].toInt() to
            Price(parts[0].toInt(), parts[1].toInt(), parts[2].toDouble())
    }
    promotions.forEachLine { s ->
        val parts = s.split(' ')
        check(parts.size >= 4) { "Invalid promotion for '$s'" }
        val pairCount = parts.first().toInt()
        val items = mutableListOf<Promotion.Item>()
        var id: Int? = null
        parts.drop(1).take(pairCount * 2).forEach {
            if (id != null) {
                items += Promotion.Item(id!!, it.toInt(), priceMap[id]!!.price)
                id = null
            } else {
                id = it.toInt()
            }
        }
        val price = parts.last().toDouble()
        val saving = items.sumOf { it.worth } - price
        promotionMultimap.put(saving, Promotion(items, price))
    }
    return onParse(priceMap, promotionMultimap)
}
```

## Algorithms

### Greedy

![Greedy algorithm.](https://github.com/hendraanggrian/IIT-CS430/raw/assets/cheapest-merchandise/dsa_algo_greedy.png)

With greedy algorithm, the best promotion is determined by comparing their
savings, that is, actual price of all items combined minus promotion bundle
price.

- Find the best promotion that involves current item, use as much as possible
  until any of the item is depleted.
- Deduce leftovers, if any, at usual price rate. Go on to next item.

Not the best algorithm because it only consider 1 best promotion.

```kotlin
override fun onParse(
    priceMap: Map<Int, Price>,
    promotionMultimap: Multimap<Double, Promotion>
): String {
    val sb = StringBuilder()
    val keysIterator = priceMap.keys.iterator()
    var spent = 0.0
    while (keysIterator.hasNext()) {
        val item = priceMap[keysIterator.next()]!!
        val bestPromotion = promotionMultimap.values().lastOrNull { purchase ->
            purchase.items.any { it.id == item.id && it.amount <= item.amount }
        }
        // use promotions
        if (bestPromotion != null) {
            while (bestPromotion.items.all { priceMap[it.id]!!.amount - it.amount >= 0 }) {
                sb.appendLine(bestPromotion.toString())
                spent += bestPromotion.price
                bestPromotion.items.forEach { priceMap[it.id]!!.amount -= it.amount }
            }
        }
        // deduce leftover with non-promotions
        if (item.amount > 0) {
            sb.appendLine(item.toString())
            spent += item.worth
            item.amount = 0
        }
    }
    return sb.append(spent.formatted).toString()
}
```

### DFS

![DFS algorithm.](https://github.com/hendraanggrian/IIT-CS430/raw/assets/cheapest-merchandise/dsa_algo_dfs.png)

With recursive function, the saving keys stored in `priceMultimap` is no longer
necessary. It is now indexed as `ArrayList`.

- Gather total items' worth as `sum`.
- Filter promotion that can be applied to current stock.
- Start a DFS recursive function using current promotion.
- Backtrack to try another promotion.

Provides more accurate result by permutating every promotions. But at the
moment, the string output does not yet work.

```kotlin
override fun onParse(
    priceMap: Map<Int, Price>,
    promotionMultimap: Multimap<Double, Promotion>
): String {
    val sb = StringBuilder()
    val spent = dfs(priceMap, promotionMultimap.values().toList(), 0)
    return sb.append(spent.formatted).toString()
}

private fun dfs(
    priceMap: Map<Int, Price>,
    promotionList: List<Promotion>,
    i: Int
): Double {
    var sum = priceMap.values.sumOf { it.worth }
    for (j in i until promotionList.size) {
        val promotion = promotionList[j]
        if (promotion.items.all { priceMap[it.id]!!.amount >= it.amount }) {
            // use special
            promotion.items.forEach { priceMap[it.id]!!.amount -= it.amount }
            sum = minOf(sum, promotion.price + dfs(priceMap, promotionList, i))
            // backtrack
            promotion.items.forEach { priceMap[it.id]!!.amount += it.amount }
        }
    }
    return sum
}
```

## Sample

![PDF sample.](https://github.com/hendraanggrian/IIT-CS430/raw/assets/cheapest-merchandise/sample_pdf.png)

![Blackboard 1 sample.](https://github.com/hendraanggrian/IIT-CS430/raw/assets/cheapest-merchandise/sample_blackboard1.png)

![Blackboard 1 sample.](https://github.com/hendraanggrian/IIT-CS430/raw/assets/cheapest-merchandise/sample_blackboard2.png)

