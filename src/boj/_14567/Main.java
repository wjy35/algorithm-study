package _14567;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/14567
 * @title 선수과목
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
    List<Integer>[] edge;

    int[] incomingEdgeCount;

    int[] semester;

    public Solution readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edge = new List[N+1];
        for(int i=1; i<=N; i++) edge[i] = new ArrayList<>();

        incomingEdgeCount = new int[N+1];
        int u,v;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            edge[u].add(v);
            incomingEdgeCount[v]++;
        }

        return this;
    }

    public Solution solve(){
        semester = new int[N+1];

        Queue<Integer> q = new ArrayDeque<>();
        for(int i=1; i<=N; i++) {
            if(incomingEdgeCount[i]!=0) continue;

            q.offer(i);
        }

        int now;
        while(!q.isEmpty()){
            now = q.poll();
            semester[now]++;

            for(Integer next : edge[now]){
                incomingEdgeCount[next]--;

                semester[next] = Math.max(semester[next],semester[now]);

                if(incomingEdgeCount[next]!=0) continue;

                q.offer(next);
            }
        }

        return this;
    }

    public void writeOutput() throws IOException{
        for(int i=1; i<=N; i++){
            bw.write(Integer.toString(semester[i]));
            bw.write(" ");
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
}

