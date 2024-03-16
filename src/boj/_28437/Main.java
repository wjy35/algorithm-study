package _28437;

import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/28437">막대 만들기</a>
 * @category
 * @Note
 */
public class Main {

    static int N;
    static int[] A;
    static int Q;
    static int[] L;

    static int[] dp;

    static void solution(){
        dp = new int[100_001];
        for(int i=0; i<N; i++){
            dp[A[i]]++;
        }

        for(int i=1; i<=100_000; i++){
            if(dp[i]==0)continue;
            for(int j=2; i*j<=100_000; j++){
                dp[i*j] += dp[i];
            }
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        Q = Integer.parseInt(br.readLine());

        L = new int[Q];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<Q; i++){
            L[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=0; i<Q; i++){
            bw.write(Integer.toString(dp[L[i]]));
            bw.write(" ");
        }
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        output();
    }
}
