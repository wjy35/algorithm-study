package _27085;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/27085
 * @title 최대 점수
 * @algorithm 
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,s;
    long[] score;

    long maxScore;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        s--;

        score = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            score[i] = Long.parseLong(st.nextToken());
        }
    }

    private void solve(){
        int leftPosition = s;
        int rightPosition = s;

        long totalLeftScore = 0;
        long totalRightScore = 0;
        long tmpLeftScore = 0;
        long tmpRightScore = 0;
        long maxLeftScore = 0;
        long maxRightScore= 0;

        do{
            maxLeftScore=tmpLeftScore;
            maxRightScore=tmpRightScore;

            for(; leftPosition>=0 && totalLeftScore+score[leftPosition]+maxRightScore>=0; leftPosition--) {
                totalLeftScore += score[leftPosition];
                tmpLeftScore = Math.max(tmpLeftScore, totalLeftScore);
            }

            for(; rightPosition<N && totalRightScore+score[rightPosition]+maxLeftScore>=0; rightPosition++){
                totalRightScore += score[rightPosition];
                tmpRightScore = Math.max(tmpRightScore, totalRightScore);
            }
        }while(maxLeftScore<tmpLeftScore || maxRightScore<tmpRightScore);

        maxScore = maxLeftScore + maxRightScore;
    }

    private void writeOutput() throws IOException{
        bw.write(Long.toString(maxScore));
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

