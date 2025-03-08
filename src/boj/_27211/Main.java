package _27211;
import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/27211"/>
 */
class Solution {

  int N,M;
  int[][] board;

  int[] dx = {1,0,-1,0};
  int[] dy = {0,1,0,-1};
  boolean[][] isVisited;
  int count ;

  static class Point{
    int x,y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  void readInput() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    board = new int[N][M];
    for(int i=0; i<N; i++){
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<M; j++){
        board[i][j] = Integer.parseInt(st.nextToken());
      }
    }
  }

  void solve() {
    isVisited = new boolean[N][M];
    count = 0;

    for(int i=0; i<N; i++){
      for(int j=0; j<M; j++){
        if(isVisited[i][j]) continue;
        if(board[i][j]==1) continue;

        count++;
        bfsFrom(new Point(i,j));
      }
    }
  }

  void bfsFrom(Point start){
    Queue<Point> q = new ArrayDeque<>();

    q.offer(start);
    isVisited[start.x][start.y] = true;

    while(!q.isEmpty()){
      Point current = q.poll();

      for(int i=0; i<4; i++){
        int nx = (N + current.x + dx[i]) % N;
        int ny = (M + current.y + dy[i]) % M;

        if(isVisited[nx][ny]) continue;
        if(board[nx][ny]==1) continue;

        isVisited[nx][ny] = true;
        q.offer(new Point(nx,ny));
      }
    }
  }

  void writeOutput() throws IOException {
    bw.write(Integer.toString(count));
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

