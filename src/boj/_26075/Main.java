package _26075;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/26075
 * @title 곰곰아 선 넘지마
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,M;
    String S;
    String T;

    String answer = "";
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        S = br.readLine();
        T = br.readLine();
    }

    private void solve(){
        int length = N+M;
        long[][] position = new long[2][M];
        int positionIndex = 0;
        for(int i=0; i<length; i++){
            if(S.charAt(i)=='0') continue;

            position[0][positionIndex] = i;
            positionIndex++;
        }

        positionIndex = 0;
        for(int i=0; i<length; i++){
            if(T.charAt(i)=='0') continue;

            position[1][positionIndex] = i;
            positionIndex++;
        }

        long differenceSum = 0;
        for(int i=0; i<M; i++){
            differenceSum += Math.abs(position[0][i]-position[1][i]);
        }

        long X = differenceSum/2;
        long Y = X + (differenceSum%2==0 ? 0:1);

        answer = Long.toString(X*X + Y*Y);
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

