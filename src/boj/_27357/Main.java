package _27357;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/27357
 * @title 부가세
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Test.createTest()
                .start();
    }
}

class Test {

    int N;
    long X,P;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Long.parseLong(st.nextToken());


        P = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine(),".");
            P += Long.parseLong(st.nextToken())*100 + Long.parseLong(st.nextToken());
        }
    }

    long min,max;
    private void solve(){
        min = 10_001;
        max = 0;

        for(long F=0; F<=10_000; F++){
            long product = P * F;
            long quotient = product / 100;
            long price = P + quotient;

            if(price/100==X || (product%100!=0 && (price+1)/100==X)){
                min = Math.min(min,F);
                max = Math.max(max,F);
            }
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Long.toString(min));
        bw.write(" ");
        bw.write(Long.toString(max));
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
