package _22237;

import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/22237">가희와 거북이 인형 </a>
 * @category BFS, 구현, 역발상,
 * @Note 히트박스, 반대로 이동할때 이동 범위 제한
 */
public class Main {
    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Mover{
        Point house;
        List<Point> block;
        Point min;
        Point max;
        String cmd;
    }

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n,m;
    static char[][] map;

    static Mover start;

    static int[] dx={1,0,-1,0};
    static int[] dy={0,1,0,-1};
    static int[] cross={2,3,0,1};
    static boolean flag;
    static char[] direction={'U','L','D','R'};
    static boolean isIn(int nx,int ny){
        return 0<=nx && nx<n && 0<=ny && ny<m;
    }
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        start = new Mover();
        start.max = new Point(0,0);
        start.min = new Point(Integer.MAX_VALUE,Integer.MAX_VALUE);
        start.block = new ArrayList<>();
        start.cmd = "";

        String input;
        for(int i=0; i<n; i++){
            input = br.readLine();
            for(int j=0; j<m; j++){
                map[i][j] = input.charAt(j);
                if(map[i][j] == 'T'){
                    start.min.x = Math.min(start.min.x ,i);
                    start.max.x = Math.max(start.max.x,i);
                    start.min.y = Math.min(start.min.y,j);
                    start.max.y = Math.max(start.max.y,j);
                } else if(map[i][j]=='#'){
                    map[i][j]='.';
                    start.block.add(new Point(i,j));
                } else if(map[i][j]=='H'){
                    map[i][j]='.';
                    start.house = new Point(i,j);
                }
            }
        }

        System.out.println(bfs());

    }

    static String bfs(){
        Queue<Mover> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[2*n][2*m];

        q.offer(start);
        visit[start.min.x+start.max.x][start.min.y+start.max.y] = true;

        Mover now,next;
        while(!q.isEmpty()){
            now = q.poll();

            if(now.min.x-1>=0 && !visit[now.min.x+now.max.x-2][now.min.y+now.max.y]){
                next = move(now,0);

                if(next!=null){
                    if(flag) return next.cmd;
                    visit[now.min.x+now.max.x-2][now.min.y+now.max.y] = true;
                    q.offer(next);
                }
            }
            if(now.min.y-1>=0 && !visit[now.min.x+now.max.x][now.min.y+now.max.y-2]){
                next = move(now,1);
                if(next!=null){
                    if(flag) return next.cmd;
                    visit[now.min.x+now.max.x][now.min.y+now.max.y-2]=true;
                    q.offer(next);
                }            }
            if(now.max.x+1<n && !visit[now.min.x+now.max.x+2][now.min.y+now.max.y]){
                next = move(now,2);
                if(next!=null){
                    if(flag) return next.cmd;
                    visit[now.min.x+now.max.x+2][now.min.y+now.max.y]=true;
                    q.offer(next);
                }            }
            if(now.max.y+1<m && !visit[now.min.x+now.max.x][now.min.y+now.max.y+2]){
                next = move(now,3);
                if(next!=null){
                    if(flag) return next.cmd;
                    visit[now.min.x+now.max.x][now.min.y+now.max.y+2]=true;
                    q.offer(next);
                }
            }
        }

        return "-1";
    }


    static Mover move(Mover now,int d){
        int nx,ny;
        for(int i=0; i<now.block.size(); i++){
            nx = now.block.get(i).x+dx[d];
            ny = now.block.get(i).y+dy[d];
            if(isIn(nx,ny)&&map[nx][ny]=='T'){
                return null;
            }
        }

        Mover next = new Mover();
        next.cmd = now.cmd+direction[d];

        nx = now.house.x+dx[d];
        ny = now.house.y+dy[d];

        if(isIn(nx,ny)&&map[nx][ny]=='T'){
            flag=true;
            return next;
        }
        next.house = new Point(now.house.x + dx[d],now.house.y+dy[d]);
        next.block = new ArrayList<>();
        for(int i=0; i<now.block.size(); i++){
            next.block.add(new Point(now.block.get(i).x+dx[d],now.block.get(i).y+dy[d]));
        }

        next.min = new Point(now.min.x + dx[cross[d]] ,now.min.y+dy[cross[d]]);
        next.max = new Point(now.max.x + dx[cross[d]],now.max.y+dy[cross[d]]);

        return next;
    }
}