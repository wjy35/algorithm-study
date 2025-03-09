package _20166;
import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/20166"/>
 */
class Solution {

  int N,M,K;
  char[][] board;
  String[] favoriteTexts;

  int[] dx = {1,0,-1,0,1,1,-1,-1};
  int[] dy = {0,1,0,-1,1,-1,1,-1};

  static class Node{
    int x,y;
    char[] text;

    public Node(int x, int y,char[] text) {
      this.x = x;
      this.y = y;
      this.text = text;
    }
  }

  void readInput() throws IOException {
    st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());

    board = new char[N][M];
    for(int i=0; i<N; i++) {
      board[i] = br.readLine().toCharArray();
    }

    favoriteTexts = new String[K];
    for(int i=0; i<K; i++){
      favoriteTexts[i] = br.readLine();
    }
  }

  Map<String,Integer> textToCount;
  void solve() {
    textToCount = new HashMap<>();

    for(int i=0; i<N; i++){
      for(int j=0; j<M; j++){
        updateFrom(new Node(i,j,new char[]{board[i][j]}));
      }
    }
  }

  void updateFrom(Node start){
    Queue<Node> q = new ArrayDeque<>();
    q.offer(start);
    while(!q.isEmpty()){
      Node current = q.poll();

      String text = String.valueOf(current.text);
      textToCount.put(text, textToCount.getOrDefault(text,0)+1);

      if(text.length()==5) continue;

      for(int i=0; i<8; i++){
        int nx = (N + current.x + dx[i]) % N;
        int ny = (M + current.y + dy[i]) % M;
        char[] nText = Arrays.copyOf(current.text,current.text.length+1);
        nText[nText.length-1] = board[nx][ny];

        q.offer(new Node(nx,ny,nText));
      }
    }
  }

  void writeOutput() throws IOException {
    for(String text : favoriteTexts){
      bw.write(textToCount.getOrDefault(text,0).toString());
      bw.write("\n");
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