package _31502;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/31502">만화에서 나오는 거 따라하고 그러면 안 된다</a>
 * @category dijkstra, bfs
 * @Note
 */
public class Main {
    static int answer;

    static int N,M;
    static int A,B,C;

    static List<Node>[] edgeList;
    static int[] edgeCount;

    static final long INF = 1_000_000_000l * 100_000l + 1;

    /**
     * treeCount[i] 학교부터 i번 은행나무까지 나무 개수
     * isPath[i] 한별이가 이동하는 경로에 i번 은행나무가 포함되는지 여부
     * minCost[i] 토카가 i번 은행나무까지 이동하는데 필요한 최단거리
     */
    static int[] treeCount;
    static boolean[] isPath;
    static long[] minCost;

    static void solution(){
        updateTreeCount();
        updatePath();
        updateMinCost();

        long tmpCost = INF;
        answer = 0;
        for(int i=1; i<=N; i++){
            if(isPath[i] && tmpCost>minCost[i]){
                tmpCost = minCost[i];
                answer = i;
            }
        }
    }


    static void updateTreeCount(){
        treeCount = new int[N+1];

        treeCount[C] = 1;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(C);

        int now,next;
        while(!q.isEmpty()){
            now = q.poll();

            for(int i=0; i<edgeList[now].size(); i++){
                next = edgeList[now].get(i).number;
                if(treeCount[next]!=0) continue;

                q.offer(next);
                treeCount[next] = treeCount[now]+1;
            }
        }
    }

    static void updatePath(){
        isPath = new boolean[N+1];

        int now = B;
        int tmp;
        int next,nextEdgeCount;
        while(now!=C){
            isPath[now] = true;

            nextEdgeCount = 0;
            next = 0;
            for(int i=0; i<edgeList[now].size(); i++){
                tmp = edgeList[now].get(i).number;
                if(treeCount[tmp]!=treeCount[now]-1)continue;
                if(nextEdgeCount>edgeCount[tmp]) continue;
                if(nextEdgeCount==edgeCount[tmp] && next>tmp) continue;

                next = tmp;
                nextEdgeCount = edgeCount[tmp];
            }

            now = next;
        }

        isPath[C] = true;
    }

    static void updateMinCost(){
        minCost = new long[N+1];
        Arrays.fill(minCost,INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        minCost[A] = 0;
        pq.offer(new Node(A,0));

        Node nowNode,tmpNode;
        while(!pq.isEmpty()){
            nowNode = pq.poll();

            if(nowNode.cost!= minCost[nowNode.number]) continue;

            for(int i = 0; i<edgeList[nowNode.number].size(); i++){
                tmpNode = edgeList[nowNode.number].get(i);
                if(nowNode.cost+tmpNode.cost< minCost[tmpNode.number]){
                    minCost[tmpNode.number] = nowNode.cost+tmpNode.cost;
                    pq.offer(new Node(tmpNode.number, minCost[tmpNode.number]));
                }
            }
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        edgeList = new List[N+1];
        edgeCount = new int[N+1];

        for(int i=1; i<=N; i++){
            edgeList[i] = new ArrayList<>();
        }

        int u,v,c;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            edgeCount[u]++;
            edgeCount[v]++;
            edgeList[u].add(new Node(v,c));
            edgeList[v].add(new Node(u,c));
        }

    }

    static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(Integer.toString(answer));
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        output();
    }

    static class Node implements Comparable<Node> {
        int number;
        long cost;

        public Node(int number, long cost) {
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost,o.cost);
        }
    }

}
