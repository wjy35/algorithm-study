package _1103;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/1103">게임</a>
 * @category DP, dfs를 활용한 cycle 판별
 * @Note
 */
public class Main {
    static int answer;
    static int N,M;
    static char[][] board;

    static int[][]dp;
    static boolean[][] visited;
    static boolean isCycle;
    static int[] dx={1,0,-1,0};
    static int[] dy={0,1,0,-1};

    static void solution(){
        dp = new int[N][M];
        visited = new boolean[N][M];

        visited[0][0]=true;
        answer = dfs(0,0);

        if(isCycle) answer = -1;
    }

    static int dfs(int x,int y){
        if(isCycle) return -1;
        if(dp[x][y]!=0) return dp[x][y];

        int nx,ny;
        int count=1;

        for(int i=0; i<4; i++){
            nx = x+dx[i]*Character.getNumericValue(board[x][y]);
            ny = y+dy[i]*Character.getNumericValue(board[x][y]);

            if(isIn(nx,ny)){
                if(board[nx][ny]=='H') continue;
                if(visited[nx][ny]) {
                    isCycle=true;
                    return -1;
                }else{
                    visited[nx][ny] = true;
                    count = Math.max(count,1+dfs(nx,ny));
                    visited[nx][ny] = false;
                }
            }
        }

        return dp[x][y]=count;
    }

    static boolean isIn(int nx,int ny){
        return 0<=nx && nx<N && 0<=ny && ny<M;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for(int i=0; i<N; i++){
            board[i] = br.readLine().toCharArray();
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
