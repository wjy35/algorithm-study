package _1261;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1261
 * @title 알고스팟
 * @algorithm BFS
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,M;
    char[][] map;
    final char wall = '1';
    final char room = '0';

    int[] dx = {1,0,-1,0};
    int[] dy = {0,1,0,-1};
    private boolean isIn(int nx,int ny){
        return 0<=nx && nx<N && 0<=ny && ny<M;
    }

    int answer;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i=0; i<N; i++) map[i] = br.readLine().toCharArray();
    }

    private void solve(){
        int maxCount = N+M-2;
        boolean[][][] isVisited = new boolean[N][M][maxCount+1];

        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(0,0,0));
        isVisited[0][0][0] = true;

        Point now;
        int nx,ny;
        while(!q.isEmpty()){
            now = q.poll();

            for(int i=0; i<4; i++){
                nx = now.x+dx[i];
                ny = now.y+dy[i];

                if(isIn(nx,ny)){
                    if(map[nx][ny]==room && !isVisited[nx][ny][now.count]){
                        q.offer(new Point(nx,ny,now.count));
                        isVisited[nx][ny][now.count] = true;
                    }else if(map[nx][ny]==wall && now.count+1<=maxCount && !isVisited[nx][ny][now.count+1]){
                        q.offer(new Point(nx,ny,now.count+1));
                        isVisited[nx][ny][now.count+1] = true;
                    }
                }
            }
        }

        int n = N-1;
        int m = M-1;
        int length = isVisited[n][m].length;
        for(int i=0; i<length; i++){
            if(isVisited[n][m][i]) {
                answer = i;
                break;
            }
        }

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

    static class Point{
        int x,y;
        int count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}

