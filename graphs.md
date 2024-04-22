# [Graphs](https://www.programiz.com/dsa/graph/)

In this tutorial, you will learn what a Graph Data Structure is. Also, you will
find representations of a graph.

A graph data structure is a collection of nodes that have data and are connected
to other nodes.

Let's try to understand this through an example. On facebook, everything is a
node. That includes User, Photo, Album, Event, Group, Page, Comment, Story,
Video, Link, Note... anything that has data is a node.

Every relationship is an edge from one node to another. Whether you post a
photo, join a group, like a page, etc., a new edge is created for that
relationship.

![Figure 1-1](https://cdn.programiz.com/sites/tutorial2program/files/facebook-graph.png)

<small>Example of graph data structure</small>

All of facebook is then a collection of these nodes and edges. This is because
facebook uses a graph data structure to store its data.

More precisely, a graph is a data structure $(V, E)$ that consists of

- A collection of vertices $V$.
- A collection of edges $E4, represented as ordered pairs of vertices $(u,v)$.

![Figure 1-2](https://cdn.programiz.com/sites/tutorial2program/files/graph-vertices-edges_0.png)

<small>Vertices and edges</small>

In the graph,

```
V = {0, 1, 2, 3}
E = {(0,1), (0,2), (0,3), (1,2)}
G = {V, E}
```

Terminology | Description
--- | ---
Adjacency | A vertex is said to be adjacent to another vertex if there is an edge connecting them. Vertices $2$ and $3$ are not adjacent because there is no edge between them.
Path | A sequence of edges that allows you to go from vertex $A$ to vertex $B$ is called a path. $0-1$, $1-2$ and $0-2$ are paths from vertex $0$ to vertex $2$.
Directed Graph | A graph in which an edge $(u,v)$ doesn't necessarily mean that there is an edge $(v, u)$ as well. The edges in such a graph are represented by arrows to show the direction of the edge.

### Graph representation

Graphs are commonly represented in two ways:

#### 1. Adjacency matrix

An adjacency matrix is a 2D array of $V \times V$ vertices. Each row and column
represent a vertex.

If the value of any element `a[i][j]` is $1$, it represents that there is an
edge connecting vertex $i$ and vertex $j$.

The adjacency matrix for the graph we created above is:

![Figure 1-3](https://cdn.programiz.com/sites/tutorial2program/files/adjacency-matrix_1.png)

<small>Graph adjacency matrix</small>

Since it is an undirected graph, for edge $(0,2)$, we also need to mark
edge $(2,0)$; making the adjacency matrix symmetric about the diagonal.

Edge lookup(checking if an edge exists between vertex $A$ and vertex $B$) is
extremely fast in adjacency matrix representation but we have to reserve space
for every possible link between all vertices $(V \times V)$, so it requires more
space.

#### 2. Adjacency list

An adjacency list represents a graph as an array of linked lists.

The index of the array represents a vertex and each element in its linked list
represents the other vertices that form an edge with the vertex.

The adjacency list for the graph we made in the first example is as follows:

![Figure 1-4](https://cdn.programiz.com/sites/tutorial2program/files/adjacency-list.png)

<small>Adjacency list representation</small>

An adjacency list is efficient in terms of storage because we only need to store
the values for the edges. For a graph with millions of vertices, this can mean a
lot of saved space.

### Graph operations

The most common graph operations are:

- Check if the element is present in the graph.
- Graph Traversal.
- Add elements (vertex, edges) to graph.
- Finding the path from one vertex to another.

## [Bellman Ford's algorithm](https://www.programiz.com/dsa/bellman-ford-algorithm/)

Algorithm | Best | Average | Worst | Space
--- | --- | --- | --- | ---
Bellman Ford | $O(E)$ | $O(VE)$ | $O(VE)$ | $O(V)$

Bellman Ford algorithm helps us find the shortest path from a vertex to all
other vertices of a weighted graph.

It is similar to [Dijkstra's algorithm](#dijkstras-algorithm) but it can work
with graphs in which edges can have negative weights.

### Why would one ever have edges with negative weights in real life?

Negative weight edges might seem useless at first but they can explain a lot of
phenomena like cashflow, the heat released/absorbed in a chemical reaction, etc.

For instance, if there are different ways to reach from one chemical A to
another chemical B, each method will have sub-reactions involving both heat
dissipation and absorption.

If we want to find the set of reactions where minimum energy is required, then
we will need to be able to factor in the heat absorption as negative weights and
heat dissipation as positive weights.

### Why do we need to be careful with negative weights?

Negative weight edges can create negative weight cycles i.e. a cycle that will
reduce the total path distance by coming back to the same point.

![Figure 2-1](https://cdn.programiz.com/sites/tutorial2program/files/negative-weight-cycle_1.png)

<small>Negative weight cycles can give an incorrect result when trying to find out the shortest path</small>

Shortest path algorithms like Dijkstra's Algorithm that aren't able to detect
such a cycle can give an incorrect result because they can go through a negative
weight cycle and reduce the path length.

### How Bellman Ford's algorithm works

Bellman Ford algorithm works by overestimating the length of the path from the
starting vertex to all other vertices. Then it iteratively relaxes those
estimates by finding new paths that are shorter than the previously
overestimated paths.

By doing this repeatedly for all vertices, we can guarantee that the result is
optimized.

![Figure 2-2](https://cdn.programiz.com/sites/tutorial2program/files/Bellman-Ford-Algorithm-1.png)

<small>Step-1 for Bellman Ford's algorithm</small>

![Figure 2-3](https://cdn.programiz.com/sites/tutorial2program/files/Bellman-Ford-Algorithm-2.png)

<small>Step-2 for Bellman Ford's algorithm</small>

![Figure 2-4](https://cdn.programiz.com/sites/tutorial2program/files/Bellman-Ford-Algorithm-3.png)

<small>Step-3 for Bellman Ford's algorithm</small>

![Figure 2-5](https://cdn.programiz.com/sites/tutorial2program/files/Bellman-Ford-Algorithm-4.png)

<small>Step-4 for Bellman Ford's algorithm</small>

![Figure 2-6](https://cdn.programiz.com/sites/tutorial2program/files/Bellman-Ford-Algorithm-5.png)

<small>Step-5 for Bellman Ford's algorithm</small>

![Figure 2-7](https://cdn.programiz.com/sites/tutorial2program/files/Bellman-Ford-Algorithm-6.png)

<small>Step-6 for Bellman Ford's algorithm</small>

### Bellman Ford pseudocode

We need to maintain the path distance of every vertex. We can store that in an
array of size $v$, where $v$ is the number of vertices.

We also want to be able to get the shortest path, not only know the length of
the shortest path. For this, we map each vertex to the vertex that last updated
its path length.

Once the algorithm is over, we can backtrack from the destination vertex to the
source vertex to find the path.

```
function bellmanFord(G, S)
  for each vertex V in G
    distance[V] <- infinite
      previous[V] <- NULL
  distance[S] <- 0

  for each vertex V in G
    for each edge (U,V) in G
      tempDistance <- distance[U] + edge_weight(U, V)
      if tempDistance < distance[V]
        distance[V] <- tempDistance
        previous[V] <- U

  for each edge (U,V) in G
    if distance[U] + edge_weight(U, V) < distance[V]
      Error: Negative Cycle Exists

  return distance[], previous[]
```

### Bellman Ford vs Dijkstra

Bellman Ford's algorithm and Dijkstra's algorithm are very similar in structure.
While Dijkstra looks only to the immediate neighbors of a vertex, Bellman goes
through each edge in every iteration.

![Figure 2-8](https://cdn.programiz.com/sites/tutorial2program/files/bellman-ford-vs-dijkstra.jpg)

<small>Bellman Ford's Algorithm vs Dijkstra's Algorithm</small>

### Code for Bellman Ford

```java
class CreateGraph {
  // CreateGraph - it consists of edges
  class CreateEdge {
    int s, d, w;

    CreateEdge() {
      s = d = w = 0;
    }
  };

  int V, E;
  CreateEdge edge[];

  // Creates a graph with V vertices and E edges
  CreateGraph(int v, int e) {
    V = v;
    E = e;
    edge = new CreateEdge[e];
    for (int i = 0; i < e; ++i)
      edge[i] = new CreateEdge();
  }

  void BellmanFord(CreateGraph graph, int s) {
    int V = graph.V, E = graph.E;
    int dist[] = new int[V];

    // Step 1: fill the distance array and predecessor array
    for (int i = 0; i < V; ++i)
      dist[i] = Integer.MAX_VALUE;

    // Mark the source vertex
    dist[s] = 0;

    // Step 2: relax edges |V| - 1 times
    for (int i = 1; i < V; ++i) {
      for (int j = 0; j < E; ++j) {
        // Get the edge data
        int u = graph.edge[j].s;
        int v = graph.edge[j].d;
        int w = graph.edge[j].w;
        if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v])
          dist[v] = dist[u] + w;
      }
    }

    // Step 3: detect negative cycle
    // if value changes then we have a negative cycle in the graph
    // and we cannot find the shortest distances
    for (int j = 0; j < E; ++j) {
      int u = graph.edge[j].s;
      int v = graph.edge[j].d;
      int w = graph.edge[j].w;
      if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
        System.out.println("CreateGraph contains negative w cycle");
        return;
      }
    }

    // No negative w cycle found!
    // Print the distance and predecessor array
    printSolution(dist, V);
  }

  // Print the solution
  void printSolution(int dist[], int V) {
    System.out.println("Vertex Distance from Source");
    for (int i = 0; i < V; ++i)
      System.out.println(i + "\t\t" + dist[i]);
  }

  public static void main(String[] args) {
    int V = 5; // Total vertices
    int E = 8; // Total Edges

    CreateGraph graph = new CreateGraph(V, E);

    // edge 0 --> 1
    graph.edge[0].s = 0;
    graph.edge[0].d = 1;
    graph.edge[0].w = 5;

    // edge 0 --> 2
    graph.edge[1].s = 0;
    graph.edge[1].d = 2;
    graph.edge[1].w = 4;

    // edge 1 --> 3
    graph.edge[2].s = 1;
    graph.edge[2].d = 3;
    graph.edge[2].w = 3;

    // edge 2 --> 1
    graph.edge[3].s = 2;
    graph.edge[3].d = 1;
    graph.edge[3].w = 6;

    // edge 3 --> 2
    graph.edge[4].s = 3;
    graph.edge[4].d = 2;
    graph.edge[4].w = 2;

    graph.BellmanFord(graph, 0); // 0 is the source vertex
  }
}
```

### Bellman Ford's Algorithm Applications

- For calculating shortest paths in routing algorithms.
- For finding the shortest path.

## [Dijkstra's algorithm](https://www.programiz.com/dsa/dijkstra-algorithm/)

Algorithm | Time | Space
--- | --- | ---
Bellman Ford | $O(E \log V)$ | $O(V)$

Dijkstra's algorithm allows us to find the shortest path between any two
vertices of a graph.

It differs from the minimum spanning tree because the shortest distance between
two vertices might not include all the vertices of the graph.

### How Dijkstra's Algorithm works

Dijkstra's Algorithm works on the basis that any subpath $B \to D$ of the
shortest path $A \to D$ between vertices $A$ and $D$ is also the shortest path
between vertices $B$ and $D$.

![Figure 3-1](https://cdn.programiz.com/sites/tutorial2program/files/shortest-subpath.png)

<small>Each subpath is the shortest path</small>

Djikstra used this property in the opposite direction i.e we overestimate the
distance of each vertex from the starting vertex. Then we visit each node and
its neighbors to find the shortest subpath to those neighbors.

The algorithm uses a greedy approach in the sense that we find the next best
solution hoping that the end result is the best solution for the whole problem.

### Example of Dijkstra's algorithm

It is easier to start with an example and then think about the algorithm.

![Figure 3-2](https://cdn.programiz.com/sites/tutorial2program/files/dj-1.png)

<small>Start with a weighted graph</small>

![Figure 3-3](https://cdn.programiz.com/sites/tutorial2program/files/dj-2.png)

<small>Choose a starting vertex and assign infinity path values to all other devices</small>

![Figure 3-4](https://cdn.programiz.com/sites/tutorial2program/files/dj-3.png)

<small>Go to each vertex and update its path length</small>

![Figure 3-5](https://cdn.programiz.com/sites/tutorial2program/files/dj-4.png)

<small>If the path length of the adjacent vertex is lesser than new path length, don't update it</small>

![Figure 3-6](https://cdn.programiz.com/sites/tutorial2program/files/dj-5.png)

<small>Avoid updating path lengths of already visited vertices</small>

![Figure 3-7](https://cdn.programiz.com/sites/tutorial2program/files/dj-6.png)

<small>After each iteration, we pick the unvisited vertex with the least path length. So we choose 5 before 7</small>

![Figure 3-8](https://cdn.programiz.com/sites/tutorial2program/files/dj-7.png)

<small>Notice how the rightmost vertex has its path length updated twice</small>

![Figure 3-9](https://cdn.programiz.com/sites/tutorial2program/files/dj-8.png)

<small>Repeat until all the vertices have been visited</small>

### Djikstra's algorithm pseudocode

We need to maintain the path distance of every vertex. We can store that in an
array of size $v$, where $v$ is the number of vertices.

We also want to be able to get the shortest path, not only know the length of
the shortest path. For this, we map each vertex to the vertex that last updated
its path length.

Once the algorithm is over, we can backtrack from the destination vertex to the
source vertex to find the path.

A minimum priority queue can be used to efficiently receive the vertex with
least path distance.

```
function dijkstra(G, S)
  for each vertex V in G
    distance[V] <- infinite
    previous[V] <- NULL
    If V != S, add V to Priority Queue Q
  distance[S] <- 0

  while Q IS NOT EMPTY
    U <- Extract MIN from Q
    for each unvisited neighbour V of U
      tempDistance <- distance[U] + edge_weight(U, V)
      if tempDistance < distance[V]
        distance[V] <- tempDistance
        previous[V] <- U
  return distance[], previous[]
```

### Code for Dijkstra's Algorithm

The implementation of Dijkstra's Algorithm in Java is given below. The
complexity of the code can be improved, but the abstractions are convenient to
relate the code with the algorithm.

```java
public class Dijkstra {
  public static void dijkstra(int[][] graph, int source) {
    int count = graph.length;
    boolean[] visitedVertex = new boolean[count];
    int[] distance = new int[count];
    for (int i = 0; i < count; i++) {
      visitedVertex[i] = false;
      distance[i] = Integer.MAX_VALUE;
    }

    // Distance of self loop is zero
    distance[source] = 0;
    for (int i = 0; i < count; i++) {

      // Update the distance between neighbouring vertex and source vertex
      int u = findMinDistance(distance, visitedVertex);
      visitedVertex[u] = true;

      // Update all the neighbouring vertex distances
      for (int v = 0; v < count; v++) {
        if (!visitedVertex[v] && graph[u][v] != 0 && (distance[u] + graph[u][v] < distance[v])) {
          distance[v] = distance[u] + graph[u][v];
        }
      }
    }
    for (int i = 0; i < distance.length; i++) {
      System.out.println(String.format("Distance from %s to %s is %s", source, i, distance[i]));
    }

  }

  // Finding the minimum distance
  private static int findMinDistance(int[] distance, boolean[] visitedVertex) {
    int minDistance = Integer.MAX_VALUE;
    int minDistanceVertex = -1;
    for (int i = 0; i < distance.length; i++) {
      if (!visitedVertex[i] && distance[i] < minDistance) {
        minDistance = distance[i];
        minDistanceVertex = i;
      }
    }
    return minDistanceVertex;
  }

  public static void main(String[] args) {
    int graph[][] = new int[][] { { 0, 0, 1, 2, 0, 0, 0 }, { 0, 0, 2, 0, 0, 3, 0 }, { 1, 2, 0, 1, 3, 0, 0 },
        { 2, 0, 1, 0, 0, 0, 1 }, { 0, 0, 3, 0, 0, 2, 0 }, { 0, 3, 0, 0, 2, 0, 1 }, { 0, 0, 0, 1, 0, 1, 0 } };
    Dijkstra T = new Dijkstra();
    T.dijkstra(graph, 0);
  }
}
```

### Dijkstra's Algorithm Applications

- To find the shortest path.
- In social networking applications.
- In a telephone network.
- To find the locations in the map.
