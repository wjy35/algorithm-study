package _33906;

import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/33906"/>
 */
class Solution {

  static class Node implements Comparable<Node> {

    int v, w;

    public Node(int v, int w) {
      this.v = v;
      this.w = w;
    }

    @Override
    public int compareTo(Node target) {
      return Integer.compare(this.w, target.w);
    }
  }

  static class Place {
    int index, price;

    Place(){
      index = 0;
      price = Integer.MAX_VALUE;
    }
  }

  int N, M;
  int[] x;
  int[] y;
  List<Node>[] edges;

  final int INF = 2_000_000_001;

  void readInput() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    x = new int[N+1];
    for (int i = 1; i <= N; i++) {
      x[i] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine());
    y = new int[N+1];
    for (int i = 1; i <= N; i++) {
      y[i] = Integer.parseInt(st.nextToken());
    }

    edges = new List[N + 1];
    for (int i = 1; i <= N; i++) {
      edges[i] = new ArrayList<>();
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());

      edges[u].add(new Node(v, w));
      edges[v].add(new Node(u, w));
    }
  }

  long minDistance = 0;
  void solve() {
    int[] distancesFromHome = getDistancesFrom(1);

    Place minCafe = new Place();
    Place minRestaurant = new Place();

    for (int i = 2; i <= N; i++) {
      if (distancesFromHome[i] == INF) {
        continue;
      }

      if (x[i] != 0 && minCafe.price > x[i]) {
        minCafe.index = i;
        minCafe.price = x[i];
      }

      if (y[i] != 0 && minRestaurant.price > y[i]) {
        minRestaurant.index = i;
        minRestaurant.price = y[i];
      }
    }

    int[] distanceFromCafe = getDistancesFrom(minCafe.index);

    minDistance = (long)distancesFromHome[minCafe.index]
        + (long) distanceFromCafe[minRestaurant.index]
        + (long) distancesFromHome[minRestaurant.index];
  }

  int[] getDistancesFrom(int start) {
    int[] distances = new int[N + 1];
    Arrays.fill(distances, INF);

    PriorityQueue<Node> pq = new PriorityQueue<>();

    distances[start] = 0;
    pq.offer(new Node(start, 0));

    while (!pq.isEmpty()) {
      Node current = pq.poll();

      if (current.w > distances[current.v]) {
        continue;
      }

      for (Node next : edges[current.v]) {
        int nextW = next.w + current.w;

        if (nextW >= distances[next.v]) {
          continue;
        }

        pq.offer(new Node(next.v, nextW));
        distances[next.v] = nextW;
      }
    }

    return distances;
  }

  void writeOutput() throws IOException {
    bw.write(Long.toString(minDistance));
    bw.flush();
  }

  public static void run() throws IOException {
    Solution solution = new Solution();
    solution.readInput();
    solution.solve();
    solution.writeOutput();
  }

  private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  private StringTokenizer st;
}

public class Main {

  public static void main(String[] args) throws IOException {
    Solution.run();
  }
}