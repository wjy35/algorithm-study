package _28284;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/28284
 * @title 스터디 카페
 * @algorithm Prefix Sum, Sort
 *
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
    long[] A;
    List<Event> eventList;

    public Solution readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new long[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            A[i] = Long.parseLong(st.nextToken());
        }

        eventList = new ArrayList<>(M*2);
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            eventList.add(Event.createStartEvent(Integer.parseInt(st.nextToken())));
            eventList.add(Event.createEndEvent(Integer.parseInt(st.nextToken())));
        }

        return this;
    }

    long minRevenue,maxRevenue;

    public Solution solve(){
        long[] minPrefixSum = new long[N+1];
        long[] maxPrefixSum = new long[N+1];

        Arrays.sort(A);
        for(int i=1; i<=N; i++){
            minPrefixSum[i] = minPrefixSum[i-1]+A[i];
            maxPrefixSum[i] = maxPrefixSum[i-1]+A[N-i+1];
        }

        Collections.sort(eventList);
        int count = 0;
        minRevenue = 0;
        maxRevenue = 0;
        int latest = 0;

        Event now;
        for(int i=0; i<eventList.size(); i++){
            now = eventList.get(i);

            minRevenue += minPrefixSum[Math.min(count,N)]*(now.time-latest);
            maxRevenue += maxPrefixSum[Math.min(count,N)]*(now.time-latest);

            if(now.isStart()) count++;
            else count--;

            latest = now.time;
        }

        return this;
    }

    public void writeOutput() throws IOException{
        bw.write(Long.toString(minRevenue));
        bw.write(" ");
        bw.write(Long.toString(maxRevenue));
        bw.flush();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }

    private static class Event implements Comparable<Event>{
        int time;
        int type;

        private static final int START = 1;
        private static final int END = 2;

        public boolean isStart(){
            return this.type==Event.START;
        }

        @Override
        public int compareTo(Event o) {
            return Long.compare(this.time,o.time);
        }

        public static Event createStartEvent(int time){
            return new Event(time,Event.START);
        }

        public static Event createEndEvent(int time){
            return new Event(time+1,Event.END);
        }

        private Event(int time, int type) {
            this.time = time;
            this.type = type;
        }
    }
}



