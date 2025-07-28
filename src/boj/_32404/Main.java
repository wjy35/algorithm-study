package _32404;

import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/32404"/>
 */
class Solution {

  int N;

  void readInput() throws IOException {
    N = Integer.parseInt(br.readLine());
  }

  int[] answer;

  void solve() {
    answer = new int[N];

    answer[0] = N / 2 + 1;
    for (int i = 2; i < N; i += 2) {
      answer[i] = answer[i - 2] + 1;
    }

    if (N == 1) {
      return;
    }

    answer[1] = answer[0] - 1;
    for (int i = 3; i < N; i += 2) {
      answer[i] = answer[i - 2] - 1;
    }
  }

  void writeOutput() throws IOException {
    for (int i = 0; i < N; i++) {
      bw.write(Integer.toString(answer[i]));
      bw.write(" ");
    }
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