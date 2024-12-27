package _11559;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/11559
 * @title Puyo Puyo
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    char[][] board = new char[12][6];

    int[] dx = {1,0,-1,0};
    int[] dy = {0,1,0,-1};

    int count = 0;
    private void readInput() throws IOException{
        for(int i=0; i<12; i++){
            board[i] = br.readLine().toCharArray();
        }
    }

    private void solve(){
        boolean isChained = true;
        while(isChained){
            isChained = false;
            boolean[][] isVisited = new boolean[12][6];
            for(int x=0; x<12; x++){
                for(int y=0; y<6; y++){
                    if(isVisited[x][y]) continue;
                    if(board[x][y]=='.') continue;

                    int groupSize = 1;
                    char groupColor = board[x][y];

                    isVisited[x][y] = true;
                    Queue<Point> q = new ArrayDeque<>();
                    q.offer(new Point(x,y));

                    while(!q.isEmpty()){
                        Point current = q.poll();

                        for(int i=0; i<4; i++){
                            int nx = current.x+dx[i];
                            int ny = current.y+dy[i];

                            if(nx<0 || nx>11 || ny<0 || ny>5) continue;
                            if(isVisited[nx][ny]) continue;
                            if(board[nx][ny]!=groupColor) continue;

                            isVisited[nx][ny] = true;
                            q.offer(new Point(nx,ny));
                            groupSize++;
                        }
                    }

                    if(groupSize<4) continue;
                    isChained = true;

                    board[x][y] = '.';
                    q.offer(new Point(x,y));
                    while(!q.isEmpty()){
                        Point current = q.poll();

                        for(int i=0; i<4; i++){
                            int nx = current.x+dx[i];
                            int ny = current.y+dy[i];

                            if(nx<0 || nx>11 || ny<0 || ny>5) continue;
                            if(board[nx][ny]!=groupColor) continue;

                            board[nx][ny] = '.';
                            q.offer(new Point(nx,ny));
                        }
                    }
                }
            }

            if(isChained) count++;

            for(int y=0; y<6; y++){
                for(int x=10; x>=0; x--){
                    if(board[x][y]=='.') continue;

                    int nx = x+1;
                    while(nx<12 && board[nx][y]=='.') nx++;
                    nx--;

                    if(nx==x) continue;

                    board[nx][y] = board[x][y];
                    board[x][y] = '.';
                }
            }
        }

    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(count));
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        solve();
        writeOutput();
    }

    private static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

