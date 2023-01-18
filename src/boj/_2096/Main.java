package _2096;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/2096
 * 내려가기
 */

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int[][] dp_max;
    static int[][] dp_min;
    static int[][]game;
    static int n;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        dp_max = new int[n+1][3];
        dp_min = new int[n+1][3];
        game = new int[n+1][3];

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++){
                game[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1; i<=n; i++){
            dp_max[i][0] = Math.max(dp_max[i-1][0], dp_max[i-1][1])+game[i][0];
            dp_max[i][1] = Math.max(Math.max(dp_max[i-1][0], dp_max[i-1][1]), dp_max[i-1][2])+game[i][1];
            dp_max[i][2] = Math.max(dp_max[i-1][1], dp_max[i-1][2])+game[i][2];

            dp_min[i][0] = Math.min(dp_min[i-1][0], dp_min[i-1][1])+game[i][0];
            dp_min[i][1] = Math.min(Math.min(dp_min[i-1][0], dp_min[i-1][1]), dp_min[i-1][2])+game[i][1];
            dp_min[i][2] = Math.min(dp_min[i-1][1], dp_min[i-1][2])+game[i][2];
        }

        Arrays.sort(dp_max[n]);
        Arrays.sort(dp_min[n]);

        sb.append(dp_max[n][2]).append(" ").append(dp_min[n][0]);
        System.out.println(sb);
    }
}