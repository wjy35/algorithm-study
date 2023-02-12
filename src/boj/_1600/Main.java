package _1600;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 왕준영
 * @see <a href="https://www.acmicpc.net/problem/1600"> 말이 되고픈 원숭이 </a>
 */

class Monkey{

    /**
     * 현재 좌표
     */
    int x, y;

    /**
     * 말처럼 이동한 횟수
     */
    int use;

    /**
     * 총 이동한 횟수
     */
    int count;

    public Monkey(int x, int y, int use, int count) {
        this.x = x;
        this.y = y;
        this.use = use;
        this.count = count;
    }

}

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int k,w,h;
    static char[][] g;

    /**
     * 최초 방문 확인을 위한 값
     * k의 최대 값은 30 이므로 그 32 보다 적은 use 로 방문했을때는 최초 방문
     */
    static final int INF = 32;


    /**
     * x, y 를 방문하기 위해 사용한 말처럼 이동한 횧수 ( x, y 에 도착하기 위한 use count )
     */
    static int[][] use;

    static int[] dx={1,0,-1,0, -1, -2, -2, -1,  2,  1, 2, 1};
    static int[] dy={0,1,0,-1, -2, -1,  1,  2, -1, -2, 1, 2};
    static boolean isIn(int nx,int ny){
        return 0<=nx&&nx<h&&0<=ny&&ny<w;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        g = new char[h][w];
        use = new int[h][w];

        String input;
        for(int i=0; i<h; i++){
            input = br.readLine();
            for(int j=0,jj=0; j<w; j++,jj+=2){
                g[i][j] = input.charAt(jj);
                use[i][j]=INF;
            }
        }

        if(w==1 && h==1) System.out.print(0);
        else System.out.print(bfs());

    }

    static int bfs(){
        Queue<Monkey> q = new LinkedList<>();
        Monkey now;
        int nx,ny;

        use[0][0]=0;
        q.offer(new Monkey(0,0,0,1));

        while(!q.isEmpty()){
            now = q.poll();

            for(int i=0; i<4; i++){
                nx = now.x+dx[i];
                ny = now.y+dy[i];
                if(nx==h-1&&ny==w-1) return now.count;
                if(isIn(nx,ny)){
                    // nx, ny 에 도착하기 위해 말처럼 이동한 횟수가 더 적은 경우만 추가 탐색
                    if(g[nx][ny]=='0'&&now.use < use[nx][ny]){
                        q.offer(new Monkey(nx,ny,now.use,now.count +1));
                        use[nx][ny] = now.use;
                    }
                }
            }

            // 현재 Monkey가 말처럼 이동한 횟수가 k 보다 작으면 말처럼 이동하는 경우를 탐색
            if(now.use <k){
                for(int i=4; i<12; i++){
                    nx = now.x+dx[i];
                    ny = now.y+dy[i];

                    if(nx==h-1&&ny==w-1) return now.count;
                    if(isIn(nx,ny)){
                        if(g[nx][ny]=='0'&&now.use +1< use[nx][ny]){
                            q.offer(new Monkey(nx,ny,now.use +1,now.count +1));
                            use[nx][ny] = now.use +1;
                        }
                    }
                }
            }
        }

        return -1;
    }
}