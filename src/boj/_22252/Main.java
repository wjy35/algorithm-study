package _22252;

import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/22252"/>
 */
class Solution {

  static class Informations {

    PriorityQueue<Long> pq = new PriorityQueue<>(Comparator.reverseOrder());

    void add(long[] prices) {
      for (long price : prices) {
        pq.add(price);
      }
    }

    long buy(int count) {
      long price = 0;

      for(int i=0; !pq.isEmpty() && i<count; i++){
        price += pq.poll();
      }

      return price;
    }
  }

  int Q;
  Map<String, Informations> nameToInformations = new HashMap<>();
  long answer = 0;

  void readInputAndQuery() throws IOException {
    Q = Integer.parseInt(br.readLine());
    for (int q = 0; q < Q; q++) {
      st = new StringTokenizer(br.readLine());
      String type = st.nextToken();
      String name = st.nextToken();

      if ("1".equals(type)) {
        int k = Integer.parseInt(st.nextToken());
        long[] prices = new long[k];
        for (int i = 0; i < k; i++) {
          prices[i] = Long.parseLong(st.nextToken());
        }

        query(name, prices);
      } else {
        int count = Integer.parseInt(st.nextToken());

        query(name, count);
      }
    }
  }

  void query(String name, long[] prices) {
    nameToInformations.computeIfAbsent(name,key -> new Informations()).add(prices);
  }

  void query(String name, int count) {
    answer += nameToInformations.computeIfAbsent(name,key -> new Informations()).buy(count);
  }

  void writeOutput() throws IOException {
    bw.write(Long.toString(answer));
    bw.flush();
  }

  public static void run() throws IOException {
    Solution solution = new Solution();
    solution.readInputAndQuery();
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