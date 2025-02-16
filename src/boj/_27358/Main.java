package _27358;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        Test.createTest()
                .start();
    }
}

class Test {

    int N;
    long[] D;
    char[] S;
    long[] R;
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        D = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) D[i] = Long.parseLong(st.nextToken());

        S = br.readLine().toCharArray();

        R = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) R[i] = Long.parseLong(st.nextToken());
    }

    long minX;
    long maxDSum = 100_000_000L*100_000;
    private void solve(){
        minX = 0;

        long l = 0;
        long r = 100_000_000L*100_000+1;
        while(l<=r){
            long mid = l+(r-l)/2;

            if (isPossible(mid)){
                minX = mid;
                r = mid-1;
            }else {
                l = mid+1;
            }
        }
    }

    private boolean isPossible(long X){
        for(int i=0; i<N; i++){
            X -= D[i];

            if(X<=0) return false;
            if(S[i]=='+'){
                X += R[i];

                continue;
            }

            if(X>maxDSum/R[i]) return true;
            X *= R[i];
        }

        return true;
    }

    private void writeOutput() throws IOException{
        bw.write(Long.toString(minX));
        bw.write("\n");
        bw.flush();
    }

    int testCaseCount;
    private void readTestCaseCount() throws IOException{
        testCaseCount = Integer.parseInt(br.readLine());
    }

    public void start() throws IOException{
        readTestCaseCount();

        for(int i=0; i<testCaseCount; i++){
            readInput();
            solve();
            writeOutput();
        }
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Test(){}
    public static Test createTest(){
        return new Test();
    }
}
