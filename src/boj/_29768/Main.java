package _29768;
import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/29768"/>
 */
class Solution {

  int N,K;
  void readInput() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
  }

  String answer;
  void solve() {
    StringBuilder sb = new StringBuilder();

    int otherCount = K-1;
    int aCount = N - otherCount;
    for(int i=0; i<aCount; i++) sb.append("a");

    for(int i=1; i<=otherCount; i++){
      sb.append((Character.toString('a'+i)));
    }

    answer = sb.toString();
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