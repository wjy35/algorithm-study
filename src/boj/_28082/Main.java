package _28082;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/28082
 * @title 기계오리 연구
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,K;
    int[] I;

    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        I = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            I[i] = Integer.parseInt(st.nextToken());
        }
    }

    List<Integer> values;
    private void solve(){
        int[][] cache = new int[N+1][50_001];

        for(int i=0; i<=N; i++) Arrays.fill(cache[i],1000);

        cache[0][0] = 0;
        for(int i=1; i<=N; i++){
            for(int j=0; j<=50000; j++){
                cache[i][j] = cache[i-1][j];

                if(j<I[i]) continue;
                if(cache[i-1][j-I[i]]==K) continue;

                cache[i][j] =  Math.min(cache[i-1][j-I[i]]+1,cache[i][j]);
            }
        }

        values = new ArrayList<>();
        for(int i=1; i<=50_000; i++){
            if(cache[N][i]==1000) continue;

            values.add(i);
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(values.size()));
        bw.write("\n");
        for(int value : values){
            bw.write(Integer.toString(value));
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

