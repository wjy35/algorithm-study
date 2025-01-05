package _10836;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/10836
 * @title 여왕벌
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int M,N;
    int[] outside;

    int[][] result;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        outside = new int[M*2-1];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int[] count = new int[3];
            for(int j=0; j<3; j++) count[j] = Integer.parseInt(st.nextToken());

            int point = 2;
            if(count[1]>0){
                point = 1;
                outside[count[0]] += point;
            }

            if(count[2]>0){
                int sidePosition = count[0]+count[1];
                outside[sidePosition] += point;
            }
        }

        outside[0]++;
        for(int i=1; i<outside.length; i++) outside[i] += outside[i-1];

        result = new int[M][M];
        for(int i=M-1,j=0; i>=0; i--,j++) result[i][0] = outside[j];
        for(int i=1,j=M; i<M; i++,j++) result[0][i] = outside[j];

        for(int i=1; i<M; i++){
            for(int j=1; j<M; j++){
                result[i][j] = result[i-1][j];
            }
        }
    }

    private void writeOutput() throws IOException{
        for(int i=0; i<M; i++){
            for(int j=0; j<M; j++){
                bw.write(Integer.toString(result[i][j]));
                bw.write(" ");
            }
            bw.write("\n");
        }
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
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

