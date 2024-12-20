package _2715;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2715
 * @title 상범 마법 팬케이크 하우스
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Test.createTest()
                .start();
    }
}

class Test {
    int M;
    int[] cakes;

    StringBuilder reverseOrderBuilder;
    int reverseCount;

    String answer;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        cakes = new int[M+1];
        for(int i=1; i<=M; i++){
            cakes[i] = Integer.parseInt(st.nextToken());
        }
    }

    private void solve(){
        reverseOrderBuilder = new StringBuilder();
        reverseCount = 0;
        for(int m=M; m>1; m--){
            int position=1;
            while(Math.abs(cakes[position])!=m) position++;

            if(position==m && cakes[position]>0) continue;
            if(position>1) reverseCakeAt(position);
            if(cakes[1]>0) reverseCakeAt(1);

            reverseCakeAt(m);
        }
        if(cakes[1]<0) reverseCakeAt(1);

        answer = reverseCount + " " + reverseOrderBuilder.toString();
    }

    private void reverseCakeAt(int pos) {
        reverseOrderBuilder.append(pos).append(" ");
        reverseCount++;

        pos++;
        int mid = pos/2;
        for(int i=1; i<=mid; i++){
            int tmp = cakes[i];
            cakes[i] = -cakes[pos-i];
            cakes[pos-i] = -tmp;
        }
    }

    private void writeOutput() throws IOException{
        bw.write(answer);
        bw.write("\n");
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
