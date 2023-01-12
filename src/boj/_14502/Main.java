package _14502;

import java.io.*;
import java.util.*;

/*
 * https://www.acmicpc.net/problem/14502
 * 연구소
 */

class Point{
    public int x,y;
    Point(int x,int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int n,m,cc;
    static int result=0;
    static char[][] g = new char[8][8];
    static char[][] tmp = new char[8][8];
    static int[] dx={1,0,-1,0};
    static int[] dy={0,1,0,-1};
    static boolean isin(int nx,int ny){
        return nx>=0&&ny>=0&&nx<n&&ny<m ;
    }

    static void print(){
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                System.out.print(g[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        return;
    }
    static void bf(int x,int y,int count){
        if(count==3){
            cc = count();
            if(cc>result) result=cc;
            return;
        }
        if(x==n) return;
        for(int j=y; j<m; j++){
            if(g[x][j]=='0'){
                g[x][j]='1';
                if(j+1<m) bf(x,j+1,count+1);
                else bf(x+1,0,count+1);
                g[x][j]='0';
            }
        }
        for(int i=x+1; i<n; i++){
            for(int j=0; j<m; j++){
                if(g[i][j]=='0'){
                    g[i][j]='1';
                    if(j+1<m) bf(i,j+1,count+1);
                    else bf(i+1,0,count+1);
                    g[i][j]='0';
                }
            }
        }
    }
    static void copy(){
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                tmp[i][j]=g[i][j];
            }
        }
    }
    static void bfs(int x,int y){
        int nx,ny;
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x,y));
        while(!q.isEmpty()){
            Point p = q.poll();
            for(int i=0; i<4; i++){
                nx = p.x+dx[i];
                ny = p.y+dy[i];
                if(isin(nx,ny)&&tmp[nx][ny]=='0'){
                    tmp[nx][ny]='2';
                    q.offer(new Point(nx,ny));
                }
            }
        }
    }
    static int count(){
        copy();
        int c=0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(tmp[i][j]=='2') bfs(i,j);
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(tmp[i][j]=='0') c++;
            }
        }
        return c;
    }
    public static void main(String[] args) throws IOException {
        String input;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                g[i][j] = st.nextToken().charAt(0);
            }
        }

        bf(0,0,0);
        System.out.println(result);
    }


}