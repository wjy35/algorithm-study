package _13334;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/13334
 * @title 철로
 * @algorithm Priority Queue, Greedy
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

    int n;
    Point[] points;
    int d;

    public Solution readInput() throws IOException{
        n = Integer.parseInt(br.readLine());
        points = new Point[n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        d = Integer.parseInt(br.readLine());

        return this;
    }

    int answer;
    public Solution solve(){
        answer = 0;
        Arrays.sort(points);
        PriorityQueue<Integer> lPq = new PriorityQueue<>();

        for(int i=0; i<n; i++){
            lPq.offer(points[i].l);

            while(!lPq.isEmpty() && lPq.peek()<points[i].r-d) lPq.poll();
            answer = Math.max(answer,lPq.size());
        }

        return this;
    }

    public void writeOutput() throws IOException{
        bw.write(Integer.toString(answer));
        bw.flush();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }

    private static class Point implements Comparable<Point>{
        int l,r;

        public Point(int l, int r) {
            this.l = Math.min(l,r);
            this.r = Math.max(l,r);
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.r,o.r);
        }
    }
}

