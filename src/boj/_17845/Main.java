package _17845;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/17845
 * @title 수강 과목 
 * @algorithm 
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,K;
    Subject[] subjects;

    String answer;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        subjects = new Subject[K+1];
        for(int i=1; i<=K; i++){
            st = new StringTokenizer(br.readLine());

            subjects[i] = new Subject(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
    }

    private void solve(){
        int[][] dp = new int[K+1][N+1];

        for(int k=1; k<=K; k++){
            for(int n=1; n<=N; n++){
                if(n<subjects[k].T) {
                    dp[k][n] = dp[k-1][n];

                    continue;
                }

                dp[k][n] = Math.max(dp[k-1][n],dp[k-1][n-subjects[k].T]+subjects[k].I);
            }
        }

        answer = Integer.toString(dp[K][N]);
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

    private static class Subject{
        int I;
        int T;

        public Subject(int i, int t) {
            I = i;
            T = t;
        }
    }
}



