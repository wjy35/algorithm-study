package _1949;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/1949">우수 마을</a>
 * @category Tree 에서 DP
 * @Note
 */
public class Main {
    static int answer;

    static int N;
    static int[] population;
    static List<Integer>[] edge;
    static int[][] dp;

    static void solution() {
        if(N==1) {
            answer = population[1];
            return;
        }
        dp = new int[2][N+1];
        answer = dfs(0, 0, 1);
    }

    static int dfs(int pre,int isPreBest,int now){
        if(isLeaf(pre,now)){
            if(isPreBest==0) return population[now];

            return 0;
        }

        if(dp[isPreBest][now]!=0) return dp[isPreBest][now];

        int maxPopulationSum=0;
        for(int i=0; i<edge[now].size(); i++){
            if(pre==edge[now].get(i)) continue;

            maxPopulationSum += dfs(now,0,edge[now].get(i));
        }

        if(isPreBest==0){
            int populationSum = population[now];
            for(int i=0; i<edge[now].size(); i++){
                if(pre==edge[now].get(i)) continue;

                populationSum += dfs(now,1,edge[now].get(i));
            }

            maxPopulationSum = Math.max(maxPopulationSum,populationSum);
        }

        return dp[isPreBest][now]=maxPopulationSum;
    }

    static boolean isLeaf(int pre,int now){
        for(int i=0; i<edge[now].size(); i++){
            if(edge[now].get(i)==pre) continue;

            return false;
        }
        return true;
    }
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        population = new int[N+1];
        for(int i=1; i<=N; i++){
            population[i] = Integer.parseInt(st.nextToken());
        }

        edge = new List[N+1];
        for(int i=1; i<=N; i++){
            edge[i] = new ArrayList<>();
        }

        int u,v;
        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            edge[u].add(v);
            edge[v].add(u);
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
