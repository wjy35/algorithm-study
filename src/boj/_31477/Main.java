package _31477;

import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/31477">양갈래 구하기</a>
 * @category
 * @Note
 */
public class Main {
    static int answer;

    static int N;
    static List<Edge>[] edgeList;

    static int[] dp;

    static void solution(){
        dp = new int[N+1];
        answer = getMinCostSum(-1,1);
    }

    static int getMinCostSum(int pre, int now){
        if(dp[now]!=0) return dp[now];

        int parentCost=Integer.MAX_VALUE;
        int childCostSum=0;

        for(int i=0; i<edgeList[now].size(); i++){
            if(edgeList[now].get(i).node==pre){
                parentCost = edgeList[now].get(i).cost;
                continue;
            }

            childCostSum += getMinCostSum(now,edgeList[now].get(i).node);
        }

        if(childCostSum==0) childCostSum = Integer.MAX_VALUE;

        return dp[now] = Math.min(parentCost,childCostSum);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        edgeList = new List[N+1];
        for (int i=1; i<=N; i++){
            edgeList[i] = new ArrayList<>();
        }
        int u,v,c;
        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            edgeList[u].add(new Edge(v,c));
            edgeList[v].add(new Edge(u,c));
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

    static class Edge{
        int node;
        int cost;

        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }
}
