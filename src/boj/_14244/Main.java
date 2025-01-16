package _14244;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/14244
 * @title 트리 만들기
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int n,m;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
    }

    private void solveAndWriteOutput() throws IOException {

        for(int i=1; i<=m; i++){
            bw.write("0 ");
            bw.write(Integer.toString(i));
            bw.write("\n");
        }

        int nonLeafCount = n-m-1;
        for(int i=0; i<nonLeafCount; i++){
            bw.write(Integer.toString(m+i));
            bw.write(" ");
            bw.write(Integer.toString(m+i+1));
            bw.write("\n");
        }

        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        solveAndWriteOutput();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

