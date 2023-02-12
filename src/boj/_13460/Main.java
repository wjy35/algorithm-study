package _13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 왕준영
 * @see <a href="https://www.acmicpc.net/problem/13460"> 구슬 탈출 2 </a>
 */

class Ball{
    /**
     * 구슬의 x,y 좌표
     */
    int x,y;

    /**
     * 구슬의 색
     */
    char color;

    public Ball(int x, int y, char color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
    public Ball(Ball ball){
        this.x = ball.x;
        this.y = ball.y;
        this.color = ball.color;
    }
}

class RedAndBlue{
    /**
     * 현재 구슬의 정보
     */
    Ball red,blue;

    public RedAndBlue(Ball red, Ball blue) {
        this.red = red;
        this.blue = blue;
    }
}

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n,m;
    static char[][] g;
    static Ball red,blue,goal;


    /**
     * count[red.x][red.y][blue.x][blue.y] 까지 가기위해 필요한 기울이기 횟수
     */
    static int [][][][] count;


    /**
     * 구슬을 기울였을때 구슬들의 x, y 좌표에 따른 결과
     * FAIL : 파란공이 탈출해 실패
     * SUCCESS : 빨간공만 탈출해 성공
     * CONTINUE : 어떤 공도 탈출하지 못해 기울이기를 더 해야함
     */
    static final int SUCCESS = 1;
    static final int FAIL = -1;
    static final int CONTINUE = 0;



    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        g = new char[n][m];
        count = new int[n][m][n][m];


        String input;

        for(int i=0; i<n; i++){
            input = br.readLine();
            for(int j=0; j<m; j++){
                g[i][j]=input.charAt(j);
                if(g[i][j]=='R'){
                    red = new Ball(i,j,'R');
                    g[i][j]='.';
                }
                else if(g[i][j]=='B'){
                    blue = new Ball(i,j,'B');
                    g[i][j]='.';
                }
                else if(g[i][j]=='O'){
                    goal = new Ball(i,j,'O');
                }
            }
        }

