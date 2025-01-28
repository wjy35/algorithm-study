package _2805;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2805
 * @title 나무 자르기
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
    long[] heights;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        heights = new long[N];
        for(int i=0; i<N; i++) heights[i] = Long.parseLong(st.nextToken());
    }

    long max = 0;
    private void solve(){
        long l = 0;
        long r = 1_000_000_000;

        while(l<=r){
            long mid = (l+r)/2;

            long sum = 0;
            for(long height : heights){
                sum += Math.max(height-mid,0);
            }

            if(sum>=M){
                l = mid+1;
                max = mid;
            }else{
                r = mid-1;
            }
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Long.toString(max));
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

