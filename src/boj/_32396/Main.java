package _32396;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/32396
 * @title 차이를 M 이상으로
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
    long M;
    long[] A;
    final long INF = 10_000_000_000_000l;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        A = new long[N];
        for(int i=0; i<N; i++){
            A[i] = Long.parseLong(st.nextToken());
        }
    }

    int count = 0;
    private void solve(){
        for(int i=0; i<N-1; i++){
            if(Math.abs(A[i]-A[i+1])>=M) continue;

            count++;
            A[i+1] = INF;
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(count));
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

