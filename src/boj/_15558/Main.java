package _15558;
import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/15558"/>
 */
class Solution {

  static class Node{
    int second;

    // left = 0, right = 1;
    int side;

    int position;

    public int getOtherSide(){
      return 1-this.side;
    }

    public Node(int second, int side, int position) {
      this.second = second;
      this.side = side;
      this.position = position;
    }
  }

  int N,K;
  char[][] board;
  final char SAFE = '1';
  final char DANGEROUS = '0';

  void readInput() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    board = new char[2][];

    board[0] = br.readLine().toCharArray();
    board[1] = br.readLine().toCharArray();
  }

  String answer;
  void solve() {
    answer = "0";

    int[] dPosition = {1,-1,K};

    boolean[][] isVisited = new boolean[2][N];
    Queue<Node> q = new ArrayDeque<>();
    q.offer(new Node(0,0,0));

    while(!q.isEmpty()){
      Node current = q.poll();

      for(int i=0; i<3; i++){
        int nextPosition = current.position + dPosition[i];
        int nextSide = i==2 ? current.getOtherSide() : current.side;

        if(nextPosition>=N){
          answer = "1";
          return;
        }

        if(nextPosition<=current.second) continue;
        if(isVisited[nextSide][nextPosition]) continue;
        if(board[nextSide][nextPosition]==DANGEROUS) continue;

        q.offer(new Node(current.second+1,nextSide,nextPosition));
        isVisited[nextSide][nextPosition] = true;
      }
    }
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