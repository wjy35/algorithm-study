package _7490;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/7490
 * @title 0 만들기
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
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());
    }

    int[] operators;
    final int SPACE = 0;
    final int PLUS = 1;
    final int MINUS = 2;

    String answer;
    private void solve() throws IOException {
        operators = new int[N-1];

        dfsAndWrite(0);
    }

    private void dfsAndWrite(int index) throws IOException {
        if(index==N-1) {
            Deque<Integer> numberDq = new ArrayDeque<>();
            Deque<Integer> operatorDq = new ArrayDeque<>();
            numberDq.offer(1);

            int number = 2;
            for(int operator : operators){
                if(operator==SPACE){
                    numberDq.offer(numberDq.pollLast()*10+number);
                } else{
                    numberDq.offer(number);
                    operatorDq.offer(operator);
                }
                number++;
            }

            int sum = numberDq.pollFirst();

            while(!operatorDq.isEmpty()){
                if(operatorDq.pollFirst()==PLUS){
                    sum += numberDq.pollFirst();
                }else{
                    sum -= numberDq.pollFirst();
                }
            }


            if(sum==0){
                number = 1;
                bw.write(Integer.toString(number));
                number++;
                for(int operator : operators){
                    if(operator==PLUS) bw.write("+");
                    else if(operator==SPACE) bw.write(" ");
                    else bw.write("-");
                    bw.write(Integer.toString(number));
                    number++;
                }
                bw.write("\n");
            }

            return;
        }

        for(int operator=0; operator<3; operator++){
            operators[index] = operator;

            dfsAndWrite(index+1);
        }
    }

    private void writeOutput() throws IOException{
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
