package _17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/17144
 * 미세먼지 안녕!
 */

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int r,c,t;
    static int air_top,air_bottom;
    static int[][] house;
    static int[][] update;
    static int ans=0;
    static int[] dx={1,0,-1,0};
    static int[] dy={0,1,0,-1};

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        house = new int[r][c];
        update = new int[r][c];

        for(int i=0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<c; j++){
                house[i][j]= Integer.parseInt(st.nextToken());
                update[i][j]=house[i][j];
            }
        }

        for(int i=0; i<r; i++){
            if(house[i][0]==-1){
                air_top=i;
                air_bottom=i+1;
                break;
            }
        }

        while(t-->0){
            makeUpdateByDust();
            makeUpdateByAir();
            updateHouse();
        }
        solution();
        System.out.println(ans);
    }

    static void solution(){
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                ans +=house[i][j];
            }
        }
        ans+=2;
    }

    static boolean isIn(int nx,int ny){
        return 0<=nx && nx<r && 0<=ny && ny<c && (house[nx][ny]!=-1);
    }

    static void makeUpdateByDust(){
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                calDust(i,j);
            }
        }
    }

    static void calDust(int x,int y){
        int nx,ny;
        int amount=house[x][y]/5;
        int spreadDust=0;

        for(int i=0; i<4; i++){
            nx = x+dx[i];
            ny = y+dy[i];
            if(isIn(nx,ny)){
                update[nx][ny]+=amount;
                spreadDust +=amount;
            }
        }
        update[x][y]-=spreadDust;
    }

    static void makeUpdateByAir(){

        update[air_top-1][0]=0;
        update[air_bottom+1][0]=0;

        for(int i=air_top-1; i>0; i--){
            update[i][0]=update[i-1][0];
        }
        for(int i=air_bottom+1; i<r-1; i++){
            update[i][0]=update[i+1][0];
        }

        for(int i=0; i<c-1; i++){
            update[0][i]=update[0][i+1];
            update[r-1][i]=update[r-1][i+1];
        }


        for(int i=0; i<air_top; i++){
            update[i][c-1]=update[i+1][c-1];
        }
        for(int i=r-1; i>air_bottom; i--){
            update[i][c-1]=update[i-1][c-1];
        }


        for(int i=c-1; i>1; i--){
            update[air_top][i]=update[air_top][i-1];
            update[air_bottom][i]=update[air_bottom][i-1];

        }
        update[air_top][1]=0;
        update[air_bottom][1]=0;
    }

    static void updateHouse(){
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                house[i][j]=update[i][j];
            }
        }
    }
    static void print(){
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                System.out.print(update[i][j]+" ");
            }
            System.out.println();
        }
    }
}