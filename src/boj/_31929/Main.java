package _31929;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/31929
 * @title 너 재능 있어
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
    int[] W;
    int M;
    int[] L;
    int K;

    int[][] dp;
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());
        W = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) W[i] = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());
        L = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) L[i] = Integer.parseInt(st.nextToken());

        K = Integer.parseInt(br.readLine());
    }

    private void solve(){
        dp = new int[N+1][M+1];
        for(int i=0; i<=N; i++) for(int j=0; j<=M; j++) dp[i][j] = -100_001;

        dp[0][0] = 0;
        int endRound = N+M;
        for(int round=0; round<endRound; round++){
            for(int win=0; win<=round && win<=N; win++){
                if(round-win>M) continue;
                int lose = round-win;

                if(win<N) {
                    dp[win+1][lose] = Math.max(dp[win+1][lose],dp[win][lose]+W[win]);
                }

                if(lose<M){
                    int remainder = dp[win][lose]%K;
                    int b = L[lose];
                    if(remainder!=0){
                        if(dp[win][lose]>0) b = Math.min(b,remainder);
                        else b = Math.min(b,dp[win][lose] - (dp[win][lose]/K-1) * K);
                    }
                    dp[win][lose+1] = Math.max(dp[win][lose+1],dp[win][lose]-b);
                }
            }
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(dp[N][M]));
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

