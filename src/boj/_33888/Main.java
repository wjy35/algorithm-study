package _33888;
import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/33888"/>
 */
class Solution {

  int N;
  List<Integer>[] edges;

  final int HEAD = 0;
  final int LEFT_WING = 1;
  final int CENTER = 2;
  final int RIGHT_WING = 3;
  final int LOW_WING = 4;
  final int TAIL = 5;
  int[] cores = new int[6];

  void readInput() throws IOException {
    N = Integer.parseInt(br.readLine());

    edges = new List[N+1];
    for(int i=1; i<=N; i++) edges[i] = new ArrayList<>();

    for(int i=0; i<N+3; i++){
      st = new StringTokenizer(br.readLine());
      int U = Integer.parseInt(st.nextToken());
      int V = Integer.parseInt(st.nextToken());

      edges[U].add(V);
      edges[V].add(U);
    }
  }

  boolean[] isVisited;
  Queue<Integer> q;

  void solve() {
    isVisited = new boolean[N+1];
    q = new ArrayDeque<>();

    findTail();
    findLowWing();
    findMidWings();
    findHead();
  }

  boolean isTail(int number){
    return edges[number].size()==1;
  }

  boolean isCenter(int number){
    return edges[number].size()==4;
  }

  boolean isCore(int number){
    return edges[number].size()!=2;
  }

  void findTail(){
    for(int i=1; i<=N; i++){
      if(isTail(i)){
        cores[TAIL] = i;
        return;
      }
    }
  }

  void findLowWing(){
    q.offer(cores[TAIL]);
    isVisited[cores[TAIL]] = true;

    while(!q.isEmpty()){
      int current = q.poll();

      for(int next : edges[current]){
        if(isVisited[next]) continue;

        if(isCore(next)){
          cores[LOW_WING] = next;
          return;
        }

        q.offer(next);
        isVisited[next] = true;
      }
    }
  }

  void findMidWings(){
    cores[LEFT_WING] = Integer.MAX_VALUE;
    cores[RIGHT_WING] = 0;

    q.offer(cores[LOW_WING]);
    isVisited[cores[LOW_WING]] = true;

    while(!q.isEmpty()){
      int current = q.poll();

      for(int next : edges[current]){
        if(isVisited[next]) continue;

        isVisited[next] = true;

        if(isCenter(next)){
          cores[CENTER] = next;
          continue;
        }

        if(isCore(next)){
          cores[LEFT_WING] = Math.min(cores[LEFT_WING],next);
          cores[RIGHT_WING] = Math.max(cores[RIGHT_WING],next);
          continue;
        }

        q.offer(next);
      }
    }
  }

  void findHead(){
    q.offer(cores[CENTER]);
    isVisited[cores[CENTER]] = true;

    while(!q.isEmpty()){
      int current = q.poll();

      for(int next : edges[current]){
        if(isVisited[next]) continue;

        if(isCore(next)){
          cores[HEAD] = next;
          continue;
        }

        q.offer(next);
        isVisited[next] = true;
      }
    }
  }

  void writeOutput() throws IOException {
    for(int core : cores){
      bw.write(Integer.toString(core));
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