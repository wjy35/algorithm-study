package _25759;

import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/25759"/>
 */
class Solution {

  int N;
  int[] A;

  void readInput() throws IOException {
    N = Integer.parseInt(br.readLine());

    st = new StringTokenizer(br.readLine());
    A = new int[N];
    for (int i = 0; i < N; i++) {
      A[i] = Integer.parseInt(st.nextToken());
    }
  }

  int answer = 0;
  void solve() {
    int[][] dp = new int[N][101];

    Arrays.fill(dp[0], -1);
    dp[0][A[0]] = 0;

    for (int i = 1; i < N; i++) {
      for (int j = 1; j <= 100; j++) {
        dp[i][j] = dp[i-1][j];
      }

      for (int j = 1; j <= 100; j++) {
        if (dp[i - 1][j] == -1) {
          continue;
        }
        dp[i][A[i]] = Math.max(dp[i][A[i]], dp[i - 1][j] + power(j - A[i]));
      }
    }

    for(int i=1; i<=100; i++){
      answer = Math.max(dp[N-1][i],answer);
    }
  }

  int power(int x) {
    return x * x;
  }

  void writeOutput() throws IOException {
    bw.write(Integer.toString(answer));
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