package _1516;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1516
 * @title 게임 개발
 * @algorithm Topological Sort
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

    int N;
    int[] cost;
    List<Integer>[] edgeList;

    int[] count;

    int[] answer;

    public Solution readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        edgeList = new List[N+1];
        for(int i=1; i<=N; i++) edgeList[i] = new ArrayList<>();
        cost = new int[N+1];
        count = new int[N+1];

        int pre;
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());

            cost[i] = Integer.parseInt(st.nextToken());
            while(true){
                pre = Integer.parseInt(st.nextToken());
                if(pre == -1) break;

                count[i]++;
                edgeList[pre].add(i);
            }
        }
        return this;
    }


    public Solution solve(){
        answer = new int[N+1];

        Queue<Integer> q = new ArrayDeque<>();
        for(int i=1; i<=N; i++) {
            if(count[i]!=0) continue;
            q.offer(i);
        }

        int now,next;
        while(!q.isEmpty()){
            now = q.poll();
            answer[now] += cost[now];

            for(int i=0; i<edgeList[now].size(); i++){
                next = edgeList[now].get(i);

                count[next]--;
                answer[next] = Math.max(answer[next],answer[now]);
                if(count[next]!=0) continue;
                q.offer(next);
            }
        }

        return this;
    }

    public void writeOutput() throws IOException{
        for(int i=1; i<=N; i++){
            bw.write(Integer.toString(answer[i]));
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

