package _1509;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/1509
 * 팰린드롬 분할
 */


public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static String input;
    static boolean[][] dp;
    static int[] ml;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();

        dp = new boolean[input.length()][input.length()];
        ml = new int[input.length()];
        for(int i=0; i<input.length(); i++) {
            dp[i][i]=true;
        }

        for(int i=0; i<input.length()-1; i++) {
            if(input.charAt(i)==input.charAt(i+1))dp[i][i+1]=true;
            else dp[i][i+1]=false;
        }

        for(int i=2; i<input.length(); i++) {
            for(int j=0; j+i<input.length(); j++) {
                if((input.charAt(j)==input.charAt(j+i)) && dp[j+1][j+i-1] ) {
                    dp[j][j+i]=true;
                }
                else dp[j][j+i]=false;
            }
        }


        ml[0]=1;
        int min=0;
        for(int i=1; i<input.length(); i++) {
            min=ml[i-1]+1;
            if(dp[0][i]) min=1;
            for(int j=i-1; j>0; j--) {
                if(dp[j][i]) {
                    min = Math.min(ml[j-1]+1, min);
                }
            }
            ml[i]=min;
        }

        System.out.println(ml[input.length()-1]);

    }
}