package _1359;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1359
 * @title 복권
 * @algorithm Math
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .readInput()
                .solve()
                .writeOutput();
    }
}

class Solution{

    int N,M,K;

    double[] factorial;
    double answer;
    public Solution readInput() throws IOException{
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        factorial = new double[N+1];
        factorial[1] = 1;
        for(int i=2; i<=N; i++) factorial[i] = factorial[i-1]*i;
        return this;
    }

    private double combination(int n,int r){
        if(r==0) return 1;
        if(n==r) return 1;

        return factorial[n]/(factorial[r]*factorial[n-r]);
    }

    public Solution solve(){
        double bottom = combination(N,M);
        double top = 0;
        for(int k=K; k<=M; k++){
            if(N-M<M-k) continue;

            top += combination(M,k)*combination(N-M,M-k);
        }

        answer = top/bottom;
        return this;
    }

    public void writeOutput() throws IOException{
        bw.write(Double.toString(answer));
        bw.flush();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

