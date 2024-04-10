package _2283.v1;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2283
 * @title 구간 자르기
 * @algorithm 스위핑
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

    int N,K;
    Queue<Point>[] pointList;

    final int maxR = 1_000_001;
    public Solution readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        Point point;
        pointList = new Queue[maxR];
        for(int i=0; i<maxR; i++){
            pointList[i] = new ArrayDeque<>();
        }
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            point = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            pointList[point.l].offer(point);
        }

        return this;
    }

    String answer;
    public Solution solve(){
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int[] size = new int[maxR+1];
        for(int i=1; i<maxR; i++){
            while(!pointList[i-1].isEmpty()) pq.offer(pointList[i-1].poll().r);
            while(!pq.isEmpty() && pq.peek()<i) pq.poll();
            size[i] = pq.size();
        }

        int answerL=0, answerR=0;
        int l=0,r=0;
        int sum = 0;
        while(l<=r && r<maxR){
            if(sum<K){
                r++;
                sum += size[r];
            }else if(sum>K){
                l++;
                sum -= size[l];
            }else{
                answerL = l;
                answerR = r;
                break;
            }
        }

        answer = sb.append(answerL).append(" ").append(answerR).toString();

        return this;
    }

    public void writeOutput() throws IOException{
        bw.write(answer);
        bw.flush();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }

    private static class Point{
        int l,r;

        public Point(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }
}

