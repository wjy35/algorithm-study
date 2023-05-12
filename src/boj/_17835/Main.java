package _17835;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/17835">면접보는 승범이네</a>
 * @category
 * @Note
 */
public class Main {
    static class Node implements Comparable<Node>{
        int num;
        long cost;

        public Node(int num, long cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost,o.cost);
        }
    }

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n,m,k;
    static ArrayList<Node>[] reverseEdge;
    static PriorityQueue<Node> pq;
    static long[] d;
    static long INF = 1_000_000_000_000l;
    static int goal;
    
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        reverseEdge = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            reverseEdge[i] = new ArrayList<>();
        }
        pq = new PriorityQueue<>();
        d = new long[n+1];

        int u,v;
        long w;

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Long.parseLong(st.nextToken());

            reverseEdge[v].add(new Node(u,w));
        }

        Arrays.fill(d,INF);
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++){
            goal = Integer.parseInt(st.nextToken());
            pq.offer(new Node(goal,0));
            d[goal] = 0;
        }

        dijkstra();

        long maxCost = 0;
        for(int i=1; i<=n; i++){
            maxCost = Math.max(maxCost,d[i]);
        }

        for(int i=1; i<=n; i++){
            if(d[i] == maxCost){
                bw.write(Integer.toString(i));
                bw.write("\n");
                bw.write(Long.toString(d[i]));
                break;
            }
        }
        bw.flush();
    }

    static void dijkstra(){
        Node now,nextEdge;
        while(!pq.isEmpty()){
            now = pq.poll();

            if(now.cost != d[now.num]) continue;

            for(int i=0; i<reverseEdge[now.num].size(); i++){
                nextEdge = reverseEdge[now.num].get(i);
                if(now.cost+nextEdge.cost < d[nextEdge.num]){
                    pq.offer(new Node(nextEdge.num,now.cost+nextEdge.cost));
                    d[nextEdge.num] = now.cost+nextEdge.cost;
                }
            }
        }
    }
}
