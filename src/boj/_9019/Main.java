package _9019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/9019
 * DSLR
 */

class Node{
    int num;
    String cmd;
    Node(int num, String cmd){
        this.num = num;
        this.cmd = cmd;
    }
}
public class Main {
    static int T,a,b;
    static int[] visit;
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for(int test_case=1; test_case<=T; test_case++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            visit = new int[10000];

            Queue<Node> q = new LinkedList<>();
            q.offer(new Node(a,""));
            while(!q.isEmpty()) {
                Node now = q.poll();

                if(visit[now.num]==1) continue;
                if(now.num == b) { System.out.println(now.cmd);break;}
                visit[now.num]=1;

                q.offer(new Node(D(now.num),now.cmd+"D"));
                q.offer(new Node(S(now.num),now.cmd+"S"));
                q.offer(new Node(L(now.num),now.cmd+"L"));
                q.offer(new Node(R(now.num),now.cmd+"R"));
            }


        }
    }


    static int D(int x) {
        return 2*x%10000;
    }

    static int S(int x) {
        if(x==0) return 9999;
        return x-1;
    }
    static int L(int x) {
        return (x%1000)*10+x/1000;
    }
    static int R(int x) {
        return (x%10)*1000+x/10;
    }

}