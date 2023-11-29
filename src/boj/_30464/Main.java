package _30464;

import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/30464">시간낭비</a>
 * @category DP
 * @Note
 */
public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int N;
    static int[] a;
    static int[] dpFirst,dpSecond;
    static int answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        a = new int[N];
        for(int i=0; i<N; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        solution();

        bw.write(Integer.toString(answer));
        bw.flush();
    }

    private static void solution(){
        if(a[0] == N-1){
            answer = 1;
            return;
        }

        dpFirst = new int[N];

        for(int i=N-2; i>=0; i--){
            if(i+a[i]<N){
                if(i+a[i] == N-1 || dpFirst[i+a[i]]!=0){
                    dpFirst[i] = dpFirst[i+a[i]]+1;
                }
            }
        }

        dpSecond = new int[N];
        for(int i=0; i<N-1; i++){
            if(i-a[i]<0) continue;
            if(dpFirst[i-a[i]]>0 && dpSecond[i-a[i]]>0) dpSecond[i] = Math.max(dpFirst[i-a[i]]+1,dpSecond[i-a[i]]+1);
            else if(dpFirst[i-a[i]]>0) dpSecond[i] = dpFirst[i-a[i]]+1;
            else if(dpSecond[i-a[i]]>0) dpSecond[i] = dpSecond[i-a[i]]+1;
        }

        int max = 0;
        int now = 0;
        int minute = 0;
        while(now<N){
            if(dpSecond[now] !=0) max = Math.max(max,dpSecond[now]+minute);
            if(a[now]==0) break;
            now = now+a[now];
            minute++;
        }

        if(max == 0){
            answer = -1;
            return;
        }

        answer = max;
    }
}
