package _2475;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2475
 * @title 검증수
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    String answer;

    private void readInputAndSolve() throws IOException{
        st = new StringTokenizer(br.readLine());

        int sum = 0;
        for(int i=0; i<5; i++) {
            int input = Integer.parseInt(st.nextToken());
            sum += input*input;
        }

        answer = Integer.toString(sum%10);
    }

    private void writeOutput() throws IOException{
        bw.write(answer);
        bw.flush();
    }

    public void start() throws IOException{
        readInputAndSolve();
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

