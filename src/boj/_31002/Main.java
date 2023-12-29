package _31002;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/31002">그래프 변환</a>
 * @category DP, 모듈러 나눗셈
 * @Note
 */
public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int N,K;
    static long[] dpNode;
    static long[] dpCount;
    static long MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dpNode = new long[K+1];
        dpCount = new long[K+1];

        dpNode[0] = N;
        dpCount[0] = N-1;

        for(int i=1; i<=K; i++){
            dpNode[i] = (((dpCount[i-1]*dpNode[i-1])%MOD)*500_000_004)%MOD;
            dpCount[i] = (dpCount[i-1]-1)*2%MOD;
        }

        bw.write(Long.toString(dpNode[K]));
        bw.flush();
    }
}
