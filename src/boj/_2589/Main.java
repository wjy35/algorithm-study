package _2589;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2589
 * @title 보물섬
 * @algorithm BFS
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int height,width;
    char[][] map;

    String answer;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        map = new char[height][width];
        for(int x=0; x<height; x++) map[x] = br.readLine().toCharArray();
    }

    private void solve(){
        int maxDistance = 0;
        for(int x=0; x<height; x++){
            for(int y=0; y<width; y++){
                if(map[x][y]!='L') continue;

                maxDistance = Math.max(maxDistance,getMaxDistanceFrom(new Point(x,y)));
            }
        }

        answer = Integer.toString(maxDistance);
    }

    private int getMaxDistanceFrom(Point origin){
        Queue<Point> q = new ArrayDeque<>();

        int[][] distance = new int[height][width];
        distance[origin.x][origin.y] = 1;
        q.offer(origin);

        int maxDistance = 0;
        while(!q.isEmpty()){
            Point current = q.poll();

            for(int i=0; i<4; i++){
                int nextX = current.x+dx[i];
                int nextY = current.y+dy[i];

                if(isInMap(nextX,nextY) && distance[nextX][nextY]==0 && map[nextX][nextY]=='L'){
                    q.offer(new Point(nextX,nextY));
                    distance[nextX][nextY] = distance[current.x][current.y]+1;
                    maxDistance = Math.max(maxDistance, distance[current.x][current.y]);
                }
            }
        }

        return maxDistance;
    }

    int[] dx = {1,0,-1,0};
    int[] dy = {0,1,0,-1};
    boolean isInMap(int x, int y){ return 0<=x && x<height && 0<=y && y<width;}

    private static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private void writeOutput() throws IOException{
        bw.write(answer);
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

