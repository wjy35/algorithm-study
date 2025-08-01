package _34013;

import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/34013"/>
 */
class Solution {

  int N;
  long[][][] costs;

  void readInput() throws IOException {
    N = Integer.parseInt(br.readLine());

    costs = new long[N][N][4];

    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N - 1; j++) {
        long w = Long.parseLong(st.nextToken());
        costs[i][j][0] = w;
        costs[i][j + 1][2] = w;
      }

      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        long w = Long.parseLong(st.nextToken());
        costs[i][j][1] = w;
        costs[i + 1][j][3] = w;
      }
    }

    st = new StringTokenizer(br.readLine());
    for (int j = 0; j < N - 1; j++) {
      long w = Long.parseLong(st.nextToken());
      costs[N - 1][j][0] = w;
      costs[N - 1][j + 1][2] = w;
    }
  }

  static class Node implements Comparable<Node> {

    int x, y;
    int direction;
    long w;
    long turnCount;

    @Override
    public int compareTo(Node target) {
      if (this.w == target.w) {
        return -Long.compare(this.turnCount, target.turnCount);
      }
      return Long.compare(this.w, target.w);
    }

    public Node(int x, int y, int direction, long w, long turnCount) {
      this.x = x;
      this.y = y;
      this.direction = direction;
      this.w = w;
      this.turnCount = turnCount;
    }
  }

  final int[][] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  boolean isOut(int x, int y) {
    return 0 > x || x >= N || 0 > y || y >= N;
  }

  final long INF = 502L * 502 * 10_000 + 1;

  long[][] minDistances, maxTurnCounts;

  void solve() {
    minDistances = new long[N][N];
    maxTurnCounts = new long[N][N];
    PriorityQueue<Node> pq = new PriorityQueue<>();

    for (int i = 0; i < N; i++) {
      Arrays.fill(minDistances[i], INF);
    }

    minDistances[0][0] = 0;
    maxTurnCounts[0][0] = 0;

    pq.offer(new Node(0, 1, 0, costs[0][0][0], 0));
    minDistances[0][1] = costs[0][0][0];
    maxTurnCounts[0][1] = 0;

    pq.offer(new Node(1, 0, 1, costs[0][0][1], 0));
    minDistances[1][0] = costs[0][0][1];
    maxTurnCounts[1][0] = 0;

    while (!pq.isEmpty()) {
      Node current = pq.poll();

      if (minDistances[current.x][current.y] != current.w) {
        continue;
      }
      if (maxTurnCounts[current.x][current.y] != current.turnCount) {
        continue;
      }

      for (int direction = 0; direction < 4; direction++) {
        int nx = current.x + delta[direction][0];
        int ny = current.y + delta[direction][1];
        long nw = current.w + costs[current.x][current.y][direction];
        long nTurnCount =
            current.turnCount + (Math.abs(current.direction - direction) % 2 == 0 ? 0 : 1);

        if (isOut(nx, ny)) {
          continue;
        }
        if (nw < minDistances[nx][ny] || (nw == minDistances[nx][ny]
            && nTurnCount > maxTurnCounts[nx][ny])) {
          pq.offer(new Node(nx, ny, direction, nw, nTurnCount));
          minDistances[nx][ny] = nw;
          maxTurnCounts[nx][ny] = nTurnCount;
        }
      }
    }
  }

  void writeOutput() throws IOException {
    bw.write(Long.toString(minDistances[N - 1][N - 1]));
    bw.write(" ");
    bw.write(Long.toString(maxTurnCounts[N - 1][N - 1]));
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