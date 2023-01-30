package _1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
/*
 * https://www.acmicpc.net/problem/1238
 * 파티
 */

class Node implements Comparable<Node>{
    int num;
    int t;
    public Node(int num, int t) {

        this.num = num;
        this.t = t;
    }
    @Override
    public int compareTo(Node o) {
        return this.t-o.t;
    }
}


public class Main {
    static int n,m,x;
    static List<Node>[] edge;
    static List<Node>[] redge;

    static final int INF = 1_000_000_00;
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        edge = new List[n+1];
        redge = new List[n+1];

        for(int i=1; i<=n; i++) {
            edge[i] = new ArrayList<>();
            redge[i] = new ArrayList<>();
        }

        int u,v,t;

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());

            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            edge[u].add(new Node(v,t));
            redge[v].add(new Node(u,t));
        }

        System.out.println(getMaxDuration());

    }
    static int[] toX(){
        int[] d = new int[n+1];
        Arrays.fill(d, INF);

        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        d[x]=0;
        Node now;
        pq.offer(new Node(x,0));

        while(!pq.isEmpty()) {
            now = pq.poll();

            if(now.t>d[now.num]) continue;

            for(int i=0; i<redge[now.num].size(); i++) {
                Node next = redge[now.num].get(i);

                if(now.t+next.t <d[next.num]) {
                    d[next.num] = now.t+next.t;
                    pq.offer(new Node(next.num,d[next.num]));
                }
            }
        }
        return d;
    }
    static int[] fromX() {
        int[] d = new int[n+1];
        Arrays.fill(d, INF);

        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        d[x]=0;
        Node now;
        pq.offer(new Node(x,0));

        while(!pq.isEmpty()) {
            now = pq.poll();

            if(now.t>d[now.num]) continue;

            for(int i=0; i<edge[now.num].size(); i++) {
                Node next = edge[now.num].get(i);

                if(now.t+next.t <d[next.num]) {
                    d[next.num] = now.t+next.t;
                    pq.offer(new Node(next.num,d[next.num]));
                }
            }
        }
        return d;
    }


    static int getMaxDuration() {
        int MAX=0;
        int result;
        int[] to = toX();
        int[] from = fromX();

        for(int i=1; i<=n; i++) {

            result = to[i]+from[i];
            if(result>MAX) MAX= result;
        }

        return MAX;
    }
}
