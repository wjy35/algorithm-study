package _10942;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/10942
 * 팰린드롬?
 */

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int n,m;
    static int s,e;
    static int[] a;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        a = new int[n+1];
        dp = new int[n+1][n+1];

        for(int i=1; i<=n; i++){
            a[i] = Integer.parseInt(st.nextToken());
            dp[i][i]=1;
        }

        for(int i=1; i<n; i++){
            if(a[i]==a[i+1]) dp[i][i+1]=1;
        }

        for(int i=2; i<=n; i++){
            for(int j=1; i+j<=n; j++){
                if(a[j]==a[j+i] && dp[j+1][i+j-1]==1){
                    dp[j][j+i]=1;
                }
            }
        }

        m = Integer.parseInt(br.readLine());

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            sb.append(dp[s][e]).append("\n");
        }

        System.out.print(sb);
    }
}