package _33899;
import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/33899"/>
 */
class Solution {

  int N,M;
  int[][] board;

  void readInput() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    board = new int[N][M];
    for(int i=0; i<N; i++){
      String line = br.readLine();
      for(int j=0; j<M; j++){
        board[i][j] = line.charAt(j)-'0';
      }
    }
  }

  /**
   * segment : index
   * a : 0
   * f : 1
   * e : 2
   * c : 3
   * b : 4
   * d : 5
   */
  boolean[][] segments = {
      // 0
      {true,true,true,true,true,true},

      // 1
      {false,false,false,true,true,false},

      // 2
      {true,false,true,false,true,true},

      // 3
      {true,false,false,true,true,true},

      // 4
      {false,true,false,true,true,false},

      // 5
      {true,true,false,true,false,true},

      // 6
      {true,true,true,true,false,true},

      // 7
      {true,false,false,true,true,false},

      // 8
      {true,true,true,true,true,true},

      //9
      {true,true,false,true,true,true}
  };

  static class Point{
    int x,y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  boolean isDisconnected(int segment, int sourceNumber, int targetNumber){
    return !(segments[sourceNumber][segment] && segments[targetNumber][5-segment]);
  }

  boolean isOut(int x,int y){ return x<0 || x>=N || y<0 || y>=M; }
  int[][] delta = {{-1,0},{0,-1},{0,1},{1,0}};

  String answer;
  void solve() {
    boolean[][] isVisited = new boolean[N][M];
    int visitedCount = 0;
    Queue<Point> q = new ArrayDeque<>();

    q.offer(new Point(0,0));
    isVisited[0][0] = true;
    visitedCount++;
    while(!q.isEmpty()){
      Point current = q.poll();

      for(int i=0; i<6; i++){
        int nx = current.x + delta[(i+1)/2][0];
        int ny = current.y + delta[(i+1)/2][1];

        if(isOut(nx,ny)) continue;
        if(isVisited[nx][ny]) continue;
        if(isDisconnected(i,board[current.x][current.y],board[nx][ny])) continue;

        q.offer(new Point(nx,ny));
        isVisited[nx][ny] = true;
        visitedCount++;
      }
    }

    answer = visitedCount==N*M ? "YES" : "NO";
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