        System.out.print(bfs());
    }

    static int bfs(){
        Queue<RedAndBlue> q = new LinkedList<>();
        count[red.x][red.y][blue.x][blue.y]=1;
        q.offer(new RedAndBlue(red,blue));
        RedAndBlue next;
        int next_result;

        while(!q.isEmpty()){
            RedAndBlue now = q.poll();
            if(count[now.red.x][now.red.y][now.blue.x][now.blue.y]>10) return -1;

            next = left(now);
            if(count[next.red.x][next.red.y][next.blue.x][next.blue.y]==0){
                next_result = getResult(next);
                if(next_result==SUCCESS){ return count[now.red.x][now.red.y][now.blue.x][now.blue.y]; }
                if(next_result==CONTINUE){
                    q.offer(next);
                    count[next.red.x][next.red.y][next.blue.x][next.blue.y]= count[now.red.x][now.red.y][now.blue.x][now.blue.y]+1;
                }
            }

            next = right(now);
            if(count[next.red.x][next.red.y][next.blue.x][next.blue.y]==0){
                next_result = getResult(next);
                if(next_result==SUCCESS){ return count[now.red.x][now.red.y][now.blue.x][now.blue.y]; }
                if(next_result==CONTINUE){
                    q.offer(next);
                    count[next.red.x][next.red.y][next.blue.x][next.blue.y]= count[now.red.x][now.red.y][now.blue.x][now.blue.y]+1;
                }
            }

            next = up(now);
            if(count[next.red.x][next.red.y][next.blue.x][next.blue.y]==0){
                next_result = getResult(next);
                if(next_result==SUCCESS){ return count[now.red.x][now.red.y][now.blue.x][now.blue.y]; }
                if(next_result==CONTINUE){
                    q.offer(next);
                    count[next.red.x][next.red.y][next.blue.x][next.blue.y]= count[now.red.x][now.red.y][now.blue.x][now.blue.y]+1;
                }
            }

            next = down(now);
            if(count[next.red.x][next.red.y][next.blue.x][next.blue.y]==0){
                next_result = getResult(next);
                if(next_result==SUCCESS){ return count[now.red.x][now.red.y][now.blue.x][now.blue.y]; }
                if(next_result==CONTINUE){
                    q.offer(next);
                    count[next.red.x][next.red.y][next.blue.x][next.blue.y]= count[now.red.x][now.red.y][now.blue.x][now.blue.y]+1;
                }
            }
        }
        return -1;
    }

    /**
     * @param now 결과를 확인하려는 Red 와 Blue 좌표
     */
    static int getResult(RedAndBlue now){
        if(now.blue.x == goal.x && now.blue.y == goal.y) return FAIL;
        if(now.red.x == goal.x && now.red.y == goal.y) return SUCCESS;
        return CONTINUE;
    }

    /**
     * @param now 현재 Red,Blue 의 좌표
     * @return 왼쪽으로 기울였을때 Red,Blue 좌표
     */
    static RedAndBlue left(RedAndBlue now){
        Ball f,b;

        if(now.red.y<now.blue.y){
            f = new Ball(now.red);
            b = new Ball(now.blue);
        }
        else{
            f = new Ball(now.blue);
            b = new Ball(now.red);
        }
        for(int i=f.y; i>0; i--){
            if(g[f.x][i-1]=='O'){
                f.y = i-1;
                break;
            }
            else if(g[f.x][i-1]=='#') {
                f.y = i;
                break;
            }
        }

        for(int i=b.y; i>0; i--){
            if(g[b.x][i-1]=='O'){
                b.y = i-1;
                break;
            }
            else if(g[b.x][i-1]=='#'|| (f.x==b.x && f.y==i-1)) {
                b.y = i;
                break;
            }
        }

        if(f.color == 'R') return new RedAndBlue(f,b);
        return new RedAndBlue(b,f);
    }

    /**
     * @param now 현재 Red,Blue 좌표
     * @return 오른쪽으로 기울였을때 Red,Blue 좌표
     */
    static RedAndBlue right(RedAndBlue now){
        Ball f,b;

        if(now.red.y>now.blue.y){
            f = new Ball(now.red);
            b = new Ball(now.blue);
        }
        else{
            f = new Ball(now.blue);
            b = new Ball(now.red);
        }
        for(int i=f.y; i<m; i++){
            if(g[f.x][i+1]=='O'){
                f.y = i+1;
                break;
            }
            else if(g[f.x][i+1]=='#') {
                f.y = i;
                break;
            }
        }

        for(int i=b.y; i<m; i++){
            if(g[b.x][i+1]=='O'){
                b.y = i+1;
                break;
            }
            else if(g[b.x][i+1]=='#'|| (f.x==b.x && f.y==i+1)) {
                b.y = i;
                break;
            }
        }

        if(f.color == 'R') return new RedAndBlue(f,b);
        return new RedAndBlue(b,f);
    }

    /**
     * @param now 현재 Red,Blue 좌표
     * @return 위로 기울였을때 Red,Blue 좌표
     */
    static RedAndBlue up(RedAndBlue now){
        Ball f,b;

        if(now.red.x<now.blue.x){
            f = new Ball(now.red);
            b = new Ball(now.blue);
        }
        else{
            f = new Ball(now.blue);
            b = new Ball(now.red);
        }
        for(int i=f.x; i>0; i--){
            if(g[i-1][f.y]=='O'){
                f.x = i-1;
                break;
            }
            else if(g[i-1][f.y]=='#') {
                f.x = i;
                break;
            }
        }

        for(int i=b.x; i>0; i--){
            if(g[i-1][b.y]=='O'){
                b.x = i-1;
                break;
            }
            else if(g[i-1][b.y]=='#'|| (f.x==i-1 && f.y==b.y)) {
                b.x = i;
                break;
            }
        }

        if(f.color == 'R') return new RedAndBlue(f,b);
        return new RedAndBlue(b,f);
    }

    /**
     * @param now 현재 Red,Blue 좌표
     * @return 아래로 기울였을때 Red,Blue 좌표
     */
    static RedAndBlue down(RedAndBlue now){
        Ball f,b;

        if(now.red.x>now.blue.x){
            f = new Ball(now.red);
            b = new Ball(now.blue);
        }
        else{
            f = new Ball(now.blue);
            b = new Ball(now.red);
        }
        for(int i=f.x; i<n; i++){
            if(g[i+1][f.y]=='O'){
                f.x = i+1;
                break;
            }
            else if(g[i+1][f.y]=='#') {
                f.x = i;
                break;
            }
        }

        for(int i=b.x; i<n; i++){
            if(g[i+1][b.y]=='O'){
                b.x = i+1;
                break;
            }
            else if(g[i+1][b.y]=='#'|| (f.x==i+1 && f.y==b.y)) {
                b.x = i;
                break;
            }
        }

        if(f.color == 'R') return new RedAndBlue(f,b);
        return new RedAndBlue(b,f);
    }
}