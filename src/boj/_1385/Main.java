package _1385;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static class Point{
        int x,y;
        String load;

        public Point(int x, int y, String load) {
            this.x = x;
            this.y = y;
            this.load = load;
        }
    }
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int a,b;
    static int[][] map;
    static int[]dx = {-1,0,1, 1,0,-1};
    static int[]dy = {1 ,1,0,-1,-1,0};
    static boolean isIn(int nx,int ny){ return 0<=nx && nx<1200 && 0<=ny && ny<1200;}
    static int value;
    static Point start,end;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        makeMap();
        findLoad();
        bw.flush();

    }
    static void findLoad() throws IOException {
        int dAxisX = Math.abs(start.y - end.y );
        int dAxisY = Math.abs(start.x - end.x);
        int dAxisXY = Math.abs(start.x+start.y-end.x-end.y);

        if(dAxisX == 0){
            if(start.x > end.x){
                move(new int[]{0,-1},new int[]{0,0},0);
            }
            else{
                move(new int[]{0,1},new int[]{0,0},0);
            }
        }
        else if(dAxisXY == 0){
            if(start.x > end.x){
                move(new int[]{-1},new int[]{1},2);
            }
            else{
                move(new int[]{1},new int[]{-1},2);
            }
        }
        else if(dAxisY == 0){
            if(start.y > end.y){
                move(new int[]{0,0},new int[]{0,-1},1);
            }
            else{
                move(new int[]{0,0},new int[]{0,1},1);
            }
        }
        else{
            if(dAxisX>dAxisXY && dAxisX>dAxisY){
                if(start.y - end.y>0){
                    move(new int[]{1,0},new int[]{-1,-1},1);
                }
                else{
                    move(new int[]{-1,0},new int[]{1,1},1);
                }
            }
            else if(dAxisY>dAxisXY && dAxisY>dAxisX){
                if(start.x - end.x>0){
                    move(new int[]{-1,-1},new int[]{1,0},0);
                }
                else{
                    move(new int[]{1,1},new int[]{-1,0},0);
                }
            }
            else{
                if(start.y - end.y>0){
                    move(new int[]{0,-1},new int[]{-1,0},0);
                }
                else{
                    move(new int[]{0,1},new int[]{1,0},0);
                }
            }
        }
    }

    //axis 0:x 1:y 2:x-y

    static void move(int[] mdx, int[] mdy,int axis) throws IOException {
        int x = start.x;
        int y = start.y;

        if(axis == 1){
            while(x!=end.x){
                bw.write(Integer.toString(map[x][y]));
                bw.write(" ");

                x += mdx[0];
                y += mdy[0];
            }
            while(y!=end.y){
                bw.write(Integer.toString(map[x][y]));
                bw.write(" ");

                x += mdx[1];
                y += mdy[1];
            }
            bw.write(Integer.toString(map[x][y]));
            bw.write(" ");
        }
        else if(axis == 0){
            while(y!=end.y){
                bw.write(Integer.toString(map[x][y]));
                bw.write(" ");

                x += mdx[0];
                y += mdy[0];
            }
            while(x!=end.x){
                bw.write(Integer.toString(map[x][y]));
                bw.write(" ");

                x += mdx[1];
                y += mdy[1];
            }
            bw.write(Integer.toString(map[x][y]));
            bw.write(" ");
        }
        else{
            while(x!=end.x){
                bw.write(Integer.toString(map[x][y]));
                bw.write(" ");
                x += mdx[0];
                y += mdy[0];
            }
            bw.write(Integer.toString(map[x][y]));
            bw.write(" ");
        }
    }


    static void makeMap(){
        map = new int[1200][1200];
        value = 1;
        map[601][600] = value++;

        for(int r=1; value<=1_000_000; r++){
            fillMapAtRound(r);
        }
    }

    static void fillMapAtRound(int r){
        int x = 600;
        int y = 601-r;

        for(int i=0; i<r-1; i++){
            if(value == a){
                start = new Point(x,y,Integer.toString(a));
            }
            if(value == b){
                end = new Point(x,y,"");
            }
            map[x][y] = value++;
            x += dx[0];
            y += dy[0];
        }

        for(int i=1; i<6; i++){
            for(int j=0; j<r; j++){
                if(value == a){
                    start = new Point(x,y,Integer.toString(a));
                }
                if(value == b){
                    end = new Point(x,y,"");
                }
                map[x][y] = value++;
                x+= dx[i];
                y+= dy[i];
            }
        }
        if(value == a){
            start = new Point(x,y,Integer.toString(a));
        }
        if(value == b){
            end = new Point(x,y,"");
        }
        map[x][y] = value++;
    }
}
