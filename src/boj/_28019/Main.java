package _28019;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/28019;">산지니의 여행계획v</a>
 * @category
 * @Note
 */
public class Main {
    static class Edge implements Comparable<Edge>{
        int a,b,c;

        public Edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return -Integer.compare(this.c,o.c);
        }
    }

    static class Ndge{
        int node;
        long cost;

        public Ndge(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n,m;
    static int[] root;
    static PriorityQueue<Edge> pq;

    static ArrayList<Ndge>[] selectedEdge;
    static boolean[] visit;
    static int start;
    static long maxCostSum;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
        selectedEdge = new ArrayList[n+1];
        visit = new boolean[n+1];

        for(int i=1; i<=n; i++){
            selectedEdge[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            pq.offer(new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        start = Integer.parseInt(br.readLine());
        maxCostSum = 0;

        makeSet();

        long mst = getMst();
        findDistance(start,-1,0);

        System.out.println(mst*2 - maxCostSum);
        bw.flush();
    }

    public static void findDistance(int now,int pre,long costSum){
        if(selectedEdge[now].size() == 1 && now != start){
            maxCostSum = Math.max(costSum,maxCostSum);
            return;
        }

        for(int i=0; i<selectedEdge[now].size(); i++){
            if(selectedEdge[now].get(i).node == pre) continue;

            findDistance(selectedEdge[now].get(i).node,now,costSum+selectedEdge[now].get(i).cost);
        }
    }
    public static long getMst(){
        Edge now;
        int count = 0;
        long mst = 0;

        while(!pq.isEmpty()){
            now = pq.poll();

            if(union(now.a,now.b)){
                count++;
                mst+= now.c;

                selectedEdge[now.a].add(new Ndge(now.b,now.c));
                selectedEdge[now.b].add(new Ndge(now.a,now.c));
            }
            if(count==n-1) break;
        }

        return mst;
    }

    public static void makeSet(){
        root = new int[n+1];
        for(int i=1; i<=n; i++){
            root[i]=i;
        }
    }
    public static int find(int x){
        if(root[x]==x) return x;

        return root[x]=find(root[x]);
    }
    public static boolean union(int x1,int x2){
        int r1 = find(x1);
        int r2 = find(x2);

        if(r1==r2)return false;

        root[r1]=r2;

        return true;
    }
}
