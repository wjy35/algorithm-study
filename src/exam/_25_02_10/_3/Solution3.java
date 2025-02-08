package _25_02_10._3;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution3 {
    int[][] board;
    int[] dx = {1,0,-1,0};
    int[] dy = {0,1,0,-1};
    boolean isOut(int x,int y){ return 0>x || x>=4 || 0>y || y>=4;}

    public int solution(int[][] board) {
        this.board = board;
        boolean[][][] isVisited = new boolean[4][4][1<<16];

        Queue<Point> q = new ArrayDeque<>();

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                int bit = 1 << (x * 4 + y);
                q.offer(new Point(x,y,bit));
            }
        }

        int maxLength = 1;
        while(!q.isEmpty()){
            Point current = q.poll();

            for(int i=0; i<4; i++){
                int nx = current.x+dx[i];
                int ny = current.y+dy[i];

                if(isOut(nx,ny)) continue;
                if(board[current.x][current.y]!=board[nx][ny]) continue;
                if((current.bit & (1<<nx*4+ny))>0) continue;

                int nBit = current.bit | (1<<nx*4+ny);

                if(isVisited[nx][ny][nBit]) continue;

                int nBitCount = Integer.bitCount(nBit);
                maxLength = Math.max(maxLength,nBitCount);

                isVisited[nx][ny][nBit] = true;
                q.offer(new Point(nx,ny,nBit));
            }
        }

        if (maxLength==1) maxLength = -1;
        return maxLength;
    }

    private static class Point{
        int x,y,bit;

        public Point(int x, int y, int bit) {
            this.x = x;
            this.y = y;
            this.bit = bit;
        }
    }
}