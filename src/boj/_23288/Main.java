package _23288;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/23288
 * @title 주사위 굴리기 2
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,M,K;
    int[][] map;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int x=0; x<N; x++){
            st = new StringTokenizer(br.readLine());
            for(int y=0; y<M; y++){
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }
    }

    int[][] pointToGroupId;
    Map<Integer,Integer> groupIdToSize;
    void initGroupInfo(){
        pointToGroupId = new int[N][M];
        groupIdToSize = new HashMap<>();

        int groupId = 1;
        for(int x=0; x<N; x++){
            for(int y=0; y<M; y++){
                if(pointToGroupId[x][y]!=0) continue;

                int groupSize = 1;
                Queue<Point> q = new ArrayDeque<>();
                q.offer(new Point(x,y));
                pointToGroupId[x][y] = groupId;

                while(!q.isEmpty()){
                    Point current = q.poll();

                    for(int i=0; i<4; i++){
                        int nx = current.x+dx[i];
                        int ny = current.y+dy[i];

                        if(isOut(nx,ny)) continue;
                        if(pointToGroupId[nx][ny]!=0) continue;
                        if(map[nx][ny] != map[x][y]) continue;

                        pointToGroupId[nx][ny] = groupId;
                        q.offer(new Point(nx,ny));
                        groupSize++;
                    }
                }

                groupIdToSize.put(groupId,groupSize);

                groupId++;
            }
        }
    }

    int score = 0;
    private void solve(){
        initGroupInfo();

//        for(int i=0; i<N; i++){
//            for(int j=0; j<M; j++){
//                System.out.print(pointToGroupId[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println(groupIdToSize);

        Dice dice = new Dice();
        int direction = 1;
        int x = 0, y = 0;
        for(int i=0; i<K; i++){
            int nx = x+dx[direction];
            int ny = y+dy[direction];

            if(isOut(nx,ny)){
                direction = (direction+2)%4;
                x = x+dx[direction];
                y = y+dy[direction];
            }else{
                x = nx;
                y = ny;
            }

            dice.rollTo(direction);

            score += groupIdToSize.get(pointToGroupId[x][y]) * map[x][y];

            int bottom = dice.getBottomFace();
            if(bottom>map[x][y]) direction = (direction+3)%4;
            if(bottom<map[x][y]) direction = (direction+1)%4;
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(score));
        bw.flush();
    }

    int[] dx = {1,0,-1,0};
    int[] dy = {0,1,0,-1};
    boolean isOut(int x,int y){return 0>x || x>=N || 0>y || y>=M; }

    private static class Dice{
        private final int TOP = 0;
        private final int FRONT = 1;
        private final int RIGHT = 2;
        private final int BACK = 3;
        private final int LEFT = 4;
        private final int BOTTOM = 5;

        private int[] faces;

        public Dice() {
            this.faces = new int[6];
            faces[TOP] = 1;
            faces[FRONT] = 5;
            faces[RIGHT] = 3;
            faces[BACK] = 2;
            faces[LEFT] = 4;
            faces[BOTTOM] = 6;
        }

        public int getBottomFace(){
            return faces[BOTTOM];
        }

        public void rollTo(int direction){
            if(direction==0) rightShift(TOP,FRONT,BOTTOM,BACK);
            else if(direction==2) rightShift(TOP,BACK,BOTTOM,FRONT);
            else if(direction==1) rightShift(TOP,RIGHT,BOTTOM,LEFT);
            else rightShift(TOP,LEFT,BOTTOM,RIGHT);
        }

        private void rightShift(int... indexes){
            int tmp = faces[indexes[indexes.length-1]];

            for(int i=indexes.length-1; i>0; i--){
                faces[indexes[i]] = faces[indexes[i-1]];
            }
            faces[0] = tmp;
        }
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

