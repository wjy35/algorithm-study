package _1010;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1010
 * @title 다리 놓기
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Test.createTest()
                .start();
    }
}

class Test {
    BigInteger[] factorial;

    int N,M;
    private void solve() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int n = Math.max(N,M);
        int r = Math.min(N,M);

        bw.write(combination(n,r).toString());
        bw.write("\n");
    }

    private BigInteger combination(int n,int r){
        if(n==r || r==0) return BigInteger.ONE;
        return factorial[n].divide(factorial[n-r].multiply(factorial[r]));
    }

    int testCaseCount;
    private void readTestCaseCount() throws IOException{
        testCaseCount = Integer.parseInt(br.readLine());
    }

    public void start() throws IOException{
        readTestCaseCount();

        for(int i=0; i<testCaseCount; i++){
            solve();
        }

        bw.flush();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Test(){
        factorial = new BigInteger[31];
        factorial[1] = BigInteger.valueOf(1);
        for(int number=2; number<=30; number++) factorial[number] = factorial[number-1].multiply(BigInteger.valueOf(number));
    }

    public static Test createTest(){
        return new Test();
    }
}
