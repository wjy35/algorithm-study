package _3197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

/**
 * @author 왕준영
 * @see <a href="https://www.acmicpc.net/problem/3197"> 백조의 호수 </a>
 */

class Point {
    int x,y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main{
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int r,c;
    static char[][] g;

    /**
     * 처음 백조의 위치 저장
     */
    static Point[] l;
    static int li;

    /**
     * 백조의 위치에서부터 t 시간일 때 bfs를 위한 Queue
     */
    static Queue<Point> l_q;

    /**
     * 백조의 위치에서부터 t+1 시간일 때 bfs를 시작할 좌표를 담는 Queue
     */
    static Queue<Point> l_update;

    /**
     * 백조의 bfs 방문 체크를 위한 Array
     */
    static boolean[][] l_visit;


    /**
     * 물의 위치에서부터 t 시간일 때 bfs를 위한 Queue
     */
    static Queue<Point> melt_q;

    /**
     * 물의 위치에서부터 t+1 시간일 때 bfs를 시작할 좌표를 담는 Queue
     */
    static Queue<Point> melt_update;

    /**
     * 물의 bfs 방문 체크를 위한 Array
     */
    static boolean[][] melt_visit;


    static int[] dx= {1,0,-1,0};
    static int[] dy= {0,1,0,-1};

    static boolean isIn(int nx,int ny) {
        return 0<=nx&&nx<r&&0<=ny&&ny<c;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        g = new char[r][c];

        l_visit = new boolean[r][c];
        l_update = new LinkedList<>();

        melt_visit = new boolean[r][c];
        melt_update = new LinkedList<>();

        l = new Point[2];
        li = 0;

        String input;
        for(int i=0; i<r; i++) {
            input = br.readLine();
            for(int j=0; j<c; j++) {
                g[i][j]=input.charAt(j);
                if(g[i][j]=='L') {
                    l[li]=new Point(i,j);
                    li++;
                }
                else if(g[i][j]=='.') {
                    melt_update.offer(new Point(i,j));
                    melt_visit[i][j]=true;
                }
            }
        }

        l_update.offer(l[0]);
        l_visit[l[0].x][l[0].y] = true;
        melt_update.offer(l[1]);
        melt_visit[l[1].x][l[1].y] = true;

        bw.write(Integer.toString(bfs()));
        bw.flush();
    }

    static int bfs() {

        int t=0;
        int nx,ny;

        while(true) {
            // 이전 t에서 bfs를 종료했던 시점부터 bfs를 시작
            l_q = l_update;
            melt_q = melt_update;

            l_update = new LinkedList<>();
            melt_update = new LinkedList<>();
            while(!l_q.isEmpty()) {
                Point now = l_q.poll();
                for(int i=0; i<4; i++) {
                    nx = now.x+dx[i];
                    ny = now.y+dy[i];

                    if(isIn(nx,ny)&&!l_visit[nx][ny]) {
                        if(g[nx][ny]=='L') {
                            return t;
                        }
                        // 물이라면 현재 시간 (t) Queue에 넣어 계속 bfs
                        else if(g[nx][ny]=='.'){
                            l_q.offer(new Point(nx,ny));
                            l_visit[nx][ny]=true;
                        }
                        // 얼음이라면 다음 시간 (t+1) Queue에 넣어 다음 시간의 bfs 시작 점으로 update
                        else {
                            l_update.offer(new Point(nx,ny));
                            l_visit[nx][ny]=true;
                            melt_visit[nx][ny]=true;
                            g[nx][ny]='.';
                        }

                    }
                }
            }

            while(!melt_q.isEmpty()) {
                Point now = melt_q.poll();

                for(int i=0; i<4; i++) {
                    nx = now.x+dx[i];
                    ny = now.y+dy[i];

                    if(isIn(nx,ny)&&!melt_visit[nx][ny]) {

                        // 물이라면 현재 시간 (t) Queue에 넣어 계속 bfs
                        if(g[nx][ny]=='L'||g[nx][ny]=='.') {
                            melt_q.offer(new Point(nx,ny));
                            melt_visit[nx][ny] = true;
                        }
                        // 얼음이라면 다음 시간 (t+1) Queue에 넣어 다음 시간의 bfs 시작 점으로 update
                        else {
                            melt_update.offer(new Point(nx,ny));
                            melt_visit[nx][ny]=true;
                            g[nx][ny]='.';
                        }
                    }
                }
            }

            t++;
        }
    }

}