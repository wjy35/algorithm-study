package _30621;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/30621">어? 금지</a>
 * @category 
 * @Note
 */
public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N;
    static int[] t;
    static int[] b;
    static long[] c;

    static long[] dp;
    static final int INF = 2_000_000_000;
    
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        t = new int[N+1];
        b = new int[N+1];
        c = new long[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            t[i] = Integer.parseInt(st.nextToken());
        }
        t[0] = -INF;

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            b[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            c[i] = Long.parseLong(st.nextToken());
        }

        dp = new long[N+1];
        dp[1] = c[1];
        dp[0] = 0l;

        int index;
        for(int i=2; i<=N; i++){
            index = Arrays.binarySearch(t,t[i]-b[i]);

            if(index<0)index= -index-1;
            index--;
            dp[i] = Math.max(dp[i-1],dp[index]+c[i]);
        }

        bw.write(Long.toString(dp[N]));
        bw.flush();
    }
}
