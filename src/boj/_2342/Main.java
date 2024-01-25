package _2342;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/2342">Dance Dance Revolution</a>
 * @category DP
 * @Note
 */
public class Main {
    static int answer;
    static StringTokenizer dance;
    static final int INF = Integer.MAX_VALUE;

    static int[][][] force;
    static void solution(){
        force = new int[dance.countTokens()][5][5];
        for(int i = 0; i< force.length; i++){
            for(int j = 0; j< force[i].length; j++){
                Arrays.fill(force[i][j],INF);
            }
        }
        force[0][0][0] = 0;

        int length = dance.countTokens();
        for(int i=1; i<length; i++){
            updateForceAt(i);
        }

        int min = INF;
        for(int l=0; l<5; l++){
            for(int r=0; r<5; r++){
                if(force[length-1][l][r]==INF) continue;
                min = Math.min(min, force[length-1][l][r]);
            }
        }

        answer = min;
    }

    static void updateForceAt(int i){
        int next = Integer.parseInt(dance.nextToken());
        for(int l=0; l<5; l++) {
            for (int r = 0; r < 5; r++) {
                if (force[i - 1][l][r] == INF) continue;

                if (next == l) {
                    force[i][next][r] = Math.min(force[i][next][r], force[i - 1][l][r] + 1);
                } else if (next == r) {
                    force[i][l][next] = Math.min(force[i][l][next], force[i - 1][l][r] + 1);
                } else {
                    force[i][next][r] = Math.min(force[i][next][r], force[i - 1][l][r] + calRequiredForce(l, next));
                    force[i][l][next] = Math.min(force[i][l][next], force[i - 1][l][r] + calRequiredForce(r, next));
                }
            }
        }
    }

    static int calRequiredForce(int now, int next){
        if(now==0 || next==0) return 2;
        if(now+next==6 || now+next==4) return 4;

        return 3;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dance = new StringTokenizer(br.readLine());
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
