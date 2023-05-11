package _28016;

import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/28016">경품 추첨</a>
 * @category DP, Probability
 * @Note
 */
public class Main {
    static class Ball{
        int[] p;
        int x,y;

        public Ball(int x, int y, int[] p) {
            this.p = Arrays.copyOf(p,p.length);
            this.x = x;
            this.y = y;
        }
    }

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int n,m;
    static int[][] map;
    static boolean[][] visit;

    static int startY;
    static int[][][] percent;

    static int ans;
    static int[] min;


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visit = new boolean[n][m];
        percent = new int[n][m][m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){startY =j;}
            }
        }

        bfs();
        ans = -1;
        min = new int[m];
        for(int i=0; i<m; i++){
            if(compare(min,percent[n-1][i])){
                min = percent[n-1][i];
                ans = i;
            }
        }
        bw.write(Integer.toString(ans));
        bw.flush();
    }

    static void bfs(){
        Queue<Ball> q = new ArrayDeque<>();

        int[] startP = new int[m];
        startP[0] = 1;

        percent[0][startY] = startP;
        visit[0][startY] = true;
        Ball ball = new Ball(0,startY,startP);
        q.offer(ball);

        Ball now;
        int[] np;
        while(!q.isEmpty()){
            now = q.poll();
            if(now.x== n-1) return;
            if(map[now.x+1][now.y] == 0){
                percent[now.x+1][now.y] = sum(percent[now.x+1][now.y],percent[now.x][now.y]);
                if(!visit[now.x+1][now.y]){
                    q.offer(new Ball(now.x+1,now.y,percent[now.x][now.y]));
                    visit[now.x+1][now.y] = true;
                }
            }else{
                np = div(percent[now.x][now.y]);
                if(map[now.x+1][now.y+1] == 0 && map[now.x][now.y+1]==0){
                    percent[now.x+1][now.y+1] = sum(percent[now.x+1][now.y+1],np);
                    if(!visit[now.x+1][now.y+1]){
                        q.offer(new Ball(now.x+1,now.y+1,np));
                        visit[now.x+1][now.y+1] = true;
                    }
                }
                if(map[now.x+1][now.y-1] == 0 && map[now.x][now.y-1]==0){
                    percent[now.x+1][now.y-1] = sum(percent[now.x+1][now.y-1],np);
                    if(!visit[now.x+1][now.y-1]){
                        visit[now.x+1][now.y-1] = true;
                        q.offer(new Ball(now.x+1,now.y-1,np));
                    }
                }
            }
        }
    }

    static int[] sum (int[] arr1,int[] arr2){
        int[] result = new int[m];

        for(int i=m-1; i>=0; i--){
            if(arr1[i]+arr2[i]+result[i] < 2){
                result[i] = arr1[i]+arr2[i]+result[i];
            } else{
                result[i] = arr1[i]+arr2[i]+result[i]-2;
                result[i-1] = 1;
            }
        }

        return result;
    }

    static int[] div(int[] arr){
        int[] result = new int[m];
        for(int i=m-1; i>0; i--){
            result[i] = arr[i-1];
        }
        return result;
    }

    static boolean compare(int[] arr1,int[] arr2){
        for(int i=0; i<m; i++){
            if(arr1[i] == arr2[i]) continue;
            return arr1[i] < arr2[i];
        }
        return false;
    }
}

