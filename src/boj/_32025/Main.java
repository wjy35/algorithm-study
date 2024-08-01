package _32025;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/32025
 * @title 체육은 수학과목 입니다
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int H,W;

    int answer = 0;
    private void readInput() throws IOException{
        H = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());
    }

    private void solve(){
        answer = Math.min(H,W)*100/2;
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(answer));
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

