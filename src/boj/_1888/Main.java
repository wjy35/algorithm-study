package _1888;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1888
 * @title 곰팡이
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
    int[][] inputMap;

    int[] dx = {1,0,-1,0};
    int[] dy = {0,1,0,-1};

    int day = 0;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        String input;
        inputMap = new int[N][M];
        for(int i=0; i<N; i++){
            input = br.readLine();
            for(int j=0; j<M; j++){
                inputMap[i][j] = Character.getNumericValue(input.charAt(j));
            }
        }
    }

    private void solve(){
        int[][] map = inputMap;

        while(!isOne(map)){
            day++;
            int[][] tmpMap = new int[N][M];
            for(int i=0; i<N; i++) tmpMap[i] = Arrays.copyOf(map[i],M);

            for(int x=0; x<N; x++){
                for(int y=0; y<M; y++){
                    if(map[x][y]==0) continue;
                    int k = map[x][y];

                    int l = Math.max(0,y-k);
                    int r = Math.min(M-1,y+k);
                    int t = Math.max(0,x-k);
                    int b = Math.min(N-1,x+k);

                    for(int i=t; i<=b; i++){
                        for(int j=l; j<=r; j++){
                            tmpMap[i][j] = Math.max(tmpMap[i][j],k);
                        }
                    }
                }
            }

            map = tmpMap;
        }
    }

    private boolean isOne(int[][] map){
        boolean[][] isVisited = new boolean[N][M];
        boolean isExist = false;

        for(int x=0; x<N; x++){
            for(int y=0; y<M; y++){
                if(map[x][y]==0) continue;
                if(isVisited[x][y]) continue;
                if(isExist) return false;
                isExist = true;
                Queue<Point> q = new ArrayDeque<>();
                isVisited[x][y] = true;
                q.offer(new Point (x,y));

                while(!q.isEmpty()){
                    Point now = q.poll();

                    for(int i=0; i<4; i++){
                        int nx = now.x+dx[i];
                        int ny = now.y+dy[i];

                        if(isIn(nx,ny) && !isVisited[nx][ny] && map[nx][ny]!=0){
                            isVisited[nx][ny] = true;
                            q.offer(new Point(nx,ny));
                        }
                    }
                }
            }
        }

        return true;
    }

    private boolean isIn(int nx,int ny){
        return 0<=nx && nx<N && 0<=ny && ny<M;
    }

    private static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(day));
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

