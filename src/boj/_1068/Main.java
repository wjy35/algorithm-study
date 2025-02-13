package _1068;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1068
 * @title 트리
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N;
    List<Integer>[] edgeLists;
    int deletedNode;

    int root;
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        edgeLists = new List[N];
        for(int i=0; i<N; i++) edgeLists[i] = new ArrayList<>();
        for(int i=0; i<N; i++){
            int parent = Integer.parseInt(st.nextToken());

            if(parent==-1){
                root = i;

                continue;
            }

            edgeLists[parent].add(i);
        }

        deletedNode = Integer.parseInt(br.readLine());
    }

    int leafCount = 0;
    private void solve(){
        if(deletedNode==root) return;

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(root);

        while(!q.isEmpty()){
            int current = q.poll();

            int nextCount = 0;
            for(int next : edgeLists[current]){
                if(next==deletedNode) continue;

                q.offer(next);
                nextCount++;
            }

            if(nextCount==0) leafCount++;
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(leafCount));
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

