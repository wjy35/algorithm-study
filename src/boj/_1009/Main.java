package _1009;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1009
 * @title 분산처리
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Test.createTest()
                .start();
    }
}

class Test {
    final int MOD = 10;
    int a,b;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
    }

    private int poweredMod(int exponent){
        if(exponent==1) return a;

        int ret = poweredMod(exponent/2);
        ret = (ret*ret)%MOD;

        if(exponent%2==1) ret = (ret*a)%MOD;

        return ret;
    }

    int number;
    private void solve(){
        a = a%MOD;

        number = poweredMod(b);
        if(number==0) number = 10;
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(number));
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
