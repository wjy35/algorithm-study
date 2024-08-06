package _20955;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/20955
 * @title 민서의 응급 수술
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
    int[] root;

    int groupCount = 0;
    int cutCount = 0;
    int answer = 0;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        root = new int[N+1];

        for(int i=1; i<=N; i++) root[i] = i;

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            union(u,v);
        }
    }

    private void solve(){
        for(int i=1; i<=N; i++) root[i] = find(root[i]);

        boolean[] isLinked  = new boolean[N+1];
        int groupCount = 0;

        for(int i=1; i<=N; i++){
            if(isLinked[root[i]]) continue;

            groupCount++;
            isLinked[root[i]] = true;
        }

        answer = groupCount-1+cutCount;
    }

    private int find(int x){
        if(x == root[x]) return x;

        return root[x] = find(root[x]);
    }

    private void union(int a,int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot) {
            cutCount++;
            return;
        }

        root[aRoot] = bRoot;
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(answer));
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

