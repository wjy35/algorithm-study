package _32933;
import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/32933"/>
 */
class Solution {

  static class Fruit{
    int A,B,C;

    public Fruit(int a, int b, int c) {
      A = a;
      B = b;
      C = c;
    }
  }

  int N,M,K;
  Fruit[] fruits;

  void readInput() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    fruits = new Fruit[K];
    for(int i=0; i<K; i++){
      st = new StringTokenizer(br.readLine());
      fruits[i] = new Fruit(
        Integer.parseInt(st.nextToken()),
        Integer.parseInt(st.nextToken()),
        Integer.parseInt(st.nextToken())
      );
    }
  }

  int maxMoney = 0;
  void solve() {
    int[] dp = new int[M+1];

    for(int i=0; i<K; i++){
      for(int day = 0; day<=M; day++){
        if(day<fruits[i].A) continue;

        for(int j=0; fruits[i].A + fruits[i].B * j <= day; j++){
          dp[day] = Math.max(dp[day], dp[day-(fruits[i].A + fruits[i].B * j)] + fruits[i].C * (j+1));
        }
      }
    }

    maxMoney = dp[M] * N;
  }

  void writeOutput() throws IOException {
    bw.write(Integer.toString(maxMoney));
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