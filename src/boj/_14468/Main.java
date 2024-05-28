package _14468;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/14468
 * @title 소가 길을 건너간 이유 2
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{
    String path;

    int answer = 0;
    private void readInput() throws IOException{
        path = br.readLine();
    }

    private void solve(){
        int crossCount;
        boolean[] isChecked = new boolean[26];
        boolean[] isCross;
        char cow,otherCow;
        for(int i=0; i<52; i++){
            cow = path.charAt(i);

            if(isChecked[cow-'A']) continue;
            isChecked[cow-'A'] = true;

            isCross = new boolean[26];
            crossCount = 0;
            for(int j=i+1; j<52; j++){
                otherCow = path.charAt(j);
                if(otherCow==cow) break;

                if(isCross[otherCow-'A']) {
                    crossCount--;
                } else {
                    isCross[otherCow-'A'] = true;
                    crossCount++;
                }
            }
            answer += crossCount;
        }

        answer = answer/2;
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(answer));
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

