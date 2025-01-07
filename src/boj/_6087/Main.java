package _6087;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/6087
 * @title 레이저 통신
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int W,H;
    char[][] map;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        for(int i=0; i<H; i++) map[i] = br.readLine().toCharArray();
    }

    int mirrorCount = 0;
    private void solve(){
        Point[] C = new Point[2];
        int cIndex = 0;
        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++){
                if(map[i][j]!='C') continue;

                C[cIndex] = new Point(i,j);
                cIndex++;
            }
        }

        int[][][] minCosts = new int[H][W][4];
        for(int i=0; i<H; i++){
            for(int j=0; j<W; j++){
                for(int direction=0; direction<4; direction++){
                    minCosts[i][j][direction] = 1_000_000_000;
                }
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int direction=0; direction<4; direction++){
            int nx = C[0].x+dx[direction];
            int ny = C[0].y+dy[direction];

            if(isOut(nx,ny)) continue;
            if(map[nx][ny]=='*') continue;

            pq.offer(new Node(new Point(nx,ny),direction,0));
            minCosts[nx][ny][direction] = 0;
        }

        while(!pq.isEmpty()){
            Node current = pq.poll();

            if(minCosts[current.point.x][current.point.y][current.direction]!=current.cost) continue;
            if(current.point.x==C[1].x&&current.point.y==C[1].y){
                mirrorCount = current.cost;
                return;
            }

            for(int i=0; i<4; i++){
                int nx = current.point.x+dx[i];
                int ny = current.point.y+dy[i];

                if(isOut(nx,ny)) continue;
                if(map[nx][ny]=='*') continue;

                int nextCost = current.cost;
                if(i!=current.direction) nextCost++;

                if(nextCost>=minCosts[nx][ny][i]) continue;
                pq.offer(new Node(new Point(nx,ny),i,nextCost));
                minCosts[nx][ny][i] = nextCost;
            }
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(mirrorCount));
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        solve();
        writeOutput();
    }

    private int[] dx = {-1,1,0,0};
    private int[] dy = {0,0,-1,1};
    private boolean isOut(int x,int y){
        return 0>x || x>=H || 0>y || y>=W;
    }

    private static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Node implements Comparable<Node> {
        Point point;
        int direction;
        int cost;

        public Node(Point point, int direction, int cost) {
            this.point = point;
            this.direction = direction;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost,o.cost);
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

