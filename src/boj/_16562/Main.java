package _16562;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/16562
 * @title 친구비
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,M,k;
    int[] price;

    int[] root;

    String answer;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        price = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) price[i] = Integer.parseInt(st.nextToken());

        root = new int[N+1];
        for(int i=1; i<=N; i++) root[i] = i;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            union(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
    }

    private void solve(){
        for(int i=1; i<=N; i++){
            find(i);
        }

        int cost = 0;
        boolean[] isCalculated = new boolean[N+1];
        for(int i=1; i<=N; i++){
            if(isCalculated[root[i]]) continue;

            cost += price[root[i]];
            isCalculated[root[i]] = true;
        }

        if(k<cost) answer = "Oh no";
        else answer = Integer.toString(cost);
    }

    private void writeOutput() throws IOException{
        bw.write(answer);
        bw.flush();
    }

    private int find(int x){
        if(root[x]==x) return x;

        return root[x] = find(root[x]);
    }
    private void union(int a,int b){
        int aRoot = find(a);
        int bRoot = find(b);

        int min,max;

        if(price[aRoot]<price[bRoot]){
            min = aRoot;
            max = bRoot;
        }else {
            min = bRoot;
            max = aRoot;
        }

        root[max] = min;
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

