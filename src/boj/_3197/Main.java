package _3197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

/*
 * https://www.acmicpc.net/problem/3197
 * 백조의 호수
 */


class Point {
    int x,y;

    public Point(int x, int y) {
        super();
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
    static boolean[][] melt_visit;
    static boolean[][] l_visit;
    static Point[] l;



    static Queue<Point> l_q;
    static Queue<Point> l_update;
    static Queue<Point> melt_q;
    static Queue<Point> melt_update;


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
        l = new Point[2];
        int li=0;
        l_update = new LinkedList<>();
        melt_update = new LinkedList<>();
        melt_visit = new boolean[r][c];

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
                        else if(g[nx][ny]=='.'){
                            l_q.offer(new Point(nx,ny));
                            l_visit[nx][ny]=true;
                        }
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
                        if(g[nx][ny]=='L'||g[nx][ny]=='.') {
                            melt_q.offer(new Point(nx,ny));
                            melt_visit[nx][ny] = true;
                        }
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