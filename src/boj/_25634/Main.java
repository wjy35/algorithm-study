package _25634;

import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/25634"/>
 */
class Solution {

  int N;
  int[] a;
  int[] b;

  void readInput() throws IOException {
    N = Integer.parseInt(br.readLine());

    st = new StringTokenizer(br.readLine());
    a = new int[N];
    for (int i = 0; i < N; i++) {
      a[i] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine());
    b = new int[N];
    for (int i = 0; i < N; i++) {
      b[i] = Integer.parseInt(st.nextToken());
    }
  }

  int brightness = 0;
  void solve() {
    for (int i = 0; i < N; i++) {
      if (b[i] == 0) {
        continue;
      }

      brightness += a[i];
      a[i] = -a[i];
    }

    int[] dp = new int[N];
    dp[0] = a[0];
    for (int i = 1; i < N; i++) {
      dp[i] = Math.max(dp[i - 1] + a[i], a[i]);
    }

    int max = Integer.MIN_VALUE;
    for (int i = 0; i < N; i++) {
      max = Math.max(dp[i], max);
    }

    brightness += max;
  }

  void writeOutput() throws IOException {
    bw.write(Integer.toString(brightness));
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