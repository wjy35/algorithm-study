package _20500;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/20500
 * @title Ezreal 여눈부터 가네 ㅈㅈ
 * @algorithm DP
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N;

    final long MOD = 1_000_000_007;

    long answer = 0;
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());
    }

    private void solve(){
        long[] factorial = new long[1516];
        factorial[0] = 1;
        for(int i=1; i<1516; i++) factorial[i] = (factorial[i-1]*i)%MOD;

        for(int fiveCount = N-1,oneCount = 0; fiveCount>=0; fiveCount--,oneCount++){
            if((fiveCount*5+oneCount+5)%3!=0) continue;

            long div = (factorial[fiveCount]*factorial[oneCount])%MOD;
            long inverse = calculatePower(div,MOD-2);

            answer = (answer+(factorial[N-1] * inverse)%MOD)%MOD;
        }
    }

    private long calculatePower(long number, long power){
        if(power==1) return number;

        long halfPower = calculatePower(number,power/2);

        long ret = (halfPower*halfPower)%MOD;
        if(power%2==1) ret = (number*ret)%MOD;

        return ret;
    }

    private void writeOutput() throws IOException{
        bw.write(Long.toString(answer));
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

