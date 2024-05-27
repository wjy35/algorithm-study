package _31870;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/31870
 * @title 버블버블
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
    int[] A;

    int answer = 0;
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        A = new int[N];
        for(int i=0; i<N; i++) A[i] = Integer.parseInt(st.nextToken());
    }

    private void solve(){
        int[] ascFirstA = Arrays.copyOf(A,N);
        int ascFirstCount = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N-1; j++){
                if(ascFirstA[j]>ascFirstA[j+1]){
                    int tmp = ascFirstA[j];
                    ascFirstA[j] = ascFirstA[j+1];
                    ascFirstA[j+1] = tmp;
                    ascFirstCount++;
                }
            }
        }

        int[] descFirstA = Arrays.copyOf(A,N);
        int descFirstCount = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N-1; j++){
                if(descFirstA[j]<descFirstA[j+1]){
                    int tmp = descFirstA[j];
                    descFirstA[j] = descFirstA[j+1];
                    descFirstA[j+1] = tmp;
                    descFirstCount++;
                }
            }
        }

        answer = Math.min(ascFirstCount,descFirstCount+1);
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

