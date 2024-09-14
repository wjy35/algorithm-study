package _1939.v1;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1939
 * @title 중량제한
 * @algorithm Binary Search
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .readInput()
                .solve()
                .writeOutput();
    }
}

class Solution{

    int N,M;
    List<Edge>[] nodeToEdgeList;
    int start,end;

    long answer;

    public Solution readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodeToEdgeList = new List[N+1];
        for(int i=1; i<=N; i++) nodeToEdgeList[i] = new ArrayList<>();

        int u,v;
        long c;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            c = Long.parseLong(st.nextToken());

            nodeToEdgeList[u].add(new Edge(v,c));
            nodeToEdgeList[v].add(new Edge(u,c));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        return this;
    }

    public Solution solve(){
        long l = 0;
        long r = 1_000_000_000;
        long mid;

        while(l<=r){
            mid = (l+r)/2;

            if(isPathAvailableIn(mid)){
                l = mid+1;
                answer = Math.max(answer,mid);
            }else{
                r = mid-1;
            }
        }

        return this;
    }

    private boolean isPathAvailableIn(long minCost){
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] isVisited = new boolean[N+1];

        q.offer(start);
        isVisited[start] = true;

        int nowNode;
        while(!q.isEmpty()){
            nowNode = q.poll();
            if(nowNode==end) return true;
            for(Edge edge : nodeToEdgeList[nowNode]){
                if(edge.cost<minCost) continue;
                if(isVisited[edge.nextNode]) continue;

                isVisited[edge.nextNode] = true;
                q.offer(edge.nextNode);
            }
        }

        return false;
    }

    public void writeOutput() throws IOException{
        bw.write(Long.toString(answer));
        bw.flush();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }

    static private class Edge{
        int nextNode;
        long cost;

        public Edge(int nextNode, long cost) {
            this.nextNode = nextNode;
            this.cost = cost;
        }
    }
}

