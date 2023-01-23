package _17143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/17143
 * 낚시왕
 */

class Shark {
    int s,size,d;

    public Shark(int s, int d, int z) {
        this.s = s;
        this.size = z;
        this.d = d;
    }
}

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static Shark[][] update;
    static Shark[][] sea;
    static int R,C,M;
    static int cycle_r,cycle_c;

    public static void main(String[] args) throws IOException {
        int r,c,s,d,z;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cycle_r = (R-1)*2;
        cycle_c = (C-1)*2;

        sea = new Shark[R][C];
        update = new Shark[R][C];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());
            sea[r-1][c-1]= new Shark(s,d,z);
        }
        int ans=0;
        for(int i=0; i<C; i++){
            for(int j=0; j<R; j++){
                if(sea[j][i]!=null){
                    ans+=sea[j][i].size;
                    sea[j][i]=null;
                    break;
                }
            }

            for(int ii=0; ii<R; ii++){
                for(int jj=0; jj<C; jj++){
                    if(sea[ii][jj]!=null){
                        if(sea[ii][jj].d<3) moveUpDown(ii,jj);
                        else moveLeftRight(ii,jj);
                    }
                }
            }

            for(int ii=0; ii<R; ii++){
                for(int jj=0; jj<C; jj++){
                    if(update[ii][jj]!=null){
                        sea[ii][jj]=update[ii][jj];
                        update[ii][jj]=null;
                    }
                    else sea[ii][jj]=null;
                }
            }
        }

        System.out.println(ans);
    }

    static void moveUpDown(int x,int y){
        int bf,nx;
        if(x==0 || sea[x][y].d==1) bf =0;
        else bf=x*2;

        int last_s = (sea[x][y].s+bf) % cycle_r;
        if(last_s==0) {
            update[x][y]=winner(sea[x][y],update[x][y]);
            if(sea[x][y].s>0)sea[x][y].d=3-sea[x][y].d;
            return;
        }
        if(last_s<=x*2){
            nx = Math.abs(x-last_s);
            if(last_s>x)sea[x][y].d=2;
            else sea[x][y].d=1;
        }
        else{
            last_s -= 2*x;
            nx = R-1-Math.abs(R-x-1-last_s);
            if(R-x-1<last_s)sea[x][y].d=1;
            else sea[x][y].d=2;
        }
        update[nx][y]=winner(sea[x][y],update[nx][y]);
    }
    static void moveLeftRight(int x,int y){
        int bf,ny;
        if(y==0 || sea[x][y].d==4) bf =0;
        else bf=y*2;

        int last_s = (sea[x][y].s+bf) % cycle_c;

        if(last_s==0) {
            update[x][y]=winner(sea[x][y],update[x][y]);
            if(sea[x][y].s>0)sea[x][y].d=7-sea[x][y].d;
            return;
        }

        if(last_s<= y*2){
            ny = Math.abs(y-last_s);
            if(last_s>y)sea[x][y].d=3;
            else sea[x][y].d=4;
        }
        else{
            last_s -= 2*y;
            ny = C-1-Math.abs(C-y-1-last_s);
            if(C-y-1<last_s)sea[x][y].d=4;
            else sea[x][y].d=3;
        }
        update[x][ny]=winner(sea[x][y],update[x][ny]);
    }

    static Shark winner(Shark o1,Shark o2){
        if(o1==null)return o2;
        if(o2==null)return o1;
        if(o1.size>o2.size)return o1;
        return o2;
    }
}