package _15681;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/15681
 * @title 트리와 쿼리
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,R,Q;
    List<Integer>[] nodeToEdgeList;
    int[] querys;

    int[] subTreeNodeCount;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        nodeToEdgeList = new List[N+1];
        for(int node=1;node<=N;node++){
            nodeToEdgeList[node] = new ArrayList<>();
        }

        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            nodeToEdgeList[u].add(v);
            nodeToEdgeList[v].add(u);
        }

        querys = new int[Q];
        for(int i=0; i<Q; i++) querys[i] = Integer.parseInt(br.readLine());
    }

    private void solve(){
        subTreeNodeCount = new int[N+1];
        updateSubTreeNodeCount(0,R);
    }

    private int updateSubTreeNodeCount(int pre, int now){
        if(nodeToEdgeList[now].size()==1 && nodeToEdgeList[now].get(0)==pre) {
            subTreeNodeCount[now] = 1;
            return subTreeNodeCount[now];
        }

        for(int i=0; i<nodeToEdgeList[now].size(); i++){
            if(nodeToEdgeList[now].get(i)==pre) continue;

            subTreeNodeCount[now] += updateSubTreeNodeCount(now,nodeToEdgeList[now].get(i));
        }

        subTreeNodeCount[now]++;
        return subTreeNodeCount[now];
    }

    private void writeOutput() throws IOException{
        for(int i=0; i<querys.length; i++){
            bw.write(Integer.toString(subTreeNodeCount[querys[i]]));
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

