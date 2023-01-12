package _1717;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/1717
 * 집합의 표현
 */

public class Main {
    static int n,m;
    static int cmd,a,b;
    static int[] root;
    static int[] height;
    static int find(int x) {
        int parent=0;
        if(x==root[x]) {
            parent = x;
        }
        else {
            parent = find(root[x]);
            root[x] = parent;
        }
        return parent;
    }
    static void union(int x, int y) {
        int parent = find(x);
        int child = find(y);
        int tmp;
        if(parent == child)return;

        if(height[child] > height[parent]) {
            tmp = child;
            child = parent;
            parent = tmp;
        }
        if(height[child]==height[parent]) height[parent]++;

        root[child]=parent;
    }
    static String isFamily(int x, int y) {
        if(find(x)==find(y)) return "YES";
        return "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        root = new int[n+1];
        height = new int[n+1];

        for(int i=0; i<=n; i++){
            root[i]=i;
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            cmd = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if(cmd==0) union(a,b);
            else System.out.println(isFamily(a,b));

        }


    }

}
