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

A Java 17+ app packaged as native distributions in Mac & Windows.

## Tech stack

- [JavaFX](https://openjfx.io/) UI.
- [Kotlin](https://kotlinlang.org/) language.
- Google libraries:
  - [Guava](https://github.com/google/guava/): Critical for its `Multimap`,
    which is a `Map` with duplicate keys allowed.
  - [Truth](https://github.com/google/truth/): Comprehensive testing for
    collection.

The library use is expansive, but I believe does not violate the code of conduct
that states:

> Note that you must implement all functions yourself and must not rely on any
  language libraries.

Because none of these are particularly mathematics-focused.

## Code

### Using [greedy algorithm](https://github.com/hendraanggrian/IIT-CS430/blob/main/greedy_algorithm.md)

```java
class GreedyParser : Parser() {
    override fun toString(): String = "Greedy parser"

    override fun onParse(
        priceMap: Map<Int, Item>,
        promotionMultimap: Multimap<Double, Purchase>
    ): String {
        val sb = StringBuilder()
        val keysIterator = priceMap.keys.iterator()
        var spent = 0.0
        while (priceMap.values.any { it.amount > 0 } && keysIterator.hasNext()) {
            val item = priceMap[keysIterator.next()]!!
            val bestPromotion = promotionMultimap.values().lastOrNull { purchase ->
                purchase.bundle.any { it.id == item.id && it.amount <= item.amount }
            } ?: continue
            // use promotions
            while (bestPromotion.bundle.all { priceMap[it.id]!!.amount - it.amount >= 0 }) {
                spent += bestPromotion.totalPrice
                bestPromotion.bundle.forEach { priceMap[it.id]!!.amount -= it.amount }
                sb.appendLine(bestPromotion.toString())
            }
            // deduce leftover with non-promotions
            while (item.amount > 0) {
                spent += item.price
                item.amount--
                sb.appendLine(item.toString())
            }
        }
        return sb.append(spent.formatted).toString()
    }
}

abstract class Parser {
    abstract fun onParse(
        priceMap: Map<Int, Item>,
        promotionMultimap: Multimap<Double, Purchase>
    ): String

    fun parse(sample: Sample): String = parse(sample.prices, sample.promotions)

    fun parse(prices: String, promotions: String): String {
        check(prices.isNotBlank()) { "Empty input." }
        check(promotions.isNotBlank()) { "Empty promotions." }
        val priceMap = mutableMapOf<Int, Item>()
        val promotionMultimap = TreeMultimap.create<Double, Purchase>()

        prices.forEachLine {
            val parts = it.split(' ')
            check(parts.size == 3) { "Invalid price for '$it'" }
            priceMap += parts[0].toInt() to Item(
                parts[0].toInt(),
                parts[1].toInt(),
                parts[2].toDouble()
            )
        }
        promotions.forEachLine { s ->
            val parts = s.split(' ')
            check(parts.size >= 4) { "Invalid promotion for '$s'" }
            val pairCount = parts.first().toInt()
            val items = linkedSetOf<Item>()
            var id: Int? = null
            parts.drop(1).take(pairCount * 2).forEach {
                if (id != null) {
                    items += Item(id!!, it.toInt(), priceMap[id!!]!!.price)
                    id = null
                } else {
                    id = it.toInt()
                }
            }
            val totalPrice = parts.last().toDouble()
            promotionMultimap.put(
                items.sumOf { it.amount * it.price } - totalPrice,
                Purchase(items, totalPrice)
            )
        }
        return onParse(priceMap, promotionMultimap)
    }

    private fun String.forEachLine(action: (String) -> Unit) {
        val itemCount = lines().first().trimStart().toInt()
        lines().drop(1).take(itemCount).forEach { action(it.trim()) }
    }
}
```

[View full code](https://github.com/hendraanggrian/IIT-CS430/blob/main/cheapest-merchandise/app/src/main/kotlin/com/example/Parsers.kt)
/ [test](https://github.com/hendraanggrian/IIT-CS430/blob/main/cheapest-merchandise/app/src/test/kotlin/com/example/ParsersTest.kt)
