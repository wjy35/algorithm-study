package _1520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/1520
 * 내리막 길
 */

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n,m;
    static int[][] g;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        g = new int[n][m];
        dp = new int [n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                g[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                dp[i][j]= -1;
            }
        }
        System.out.println(dfs(n-1,m-1));

    }

    static int dfs(int x,int y){
        if(x==0&&y==0) return 1;
        if(x<0 || y<0) return 0;

        if(dp[x][y] >=0) return dp[x][y];

        int count=0;

        int nx,ny;

        for(int i=0; i<4; i++){
            nx = x+dx[i];
            ny = y+dy[i];
            if(isIn(nx,ny) && g[nx][ny]>g[x][y]){
                count+= dfs(nx,ny);
            }

        }

        dp[x][y]=count;
        return count;
    }

    static boolean isIn(int nx,int ny){
        return nx>=0&&nx<n&&ny>=0&&ny<m;
    }
}