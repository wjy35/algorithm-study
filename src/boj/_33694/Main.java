package _33694;

import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/33694"/>
 */
class Solution {

  static class Event implements Comparable<Event> {
    int deltaCount;
    long position;

    public Event(int deltaCount, long position) {
      this.deltaCount = deltaCount;
      this.position = position;
    }

    @Override
    public int compareTo(Event target) {
      return Long.compare(this.position, target.position);
    }
  }

  int N, X;

  PriorityQueue<Event> pq;

  void readInput() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    X = Integer.parseInt(st.nextToken());

    pq = new PriorityQueue<>();
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      long A = Long.parseLong(st.nextToken());
      long B = Long.parseLong(st.nextToken());
      long C = Long.parseLong(st.nextToken());
      long T = Long.parseLong(st.nextToken());

      long start = T + ceilDiv(X, A);

      long increaseDay = ceilDiv(C, A);
      long maxSize = increaseDay * A;
      long decreaseDay = Math.max(0, maxSize - X) / B;
      long end = T + increaseDay + decreaseDay;

      if(end<start) continue;

      pq.offer(new Event(+1,start));
      pq.offer(new Event(-1,end+1));
    }
  }

  long ceilDiv(long a, long b) {
    return a / b + (a % b == 0 ? 0 : 1);
  }

  long answer = 0;
  void solve() {
    long preEventPosition = 0;
    int count = 0;
    while(!pq.isEmpty()){
      Event current = pq.poll();

      if(count>=3){
        answer += current.position - preEventPosition;
      }

      preEventPosition = current.position;
      count += current.deltaCount;
    }
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