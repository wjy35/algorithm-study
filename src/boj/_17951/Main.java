package _17951;

import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/17951"/>
 */
class Solution {

  int N, K;
  int[] x;

  void readInput() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    x = new int[N];
    for (int i = 0; i < N; i++) {
      x[i] = Integer.parseInt(st.nextToken());
    }
  }

  int maxScore = 0;
  void solve() {
    int l = 0;
    int r = 100_000 * 20;

    while (l <= r) {
      int mid = (l + r) / 2;

      if (isPossible(mid)) {
        maxScore = mid;
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }
  }

  boolean isPossible(int score) {
    int scoreSum = 0;
    int groupCount = 0;

    for (int i = 0; i < N; i++) {
      scoreSum += x[i];

      if (scoreSum >= score) {
        groupCount++;
        if(groupCount == K) return true;
        scoreSum = 0;
      }
    }

    return false;
  }

  void writeOutput() throws IOException {
    bw.write(Integer.toString(maxScore));
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