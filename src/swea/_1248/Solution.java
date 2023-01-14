package _1248;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15PTkqAPYCFAYD&categoryId=AV15PTkqAPYCFAYD&categoryType=CODE&problemTitle=%EA%B3%B5%ED%86%B5&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 * [S/W 문제해결 응용] 3일차 - 공통조상
 */

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int v,e,a,b,p,c,size,ans,now,count;
    static int[] root;
    static ArrayList<Integer>[] tree;
    static Queue<Integer> q = new LinkedList<>();
    static ArrayList<Integer> pl;
    static ArrayList<Integer> cl;

    public static void main(String args[]) throws Exception {

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();
            count = 0;
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            root = new int[v+1];
            tree = new ArrayList[v+1];
            for(int i=1; i<=v; i++){
                tree[i] = new ArrayList<>();
            }

            pl = new ArrayList<>(v+1);
            cl = new ArrayList<>(v+1);
            for(int i=1; i<=v; i++){
                root[i] = i;
            }

            st = new StringTokenizer(br.readLine());

            for(int i=0; i<e; i++){
                p = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());
                tree[p].add(c);
                root[c] = p;
            }

            while(root[a]!=a){
                pl.add(a);
                a = root[a];
            }
            while(root[b]!=b){
                cl.add(b);
                b = root[b];
            }
            pl.add(1);
            cl.add(1);

            size = pl.size();

            for(int i=0; i<size; i++){
                ans = pl.get(i);
                if(cl.indexOf(ans)>-1) break;
            }

            q.offer(ans);
            while(!q.isEmpty()){
                now = q.poll();
                count++;
                size = tree[now].size();
                for(int i=0; i<size; i++){
                    q.offer(tree[now].get(i));
                }
            }
            sb.append("#").append(test_case).append(" ").append(ans).append(" ").append(count);
            System.out.println(sb);
        }
    }
}
