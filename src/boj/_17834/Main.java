package _17834;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/17834">사자와 토끼</a>
 * @category
 * @Note
 */
public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int n,m;
    static ArrayList<Integer>[] edge;
    static int[] color;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        edge = new ArrayList[n+1];
        color = new int[n+1];

        for(int i=1; i<=n; i++){
            edge[i] = new ArrayList<>();
        }

        int u,v;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            edge[u].add(v);
            edge[v].add(u);
        }

        long ans,red,blue;
        if(bfs()){
            red = 0;
            blue = 0;
            for(int i=1; i<=n; i++){
                if(color[i] == 1){
                    red++;
                }
                else{
                    blue++;
                }
            }
            ans = red * blue * 2;
            bw.write(Long.toString(ans));
        }else{
            bw.write("0");
        }
        bw.flush();
    }

    static boolean bfs(){
        Queue<Integer> q = new ArrayDeque<>();

        color[1] = 1;
        q.offer(1);

        int now;
        while(!q.isEmpty()){
            now = q.poll();

            for(int i=0; i<edge[now].size(); i++){
                if(color[edge[now].get(i)] == color[now]) return false;
                else if(color[edge[now].get(i)] == 0){
                    color[edge[now].get(i)] = 3 - color[now];
                    q.offer(edge[now].get(i));
                }
            }
        }
        return true;
    }
}
