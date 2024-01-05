package _30985;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/30985">직장인 파댕이의 사회생활</a>
 * @category 
 * @Note
 */
public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int N,M,K;


    static List<Edge>[] edgeList;
    static long[] elevatorCost;
    static final long INF = Long.MAX_VALUE/2;
    static long ans;

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    static void solution() throws IOException {
        long[] startMoveCost = dijkstra(1);
        long[] endMoveCost = dijkstra(N);

        ans=INF;
        for(int i=1; i<=N; i++){
            if(elevatorCost[i]==-1||startMoveCost[i]==INF||endMoveCost[i]==INF)continue;
            ans = Math.min(ans,startMoveCost[i]+endMoveCost[i]+(K-1)*elevatorCost[i]);
        }

        if(ans==INF)ans = -1;

        bw.write(Long.toString(ans));
        bw.flush();
    }

    static long[] dijkstra(int startNodeNumber){
        long[] minMoveCost = new long[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(minMoveCost,INF);

        minMoveCost[startNodeNumber] = 0;
        pq.offer(new Node(startNodeNumber,0));

        Node now;
        Edge next;
        int edgeCount;
        while(!pq.isEmpty()){
            now = pq.poll();

            if(minMoveCost[now.number]<now.costSum) continue;

            edgeCount = edgeList[now.number].size();
            for(int i=0; i<edgeCount; i++){
                next = edgeList[now.number].get(i);

                if(now.costSum+next.c<minMoveCost[next.v]){
                    minMoveCost[next.v] = now.costSum +next.c;
                    pq.offer(new Node(next.v,minMoveCost[next.v]));
                }
            }
        }

        return minMoveCost;
    }

    static void input() throws IOException {
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

        int u,v;
        long c;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            c = Long.parseLong(st.nextToken());

            edgeList[u].add(new Edge(v,c));
            edgeList[v].add(new Edge(u,c));
        }

        elevatorCost = new long[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            elevatorCost[i] = Long.parseLong(st.nextToken());
        }
    }

    static class Node implements Comparable<Node>{
        int number;
        long costSum;

        public Node(int number, long costSum) {
            this.number = number;
            this.costSum = costSum;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.costSum,o.costSum);
        }
    }

    static class Edge{
        int v;
        long c;

        public Edge(int v, long c) {
            this.v = v;
            this.c = c;
        }
    }
}
