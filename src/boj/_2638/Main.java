package _2638;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/2638
 * 치즈
 */

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int n,m;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    static String[][] g;
    static final String AIR = "2";

    public static void main(String[] args) throws IOException {
        Queue<int[]> next = new LinkedList<>();

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        g = new String[n][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                g[i][j] = st.nextToken();
            }
        }

        for(int i=0; i<m; i++){
            if(g[0][i].equals("0"))bfs(0,i);
            if(g[n-1][i].equals("0"))bfs(n-1,i);

        }
        for(int i=0; i<n; i++){
            if(g[i][0].equals("0"))bfs(i,0);
            if(g[i][m-1].equals("0"))bfs(i,m-1);
        }

        int[] now;
        int t=0;

        do{
           for(int i=1; i<n-1; i++){
                for(int j=1; j<m-1; j++){
                    if(g[i][j].equals("1")&&isBye(i,j)){
                        g[i][j]="0";
                        next.offer(new int[]{i,j});
                    }
                }
            }
           if(next.isEmpty())break;

           while(!next.isEmpty()){
               now = next.poll();
               bfs(now[0],now[1]);
           }
           t++;
        } while(true);

        System.out.println(t);
    }
    
    static boolean isIn(int nx,int ny){
        return 0<=nx&&nx<n&&0<=ny&&ny<m;
    }
    
    static void bfs(int r,int c){
        Queue<int[]> q = new LinkedList<>();
        
        q.offer(new int[]{r,c});
        g[r][c] = AIR;
        int[] now;
        int nx,ny;
        while(!q.isEmpty()){
            now = q.poll();

            for(int i=0; i<4; i++){
                nx = now[0]+dx[i];
                ny = now[1]+dy[i];
                if(isIn(nx,ny)&&g[nx][ny].equals("0")){
                    q.offer(new int[]{nx,ny});
                    g[nx][ny]=AIR;
                }
            }
        }
    }

    static boolean isBye(int x,int y){
        int count=0;
        for(int i=0; i<4; i++){
            if(g[x+dx[i]][y+dy[i]].equals(AIR)) count++;
        }
        if(count>1) return true;
        return false;
    }

}