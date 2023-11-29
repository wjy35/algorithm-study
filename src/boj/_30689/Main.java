package _30689;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/30689">미로 보수</a>
 * @category dfs
 * @Note
 */
public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int N,M;
    static char[][] miro;
    static int[][] cost;

    static int minCost;
    static int minCostSum;
    static int[][] visited;
    static int[][] id;
    static int now;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        miro = new char[N][M];
        cost = new int[N][M];
        visited = new int[N][M];
        id = new int[N][M];

        minCostSum = 0;
        for(int i=0; i<N; i++){
            miro[i] = br.readLine().toCharArray();
        }

        int idValue=1;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
                id[i][j] = idValue++;
            }
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(visited[i][j]==0){
                    now++;
                    minCost=0;
                    dfs(i,j);
                    minCostSum+=minCost;
                }
            }
        }

        bw.write(Integer.toString(minCostSum));
        bw.flush();
    }

    private static boolean isIn(int x,int y){
        return 0<=x && 0<=y && x<N && y<M;
    }

    private static int dfs(int x,int y){
        int nx,ny;
        visited[x][y] = now;

        if(miro[x][y]=='L'){
            nx = x;
            ny = y-1;
        }else if(miro[x][y]=='R'){
            nx = x;
            ny = y+1;
        }else if(miro[x][y]=='U'){
            nx = x-1;
            ny = y;
        }else{
            nx = x+1;
            ny = y;
        }

        if(!isIn(nx,ny)) return -1;

        if(visited[nx][ny]==now) {
            minCost = Math.min(cost[nx][ny],cost[x][y]);
            return id[nx][ny];
        }

        if(visited[nx][ny]!=0) return -1;

        int startId = dfs(nx,ny);
        if(startId == -1) return -1;
        if(id[x][y] == startId) return -1;

        minCost = Math.min(minCost,cost[x][y]);
        return startId;
    }
}
