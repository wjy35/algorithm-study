package _17130;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/17130
 * @title 토끼가 정보섬에 올라온 이유
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
    Point rabbitPoint;
    List<Point> outPointList;

    String maxCarrotCount;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i=0; i<N; i++){
            map[i] = br.readLine().toCharArray();
        }
    }

    private void solve(){
        initRabbitAndOutPointList();
        maxCarrotCount = Integer.toString(getMaxCarrotCount());
    }

    private void initRabbitAndOutPointList(){
        this.outPointList = new ArrayList<>();

        for(int x=0; x<N; x++){
            for(int y=0; y<M; y++){
                if(map[x][y] == 'R') this.rabbitPoint = new Point(x, y);
                else if(map[x][y] == 'O') this.outPointList.add(new Point(x,y));
            }
        }
    }

    private int getMaxCarrotCount(){
        int[] dx = {0,-1,1};
        int[] dy = {1,1,1,};
        int[][] carrotCount = new int[N][M];
        Queue<Point> q = new ArrayDeque<>();

        carrotCount[rabbitPoint.x][rabbitPoint.y] = 1;
        q.offer(rabbitPoint);

        while(!q.isEmpty()){
            Point current = q.poll();

            for(int i=0; i<3; i++){
                int nextX = current.x+dx[i];
                int nextY = current.y+dy[i];

                if(isIn(nextX,nextY)&&map[nextX][nextY]!='#'){
                    int nextCarrotCount = carrotCount[current.x][current.y];
                    if(map[nextX][nextY]=='C') nextCarrotCount++;

                    if(carrotCount[nextX][nextY] >= nextCarrotCount) continue;
                    carrotCount[nextX][nextY] = nextCarrotCount;
                    q.offer(new Point(nextX,nextY));
                }
            }
        }

        int max = 0;
        for(Point outPoint : outPointList){
            max = Math.max(max,carrotCount[outPoint.x][outPoint.y]);
        }

        return max-1;
    }

    private boolean isIn(int x, int y){
        return 0<=x && x<N && 0<=y && y<M;
    }

    private void writeOutput() throws IOException{
        bw.write(maxCarrotCount);
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        solve();
        writeOutput();
    }

    private class Point{
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

