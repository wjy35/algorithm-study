package _27970;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/27970">OX</a>
 * @category
 * @Note
 */
public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static String input;
    static long[] dp;
    static long div;
    static long count;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        input = br.readLine();

        div = 1_000_000_000l + 7l;

        dp = new long[input.length()];
        dp[0] = 1;

        for(int i=1; i<dp.length; i++){
            dp[i]= ( dp[i-1] * 2 ) % div;
        }

        for(int i=0; i<input.length(); i++){
            if(input.charAt(i)=='O') count = (count + dp[i])%div;
        }

        bw.write(Long.toString(count));
        bw.flush();
    }
}