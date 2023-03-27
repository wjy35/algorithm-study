package _1197;

import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href="https://www.acmicpc.net/problem/1197"> 최소 스패닝 트리 </a>
 */

public class Main {
    static class Edge implements Comparable<Edge>{
        int s,e,w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w,o.w);
        }
    }

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int v,e;
    static PriorityQueue<Edge> pq;

    static int[] root;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();

        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            pq.offer(new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }
        makeSet();

        int count = 0;
        int mst = 0;
        while(!pq.isEmpty()){
            Edge edge = pq.poll();

            if(union(edge.s,edge.e)){
                mst+= edge.w;
                count++;
                if(count == v-1) break;
            }
        }

        bw.write(Integer.toString(mst));
        bw.flush();
    }

    public static void makeSet(){
        root = new int[v+1];
        rank = new int[v+1];

        for(int i=1; i<=v; i++){
            root[i] = i;
        }
    }
    public static int find(int node){
        if(root[node] == node) return node;

        return root[node] = find(root[node]);
    }

    public static boolean union(int node1,int node2){
        int root1 = find(node1);
        int root2 = find(node2);

        if(root1 == root2) return false;

        int p = root1;
        int c = root2;

        if(rank[p]<rank[c]){
            int tmp = p;
            p = c;
            c = tmp;
        }

        root[c] = p;
        if(root[p] == root[c]) rank[p]++;

        return true;
    }
}

