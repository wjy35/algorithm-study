package _16954;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/16954">움직이는 미로 탈출</a>
 * @category 
 * @Note
 */
public class Main {
    static int answer;
    
    static char[][][] board;

    static void solution(){
        initBoard();
        answer = getMoveResult();
    }

    static void initBoard(){
        for(int second = 1; second<8; second++){
            Arrays.fill(board[second][0],'.');
            for(int i=1; i<8; i++) {
                for (int j = 0; j < 8; j++) {
                    board[second][i][j] = board[second-1][i-1][j];
                }
            }
        }
    }

    static int getMoveResult(){
        Queue<Point> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[8][8][8];

        q.offer(new Point(7,0,0));

        Point now;
        int nx,ny;
        while(!q.isEmpty()){
            now = q.poll();

            for(int i=0; i<9; i++){
                nx = now.x+dx[i];
                ny = now.y+dy[i];

                if(isIn(nx,ny) && board[now.second][nx][ny]=='.' && board[now.second+1][nx][ny]=='.' && !visited[now.second+1][nx][ny]) {
                    if(now.second>5) return 1;
                    visited[now.second+1][nx][ny] = true;
                    q.offer(new Point(nx, ny, now.second+1));
                }
            }
        }

        return 0;
    }

    static int[] dx = {-1,-1,-1,0,0,1,1,1,0};
    static int[] dy = {-1,1,0,0,-1,1,-1,0,1};
    static boolean isIn(int nx,int ny){
        return 0<=nx && nx<8 && 0<=ny && ny<8;
    }

    static class Point{
        int x,y;
        int second;

        public Point(int x, int y, int second) {
            this.x = x;
            this.y = y;
            this.second = second;
        }
    }
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new char[8][8][8];
        for(int i=0; i<8; i++){
            board[0][i] = br.readLine().toCharArray();
        }
    }

    static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        bw.write(Integer.toString(answer));
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        output();
    }
}
