package _14267;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/14267
 * @title 회사 문화 1
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
    List<Integer>[] numberToEdgeList;
    int[] happiness;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        numberToEdgeList = new List[n+1];
        for(int i=1; i<=n; i++) numberToEdgeList[i] = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        st.nextToken();
        for(int i=2; i<=n; i++){
            numberToEdgeList[Integer.parseInt(st.nextToken())].add(i);
        }

        happiness = new int[n+1];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            happiness[Integer.parseInt(st.nextToken())] += Integer.parseInt(st.nextToken());
        }
    }

    private void solve(){
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);

        while(!q.isEmpty()){
            int current = q.poll();

            for(int next : numberToEdgeList[current]){
                happiness[next] += happiness[current];
                q.offer(next);
            }
        }
    }

    private void writeOutput() throws IOException{
        for(int i=1; i<=n; i++){
            bw.write(Integer.toString(happiness[i]));
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
}

