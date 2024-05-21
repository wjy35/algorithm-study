package _15989;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/15989
 * @title 1, 2, 3 더하기 4
 * @algorithm DP
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .readInput()
                .solve()
                .writeOutput();
    }
}

class Solution{

    int T;

    int[][] dp;
    public Solution readInput() throws IOException{
        T = Integer.parseInt(br.readLine());

        return this;
    }


    public Solution solve(){
        dp = new int[10001][4];
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for(int i=4; i<10001; i++){
            dp[i][1] = 1;
            dp[i][2] = dp[i-2][1] + dp[i-2][2];
            dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
        }

        return this;
    }


    public void writeOutput() throws IOException{
        for(int i=0; i<T; i++){
            int n = Integer.parseInt(br.readLine());
            bw.write(Integer.toString(dp[n][1]+dp[n][2]+dp[n][3]));
            bw.write("\n");
        }
        bw.flush();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

