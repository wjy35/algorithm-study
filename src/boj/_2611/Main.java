package _2611;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2611
 * @title 자동차경주
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

    int N,M;
    List<Road>[] pathRoadList;
    List<Road> endRoadList;
    int[] incomeRoadCount;

    public Solution readInput() throws IOException{
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        incomeRoadCount = new int[N+1];
        pathRoadList = new List[N+1];
        endRoadList = new ArrayList<>();

        for(int i=1; i<=N; i++) pathRoadList[i] = new ArrayList<>();

        int p,q,r;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            if(q==1){
                endRoadList.add(new Road(p,r));
                continue;
            }

            pathRoadList[p].add(new Road(q,r));
            incomeRoadCount[q]++;
        }

        return this;
    }

    int[] preNode;
    int maxScore = 0;

    public Solution solve(){
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);

        int[] scoreSum = new int[N+1];
        preNode = new int[N+1];
        preNode[1] = 1;

        int now;
        while(!q.isEmpty()){
            now = q.poll();

            for(Road nextRoad : pathRoadList[now]){
                incomeRoadCount[nextRoad.node]--;

                if(scoreSum[nextRoad.node] < scoreSum[now]+nextRoad.score){
                    scoreSum[nextRoad.node] = scoreSum[now]+nextRoad.score;
                    preNode[nextRoad.node] = now;
                }

                if(incomeRoadCount[nextRoad.node]==0){
                    q.offer(nextRoad.node);
                }
            }
        }

        maxScore = 0;

        for(Road endRoad : endRoadList){
            if(maxScore < scoreSum[endRoad.node]+endRoad.score){
                preNode[1] = endRoad.node;
                maxScore = scoreSum[endRoad.node]+endRoad.score;
            }
        }

        return this;
    }

    public void writeOutput() throws IOException{
        bw.write(Integer.toString(maxScore));
        bw.write("\n");

        Deque<Integer> q = new ArrayDeque<>();
        int now = preNode[1];
        while(now!=1){
            q.offer(now);
            now = preNode[now];
        }
        q.offerLast(1);
        if(q.size()>1)q.offerFirst(1);

        while(!q.isEmpty()){
            bw.write(q.pollLast().toString());
            bw.write(" ");
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

    static class Road{
        int node;
        int score;

        public Road(int node, int score) {
            this.node = node;
            this.score = score;
        }
    }
}

