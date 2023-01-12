package _1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/1260
 * DFS와 BFS
 */

public class Main {
    static int n,m,start,u,v;
    static int[][] edge;
    static int[] visit;
    static void dfs(int now){
        System.out.print(now+" ");
        for(int i=1; i<= n; i++){
            if(i==now) continue;
            if(edge[now][i]==1&&visit[i]==0){
                visit[i]=1;
                dfs(i);
            }
        }
    }
    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        int now;
        q.offer(start);

        while(!q.isEmpty()){
            now = q.poll();
            System.out.print(now+" ");
            for(int i=1; i<= n; i++){
                if(now==i) continue;
                if(edge[now][i]==1&&visit[i]==0){
                    q.offer(i);
                    visit[i]=1;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        edge = new int[n+1][n+1];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            edge[u][v] = 1;
            edge[v][u] = 1;
        }
        visit = new int[n+1];
        visit[start]=1;
        dfs(start);

        System.out.println();

        visit = new int[n+1];
        visit[start]=1;
        bfs(start);


    }
}
