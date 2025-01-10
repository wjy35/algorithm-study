package _17430;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/17430
 * @title 가로등
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
    Map<Integer,Integer> xPointToCount;
    Map<Integer,Integer> yPointToCount;
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        xPointToCount = new HashMap<>();
        yPointToCount = new HashMap<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            xPointToCount.put(x,xPointToCount.getOrDefault(x,0)+1);
            yPointToCount.put(y,yPointToCount.getOrDefault(y,0)+1);
        }
    }

    String answer;
    private void solve(){
        answer = new HashSet<>(xPointToCount.values()).size()==1
                && new HashSet<>(yPointToCount.values()).size()==1
                ? "BALANCED\n" : "NOT BALANCED\n";
    }

    private void writeOutput() throws IOException{
        bw.write(answer);
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
