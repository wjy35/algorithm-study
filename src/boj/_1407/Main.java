package _1407;
import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/1407"/>
 */
class Solution {

  long A,B;
  void readInput() throws IOException {
    st = new StringTokenizer(br.readLine());
    A = Long.parseLong(st.nextToken());
    B = Long.parseLong(st.nextToken());
  }

  String answer;
  void solve() {
    answer = Long.toString(count(B)-count(A-1));
  }

  long count(long n){
    long result = 0;
    long tmp = 1;

    while(n != 0){
      if(n%2!=0){
        result+=((n/2)+1)*tmp;
      }else{
        result+=(n/2)*tmp;
      }

      tmp*=2;
      n/=2;
    }

    return result;
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