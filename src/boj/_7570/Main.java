package _7570;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/7570">줄 세우기</a>
 * @category
 * @Note
 */
public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int n;
    static int[] dp;
    static int count;
    static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        dp = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            dp[Integer.parseInt(st.nextToken())] = i;
        }

        dp[0] = Integer.MAX_VALUE;
        count = 1;
        ans = 1;
        for(int i=1; i<=n; i++){
            if(dp[i-1]<dp[i]) {
                count++;
            } else{
                ans = Math.max(ans,count);
                count = 1;
            }
        }
        ans = Math.max(ans,count);

        bw.write(Integer.toString(n-ans));
        bw.flush();
    }
}