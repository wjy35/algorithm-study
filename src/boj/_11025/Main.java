package _11025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/11025
 * 요세푸스 문제 3
 */

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n,k;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int start=1;

        for(int i=2; i<=n; i++){
            start = (start+k-1)%i+1;
        }
        System.out.println(start);

    }
}