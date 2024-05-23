package _16967;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/16967
 * @title 배열 복원하기
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int H,W,X,Y;
    int[][] B;

    int[][] A;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        B = new int[H+X][W+Y];
        for(int i=0; i<B.length; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<B[i].length; j++){
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private void solve(){
        A = new int[H][W];

        for(int h=0; h<H; h++){
            for(int w=0; w<W; w++){
                if(h<X || w<Y) A[h][w] = B[h][w];
                else A[h][w] = B[h][w]-A[h-X][w-Y];
            }
        }
    }

    private void writeOutput() throws IOException{
        for(int h=0; h<H; h++){
            for(int w=0; w<W; w++){
                bw.write(Integer.toString(A[h][w]));
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

