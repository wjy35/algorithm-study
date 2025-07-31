package _23295;

import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/23259"/>
 */
class Solution {

  int N, K;
  int[] prefixSum;

  void readInput() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    prefixSum = new int[100_001];
    for (int i = 0; i < N; i++) {
      int K = Integer.parseInt(br.readLine());

      for (int j = 0; j < K; j++) {
        st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        prefixSum[S]++;
        prefixSum[E]--;
      }
    }
  }

  int s, e;

  void solve() {
    for (int i = 1; i < prefixSum.length; i++) {
      prefixSum[i] += prefixSum[i - 1];
    }

    int sum = 0;
    for (int i = 0; i < K; i++) {
      sum += prefixSum[i];
    }

    int l = 0;
    int r = K;
    int max = sum;
    s = l;
    e = r;
    for (; r < prefixSum.length; l++, r++) {
      sum -= prefixSum[l];
      sum += prefixSum[r];

      if (max < sum) {
        max = sum;
        s = l + 1;
        e = r + 1;
      }
    }
  }

  void writeOutput() throws IOException {
    bw.write(Integer.toString(s));
    bw.write(" ");
    bw.write(Integer.toString(e));
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