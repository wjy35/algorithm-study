package _17175;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/17175
 * @title
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{
    int n;
    final int MOD = 1_000_000_007;

    int[] dp;

    int answer;
    private void readInput() throws IOException{
        n = Integer.parseInt(br.readLine());
    }

    private void solve(){
        dp = new int[51];
        for(int i=0; i<2; i++) dp[i] = 1;

        answer = fibonacci(n);
    }

    int fibonacci(int n) {
        if(dp[n]!=0) return dp[n];

        return dp[n] = ((fibonacci(n-2)+fibonacci(n-1))%MOD+1)%MOD;
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(answer));
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

