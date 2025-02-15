package _33466;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/33466
 * @title 피타고라스 정리의 증명
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Test.createTest()
                .start();
    }
}

class Test {
    long N;
    private void solve() throws IOException{
        N = Long.parseLong(br.readLine());

        bw.write(Long.toString((N/2)*4+(N%2==0? 0:1)));
        bw.write("\n");
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

    private Test(){}
    public static Test createTest(){
        return new Test();
    }
}
