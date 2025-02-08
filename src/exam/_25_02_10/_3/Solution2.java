package _25_02_10._3;

class Solution2 {
    int[][] board;
    int[] dx = {1,0,-1,0};
    int[] dy = {0,1,0,-1};
    boolean isOut(int x,int y){ return 0>x || x>=4 || 0>y || y>=4;}
    boolean[][][] isVisited;


    int maxLength;
    public void dfs(int x,int y,int bit){
        for(int i=0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(isOut(nx,ny)) continue;
            if(board[x][y]!=board[nx][ny]) continue;
            if((bit & (1<<nx*4+ny))>0) continue;

            int nBit = bit | (1<<nx*4+ny);

            if(isVisited[nx][ny][nBit]) continue;

            int nBitCount = Integer.bitCount(nBit);
            maxLength = Math.max(maxLength,nBitCount);

            isVisited[nx][ny][nBit] = true;
            dfs(nx,ny,nBit);
        }
    }

    public int solution(int[][] board) {
        this.board = board;
        this.isVisited = new boolean[4][4][1<<16];

        int answer = 1;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                maxLength = 1;

                int bit = 1 << (x * 4 + y);
                dfs(x, y, bit);

                answer = Math.max(answer, maxLength);
            }
        }

        if (answer==1) answer = -1;
        return answer;
    }
}