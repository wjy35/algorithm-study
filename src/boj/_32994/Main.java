package _32994;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/32994
 * @title 데이브의 고민
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
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }

    int[][] species;
    private void solve(){
        species = new int[N][M];

        for(int i=0,start=1; i<N; i++,start = (start+1)%5+1){
            species[i][0] = start;
            for(int j=1; j<M; j++){
                species[i][j] = species[i][j-1]%5+1;
            }
        }
    }

    private void writeOutput() throws IOException{
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                bw.write(Integer.toString(species[i][j]));
                bw.write(" ");
            }
            bw.write("\n");
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

