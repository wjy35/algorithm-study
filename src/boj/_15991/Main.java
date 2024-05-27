package _15991;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/15991
 * @title 1, 2, 3 더하기 6
 * @algorithm dp
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int T;
    final int MOD = 1_000_000_009;
    private void readInput() throws IOException{
        T = Integer.parseInt(br.readLine());
    }

    private void test() throws IOException {
        int[] dp = new int[100_001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 2;
        dp[4] = 3;
        dp[5] = 3;
        dp[6] = 6;

        for(int i=7; i<100_001; i++){
            dp[i] = ((dp[i-2]+dp[i-4])%MOD + dp[i-6])%MOD;
        }

        for(int t=0; t<T; t++){
            bw.write(Integer.toString(dp[Integer.parseInt(br.readLine())]));
            bw.write("\n");
        }
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        test();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

