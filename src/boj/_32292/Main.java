package _32292;

import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/32292
 * @title ABB to BA (Easy)
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Test.createTest()
                .start();
    }
}

class Test {
    int n;
    String S;

    String answer;
    private void readInput() throws IOException{
        n = Integer.parseInt(br.readLine());
        S = br.readLine();
    }

    Deque<Character> result;
    private void solve(){
        result = new ArrayDeque<>();
        for(int i=0; i<n && i<2; i++){
            result.offerLast(S.charAt(i));
        }

        for(int i=2; i<n; i++){
            update(S.charAt(i));
        }
    }

    private void update(char c){
        Deque<Character> tmp = new ArrayDeque<>();
        tmp.offerLast(c);

        while(result.size()>1){
            char mid = result.pollLast();
            if(result.peekLast()!='A' || mid!='B' || tmp.peekFirst()!='B') {
                result.offerLast(mid);
                break;
            }

            result.pollLast();
            tmp.pollFirst();
            tmp.offerFirst('A');
            tmp.offerFirst('B');
        }

        result.addAll(tmp);
    }

    private void writeOutput() throws IOException{
        for(Character c : result){
            bw.write(Character.toString(c));
        }
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