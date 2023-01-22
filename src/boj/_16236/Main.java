package _16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/16236
 * 아기 상어
 */

class Shark implements Comparable<Shark>{
    int x;
    int y;

    public Shark(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public int compareTo(Shark o) {
        if(this.x==o.x){
            return this.y-o.y;
        }
        return this.x-o.x;
    }
}

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static int[][] sea;
    static int[][] path;
    static int[] dx={-1,0,0,1};
    static int[] dy={0,-1,1,0};
    static int baby_size,baby_eat,baby_x,baby_y;

    static Queue<Shark> q;
    static PriorityQueue<Shark> pq;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sea = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                sea[i][j] = Integer.parseInt(st.nextToken());
                if(sea[i][j]==9){
                    baby_x=i;
                    baby_y=j;
                }
            }
        }
        baby_size=2;
        baby_eat=0;
        sea[baby_x][baby_y]=0;

        int d;
        int t=0;


        while(true) {
            d = distanceFish();
            if (d == -1) break;
            t += d;
        }
        System.out.println(t);
    }

    static boolean isIn(int nx,int ny){
        return 0<=nx&&nx<n && 0<=ny&&ny<n;
    }

    static int distanceFish(){
        int nx,ny;
        int distance=-1;
        int MAX_D=100;
        Shark now = null;
        q = new LinkedList<>();
        pq = new PriorityQueue<>();
        path = new int[n][n];

        path[baby_x][baby_y]=1;
        q.offer(new Shark(baby_x,baby_y));

        while(!q.isEmpty()){
            now = q.poll();
            if(0<sea[now.x][now.y]&&sea[now.x][now.y]<baby_size){
                MAX_D = path[now.x][now.y];
                pq.offer(now);
                break;
            }
            for(int i=0; i<4; i++){
                nx = now.x+dx[i];
                ny = now.y+dy[i];
                if(isIn(nx,ny)&&path[nx][ny]==0 && sea[nx][ny]<=baby_size){
                    path[nx][ny] = path[now.x][now.y]+1;
                    q.offer(new Shark(nx,ny));
                }
            }
        }
        
        while(!q.isEmpty()){
            now = q.poll();
            if(0<sea[now.x][now.y]&&sea[now.x][now.y]<baby_size&&path[now.x][now.y]==MAX_D){
                pq.offer(now);
            }
        }

        if(!pq.isEmpty()){
            baby_x = pq.peek().x;
            baby_y = pq.peek().y;
            sea[baby_x][baby_y]=0;
            baby_eat++;
            if(baby_eat==baby_size){
                baby_size++;
                baby_eat=0;
            }
            distance = path[baby_x][baby_y]-1;
        }

        return distance;
    }

}