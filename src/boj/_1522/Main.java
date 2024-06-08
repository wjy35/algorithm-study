package _1522;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1522
 * @title 문자열 교환
 * @algorithm String
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    String input;

    int minSwapCount = Integer.MAX_VALUE;

    private void readInput() throws IOException{
        input = br.readLine();
    }

    private void solve(){
        int aCount = 0;
        int inputLength = input.length();
        for(int i=0; i<inputLength; i++){
            if(input.charAt(i)=='a') aCount++;
        }

        input = input + input.substring(0,aCount);
        for(int i=0; i<inputLength; i++){
            int bCount = 0;
            for(int j=i; j<i+aCount; j++){
                if(input.charAt(j)=='b') bCount++;
            }

            minSwapCount = Math.min(minSwapCount, bCount);
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(minSwapCount));
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

