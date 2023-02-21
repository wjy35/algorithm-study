package _3109;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author 왕준영
 * @see <a href="https://www.acmicpc.net/problem/3109"> 빵집 </a>
 */

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int[] dx  = {-1,0,1};
    static boolean isIn(int nx,int ny){return 0<=nx&&nx<n&&0<=ny&&ny<m;}
    static char[][] g;
    static int n,m,count;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        g = new char[n][m];

        for(int i=0; i<n; i++){
            g[i]=br.readLine().toCharArray();
        }

        count=0;
        for(int i=0; i<n; i++){
            dfs(i,0);
        }
        bw.write(Integer.toString(count));
        bw.flush();
    }

    static boolean dfs(int x,int y){
        g[x][y] = 'x';

        if(y == m-1){
            count++;
            return true;
        }
        int nx,ny;

        for(int i=0; i<3; i++){
            nx = x+dx[i];
            ny = y+1;
            if(isIn(nx,ny)&&g[nx][ny]=='.'){
                if(dfs(nx,ny)) return true;
            }
        }
        return false;
    }
}