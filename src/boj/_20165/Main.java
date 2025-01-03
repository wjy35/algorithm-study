package _20165;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/20165
 * @title 인내의 도미노 장인 호석
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,M,R;
    int[][] board;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N+1][M+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    boolean[][] isFall;
    Map<String,Integer> directionToDeltaIndex;
    int[] dx = {0,1,0,-1};
    int[] dy = {1,0,-1,0};
    int score = 0;
    private void query() throws IOException{
        isFall = new boolean[N+1][M+1];
        directionToDeltaIndex = new HashMap<>();
        directionToDeltaIndex.put("E",0);
        directionToDeltaIndex.put("W",2);
        directionToDeltaIndex.put("S",1);
        directionToDeltaIndex.put("N",3);

        for(int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            updateOffense(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),st.nextToken());

            st = new StringTokenizer(br.readLine());
            updateDefense(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
    }

    private void updateOffense(int x,int y,String direction){
        if(isFall[x][y]) return;

        int deltaIndex = directionToDeltaIndex.get(direction);
        int endX = x+dx[deltaIndex]*board[x][y];
        int endY = y+dy[deltaIndex]*board[x][y];

        while(x!=endX || y!=endY){
            if(!isFall[x][y]){
                score++;
                int tmpX = x+dx[deltaIndex]*board[x][y];
                int tmpY = y+dy[deltaIndex]*board[x][y];
                if(Math.abs(tmpX-x)+Math.abs(tmpY-y) > Math.abs(endX-x)+Math.abs(endY-y)){
                    endX = tmpX;
                    endY = tmpY;
                }
            }

            isFall[x][y] = true;

            int nx = x+dx[deltaIndex];
            int ny = y+dy[deltaIndex];

            if(nx<1 || nx>N || ny<1 || ny>M) break;

            x = nx;
            y = ny;
        }
    }

    private void updateDefense(int x,int y){
        isFall[x][y] = false;
    }

    private void printIsFall() throws IOException {
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                if(isFall[i][j]) bw.write("F");
                else bw.write("S");
                bw.write(" ");
            }
            bw.write("\n");
        }
        bw.write("\n");
        bw.flush();
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(score));
        bw.write("\n");
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                if(isFall[i][j]) bw.write("F");
                else bw.write("S");
                bw.write(" ");
            }
            bw.write("\n");
        }
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        query();
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

