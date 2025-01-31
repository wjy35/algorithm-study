package _2338;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2338
 * @title 긴자리 계산
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    BigInteger A,B;
    private void readInput() throws IOException{
        A = new BigInteger(br.readLine());
        B = new BigInteger(br.readLine());
    }
    private void writeOutput() throws IOException{
        bw.write(A.add(B).toString());
        bw.write("\n");
        bw.write(A.subtract(B).toString());
        bw.write("\n");
        bw.write(A.multiply(B).toString());
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
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

