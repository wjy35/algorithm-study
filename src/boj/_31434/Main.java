package _31434;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/31434">당근 클릭 게임</a>
 * @category DP
 * @Note
 */
public class Main {
    static int answer;

    static int N,K;
    static Carrot[] carrots;

    static void solution(){
        int maxSpeed = 50*K+2;
        int[][] dp = new int[K+1][maxSpeed+51];

        for(int second=0; second<=K; second++){
            Arrays.fill(dp[second],-1);
        }
        dp[0][1] = 0;

        for(int second = 0; second<K; second++){
            for(int speed=0; speed<maxSpeed; speed++){
                if(dp[second][speed]==-1) continue;

                dp[second+1][speed] = Math.max(dp[second+1][speed],dp[second][speed]+speed);

                for(int carrotIndex=0; carrotIndex<N; carrotIndex++){
                    if(dp[second][speed]-carrots[carrotIndex].price<0) continue;
                    dp[second+1][speed+carrots[carrotIndex].speed] = Math.max(dp[second+1][speed+carrots[carrotIndex].speed],dp[second][speed]-carrots[carrotIndex].price);
                }
            }
        }

        answer = 0;
        for(int i=0; i<maxSpeed; i++){
            answer = Math.max(dp[K][i],answer);
        }
    }
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        carrots = new Carrot[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            carrots[i] = new Carrot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
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


    static class Carrot{
        int price;
        int speed;

        public Carrot(int price, int speed) {
            this.price = price;
            this.speed = speed;
        }
    }

}
