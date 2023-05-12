package _17831;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/17831">대기업 승범이네</a>
 * @category 
 * @Note
 */
public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int n;
    static ArrayList<Integer>[] edge;
    static int[] a;
    static int[][] dp;

    static int MENTEE = 0;
    static int MENTOR = 1;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        edge = new ArrayList[n+1];
        a = new int[n+1];
        dp = new int[2][n+1];

        for(int i=1; i<=n ;i++){
            edge[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for(int i=2; i<=n; i++){
            edge[Integer.parseInt(st.nextToken())].add(i);
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1);

        bw.write(Integer.toString(Math.max(dp[MENTOR][1],dp[MENTEE][1])));
        bw.flush();
    }

    static void dfs(int now){
        for(int i=0; i<edge[now].size(); i++){
            dfs(edge[now].get(i));
        }

        // now == mentee
        int sum = 0;
        for(int i=0; i<edge[now].size(); i++){
            sum += Math.max(dp[MENTOR][edge[now].get(i)],dp[MENTEE][edge[now].get(i)]);
        }
        dp[MENTEE][now] = sum;

        // now == mentor
        int mentee;
        int max = 0;
        int menteeSum,other;
        for(int i=0; i<edge[now].size(); i++){
            mentee = edge[now].get(i);

            other = sum - Math.max(dp[MENTOR][mentee],dp[MENTEE][mentee]);
            menteeSum = a[now] * a[mentee] + dp[MENTEE][mentee];

            max = Math.max(max,other+menteeSum);
        }
        dp[MENTOR][now] = max;
    }
}
