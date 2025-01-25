package _2495;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2495
 * @title 연속구간
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    String[] lines;
    private void readInput() throws IOException{
        lines = new String[3];
        for(int i=0; i<3; i++){
            lines[i] = br.readLine();
        }
    }

    int[] maxSequences = {1,1,1};
    private void solve(){
        for(int i=0; i<3; i++){
            int sequence = 1;
            char pre = lines[i].charAt(0);
            int length = lines[i].length();
            for(int j=1; j<length; j++){
                if(pre==lines[i].charAt(j)){
                    sequence++;
                    maxSequences[i] = Math.max(maxSequences[i],sequence);
                }else{
                    sequence = 1;
                }
                pre = lines[i].charAt(j);
            }
        }
    }

    private void writeOutput() throws IOException{
        for(int i=0; i<3; i++){
            bw.write(Integer.toString(maxSequences[i]));
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

