package _11571;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/11571
 * @title 분수를 소수로
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Test.createTest()
                .start();
    }
}

class Test {
    int numerator;
    int denominator;

    String answer;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        numerator = Integer.parseInt(st.nextToken());
        denominator = Integer.parseInt(st.nextToken());
    }

    private void solve(){
        int integerPart = numerator / denominator;
        numerator = numerator % denominator;

        int[] numeratorToUsedPosition = new int[1024];
        List<Integer> decimalPart = new ArrayList<>();

        while(numeratorToUsedPosition[numerator]==0){
            numeratorToUsedPosition[numerator] = decimalPart.size()+1;
            numerator *= 10;

            if(numerator/denominator == 0){
                decimalPart.add(0);
                continue;
            }

            decimalPart.add(numerator/denominator);
            numerator = numerator % denominator;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(integerPart).append(".");
        for(int i=0; i<numeratorToUsedPosition[numerator]-1; i++){
           sb.append(decimalPart.get(i));
        }
        sb.append("(");
        for(int i=numeratorToUsedPosition[numerator]-1; i<decimalPart.size(); i++){
            sb.append(decimalPart.get(i));
        }
        sb.append(")");
        answer = sb.toString();
    }

    private void writeOutput() throws IOException{
        bw.write(answer);
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
