package _2169;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/2169">로봇 조종하기</a>
 * @category
 * @Note
 */
public class Main {
    static int answer;

    static int N,M;
    static int[][] map;

    static final int LEFT_TO_RIGHT = 0;
    static final int RIGHT_TO_LEFT = 1;
    static int[][] dp;
    static int[][] tmp;

    static void solution(){
        dp = new int[N][M];

        dp[0][0] = map[0][0];
        for(int i=1; i<M; i++){
            dp[0][i] = dp[0][i-1]+map[0][i];
        }

        for(int i=1; i<N; i++){
            initTmp(i);
            updateDp(i);
        }

        answer = dp[N-1][M-1];
    }

    static void initTmp(int i){
        tmp = new int[2][M];

        tmp[LEFT_TO_RIGHT][0]=dp[i-1][0]+map[i][0];
        tmp[RIGHT_TO_LEFT][M-1]=dp[i-1][M-1]+map[i][M-1];

        for(int j=1; j<M; j++){
            tmp[LEFT_TO_RIGHT][j]=Math.max(dp[i-1][j],tmp[LEFT_TO_RIGHT][j-1])+map[i][j];
            tmp[RIGHT_TO_LEFT][M-1-j]=Math.max(dp[i-1][M-j-1],tmp[RIGHT_TO_LEFT][M-j])+map[i][M-1-j];
        }
    }

    static void updateDp(int i){
        for(int j=0; j<M; j++){
            dp[i][j] = Math.max(tmp[LEFT_TO_RIGHT][j],tmp[RIGHT_TO_LEFT][j]);
        }
    }



    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(Integer.toString(answer));
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        output();
    }
}
