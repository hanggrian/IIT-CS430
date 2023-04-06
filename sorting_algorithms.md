# [Sorting Algorithm](https://en.wikipedia.org/wiki/Sorting_algorithm)

| Sorting | Best $\bf \Omega$ | Average $\bf \Theta$ | Worst $\bf O$ | Memory | Stable |
| --- | :---: | :---: | :---: | :---: | :---: |
| **Comparison** | | | |
| Quicksort | $\color{green} n \log n$ | $\color{green} n \log n$ | $\color{red} n^2$ | $\color{orange} \log n$ | $\color{red} \textsf{✗}$ |
| Merge Sort | $\color{green} n \log n$ | $\color{green} n \log n$ | $\color{green} n \log n$ | $\color{red} n$ | $\color{green} \checkmark$ |
| In-place Merge Sort | $-$ | $-$ | $\color{orange} n \log^2 n$ | $\color{green} 1$ | $\color{green} \checkmark$ |
| Heapsort | $\color{green} n \log n$ | $\color{green} n \log n$ | $\color{green} n \log n$ | $\color{green} 1$ | $\color{red} \textsf{✗}$ |
| Insertion Sort | $\color{green} n$ | $\color{red} n^2$ | $\color{red} n^2$ | $\color{green} 1$ | $\color{green} \checkmark$ |
| Selection Sort | $\color{red} n^2$ | $\color{red} n^2$ | $\color{red} n^2$ | $\color{green} 1$ | $\color{red} \textsf{✗}$ |
| Bubble Sort | $\color{green} n$ | $\color{red} n^2$ | $\color{red} n^2$ | $\color{green} 1$ | $\color{green} \checkmark$ |
| **Non-Comparison** | | | |
| Bucket Sort (uniform keys) | $-$ | $\color{green} n+k$ | $\color{red} n^2 \cdot k$ | $n \cdot k$ | $\color{green} \checkmark$ |
| Bucket Sort (integer keys) | $-$ | $\color{green} n+r$ | $\color{green} n+r$ | $n+r$ | $\color{green} \checkmark$ |
| Counting Sort | $-$ | $\color{green} n+r$ | $\color{green} n+r$ | $n+r$ | $\color{green} \checkmark$ |
| LSD Radix Sort | $\color{green} n$ | $\color{green} n \cdot \frac{k}{d}$ | $\color{green} n \cdot \frac{k}{d}$ | $n + 2^d$ | $\color{green} \checkmark$ |
| MSD Radix Sort | $-$ | $\color{green} n \cdot \frac{k}{d}$ | $\color{green} n \cdot \frac{k}{d}$ | $n + 2^d$ | $\color{green} \checkmark$ |
| MSD Radix Sort (in-place) | $-$ | $\color{green} n \cdot \frac{k}{1}$ | $\color{green} n \cdot \frac{k}{1}$ | $2^1$ | $\color{red} \textsf{✗}$ |
