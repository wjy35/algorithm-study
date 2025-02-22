package _25208;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/25208
 * @title 새벽의 탐정 게임
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,M;
    char[][] board;
    final char WALL = '#';
    final char THIEF = 'R';
    final char DETECTIVE = 'D';

    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for(int i=0; i<N; i++) board[i] = br.readLine().toCharArray();
    }

    int moveCount = -1;
    private void solve(){
        Queue<BoardState> q = new ArrayDeque<>();
        boolean[][][] isVisited = new boolean[N][M][6];

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(board[i][j]!=DETECTIVE) continue;

                q.offer(new BoardState(i,j,0,0));
                isVisited[i][j][0] = true;
            }
        }

        while(!q.isEmpty()){
            BoardState current = q.poll();

            for(int i=0; i<4; i++){
                int nx = current.x+dx[i];
                int ny = current.y+dy[i];
                int nDice = dice[current.dice][i];

                if(isOut(nx,ny)) continue;
                if(board[nx][ny]==WALL) continue;
                if(board[nx][ny]==THIEF){
                    if(nDice!=0) continue;

                    moveCount = current.count+1;
                    return;
                }
                if(isVisited[nx][ny][nDice]) continue;

                q.offer(new BoardState(nx,ny,nDice, current.count+1));
                isVisited[nx][ny][nDice] = true;
            }
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(moveCount));
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        solve();
        writeOutput();
    }

    int[] dx = {-1,0,1,0};
    int[] dy = {0,-1,0,1};
    int[][] dice = {{1,2,3,4},{5,1,0,1},{2,5,2,0},{0,3,5,3},{4,0,4,5},{3,4,1,2}};
    boolean isOut(int x,int y){ return 0>x || x>=N || 0>y|| y>=M; }

    private static class BoardState{
        int x,y;
        int dice;
        int count;

        public BoardState(int x, int y, int dice, int count) {
            this.x = x;
            this.y = y;
            this.dice = dice;
            this.count = count;
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

