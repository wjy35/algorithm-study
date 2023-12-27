package _30869;

import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/30869">빨리 기다리기</a>
 * @category
 * @Note
 */

public class Main {
    static class Edge{
        int e,t,g;

        public Edge(int e, int t, int g) {
            this.e = e;
            this.t = t;
            this.g = g;
        }
    }

    static class Node implements Comparable<Node>{
        int n,t,k;

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.t,o.t);
        }

    }

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int N,M,K;
    static List<Edge>[] edgeList;
    static final int INF = 5_000_001;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        edgeList = new List[N+1];
        for(int i=1; i<=N; i++){
            edgeList[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            edgeList[Integer.parseInt(st.nextToken())]
                    .add(new Edge(
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken())
                    ));
        }

        bw.write(Integer.toString(dijkstra()));
        bw.flush();
    }

    static int dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();

        int[][] t = new int[K+1][N+1];
        for(int i=0; i<=K; i++){
            for(int j=1; j<=N; j++){
                t[i][j]=INF;
            }
        }

        Node start;
        start = new Node();
        start.n = 1;
        start.t = 0;
        start.k = 0;
        pq.offer(start);
        t[start.k][start.n]=0;

        Node now,next;
        int edgeCount;
        int nextStart;

        while(!pq.isEmpty()){
            now = pq.poll();

            if(now.t>t[now.k][now.n]) continue;
            if(now.n==N) return now.t;

            edgeCount = edgeList[now.n].size();

            for(int i=0; i<edgeCount; i++){
                nextStart = getNextStart(now.t,edgeList[now.n].get(i).g);

                if(nextStart+edgeList[now.n].get(i).t<t[now.k][edgeList[now.n].get(i).e]){
                    next = new Node();
                    next.n = edgeList[now.n].get(i).e;
                    next.k = now.k;
                    next.t = nextStart+edgeList[now.n].get(i).t;

                    pq.offer(next);
                    t[next.k][next.n]=next.t;
                }

                if(now.k<K && now.t+edgeList[now.n].get(i).t<t[now.k+1][edgeList[now.n].get(i).e]&&now.t+edgeList[now.n].get(i).t<t[now.k][edgeList[now.n].get(i).e]){
                    next = new Node();
                    next.n = edgeList[now.n].get(i).e;
                    next.k = now.k+1;
                    next.t = now.t+edgeList[now.n].get(i).t;

                    pq.offer(next);
                    t[next.k][next.n]=next.t;
                }
            }
        }

        return -1;
    }

    static int getNextStart(int nowT,int nextG){
        if (nowT==0) return 0;

        int p = nowT/nextG;
        int q = nowT%nextG;

        return q==0 ? nowT:nextG*(p+1);
    }
}
