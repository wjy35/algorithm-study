package _31926;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/31926
 * @title 밤양갱
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

    int time = -1;
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());
    }

    private void solve(){
        N++;

        int now = 1;
        int count = 0;
        int half = N/2;
        if(N%2==1) half++;

        while(now<half) {
            now = now<<1;
            count++;
        }

        time = 10+count;
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(time));
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

