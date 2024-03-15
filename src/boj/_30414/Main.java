package _30414;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/30414">투스타 춘배</a>
 * @category 
 * @Note
 */
public class Main {
    static int answer;

    static int N,P;
    static int[] A,B;
    static List<Integer>[] edge;
    
    static void solution(){
        answer = dfs(-1,P);
    }

    static int dfs(int pre,int now){
        if(edge[now].size()==1 && edge[now].get(0)==pre){
            return Math.max(B[now]-A[now], 0);
        }

        int cost = B[now]-A[now];
        for(int i=0; i<edge[now].size(); i++){
            if(edge[now].get(i)==pre) continue;

            cost+=dfs(now,edge[now].get(i));
        }

        return Math.max(cost,0);
    }
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        A = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        B = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            B[i] = Integer.parseInt(st.nextToken());
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
