package _1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/1012
 * 유기농 배추
 */

public class Main {
    static int T,n,m,k,x,y,count;
    static int[][] g;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case<=T; test_case++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            g = new int[n][m];
            count = 0;

            for(int i=0; i<k; i++){
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());

                g[x][y] = 1;
            }

            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(g[i][j]==1){
                        count++;
                        bfs(new int[]{i,j});
                    }
                }
            }
            System.out.println(count);
        }
    }
    static boolean isin(int nx, int ny){
        return 0<=nx&&nx<n && 0<=ny&&ny<m;
    }
    static void bfs(int[] p){
        int nx,ny;
        int[] now;
        Queue<int[]> q = new LinkedList<>();
        g[p[0]][p[1]]=2;
        q.offer(p);

        while(!q.isEmpty()){
            now = q.poll();
            for(int i=0; i<4; i++){
                nx = now[0]+dx[i];
                ny = now[1]+dy[i];
                if(isin(nx,ny)&&g[nx][ny]==1){
                    g[nx][ny]=2;
                    q.offer(new int[]{nx,ny});
                }
            }
        }
    }
}