package _2623;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2623
 * @title 음악프로그램
 * @algorithm Topology Sort
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .readInput()
                .solve()
                .writeOutput();
    }
}

class Solution{

    int N,M;
    int[] count;
    List<Integer>[] edge;

    public Solution readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int n,pre,now;
        count = new int[N+1];
        edge = new List[N+1];
        for(int i=1; i<=N; i++) edge[i] = new ArrayList<>();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            pre = Integer.parseInt(st.nextToken());
            for(int j=1; j<n; j++){
                now = Integer.parseInt(st.nextToken());
                edge[pre].add(now);
                count[now]++;

                pre = now;
            }
        }

        return this;
    }

    List<Integer> answer;
    public Solution solve(){
        answer = new ArrayList<>();

        Queue<Integer> q = new ArrayDeque<>();
        for(int i=1; i<=N; i++){
            if(count[i]==0) q.offer(i);
        }

        int peekCount = 0;
        Integer now,next;
        while(!q.isEmpty()){
            now = q.poll();
            peekCount++;
            answer.add(now);
            for(int i=0; i<edge[now].size(); i++){
                next = edge[now].get(i);

                if(count[next]==0) continue;

                count[next]--;
                if(count[next]==0) q.offer(next);
            }
        }

        if(peekCount==N) return this;

        answer = new ArrayList<>();
        answer.add(0);

        return this;
    }

    public void writeOutput() throws IOException{
        for(int i=0; i<answer.size(); i++){
            bw.write(answer.get(i).toString());
            bw.write("\n");
        }
        bw.flush();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

