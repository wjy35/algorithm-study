package _1158;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/1158
 * 요세푸스 문제
 */

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int n,k;
    static Queue<Integer> q;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        q = new LinkedList<>();
        for(int i=1; i<=n; i++){
            q.offer(i);
        }
        for(int i=1; i<k; i++){
            q.offer(q.poll());
        }
        sb.append("<").append(q.poll());

        int tmp=1;
        while(!q.isEmpty()){
            if(tmp==k){
                sb.append(", ").append(q.poll());
                tmp=1;
            }
            else{
                q.offer(q.poll());
                tmp++;
            }
        }
        sb.append(">");
        System.out.println(sb);

    }
}