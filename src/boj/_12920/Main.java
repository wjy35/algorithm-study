package _12920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/12920
 * 평범한 배낭 2
 */


public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int n,m;

    static int v,c,k;

    static List<Integer> vlist;
    static List<Integer> clist;

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());


        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        vlist = new ArrayList<>();
        clist = new ArrayList<>();
        vlist.add(0);
        clist.add(0);

        int now,sum,last;

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            now=1;
            sum=0;

            while(sum+now<=k){
                sum+=now;
                vlist.add(now*v);
                clist.add(now*c);
                now=now*2;
            }
            last = k-sum;
            if(last>0){
                vlist.add(last*v);
                clist.add(last*c);
            }
        }

        dp = new int[clist.size()][m+1];


        for(int i=1; i<clist.size(); i++){
            for(int j=1; j<=m; j++){
                if(j<vlist.get(i)) dp[i][j]=dp[i-1][j];
                else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-vlist.get(i)]+clist.get(i));
                }
            }
        }

        System.out.println(dp[clist.size()-1][m]);
    }

}