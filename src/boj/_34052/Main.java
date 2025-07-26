package _34052;
import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/34052"/>
 */
class Solution {

  int sum = 0;
  void readInput() throws IOException {
    for(int i=0; i<4; i++){
      sum += Integer.parseInt(br.readLine());
    }
  }

  String answer;
  void solve() {
    answer = sum+300>1800 ? "No" : "Yes";
  }

  void writeOutput() throws IOException {
    bw.write(answer);
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