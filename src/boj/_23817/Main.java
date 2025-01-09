package _23817;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/23817
 * @title 포항항
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
    char[][] map;

    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i=0; i<N; i++) map[i] = br.readLine().toCharArray();
    }

    int[][] number;
    int count = 0;
    int[][] numberToDistances;
    boolean[] isVisited;
    int visitedCount;
    int time;

    int minTime = Integer.MAX_VALUE;
    private void solve(){
        number = new int[N][M];
        Point start = null;
        List<Point> shop = new ArrayList<>();
        for(int x=0; x<N; x++){
            for(int y=0; y<M; y++){
                if(map[x][y]=='.') continue;
                if(map[x][y]=='X') continue;

                if(map[x][y]=='S') {
                    start = new Point(x,y);
                    continue;
                }

                shop.add(new Point(x,y));
                number[x][y] = count;
                count++;
            }
        }

        shop.add(start);
        number[start.x][start.y] = count;
        count++;

        numberToDistances = new int[count][count];
        for(Point point : shop){
            numberToDistances[number[point.x][point.y]] = getDistancesFrom(point);
        }

        isVisited = new boolean[count];
        visitedCount = 0;
        time = 0;
        count--;
        dfs(count);

        if(minTime==Integer.MAX_VALUE) minTime = -1;
    }

    int[] dx = {1,0,-1,0};
    int[] dy = {0,1,0,-1};
    int[] getDistancesFrom(Point point){
        int[] distances = new int[count];
        int[][] pointToDistance = new int[N][M];
        Queue<Point> q = new ArrayDeque<>();
        q.offer(point);
        pointToDistance[point.x][point.y] = 1;
        while(!q.isEmpty()){
            Point current = q.poll();

            for(int i=0; i<4; i++){
                int nx = current.x+dx[i];
                int ny = current.y+dy[i];

                if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
                if(map[nx][ny]=='X') continue;
                if(pointToDistance[nx][ny]!=0) continue;

                if(map[nx][ny]!='.') distances[number[nx][ny]] = pointToDistance[current.x][current.y];

                q.offer(new Point(nx,ny));
                pointToDistance[nx][ny] = pointToDistance[current.x][current.y]+1;
            }
        }

        return distances;
    }

    private void dfs(int number){
        if(visitedCount==5){
            minTime = Math.min(minTime, time);
            return;
        }

        for(int i=0; i<count; i++){
            if(numberToDistances[number][i]==0) continue;
            if(isVisited[i]) continue;

            isVisited[i] = true;
            visitedCount++;
            time += numberToDistances[number][i];
            dfs(i);
            isVisited[i] = false;
            visitedCount--;
            time -= numberToDistances[number][i];
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(minTime));
        bw.flush();
    }

    private static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
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

