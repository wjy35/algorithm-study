package _24526;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/24526
 * @title 전화 돌리기
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
    List<Integer>[] reverseEdge;
    int[] outEdgeCount;


    public Solution readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        reverseEdge = new List[N+1];
        outEdgeCount = new int[N+1];
        for(int i=1; i<=N; i++) reverseEdge[i] = new ArrayList<>();

        int u,v;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            reverseEdge[v].add(u);
            outEdgeCount[u]++;
        }

        return this;
    }

    int count;
    public Solution solve(){
        count = 0;
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=1; i<=N; i++){
            if(outEdgeCount[i]!=0) continue;
            q.offer(i);
            count++;
        }

        int nowNode;
        while(!q.isEmpty()){
            nowNode = q.poll();

            for(int nextNode : reverseEdge[nowNode]){
                outEdgeCount[nextNode]--;

                if(outEdgeCount[nextNode]==0) {
                    q.offer(nextNode);
                    count++;
                }
            }
        }

        return this;
    }

    public void writeOutput() throws IOException{
        bw.write(Integer.toString(count));
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

