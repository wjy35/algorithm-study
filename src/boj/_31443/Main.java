package _31443;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/31443
 * @title 준영이
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
    final int MOD = 1_000_000_000+7;

    long answer = 1;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }

    private void solve(){
        int number = N*M;
        if(number==1) return;
        long[] count = new long[4];
        if(number%3==0){
            count[3] += number/3;
        }else if(number%3==1){
            count[3] += number/3-1;
            count[2] += 2;
        }else{
            count[3] += number/3;
            count[2] += 1;
        }
        while(count[3]-->0) answer = (answer*3)%MOD;
        while(count[2]-->0) answer = (answer*2)%MOD;
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

