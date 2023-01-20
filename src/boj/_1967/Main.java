package _1967;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/1967
 *  트리의 지름
 */

class Next{
    int node;
    int cost;

    public Next(int node, int cost) {
        super();
        this.node = node;
        this.cost = cost;
    }
}

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static ArrayList<Next>[] edge;
    static int max_cost=0,now_cost,max_node;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        edge = new ArrayList[n+1];
        visited = new boolean[n+1];

        for(int i=1; i<=n; i++) {
            edge[i] = new ArrayList<>();
        }
        int u,v,c;
        for(int i=1; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            edge[u].add(new Next(v,c));
            edge[v].add(new Next(u,c));
        }



        int start = 1;
        max_node=1;
        visited[start]=true;
        now_cost = 0;
        dfs(start);

        visited[start]=false;
        now_cost = 0;
        visited[max_node]=true;
        dfs(max_node);
        System.out.println(max_cost);
    }

    static void dfs(int now_node) {
        if(max_cost<now_cost) {
            max_node = now_node;
            max_cost = now_cost;
        }

        for(int i=0; i<edge[now_node].size(); i++) {
            Next next = edge[now_node].get(i);
            if(visited[next.node]) continue;


            visited[next.node]=true;
            now_cost += next.cost;
            dfs(next.node);
            now_cost -= next.cost;
            visited[next.node]=false;
        }

    }
}