package _32934;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/32934
 * @title 풍성한 트리
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
    List<Integer>[] edges;
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        edges = new List[N+1];
        for(int i=1; i<=N; i++) edges[i] = new ArrayList<>();

        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            edges[a].add(b);
            edges[b].add(a);
        }
    }

    int node = -1;
    private void solve(){
        Queue<Integer> q = new ArrayDeque<>();
        int[] distances = new int[N+1];

        for(int i=1; i<=N; i++){
            if(edges[i].size()!=1) continue;

            q.offer(i);
            distances[i] = 2;
        }
        while(!q.isEmpty()){
            int current = q.poll();

            for(Integer next : edges[current]){
                if(distances[current]+1==distances[next] || distances[current]-1==distances[next]) continue;
                if(distances[next]!=0) return;

                int degree = edges[next].size()-1;
                if(degree==1 || degree==3) return;

                q.offer(next);
                distances[next] = distances[current]+1;
            }
        }

        int max = 0;
        for(int i=1; i<=N; i++){
            max = Math.max(distances[i],max);
        }

        for(int i=1; i<=N; i++){
            if(max!=distances[i]) continue;
            if(edges[i].size()!=3) continue;

            node = i;
            return;
        }
    }

    private void writeOutput() throws IOException{
        if(node==-1){
            bw.write("-1");
        }else{
            bw.write("1");
            bw.write("\n");
            bw.write(Integer.toString(node));
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

