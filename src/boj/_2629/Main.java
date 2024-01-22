package _2629;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/2629">양팔저울</a>
 * @category DP
 * @Note
 */
public class Main {
    static int weightCount;
    static int ballCount;
    static int[] weightList;
    static int[] ballList;

    static int maxWeight;
    static boolean[][] dp;
    static int flag;

    static void solution(){
        flag = 0;
        dp = new boolean[2][maxWeight+1];

        for(int i=0; i<weightCount; i++){
            flag = 1-flag;

            toMeasurableWeight(flag,weightList[i]);
            for(int j=0; j<=maxWeight; j++){
                if(dp[1-flag][j]){
                    toMeasurableWeight(flag,j);
                    toMeasurableWeight(flag,Math.abs(j-weightList[i]));
                    toMeasurableWeight(flag,j+weightList[i]);
                }
            }
        }
    }

    static void toMeasurableWeight(int flag,int weight){
        if(weight<0) return;

        dp[flag][weight] = true;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        weightCount = Integer.parseInt(br.readLine());

        weightList = new int[weightCount];
        maxWeight = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<weightCount; i++){
            weightList[i] = Integer.parseInt(st.nextToken());
            maxWeight += weightList[i];
        }

        ballCount = Integer.parseInt(br.readLine());

        ballList = new int[ballCount];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<ballCount; i++){
            ballList[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i=0; i<ballCount; i++){
            if(ballList[i]<=maxWeight && dp[flag][ballList[i]])bw.write("Y ");
            else bw.write("N ");
        }
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        output();
    }
}
