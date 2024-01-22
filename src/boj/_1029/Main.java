package _1029;

import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/1029">그림 교환</a>
 * @category
 * @Note
 */
public class Main {
    static int answer;
    static int N;
    static int[][] painting;

    static int[][][] dp;

    static void solution(){
        dp = new int[1<<15][N][N];
        answer = dfs(1,0,0);
    }

    static int dfs(int status,int pre,int now){
        if(dp[status][pre][now]!=0) return dp[status][pre][now];

        int max=Integer.bitCount(status);
        for(int j=0; j<N; j++){
            if(((1<<j)&status)==0){
                if(painting[pre][now]<=painting[now][j]){
                    max = Math.max(max,dfs((1<<j)|status,now,j));
                }
            }
        }

        return (dp[status][pre][now] = max);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        painting = new int[N][N];

        String line;
        for(int i=0; i<N; i++){
            line = br.readLine();
            for(int j=0; j<N; j++){
                painting[i][j] = line.charAt(j)-'0';
            }
        }

        painting[0][0] = 0;
    }

    static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(Integer.toString(answer));
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        output();
    }
}
