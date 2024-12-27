package _18223;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/18223
 * @title 민준이와 마산 그리고 건우
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int V,E,P;
    List<Node>[] numberToEdgeList;

    String answer = "";
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        numberToEdgeList = new List[V+1];
        for(int i=1; i<=V; i++) numberToEdgeList[i] = new ArrayList<>();

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            numberToEdgeList[u].add(new Node(v,c));
            numberToEdgeList[v].add(new Node(u,c));
        }
    }

    private void solve(){
        int[] minCostsFromOne = getMinCosts(1);
        int[] minCostsFromGeonWoo = getMinCosts(P);

        if(minCostsFromOne[V]==minCostsFromOne[P]+minCostsFromGeonWoo[V]) answer = "SAVE HIM";
        else answer = "GOOD BYE";
    }

    private int[] getMinCosts(int startNumber){
        int[] minCosts = new int[V+1];
        Arrays.fill(minCosts,111_111_111);
        PriorityQueue<Node> pq = new PriorityQueue<>();

        minCosts[startNumber] = 0;
        pq.offer(new Node(startNumber,0));

        while(!pq.isEmpty()){
            Node current = pq.poll();

            if(current.cost!=minCosts[current.to]) continue;

            for(Node next : numberToEdgeList[current.to]){
                int nextCost = current.cost+next.cost;

                if(nextCost>=minCosts[next.to]) continue;
                pq.offer(new Node(next.to,nextCost));
                minCosts[next.to] = nextCost;
            }
        }

        return minCosts;
    }

    private void writeOutput() throws IOException{
        bw.write(answer);
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        solve();
        writeOutput();
    }

    private static class Node implements Comparable<Node>{
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost,o.cost);
        }
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

