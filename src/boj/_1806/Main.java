package _1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/1806
 * 부분 합
 */

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n,s;
    static int[] a;
    static int[] sum;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        a = new int[n+1];
        sum = new int[n+1];
        st = new StringTokenizer(br.readLine());

        for(int i=1; i<=n; i++){
            a[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i-1]+a[i];
        }

        int ans=1_000_001;
        int l=1;
        int r=1;
        while(l<=n&&r<=n){
            if(sum[r]-sum[l-1]>=s)
            {
                ans = Math.min(ans,r-l+1);
                l++;
                if(ans==1)break;
            }
            else{
                r++;
            }
        }
        if(ans==1_000_001) ans=0;
        System.out.println(ans);
    }
}