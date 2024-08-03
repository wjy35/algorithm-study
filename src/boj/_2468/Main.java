package _2468;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2468
 * @title 안전 영역
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
    int[][] height;

    int[] dx = {1,0,-1,0};
    int[] dy = {0,1,0,-1};

    private boolean isIn(int nx,int ny){ return 0<=nx && nx<N && 0<=ny && ny<N;}
    private class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int answer = 1;

    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        height = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                height[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private void solve(){
        for(int heightLimit=1; heightLimit<=100; heightLimit++){
            int safeAreaCount = getSafeAreaCountByHeightLimit(heightLimit);

            answer = Math.max(answer,safeAreaCount);
        }
    }

    private int getSafeAreaCountByHeightLimit(int heightLimit){
        int safeAreaCount = 0;
        boolean[][] isVisited = new boolean[N][N];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(isVisited[i][j]) continue;
                if(height[i][j]<=heightLimit) continue;

                safeAreaCount++;

                Queue<Point> q = new ArrayDeque<>();
                q.offer(new Point(i,j));
                isVisited[i][j] = true;

                while(!q.isEmpty()){
                    Point now = q.poll();

                    for(int d=0; d<4; d++){
                        int nx = now.x+dx[d];
                        int ny = now.y+dy[d];

                        if(!isIn(nx,ny)) continue;
                        if(isVisited[nx][ny]) continue;
                        if(height[nx][ny]<=heightLimit) continue;

                        isVisited[nx][ny] = true;
                        q.offer(new Point(nx,ny));
                    }
                }
            }
        }

        return safeAreaCount;
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

