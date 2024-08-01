package _10159;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/10159
 * @title 저울
 * @algorithm Bfs
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N;
    int M;
    List<Integer>[] ascEdge;
    List<Integer>[] descEdge;

    int[] immeasurableCount;
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        ascEdge = new List[N+1];
        for(int i=1; i<=N; i++) ascEdge[i] = new ArrayList<>();

        descEdge = new List[N+1];
        for(int i=1; i<=N; i++) descEdge[i] = new ArrayList<>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            ascEdge[u].add(v);
            descEdge[v].add(u);
        }
    }

    private void solve(){
        immeasurableCount = new int[N+1];
        for(int i=1; i<=N; i++){
            immeasurableCount[i] = N-(bfs(ascEdge,i)+bfs(descEdge,i)+1);
        }
    }

    private int bfs(List<Integer>[] edge,int start){
        boolean[] isVisited = new boolean[N+1];
        isVisited[start] = true;

        int measurableCount = 0;

        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);

        int now,next;
        while(!q.isEmpty()){
            now = q.poll();

            for(int i=0; i<edge[now].size(); i++){
                next = edge[now].get(i);
                if(isVisited[next]) continue;

                q.offer(next);
                isVisited[next] = true;
                measurableCount++;
            }
        }

        return measurableCount;
    }

    private void writeOutput() throws IOException{
        for(int i=1; i<=N; i++){
            bw.write(Integer.toString(immeasurableCount[i]));
            bw.write("\n");
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
}

