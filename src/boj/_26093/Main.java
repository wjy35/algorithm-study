package _26093;

import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/26093">고양이 목에 리본 달기</a>
 * @category DP, Greedy
 * @Note
 */
public class Main {
    static int answer;
    static int N,K;
    static int[][] happy;

    static void solution(){
        int[][] dp = new int[N+1][K+1];
        for(int i=1; i<=K; i++){
            dp[1][i] = happy[1][i];
        }

        int first,second;
        for(int i=2; i<=N; i++){
            first = 0;
            second = 0;
            for(int j=1; j<=K; j++){
                if(dp[i-1][j]>first){
                    second = first;
                    first = dp[i-1][j];
                    continue;
                }

                if(dp[i-1][j]>second) second = dp[i-1][j];
            }

            for(int j=1; j<=K; j++){
                if(dp[i-1][j]==first){
                    dp[i][j] = second+happy[i][j];
                    continue;
                }

                dp[i][j] = first+happy[i][j];
            }
        }

        answer = 0;
        for(int i=1; i<=K; i++){
            answer = Math.max(answer,dp[N][i]);
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        happy = new int[N+1][K+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=K; j++){
                happy[i][j] = Integer.parseInt(st.nextToken());
            }
        }
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
