package _1916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * https://www.acmicpc.net/problem/1916
 * 최소비용 구하기
 */

class City implements Comparable<City>{
    int node;
    int cost;
    City(int node,int cost){
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(City o) {
        if(this.cost>o.cost)return 1;
        return -1;
    }
}

public class Main {
    static int n,m;
    static int start,end;
    static final int INF = 1_000_000_001;

    static ArrayList<City>[] edge;
    static int[] d;
    public static void main(String[] args) throws IOException {
        int u,v,cost,size;
        PriorityQueue<City> pq = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        edge = new ArrayList[n+1];
        d = new int[n+1];
        Arrays.fill(d,INF);

        for(int i=1; i<=n; i++){
            edge[i] = new ArrayList<City>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            edge[u].add(new City(v,cost));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        d[start]=0;
        pq.offer(new City(start,0));
        City now,next;

        while(!pq.isEmpty()){
            now = pq.poll();
            if(now.cost!=d[now.node]) continue;
            size = edge[now.node].size();
            for(int i=0; i<size; i++){
                next = edge[now.node].get(i);
                if(now.cost+ next.cost < d[next.node]) {
                    pq.offer(new City(next.node,now.cost+ next.cost));
                    d[next.node] = now.cost+ next.cost;
                }
            }
        }
        System.out.println(d[end]);
    }
}