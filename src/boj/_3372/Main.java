package _3372;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/3372
 * @title 보드 점프
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N;
    int[][] board;

    int[] dx = {1,0};
    int[] dy = {0,1};

    BigInteger[][] dp;
    String answer;

    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private void solve(){
        dp = new BigInteger[N][N];
        dp[N-1][N-1] = BigInteger.ONE;

        answer = dfs(0,0).toString();
    }

    private BigInteger dfs(int x, int y){
        if(x>=N || y>=N) return BigInteger.ZERO;
        if(dp[x][y]!=null) return dp[x][y];
        if(board[x][y]==0) return BigInteger.ZERO;

        return dp[x][y] = dfs(x+board[x][y],y).add(dfs(x,y+board[x][y]));
    }

    private void writeOutput() throws IOException{
        bw.write(answer);
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        solve();
        writeOutput();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

