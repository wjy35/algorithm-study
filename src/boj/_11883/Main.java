package _11883;

import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/11883">생일수 I</a>
 * @category dp
 * @Note
 */
public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static int T;

    static int[][] dp;

    static void update(){
        dp = new int[2][1000001];
        Arrays.fill(dp[1],Integer.MAX_VALUE);
        dp[0][3] = 3;
        dp[1][3] = 1;

        dp[0][5] = 5;
        dp[1][5] = 1;

        dp[0][8] = 8;
        dp[1][8] = 1;

        for(int i=1; i<=1000000; i++){
            if(dp[0][i]==0)continue;

            updateByNumAt(3,i);
            updateByNumAt(5,i);
            updateByNumAt(8,i);
        }
    }

    static void updateByNumAt(int num,int index){
        if(index+num<=1000000 && dp[1][index+num]> dp[1][index]+1) {
            dp[0][index+num] = index;
            dp[1][index+num] = dp[1][index]+1;
        }
    }

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
    }

    static String getBirthdayNum(int index) throws IOException {
        if(dp[0][index]==0) return "-1";

        StringBuilder sb = new StringBuilder();

        while(index!= dp[0][index]){
            sb.append(index-dp[0][index]);
            index = dp[0][index];
        }
        sb.append(index);
        sb.reverse();
        return sb.toString();
    }

    static void query() throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=0; i<T; i++){
            bw.write(getBirthdayNum(Integer.parseInt(br.readLine())));
            bw.write("\n");
        }
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        input();
        update();
        query();
    }
}
