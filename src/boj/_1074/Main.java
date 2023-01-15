package _1074;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/1074
 * Z
 */

public class Main {
    static int n,r,c,count=0;
    static int[] _2 = {1,2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384,32768};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        z(0,0,_2[n]);

    }

    static void z(int x,int y, int size){
        if(x==r&&y==c) {
            System.out.println(count);
            return;
        }
        if(x<=r&& r<x+size && y<=c&&c<y+size){
            int nsize = size/2;
            z(x,y,nsize);
            z(x,y+nsize,nsize);
            z(x+nsize,y,nsize);
            z(x+nsize,y+nsize,nsize);
        }
        else{
            count += size*size;
        }

    }
}