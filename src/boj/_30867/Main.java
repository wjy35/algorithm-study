package _30867;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/30867
 * @title 과제가 너무 많아
 * @algorithm dp
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .readInput()
                .solve()
                .writeOutput();
    }
}

class Solution{

    int L,N;
    String word;

    public Solution readInput() throws IOException{
        st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        word = br.readLine();

        return this;
    }

    char[] answer;
    public Solution solve(){
        int[] dp = new int[L];

        for(int i=0; i<L-1; i++){
            if(word.charAt(i)=='w'){
                dp[i+1] = dp[i]+1;
            } else if(word.charAt(i)=='h'){
                dp[i+1] = dp[i];
            } else {
                dp[i+1] = 0;
            }
        }

        answer = new char[L];
        for(int i=0; i<L; i++){
            if(word.charAt(i)=='h'){
                answer[i] = 'h';
                swap(i,i-Math.min(N,dp[i]));

                continue;
            }
            answer[i] = word.charAt(i);
        }

        return this;
    }

    public void swap(int i,int j){
        char tmp = answer[j];
        answer[j] = answer[i];
        answer[i] = tmp;
    }

    public void writeOutput() throws IOException{
        bw.write(answer);
        bw.flush();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

