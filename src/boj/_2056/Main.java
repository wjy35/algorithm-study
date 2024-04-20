package _2056;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2056
 * @title 작업
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

    int N;
    int[] cost;
    List<Integer>[] edge;

    int[] incomingEdgeCunt;

    public Solution readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        cost = new int[N+1];
        edge = new List[N+1];
        incomingEdgeCunt = new int[N+1];
        for (int i = 1; i <= N; i++) edge[i] = new ArrayList<>();

        int preNodeCount,preNode;
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());

            preNodeCount = Integer.parseInt(st.nextToken());
            for(int j=0; j<preNodeCount; j++){
                preNode = Integer.parseInt(st.nextToken());
                edge[preNode].add(i);
                incomingEdgeCunt[i]++;
            }
        }

        return this;
    }

    int answer;

    public Solution solve(){
        Queue<Integer> q = new ArrayDeque<>();
        int[] minCost = new int[N+1];

        for(int i=1; i<=N; i++) {
            if(incomingEdgeCunt[i]!=0) continue;

            q.offer(i);
        }

        int now;
        while(!q.isEmpty()){
            now = q.poll();
            minCost[now] += cost[now];

            for(Integer next : edge[now]){
                incomingEdgeCunt[next]--;

                minCost[next] = Math.max(minCost[next],minCost[now]);

                if(incomingEdgeCunt[next]==0) q.offer(next);
            }
        }

        answer = 0;
        for(int i=1; i<=N; i++){
            answer = Math.max(answer,minCost[i]);
        }
        return this;
    }

    public void writeOutput() throws IOException{
        bw.write(Integer.toString(answer));
        bw.flush();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

