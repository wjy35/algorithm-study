package _1043;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/1043
 * 거짓말
 */

public class Main {
    static int n,m,real,people,size,knowsize;
    static ArrayList<Integer>[] party;
    static ArrayList<Integer> know;
    static Queue<Integer> checkParty;
    static ArrayList<Integer>[] edge;
    static int[] visit;
    public static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        int now,next,nowsize;
        q.offer(start);
        visit[start]=1;
        while(!q.isEmpty()){
            now = q.poll();
            nowsize = edge[now].size();
            for(int i=0; i<nowsize; i++){
                next = edge[now].get(i);
                if(visit[next]==0) {
                    q.offer(next);
                    visit[next]=1;
                }
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int count = 0;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visit = new int[m+1];
        edge = new ArrayList[m+1];
        party = new ArrayList[m+1];
        checkParty = new LinkedList<>();
        know = new ArrayList<>();

        for(int i=0; i<=m; i++){
            edge[i] = new ArrayList<>();
            party[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        real = Integer.parseInt(st.nextToken());

        for(int i=0; i<real; i++){
            know.add(Integer.parseInt(st.nextToken()));
        }

        for(int i=1; i<=m; i++){
            st = new StringTokenizer(br.readLine());
            people = Integer.parseInt(st.nextToken());
            for(int j=0; j<people; j++){
                party[i].add(Integer.parseInt(st.nextToken()));
            }


        }

        knowsize= know.size();
        for(int i=1; i<=m; i++){
            for(int j=0; j<knowsize; j++){
                if(party[i].contains(know.get(j))) checkParty.add(i);
            }
            for(int j=i+1; j<=m; j++){
                size = party[i].size();
                for(int l=0; l<size; l++){
                    if(party[j].contains(party[i].get(l))){
                        edge[i].add(j);
                        edge[j].add(i);
                    }
                }
            }
        }

        while(!checkParty.isEmpty()){
            bfs(checkParty.poll());
        }

        for(int i=1; i<=m; i++){
            if(visit[i]==0){
                count++;
            }
        }
        System.out.println(count);
    }
}
