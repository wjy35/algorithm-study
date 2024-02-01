package _2228;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/2228">구간 나누기</a>
 * @category
 * @Note
 */
public class Main {
    static int answer;
    static int N,M;
    static int[] input;

    static int[][] inputSum;
    static int[][][] dp;
    static final int INF = Integer.MIN_VALUE;

    static void solution(){
        makeInputSum();

        dp = new int[N][N][M+1];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                for(int k=0; k<=M; k++){
                    dp[i][j][k] = INF;
                }
            }
        }

        answer = INF;
        for(int i=0; i<N; i++){
            for(int j=i; j<N; j++){
                if((j+(M-1)*2)>=N) continue;
                answer = Math.max(answer,dfs(i,j,M-1));
            }
        }
    }

    static int dfs(int start,int end,int count){
        if(count==0){
            return inputSum[start][end];
        }
        if(dp[start][end][count]!=INF) return dp[start][end][count];

        int max = INF;
        for(int i=end+2; i<N; i++){
            for(int j=i; j<N; j++){
                if((j+(count-1)*2)>=N) continue;
                max = Math.max(max,dfs(i,j,count-1));
            }
        }

        return dp[start][end][count] = max+inputSum[start][end];
    }

    static void makeInputSum(){
        inputSum = new int[N][N];

        for(int i=0; i<N; i++){
            inputSum[i][i] = input[i];
        }

        for(int length=1; length<N; length++) {
            for (int i = 0; i + length < N; i++) {
                inputSum[i][i + length] = inputSum[i][i + length - 1] + input[i + length];
            }
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        input = new int[N];
        for(int i=0; i<N; i++){
            input[i] = Integer.parseInt(br.readLine());
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
