package _1600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/1600
 * 말이 되고픈 원숭이
 */

class Monkey{
    int x,y,count;

    public Monkey(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int k,w,h;
    static int[][] g;
    static int[][] count;

    static int[] dx={1,0,-1,0, -1, -2, -2, -1,  2,  1, 2, 1};
    static int[] dy={0,1,0,-1, -2, -1,  1,  2, -1, -2, 1, 2};

    static boolean isIn(int nx,int ny){
        return 0<=nx&&nx<h&&0<=ny&&ny<w;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        g = new int[h][w];
        count = new int[h][w];

        for(int i=0; i<h; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<w; j++){
                g[i][j] = Integer.parseInt(st.nextToken());
                if(g[i][j]==1) g[i][j]= -1;
            }
        }
        if(w==1 && h==1) System.out.println(0);
        else System.out.println(bfs());

    }

    static int bfs(){
        Queue<Monkey> q = new LinkedList<>();
        Monkey now;
        int nx,ny;
        g[0][0]=1;
        q.offer(new Monkey(0,0,0));

        while(!q.isEmpty()){
            now = q.poll();
            for(int i=0; i<4; i++){
                nx = now.x+dx[i];
                ny = now.y+dy[i];
                if(nx==h-1&&ny==w-1) return g[now.x][now.y];
                if(isIn(nx,ny)){
                    if(g[nx][ny]==0 || g[nx][ny]!=-1&&now.count<count[nx][ny]){
                        q.offer(new Monkey(nx,ny,now.count));
                        g[nx][ny] = g[now.x][now.y]+1;
                        count[nx][ny] = now.count;
                    }
                }
            }
            if(now.count<k){
                for(int i=4; i<12; i++){
                    nx = now.x+dx[i];
                    ny = now.y+dy[i];

                    if(nx==h-1&&ny==w-1) return g[now.x][now.y];
                    if(isIn(nx,ny)){
                        if(g[nx][ny]==0 || g[nx][ny]!=-1&&now.count+1<count[nx][ny]){
                            q.offer(new Monkey(nx,ny,now.count+1));
                            g[nx][ny] = g[now.x][now.y]+1;
                            count[nx][ny] = now.count+1;
                        }
                    }
                }
            }
        }

        return -1;
    }
}