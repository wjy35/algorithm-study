package _2482;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/2482">색 상환</a>
 * @category DP
 * @Note
 */
public class Main {
    static long answer;
    static int N,K;
    static final long MOD = 1_000_000_003;

    static void solution(){
        final int USE = 0;
        final int NO_USE = 1;

        long[][][] dp = new long[2][K+1][N+1];

        if(K*2>N){
            answer = 0;
            return;
        }

        for(int i=1; i<=N; i++){
            dp[USE][1][i] = 1;
            dp[NO_USE][1][i] = i-1;
        }

        for(int i=2; i<=K; i++){
            dp[USE][i][2*i-1] = 1;
            for(int j=2*i; j<=N; j++){
                dp[NO_USE][i][j] = (dp[USE][i][j-1]+dp[NO_USE][i][j-1])%MOD;
                dp[USE][i][j] = dp[NO_USE][i-1][j-1]%MOD;
            }
        }

        long impossibleCount;
        if(K==1){
            impossibleCount = 0;
        }else if(K==2){
            impossibleCount = 1;
        }else{
            impossibleCount = (dp[USE][K-2][N-4]+dp[NO_USE][K-2][N-4])%MOD;
        }

        answer = (dp[USE][K][N]+dp[NO_USE][K][N]-impossibleCount+MOD)%MOD;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
    }

    static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(Long.toString(answer));
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        output();
    }
}
