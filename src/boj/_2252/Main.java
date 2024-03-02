package _2252;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/2252">줄 세우기</a>
 * @category
 * @Note
 */
public class Main {
    static String answer;

    static int N,M;
    static int[] count;
    static List<Integer>[] edge;
    static void solution(){
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new ArrayDeque<>();

        for(int i=1; i<=N; i++){
            if(count[i]>0) continue;

            q.offer(i);
        }

        int now,next;
        while(!q.isEmpty()){
            now = q.poll();

            sb.append(now);
            sb.append(" ");

            for(int i=0; i<edge[now].size(); i++){
                next = edge[now].get(i);
                count[next]--;

                if(count[next]==0) q.offer(next);
            }
        }

        answer = sb.toString();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        count = new int[N+1];
        edge = new List[N+1];

        for(int i=1; i<=N; i++){
            edge[i] = new ArrayList<>();
        }

        int from,to;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            edge[from].add(to);
            count[to]++;
        }
    }

    static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(answer);
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        output();
    }
}
