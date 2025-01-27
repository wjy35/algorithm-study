package _2750;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2750
 * @title 수 정렬하기
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
    int[] a;
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        a = new int[N];
        for(int i=0; i<N; i++){
            a[i] = Integer.parseInt(br.readLine());
        }
    }

    private void solve(){
        Arrays.sort(a);
    }

    private void writeOutput() throws IOException{
        for(int i=0; i<N; i++){
            bw.write(Integer.toString(a[i]));
            bw.write("\n");
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

