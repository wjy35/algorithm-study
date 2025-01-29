package _18870;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/18870
 * @title 좌표 압축
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
    int[] X;
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        X = new int[N];
        for(int i=0; i<N; i++){
            X[i] = Integer.parseInt(st.nextToken());
        }
    }

    Map<Integer,Integer> xToXPrime = new HashMap<>();
    private void solve(){
        int[] sortedX = Arrays.copyOf(X,X.length);
        Arrays.sort(sortedX);

        int xPrime = 0;
        for(int i=0; i<N; i++){
            if(xToXPrime.containsKey(sortedX[i])) continue;

            xToXPrime.put(sortedX[i],xPrime);
            xPrime++;
        }
    }

    private void writeOutput() throws IOException{
        for(int i=0; i<N; i++){
            bw.write(xToXPrime.get(X[i]).toString());
            bw.write(" ");
        }
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

