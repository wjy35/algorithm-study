package _17404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/17404
 * RGB거리 2
 */

public class Main {


    static final int INF = 1_000_000_01;
    static final int RED=0;
    static final int GREEN=1;
    static final int BLUE=2;
    static int ans=INF;

    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int[][] cost;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cost = new int[n][3];
        dp = new int[3][n][3];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            cost[i][RED] = Integer.parseInt(st.nextToken());
            cost[i][GREEN] = Integer.parseInt(st.nextToken());
            cost[i][BLUE] = Integer.parseInt(st.nextToken());
        }

        for(int start_color=0; start_color<3; start_color++){
            for(int cost_color=0; cost_color<3; cost_color++){
                if(start_color==cost_color){
                    dp[start_color][0][cost_color]=cost[0][cost_color];
                }
                else dp[start_color][0][cost_color]=INF;
            }
        }

        for(int i=1; i<n; i++){
            for(int start_color=0; start_color<3; start_color++){
                dp[start_color][i][RED] = Math.min(dp[start_color][i-1][BLUE],dp[start_color][i-1][GREEN])+cost[i][RED];
                dp[start_color][i][GREEN] = Math.min(dp[start_color][i-1][RED],dp[start_color][i-1][BLUE])+cost[i][GREEN];
                dp[start_color][i][BLUE] = Math.min(dp[start_color][i-1][RED],dp[start_color][i-1][GREEN])+cost[i][BLUE];
            }
        }

        for(int start_color = 0; start_color<3; start_color++){
            for(int end_color = 0; end_color<3; end_color++){
                if(start_color==end_color)continue;
                ans = Math.min(ans,dp[start_color][n-1][end_color]);
            }
        }

        System.out.println(ans);

    }
}