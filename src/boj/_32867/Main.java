package _32867;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/32867
 * @title 피아노
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
    int[] sheetMusic;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        sheetMusic = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            sheetMusic[i] = Integer.parseInt(st.nextToken());
        }
    }

    int count = 0;
    private void solve(){
        int l = sheetMusic[0];
        int r = sheetMusic[0];

        for(int i=1; i<N; i++){
            if(l<=sheetMusic[i] && sheetMusic[i]<=r) continue;

            l = Math.min(l,sheetMusic[i]);
            r = Math.max(r,sheetMusic[i]);

            if(r-l+1<=K) continue;

            count++;
            l = sheetMusic[i];
            r = sheetMusic[i];
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(count));
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

