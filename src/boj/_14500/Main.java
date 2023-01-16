package _14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/14500
 * 테트로미노
 */

public class Main {
    static int n,m;
    static int max,sum;
    static int[][] g;
    static int[][] visit;
    static int[] dx= {1,0,-1,0};
    static int[] dy= {0,1,0,-1};
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        g = new int[n][m];
        visit = new int[n][m];
        max = 0;
        sum = 0;
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                g[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                visit[i][j]=1;
                sum=g[i][j];
                dfs(i,j,1);
                visit[i][j]=0;
                fs(i,j);
            }
        }
        System.out.println(max);



    }

    static boolean isin(int nx,int ny) {
        return 0<=nx&&nx<n&&0<=ny&&ny<m;
    }

    static void dfs(int x,int y,int cnt) {
        int nx,ny;
        if(cnt==4) {
            if(max<sum) max = sum;
            return;
        }

        for(int i=0; i<4; i++) {
            nx = x +dx[i];
            ny = y +dy[i];
            if(isin(nx,ny)&&visit[nx][ny]==0) {
                visit[nx][ny]=1;
                sum+=g[nx][ny];
                dfs(nx,ny,cnt+1);
                visit[nx][ny]=0;
                sum-=g[nx][ny];
            }
        }

    }
    static void fs(int x,int y) {
        int sum,nx,ny,j;
        for(int i=0; i<4; i++) {
            sum = g[x][y];
            for(j=0; j<4; j++) {
                if(j==i)continue;

                nx = x+dx[j];
                ny = y+dy[j];
                if(isin(nx,ny)) {
                    sum += g[nx][ny];
                }
                else break;
            }

            if(j==4) {
                if(max<sum) max=sum;
            }
        }
    }

}
