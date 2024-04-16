package _2637;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2637
 * @title 장난감 조립
 * @algorithm Topological Sort
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
    int[] outEdgeCount;
    int[] inEdgeCount;
    List<Edge>[] reverseEdge;

    List<Integer> basicPart;
    int[] partCount;

    public Solution readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        outEdgeCount = new int[N+1];
        inEdgeCount = new int[N+1];
        reverseEdge = new List[N+1];
        for(int i=1; i<=N; i++) reverseEdge[i] = new ArrayList<>();


        int X,Y,K;
        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            inEdgeCount[X]++;
            outEdgeCount[Y]++;
            reverseEdge[X].add(new Edge(Y,K));
        }
        return this;
    }


    public Solution solve(){
        basicPart = new ArrayList<>();
        partCount = new int[N+1];
        for(int i=1; i<=N; i++){
            if(inEdgeCount[i]==0) basicPart.add(i);
        }


        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);
        partCount[N] = 1;

        int now;
        Edge next;
        while(!q.isEmpty()){
            now = q.poll();

            for(int i = 0; i< reverseEdge[now].size(); i++){
                next = reverseEdge[now].get(i);

                outEdgeCount[next.n]--;
                partCount[next.n]+=next.count*partCount[now];

                if(outEdgeCount[next.n]==0) q.offer(next.n);
            }
        }

        return this;
    }

    public void writeOutput() throws IOException{
        for(int i=0; i<basicPart.size(); i++){
            bw.write(basicPart.get(i).toString());
            bw.write(" ");
            bw.write(Integer.toString(partCount[basicPart.get(i)]));
            bw.write("\n");
        }
        bw.flush();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }

    static class Edge{
        int n;
        int count;

        public Edge(int n, int count) {
            this.n = n;
            this.count = count;
        }
    }
}

