package _32986;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/32986
 * @title 나는 건포도가 싫어요
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int X,Y,Z;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        Z = Integer.parseInt(st.nextToken());
    }

    int count = 0;
    private void solve(){
        int min = Math.min(X,Math.min(Y,Z));
        if(X==3 && Y==3 && Z==3) return;
        count = (min+1)/2-1;
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

