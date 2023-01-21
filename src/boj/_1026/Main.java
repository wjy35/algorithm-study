package _1026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/1026
 * 보물
 */

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int result=0;
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());


        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            min.offer(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            max.offer(Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<n; i++){
            result += max.poll() * min.poll();
        }
        System.out.println(result);
    }
}