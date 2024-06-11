package _16401;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/16401
 * @title
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int M,N;
    long[] length;

    long maxLength = 0;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        length = new long[N];
        for(int i=0; i<N; i++) length[i] = Long.parseLong(st.nextToken());
    }

    private void solve(){
        long l = 1;
        long r = 1_000_000_000;

        while(l<=r){
            long tmpLength = (l+r)/2;

            long count = 0;
            for(int i=0; i<N; i++){
                count += length[i]/tmpLength;
            }

            if(count>=M){
                maxLength = Math.max(maxLength,tmpLength);
                l = tmpLength+1;
            }else{
                r = tmpLength-1;
            }
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Long.toString(maxLength));
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

