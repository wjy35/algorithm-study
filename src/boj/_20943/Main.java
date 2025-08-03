package _20943;
import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/20943"/>
 */
class Solution {

  static class Slope implements Comparable<Slope>{
    long top,bottom;

    public Slope(long top, long bottom) {
      long l = Math.max(top,bottom);
      long r = Math.min(top,bottom);
      while(r!=0){
        long tmp = l;
        l = r;
        r = tmp % r;
      }
      long gcd =  Math.abs(l);

      this.top = Math.abs(top / gcd);
      this.bottom = Math.abs(bottom / gcd);

      if(top * bottom <0){
        this.top = -this.top;
      }
    }

    @Override
    public int hashCode() {
      return Long.hashCode(top) + Long.hashCode(bottom);
    }

    @Override
    public boolean equals(Object obj) {
      if(obj instanceof Slope){
        Slope target = (Slope) obj;
        return this.top == target.top && this.bottom == target.bottom;
      }

      return false;
    }

    @Override
    public int compareTo(Slope target) {
      return Long.compare(this.top * target.bottom, target.top * this.bottom);
    }
  }

  long N;

  Map<Slope,Long> slopeToCount;

  void readInput() throws IOException {
    N = Long.parseLong(br.readLine());

    slopeToCount = new HashMap<>();
    for(int i=0; i<N; i++){
      st = new StringTokenizer(br.readLine());
      long a = Long.parseLong(st.nextToken());
      long b = Long.parseLong(st.nextToken());
      long c = Long.parseLong(st.nextToken());

      Slope slope = new Slope(a,b);
      slopeToCount.put(slope, slopeToCount.getOrDefault(slope,0L)+1);
    }
  }

  long answer = 0;

  void solve() {
    for(Long count : slopeToCount.values()){
      answer += count * (N-count);
    }
    answer = answer/2;
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