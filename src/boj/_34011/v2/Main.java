package _34011.v2;

import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/34011"/>
 */
class Solution {

  int N;
  int[] P;

  void readInput() throws IOException {
    N = Integer.parseInt(br.readLine());

    st = new StringTokenizer(br.readLine());
    P = new int[N-1];
    for(int i=0; i<P.length; i++){
      P[i] = Integer.parseInt(st.nextToken());
    }
  }

  int answer = 0;
  void solve() {
    List<Integer>[] childs = new List[N];
    for(int i=0; i<N; i++){
      childs[i] = new ArrayList<>();
    }

    for(int i=0; i<P.length; i++){
      childs[P[i]-1].add(i+1);
    }

    Queue<Integer> q = new ArrayDeque<>();
    int[] distances = new int[N];

    distances[0] = 1;
    q.offer(0);

    while(!q.isEmpty()){
      int current = q.poll();

      for(int next : childs[current]){
        if(distances[next]!=0) continue;

        distances[next] = distances[current]+1;
        q.offer(next);
      }
    }

    int[] distanceCounts = new int[N];
    for(int distance : distances){
      distanceCounts[distance-1]++;
    }

    boolean[] isNotPrime = new boolean[N];
    for(int i=2; i<N; i++){
      if(isNotPrime[i]) continue;

      int sum = 0;
      for(int j=i; j<N; j+=i){
        isNotPrime[j] = true;
        sum += distanceCounts[j];
      }

      answer = Math.max(answer,sum);
    }

    answer++;
  }

  void writeOutput() throws IOException {
    bw.write(Integer.toString(answer));
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