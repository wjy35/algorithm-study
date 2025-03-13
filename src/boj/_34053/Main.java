package _34053;

import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/34053"/>
 */
class Solution {

  int N,M;
  long[][] a;
  void readInput() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    a = new long[N][M];
    for(int i=0; i<N; i++){
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<M; j++){
        a[i][j] = Integer.parseInt(st.nextToken());
      }
    }
  }

  long answer = 0;
  void solve() {
    long sum = 0;
    long min = Long.MAX_VALUE;
    for(int i=0; i<N; i++){
      for(int j=0; j<M; j++){
        sum += a[i][j];
        min = Math.min(min,a[i][j]);
      }
    }

    answer = sum-min;
  }

  void writeOutput() throws IOException {
    bw.write(Long.toString(answer));
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