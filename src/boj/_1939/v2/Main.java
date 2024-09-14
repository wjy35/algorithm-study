package _1939;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1939
 * @title 중량제한
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,M;
    List<Node>[] numberToEdgeList;
    int startNumber,endNumber;

    final int INF = 1_000_000_001;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numberToEdgeList = new List[N+1];

        for(int i=1; i<=N; i++) numberToEdgeList[i] = new ArrayList<>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            numberToEdgeList[A].add(new Node(B,C));
            numberToEdgeList[B].add(new Node(A,C));
        }

        st = new StringTokenizer(br.readLine());
        startNumber = Integer.parseInt(st.nextToken());
        endNumber = Integer.parseInt(st.nextToken());
    }

    private void solve(){
        long[] d = new long[N+1];
        Arrays.fill(d,INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        d[startNumber] = 0;
        pq.offer(new Node(startNumber,0));

        Node now;
        while(!pq.isEmpty()){
            now = pq.poll();

            if(now.cost>d[now.number]) continue;

            int size = numberToEdgeList[now.number].size();

        }
    }

    private void writeOutput() throws IOException{
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        solve();
        writeOutput();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }

    private class Node implements Comparable<Node>{
        int number;
        long cost;

        public Node(int number, long cost) {
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node target) {
            return -Long.compare(this.cost,target.cost);
        }
    }
}

