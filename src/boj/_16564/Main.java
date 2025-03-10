package _16564;
import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/16564"/>
 */
class Solution {

  int N,K;
  int[] X;
  void readInput() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    X = new int[N];
    for(int i=0; i<N; i++){
      X[i] = Integer.parseInt(br.readLine());
    }
  }

  long T;
  void solve() {
    long l = 1;
    long r = 2_000_000_000;

    while(l<=r){
      long mid = (l+r)/2;

      if(isPossible(mid)){
        T = mid;
        l = mid + 1;
      }else{
        r = mid - 1;
      }
    }
  }

  boolean isPossible(long level){
    long last = K;

    for(int i=0; i<N; i++){
      last -= Math.max(0, level - X[i]);

      if(last<0) return false;
    }

    return true;
  }

  void writeOutput() throws IOException {
    bw.write(Long.toString(T));
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