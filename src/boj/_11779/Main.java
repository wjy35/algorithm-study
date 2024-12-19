package _11779;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/11779
 * @title 최소비용 구하기 2
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int n,m;
    List<Node>[] edgeList;
    int s,e;

    int[] d;
    ArrayDeque<Integer> pathQueue;

    private void readInput() throws IOException{
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        edgeList = new List[n+1];
        for(int i=1; i<=n; i++) edgeList[i] = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            edgeList[Integer.parseInt(st.nextToken())].add(new Node(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
    }

    private void solve(){
        d = new int[n+1];
        Arrays.fill(d,1_000 * 100_000+1);

        int[] preNumber = new int[n+1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0));
        d[s] = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();

            if(current.cost != d[current.number]) continue;

            for(Node next : edgeList[current.number]){
                if(d[next.number]<=current.cost+next.cost) continue;

                d[next.number] = current.cost+next.cost;
                pq.offer(new Node(next.number,d[next.number]));

                preNumber[next.number] = current.number;
            }
        }

        pathQueue = new ArrayDeque<>();

        int current = e;
        pathQueue.offer(e);
        while(current!=s){
            current = preNumber[current];
            pathQueue.offerFirst(current);
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(d[e]));
        bw.write("\n");
        bw.write(Integer.toString(pathQueue.size()));
        bw.write("\n");
        while(!pathQueue.isEmpty()){
            bw.write(Integer.toString(pathQueue.poll()));
            bw.write(" ");
        }
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

    private static class Node implements Comparable<Node>{
        int number;
        int cost;

        public Node(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost,o.cost);
        }
    }
}

