package _2015;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2015
 * @title 수들의 합 4
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
    int[] A;

    int[] prefixSum;

    long answer = 0;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) A[i] = Integer.parseInt(st.nextToken());
    }

    private void solve(){
        int[] prefixSum = new int[N+1];
        for(int i=1; i<=N; i++) prefixSum[i] = prefixSum[i-1]+A[i];

        Map<Integer,Long> prefixSumToCount = new HashMap<>();
        for(int i=0; i<=N; i++) {
            if(prefixSumToCount.containsKey(prefixSum[i]-K)){
                answer += prefixSumToCount.get(prefixSum[i]-K);
            }

            if(prefixSumToCount.containsKey(prefixSum[i])){
                prefixSumToCount.put(prefixSum[i],prefixSumToCount.get(prefixSum[i])+1);
            }else{
                prefixSumToCount.put(prefixSum[i],1l);
            }
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Long.toString(answer));
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

