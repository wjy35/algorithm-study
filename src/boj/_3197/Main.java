package _3197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

/**
 * @author 왕준영
 * @see <a href="https://www.acmicpc.net/problem/3197"> 백조의 호수 </a>
 */

class Swan {
    int x,y;

    public Swan(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main{
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int r,c;
    static char[][] g;

    /**
     * 처음 백조의 위치 저장
     */
    static Swan[] start_end;
    static int start_end_idx;

    /**
     * 백조의 위치에서부터 t 시간일 때 bfs를 위한 Queue
     */
    static Queue<Swan> swan;

    /**
     * 백조의 위치에서부터 t+1 시간일 때 bfs를 시작할 좌표를 담는 Queue
     */
    static Queue<Swan> updateSwan;

    /**
     * 백조의 bfs 방문 체크를 위한 Array
     */
    static boolean[][] visitSwan;


    /**
     * 물의 위치에서부터 t 시간일 때 녹는 얼음 Queue
     */
    static Queue<Swan> water;

    /**
     * 물의 위치에서부터 t+1 시간일 때 녹는 얼음을 찾기 위한 시작점을 저장하는 Queue
     */
    static Queue<Swan> updateWater;


    static int[] dx= {1,0,-1,0};
    static int[] dy= {0,1,0,-1};

    static boolean isIn(int nx,int ny) {
        return 0<=nx&&nx<r&&0<=ny&&ny<c;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        g = new char[r][c];

        visitSwan = new boolean[r][c];
        updateSwan = new ArrayDeque<>();

        updateWater = new ArrayDeque<>();

        start_end = new Swan[2];
        start_end_idx = 0;

        String input;
        for(int i=0; i<r; i++) {
            input = br.readLine();
            for(int j=0; j<c; j++) {
                g[i][j]=input.charAt(j);
                if(g[i][j]=='L') {
                    start_end[start_end_idx]=new Swan(i,j);
                    start_end_idx++;
                }
                else if(g[i][j]=='.') {
                    updateWater.offer(new Swan(i,j));
                }
            }
        }

        updateSwan.offer(start_end[0]);
        visitSwan[start_end[0].x][start_end[0].y] = true;

        updateWater.offer(start_end[0]);
        updateWater.offer(start_end[1]);

        bw.write(Integer.toString(bfs()));
        bw.flush();
    }

    static int bfs() {

        int t=0;
        int nx,ny;

        while(true) {
            // 이전 t에서 bfs를 종료했던 시점부터 bfs를 시작
            swan = updateSwan;
            water = updateWater;

            updateSwan = new ArrayDeque<>();
            updateWater = new ArrayDeque<>();

            while(!swan.isEmpty()) {
                Swan now = swan.poll();
                for(int i=0; i<4; i++) {
                    nx = now.x+dx[i];
                    ny = now.y+dy[i];

                    if(isIn(nx,ny)&&!visitSwan[nx][ny]) {
                        if(g[nx][ny]=='L') {
                            return t;
                        }
                        // 물이라면 현재 시간 (t) Queue에 넣어 계속 bfs
                        else if(g[nx][ny]=='.'){
                            swan.offer(new Swan(nx,ny));
                            visitSwan[nx][ny]=true;
                        }
                        // 얼음이라면 다음 시간 (t+1) Queue에 넣어 다음 시간의 bfs 시작 점으로 update
                        else {
                            updateSwan.offer(new Swan(nx,ny));
                            visitSwan[nx][ny]=true;
                        }
                    }
                }
            }

            while(!water.isEmpty()) {
                Swan now = water.poll();

                for(int i=0; i<4; i++) {
                    nx = now.x+dx[i];
                    ny = now.y+dy[i];

                    if(isIn(nx,ny)&&g[nx][ny]=='X') {
                        updateWater.offer(new Swan(nx,ny));
                        g[nx][ny]='.';
                    }
                }
            }
            t++;
        }
    }

}