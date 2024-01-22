package _7579;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/7579">앱</a>
 * @category DP
 * @Note
 */
public class Main {
    static int answer;

    static int N,M;
    static int[] m;
    static int[] c;

    static int maxMemory;
    static int maxCost;
    static int[][] dp;

    static void solution(){
        dp = new int[N+1][maxCost+1];

        for(int i=1; i<=N; i++){
            for(int j=0; j<=maxCost; j++){
                if(j<c[i]){
                    dp[i][j] = dp[i-1][j];
                    continue;
                }

                dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-c[i]]+m[i]);
            }
        }

        answer = getMinCost();
    }

    static int getMinCost(){
        for(int i=0; i<=maxCost; i++){
            for(int j=1; j<=N; j++){
                if(dp[j][i]>=M) return i;
            }
        }

        return -1;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        m = new int[N+1];
        c = new int[N+1];
        maxMemory = 0;
        maxCost = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            m[i] = Integer.parseInt(st.nextToken());
            maxMemory += m[i];
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            c[i] = Integer.parseInt(st.nextToken());
            maxCost += c[i];
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
