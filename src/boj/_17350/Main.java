package _17350;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/17350
 * @title 2루수 이름이 뭐야
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

    final String ANJ = "anj";
    final String EXIST_ANJ="뭐야;";
    final String NO_EXIST_ANJ="뭐야?";

    String answer;
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            if(br.readLine().equals(ANJ)) {
                answer = EXIST_ANJ;

                return;
            }
        }
        answer = NO_EXIST_ANJ;
    }

    private void solve(){

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

