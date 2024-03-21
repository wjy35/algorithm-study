package _23286;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/23286">허들 넘기</a>
 * @category
 * @Note
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .readInput()
                .update()
                .writeOutput();
    }
}

class Solution{
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    /** Input */
    private int N,M,T;
    private List<Node>[] edge;

    /** Output */
    private int answer = 0;

    /** Other Variable */
    private final int INF = 300*1_000_000+1;
    private int[][] minH;

    public Solution readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        edge = new List[N+1];
        for(int i=1; i<=N; i++) edge[i] = new ArrayList<>();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            edge[Integer.parseInt(st.nextToken())].add(new Node(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            ));
        }

        minH = new int[N+1][N+1];

        return this;
    }

    public Solution update(){
        for(int i=1; i<=N; i++){
            updateAt(i);
        }

        return this;
    }

    private void updateAt(int s){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(minH[s],INF);

        Node now,tmp;
        int nextH;
        minH[s][s] = 0;
        pq.offer(new Node(s,0));
        while(!pq.isEmpty()){
            now = pq.poll();

            if(now.h!=minH[s][now.v]) continue;
            for(int i=0; i<edge[now.v].size(); i++){
                tmp = edge[now.v].get(i);
                nextH = Math.max(now.h,tmp.h);
                if(nextH<minH[s][tmp.v]){
                    pq.offer(new Node(tmp.v,nextH));
                    minH[s][tmp.v] = nextH;
                }
            }
        }

        for(int i=1; i<=N; i++){
            if(minH[s][i]==INF) minH[s][i] = -1;
        }
    }

    public Solution writeOutput() throws IOException{
        int s,e;
        for(int i=0; i<T; i++){
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            bw.write(Integer.toString(query(s, e)));
            bw.write("\n");
        }

        bw.flush();
        return this;
    }

    private int query(int s,int e){
        return minH[s][e];
    }

    static class Node implements Comparable<Node>{
        int v,h;

        public Node(int v, int h) {
            this.v = v;
            this.h = h;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.h,o.h);
        }
    }

    private Solution(){}

    public static Solution createSolution(){
        return new Solution();
    }
}

