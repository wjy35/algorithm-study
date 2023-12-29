package _1937;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/1937">욕심쟁이 판다</a>
 * @category DFS
 * @Note
 */
public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n;
    static int[][] map;
    static int[][] dp;
    static int ans;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][n];
        ans = 0;

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                ans = Math.max(ans,dfs(i,j));
            }
        }

        bw.write(Integer.toString(ans));
        bw.flush();
    }

    static int dfs(int x,int y){
        if(dp[x][y] != 0) return dp[x][y];

        int maxMove = 1;
        int nx,ny;
        for(int i=0; i<4; i++){
            nx = x+dx[i];
            ny = y+dy[i];
            if(isIn(nx,ny) && map[x][y]<map[nx][ny]){
                maxMove = Math.max(maxMove,1+dfs(nx,ny));
            }
        }

        dp[x][y] = maxMove;

        return dp[x][y];
    }

    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static boolean isIn(int nx,int ny){
        return 0<=nx && 0<=ny && nx<n && ny<n;
    }
}
