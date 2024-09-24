package _9944;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/9944
 * @title
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Test.createTest()
                .start();
    }
}

class Test {
    int N,M;
    char[][] map;
    final char BLANK = '.';
    final char OBSTACLE = '*';
    final int INF = 1_000_001;

    int blankCount = 0;
    int minStepCount = INF;
    boolean[][] isVisited;
    int[] dx = {1,0,-1,0};
    int[] dy = {0,1,0,-1};

    private void updateMapSizeBy(String mapSize) {
        st = new StringTokenizer(mapSize);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }

    private void readMap() throws IOException{
        map = new char[N][M];
        for(int i=0; i<N; i++) map[i] = br.readLine().toCharArray();
    }

    private void solve(){
        initBlackCount();
        initMinStepCount();

        isVisited = new boolean[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j]==OBSTACLE) continue;
                isVisited[i][j] = true;
                dfs(1,new Point(i,j),0);
                isVisited[i][j] = false;
            }
        }
    }

    private void initBlackCount(){
        blankCount = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j]==BLANK) blankCount++;
            }
        }
    }

    private void initMinStepCount(){
        minStepCount = INF;
    }

    private void dfs(int totalMoveCount,Point curPoint,int stepCount){
        if(totalMoveCount==blankCount){
            minStepCount = Math.min(minStepCount, stepCount);
            return;
        }

        for(int direction=0; direction<4; direction++){
            int moveCount = moveForwardAndGetMoveCount(curPoint,direction);

            if(moveCount==0) continue;

            dfs(totalMoveCount+moveCount,curPoint,stepCount+1);
            moveBackward(curPoint,direction,moveCount);
        }
    }

    private int moveForwardAndGetMoveCount(Point curPoint,int direction){
        int moveCount = 0;

        while(true){
            int nx = curPoint.x + dx[direction];
            int ny = curPoint.y + dy[direction];

            if(isNotIn(nx,ny) || isVisited[nx][ny] || map[nx][ny]==OBSTACLE) break;

            isVisited[nx][ny] = true;
            curPoint.x = nx;
            curPoint.y = ny;
            moveCount++;
        }

        return moveCount;
    }

    private void moveBackward(Point curPoint,int direction,int moveCount){
        while(moveCount-->0){
            isVisited[curPoint.x][curPoint.y] = false;
            curPoint.x -= dx[direction];
            curPoint.y -= dy[direction];
        }
    }

    private boolean isNotIn(int nx, int ny){
        return 0>nx || nx>=N || 0>ny || ny>=M;
    }

    private void writeOutput(int caseNumber) throws IOException{
        bw.write("Case ");
        bw.write(Integer.toString(caseNumber));
        bw.write(": ");
        if(minStepCount==INF) bw.write("-1");
        else bw.write(Integer.toString(minStepCount));
        bw.write("\n");
        bw.flush();
    }

    public void start() throws IOException{
        int caseNumber = 1;
        String mapSize;
        while((mapSize = br.readLine()) !=  null){
            updateMapSizeBy(mapSize);
            readMap();
            solve();
            writeOutput(caseNumber);
            caseNumber++;
        }
    }

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

    private Test(){}
    public static Test createTest(){
        return new Test();
    }
}
