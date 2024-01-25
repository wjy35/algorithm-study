package _1947;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/1947">선물 전달</a>
 * @category DP
 * @Note
 */
public class Main {
    static long answer;
    static int N;
    static final long MOD = 1_000_000_000l;

    static void solution(){
        int size = N>2?N+1:2+1;
        long[] dp = new long[size];

        dp[1] = 0;
        dp[2] = 1;
        for(int i=3; i<=N; i++){
            dp[i] = (((dp[i-1]+dp[i-2])%MOD)*(i-1))%MOD;
        }

        answer = dp[N];
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
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
