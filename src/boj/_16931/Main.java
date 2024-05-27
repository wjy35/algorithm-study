package _16931;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/16931
 * @title 겉넓이 구하기
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
    int[][] count;

    int[] dx = {1,0,-1,0};
    int[] dy = {0,1,0,-1};

    int area;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        count = new int[N+2][M+2];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++){
                count[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private void solve(){
        int nx,ny;
        area = 0;
        for(int x=1; x<=N; x++){
            for(int y=1; y<=M; y++){
                for(int i=0; i<4; i++){
                    nx = x + dx[i];
                    ny = y + dy[i];
                    area += getVisibleArea(count[x][y],count[nx][ny]);
                }
            }
        }

        area += N*M*2;
    }

    private int getVisibleArea(int height,int nextHeight){
        return Math.max(height-nextHeight,0);
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(area));
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

