package _31836;
import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/31836"/>
 */
class Solution {

  int X;
  void readInput() throws IOException {
    X = Integer.parseInt(br.readLine());
  }

  List<Integer> A,B;
  void solve() {
    A = new ArrayList<>();
    B = new ArrayList<>();

    int last = X;
    for(; last>2; last-=3){
      A.add(last-1);
      A.add(last-2);
      B.add(last);
    }

    if(last==2){
      A.add(1);
      B.add(2);
    }
  }

  void writeOutput() throws IOException {
    bw.write(Integer.toString(A.size()));
    bw.write("\n");
    for(Integer a : A){
      bw.write(a.toString());
      bw.write(" ");
    }
    bw.write("\n");

    bw.write(Integer.toString(B.size()));
    bw.write("\n");
    for(Integer b : B){
      bw.write(b.toString());
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