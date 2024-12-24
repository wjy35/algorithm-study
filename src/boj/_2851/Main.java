package _2851;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2851
 * @title 슈퍼 마리오
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int[] scorePrefixSum;

    String answer;
    private void readInput() throws IOException{
        scorePrefixSum = new int[11];
        for(int i=1; i<=10; i++){
            scorePrefixSum[i] = scorePrefixSum[i-1]+Integer.parseInt(br.readLine());
        }
    }

    private void solve(){
        int maxScore = 0;
        int minDiff = 1_000_000;
        for(int i=1; i<=10; i++){
            int diff = Math.abs(scorePrefixSum[i]-100);
            if(diff>minDiff) break;

            maxScore = scorePrefixSum[i];
            minDiff = diff;
        }

        answer = Integer.toString(maxScore);
    }


    private void writeOutput() throws IOException{
        bw.write(answer);
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

