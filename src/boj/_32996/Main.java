package _32996;

import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/32996
 * @title 트리를 안 쓰는 트리 문제
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
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());
    }

    private void solveAndWriteOutput() throws IOException {
        bw.write(Integer.toString(2*N-1));
        bw.write("\n");

        for(int i=1; i<N; i++){
            int s = 1+(N+1)*(i-1);
            int e = s+N-1;
            write(s,e,i,1);
        }

        write(N*N,N*N,N,1);

        for(int i=1; i<N; i++){
            int s = (N+1)*i;
            write(s,s,N,i+1);
        }

        bw.flush();
    }

    private void write(int s,int e,int i,int j) throws IOException {
        bw.write(Integer.toString(s));
        bw.write(" ");
        bw.write(Integer.toString(e));
        bw.write(" ");
        bw.write(Integer.toString(i));
        bw.write(" ");
        bw.write(Integer.toString(j));
        bw.write("\n");
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

