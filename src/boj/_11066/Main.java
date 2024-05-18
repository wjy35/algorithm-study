package _11066;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/11066
 * @title 파일 합치기
 * @algorithm Greedy, Priority Queue
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++){
            int K = Integer.parseInt(br.readLine());
            int[] C = new int[K+1];
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=K; j++) C[j] = Integer.parseInt(st.nextToken());

            bw.write(Solution.createSolution(K,C).solve());
            bw.write("\n");
        }
        bw.flush();
    }
}

class Solution{
    int K;
    int[] C;

    public String solve(){
        int[][] dp = new int[K+1][K+1];
        int[] prefixSum = new int[K+1];

        for(int i=1; i<K; i++) dp[i][i+1] = C[i]+C[i+1];
        for(int i=1; i<=K; i++) prefixSum[i] = prefixSum[i-1]+C[i];

        for (int n = 2; n <= K; n++) {
            for (int from = 1; from + n <= K; from++) {
                int to = from + n;
                dp[from][to] = Integer.MAX_VALUE;
                for (int divide = from; divide < to; divide++) {
                    dp[from][to] = Math.min(dp[from][to], dp[from][divide] + dp[divide+1][to] + prefixSum[to] - prefixSum[from-1]);
                }
            }
        }

        return Integer.toString(dp[1][K]);
    }

    public Solution(int k, int[] C) {
        this.K = k;
        this.C = C;
    }
    public static Solution createSolution(int K, int[] C){
        return new Solution(K,C);
    }
}
