package _23352;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/23352
 * @title 방탈출
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
    int[][] map;

    int password = 0;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private void solve(){
        int maxDistance = 0;
        int maxSum = 0;

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j]==0) continue;

                boolean[][] isVisited = new boolean[N][M];
                isVisited[i][j] = true;

                Queue<Point> q = new ArrayDeque<>();
                q.offer(new Point(i,j));

                int distance = 0;
                while(!q.isEmpty()){
                    distance++;
                    int size = q.size();
                    for(int k=0; k<size; k++){
                        Point now = q.poll();

                        for(int d=0; d<4; d++){
                            int nx = now.x+dx[d];
                            int ny = now.y+dy[d];
                            if(isIn(nx,ny) && map[nx][ny]!=0 && !isVisited[nx][ny]){
                                isVisited[nx][ny] = true;
                                q.offer(new Point(nx,ny));

                                int sum = map[i][j] + map[nx][ny];

                                if(maxDistance<distance) {
                                    maxDistance = distance;
                                    maxSum = sum;
                                }else if(maxDistance==distance && maxSum<sum){
                                    maxSum = sum;
                                }
                            }
                        }

                    }
                }

            }
        }

        password = maxSum;
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(password));
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        solve();
        writeOutput();
    }

    int[] dx = {1,0,-1,0};
    int[] dy = {0,1,0,-1};
    boolean isIn(int nx,int ny){ return 0<=nx && nx<N && 0<=ny && ny<M; }

    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

