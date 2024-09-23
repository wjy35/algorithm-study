package _2573.v2;

import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2573
 * @title 빙산
 * @algorithm Simulation
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,M;
    int[][] currentMap;
    final int WATER = 0;
    Queue<Point> iceList;
    String answer = "0";
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        iceList = new ArrayDeque<>();
        currentMap = new int[N][M];
        for(int x=0; x<N; x++) {
            st = new StringTokenizer(br.readLine());
            for(int y=0; y<M; y++) {
                currentMap[x][y] = Integer.parseInt(st.nextToken());
                if (currentMap[x][y]!=WATER) iceList.add(new Point(x, y));
            }
        }
    }

    private void solve(){
        int chunkCount;
        int day = 0;
        while((chunkCount=getChunkCount(currentMap))<2){
            if(chunkCount==0) return;

            currentMap = getNextMap();
            day++;
        }

        answer = Integer.toString(day);
    }

    private int[][] getNextMap(){
        int[][] nextMap = new int[N][M];

        int size = iceList.size();
        for(int i=0; i<size; i++) {
            if(iceList.isEmpty()) return nextMap;

            Point point = iceList.poll();

            nextMap[point.x][point.y] = Math.max(0,currentMap[point.x][point.y]-getMeltAmount(point.x,point.y));
            if(nextMap[point.x][point.y]!=WATER) iceList.add(point);
        }

        return nextMap;
    }

    private int getChunkCount(int[][] map){
        int chunkCount = 0;
        boolean[][] isVisited = new boolean[N][M];
        for(int x=0; x<N; x++) {
            for(int y=0; y<M; y++) {
                if(map[x][y]==WATER || isVisited[x][y]) continue;

                chunkCount++;
                Queue<Point> q = new ArrayDeque<>();
                q.offer(new Point(x, y));
                isVisited[x][y] = true;

                while(!q.isEmpty()){
                    Point now = q.poll();

                    for(int i=0; i<4; i++){
                        int nx = now.x+dx[i];
                        int ny = now.y+dy[i];

                        if(map[nx][ny]==WATER) continue;
                        if(isVisited[nx][ny]) continue;

                        q.offer(new Point(nx, ny));
                        isVisited[nx][ny] = true;
                    }
                }
            }
        }

        return chunkCount;
    }

    private int getMeltAmount(int x,int y){
        int meltAmount = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(currentMap[nx][ny]==WATER) meltAmount++;
        }

        return meltAmount;
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

    private int[] dx = {1,0,-1,0};
    private int[] dy = {0,1,0,-1};

    private class Point{
        int x;
        int y;

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

