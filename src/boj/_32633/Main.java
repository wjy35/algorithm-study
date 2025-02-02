package _32633;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/32633
 * @title 두더지 찾기
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
    long L;

    long[] A;
    int[] B;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        A = new long[N];
        for(int i=0; i<N; i++) A[i] = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        B = new int[N];
        for(int i=0; i<N; i++) B[i] = Integer.parseInt(st.nextToken());
    }


    long clock = -1;
    private void solve(){
        long lcm = 1;
        for(int i=0; i<N; i++){
            if(B[i]==0) continue;

            lcm = calculateLcm(lcm,A[i]);
            if(lcm>L) return;
        }

        for(int i=0; i<N; i++){
            if(B[i]!=0) continue;
            if(lcm%A[i]==0) return;
        }

        clock = lcm;
    }

    private long calculateGcd(long pre, long current){
        long mod;
        while((mod = pre%current)!=0){
            pre = current;
            current = mod;
        }

        return current;
    }

    private long calculateLcm(long pre, long current){
        return (pre / calculateGcd(pre,current)) * current;
    }

    private void writeOutput() throws IOException{
        bw.write(Long.toString(clock));
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

