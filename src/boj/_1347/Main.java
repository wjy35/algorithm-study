package _1347;

import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1347
 * @title 미로 만들기
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int n;
    String path;

    int[][] map = new int[101][101];
    int t=50,b=50,l=50,r=50;
    private void readInput() throws IOException{
        n = Integer.parseInt(br.readLine());
        path = br.readLine();
    }

    private void solve(){
        int[] dx = {1,0,-1,0};
        int[] dy = {0,1,0,-1};
        int direction = 0;
        int x=50,y=50;
        map[x][y] = 1;

        for(int i=0; i<n; i++){
            if(path.charAt(i)=='R'){
                direction--;
                if(direction<0) direction +=4;
            }else if(path.charAt(i)=='L'){
                direction = (direction+1)%4;
            }else{
                x = x+dx[direction];
                y = y+dy[direction];

                map[x][y] = 1;
                t = Math.min(t,x);
                b = Math.max(b,x);
                l = Math.min(l,y);
                r = Math.max(r,y);
            }
        }
    }

    private void writeOutput() throws IOException{
        for(int i=t; i<=b; i++){
            for(int j=l; j<=r; j++){
                if(map[i][j]==1) bw.write(".");
                else bw.write("#");
            }
            bw.write("\n");
        }
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        solve();
        writeOutput();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

