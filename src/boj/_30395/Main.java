package _30395;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/30395
 * @title 내 스트릭을 돌려내!
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N;
    int[] P;

    int maxStreak;
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        P = new int[N+1];
        for(int i=0; i<N; i++){
            P[i] = Integer.parseInt(st.nextToken());
        }
    }

    private void solve(){
        maxStreak = 0;
        int streak = 0;
        for(int i=0; i<N; i++){
            if(P[i]>0){
                streak++;
                continue;
            }

            if(P[i+1]>0) continue;

            maxStreak = Math.max(maxStreak, streak);
            streak = 0;
        }
        maxStreak = Math.max(maxStreak, streak);
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(maxStreak));
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

