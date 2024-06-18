package _2153;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2153
 * @title 소수 단어
 * @algorithm Math
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{
    String word;

    final String IS_PRIME_NUMBER = "It is a prime word.";
    final String IS_NOT_PRIME_NUMBER = "It is not a prime word.";

    String answer = "undefined";
    private void readInput() throws IOException{
        word = br.readLine();
    }

    private void solve(){
        boolean[] isNotPrimeNumber = new boolean[52*20+1];
        for(int i=2; i<isNotPrimeNumber.length; i++){
            if(isNotPrimeNumber[i]) continue;

            for(int j=2; i*j<isNotPrimeNumber.length; j++){
                isNotPrimeNumber[i*j] = true;
            }
        }

        int wordValueSum = 0;
        for(int i=0; i<word.length(); i++){
            if('a'<=word.charAt(i) && word.charAt(i)<='z'){
                wordValueSum += word.charAt(i)-'a'+1;
            }else{
                wordValueSum += word.charAt(i)-'A'+27;
            }
        }

        if(isNotPrimeNumber[wordValueSum]) answer = IS_NOT_PRIME_NUMBER;
        else answer = IS_PRIME_NUMBER;
    }

    private void writeOutput() throws IOException{
        bw.write(answer);
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        solve();
        writeOutput();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

