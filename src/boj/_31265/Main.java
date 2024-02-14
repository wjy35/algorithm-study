package _31265;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/31265"></a>
 * @category DP
 * @Note
 */
public class Main {
    static int answer;
    
    static int N,M;
    static int[] d;
    static List<Integer>[] t;

    static int[] prefixSum;
    static int[][] dp;
    static void solution(){
        dp = new int[prefixSum[N]+1][M+1];

        for(int situation=1,row=1; situation<=N; situation++){
            updateForDiffSituation(row,t[situation].get(0));
            row++;
            for(int i=1; i<t[situation].size(); i++){
                updateForSameSituation(situation,row,t[situation].get(i));
                row++;
            }
        }

        answer = dp[prefixSum[N]][M];
    }

    static void updateForSameSituation(int situation,int row,int time){
        int tmp;
        for(int col=0; col<=M; col++){
            if(col<time){
                dp[row][col] = dp[row-1][col];
            }else{
                tmp = -1;
                if(dp[row-1][col-time]==-1){
                    if(dp[prefixSum[situation-1]][col-time]!=-1) {
                        tmp = dp[prefixSum[situation-1]][col-time]+time;
                    }
                }else{
                    tmp = dp[row-1][col-time]+time;
                }

                dp[row][col] = Math.max(tmp,dp[row-1][col]);
            }
        }
    }

    static void updateForDiffSituation(int row,int time){
        for(int col=0; col<=M; col++){
            if(col<time){
                dp[row][col] = -1;
            } else if(dp[row-1][col-time]==-1){
                dp[row][col] = -1;
            } else{
                dp[row][col] = dp[row-1][col-time]+time;
            }
        }
    }
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        d = new int[N+1];
        prefixSum = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            d[i] = Integer.parseInt(st.nextToken());
            prefixSum[i] = prefixSum[i-1]+d[i];
        }

        t = new List[N+1];
        for(int i=1; i<=N; i++){
            
            t[i] = new ArrayList<>(d[i]);
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<d[i]; j++){
                t[i].add(Integer.parseInt(st.nextToken()));
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
