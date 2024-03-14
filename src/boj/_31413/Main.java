package _31413;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/31413">입대</a>
 * @category 
 * @Note
 */
public class Main {
    static int answer;

    static int N,M;
    static int[] s;
    static int A,D;

    static void solution(){
        int endDate = N+D-1;
        int[][] dp = new int[endDate+1][N+1];

        for(int date=1; date<=endDate; date++){
            dp[date][0] = dp[date-1][0]+s[date];

            for(int count=1; count<=N; count++){
                dp[date][count] = Math.max(dp[date][count],dp[date-1][count]+s[date]);

                if(date-D<0) continue;
                dp[date][count] = Math.max(dp[date][count],dp[date-D][count-1]+A);
            }
        }

        for(int count=0; count<=N; count++){
            if(dp[endDate][count]<M)continue;
            answer = count;
            return;
        }

        answer = -1;
    }
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        s = new int[N*2];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            s[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
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
