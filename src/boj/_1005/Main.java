package _1005;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/1005">ACM Craft</a>
 * @category 위상정렬
 * @Note
 */

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int T;
    static int n,k,w;
    static int[] d;
    static ArrayList<Integer>[] edge;

    static int[] count;
    static int[] time;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case<=T; test_case++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            d = new int[n+1];
            count = new int[n+1];
            time = new int[n+1];
            edge = new ArrayList[n+1];

            st = new StringTokenizer(br.readLine());

            for(int i=1; i<=n; i++){
                d[i] = Integer.parseInt(st.nextToken());
                edge[i] = new ArrayList<>();
            }

            int u,v;
            for(int i=0; i<k; i++){
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                edge[u].add(v);
                count[v] ++;
            }

            w = Integer.parseInt(br.readLine());

            bw.write(Integer.toString(bfs()));
            bw.write("\n");
        }
        bw.flush();
    }

    static int bfs(){
        Queue<Integer> q = new ArrayDeque<>();

        for(int i=1; i<=n; i++){
            if(count[i] == 0) {
                q.offer(i);
                time[i] = d[i];
            }
        }
        int now,next;


        while(!q.isEmpty()){
            now = q.poll();
            if(now == w){
                return time[w];
            }
            for(int i=0; i<edge[now].size(); i++){
                next = edge[now].get(i);
                count[next] --;
                time[next] = Math.max(time[now] + d[next],time[next]);

                if(count[next] == 0){
                    q.offer(next);

                }
            }
        }

        return -1;
    }

}