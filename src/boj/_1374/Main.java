package _1374;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/1374">강의실</a>
 * @category Sort, Priority Queue
 * @Note
 */
public class Main {
    static class Time implements Comparable<Time>{
        int start,end;

        public Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Time o) {
            return Integer.compare(this.start,o.start);
        }

        @Override
        public String toString() {
            return "Time{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n;
    static Time[] study;
    static int count;
    static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        study = new Time[n];
        pq = new PriorityQueue<>();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            study[i]=new Time(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(study);

        count = 1;
        pq.offer(study[0].end);


        for(int i=1; i<n; i++){
            if(study[i].start<pq.peek()){
                count++;
                pq.offer(study[i].end);
            }else{
                pq.poll();
                pq.offer(study[i].end);
            }
        }
        bw.write(Integer.toString(count));
        bw.flush();
    }
}