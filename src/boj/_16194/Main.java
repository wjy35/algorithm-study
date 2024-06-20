package _16194;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/16194
 * @title 카드 구매하기 2
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
    int[] P;

    int[] dp;
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        P = new int[N+1];
        for(int i=1; i<=N; i++){
            P[i] = Integer.parseInt(st.nextToken());
        }
    }

    private void solve(){
        dp = new int[N+1];

        for(int i=1; i<=N; i++){
            dp[i] = P[i];

            int half = i/2;
            for(int j=1; j<=half; j++){
                dp[i] = Math.min(dp[i],dp[i-j]+dp[j]);
            }
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(dp[N]));
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

