package _2836;

import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2836
 * @title 수상 택시
 * @algorithm Sweep Line
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .readInput()
                .solve()
                .writeOutput();
    }
}

class Solution {

    int N,M;
    List<Event> eventList;
    long answer;

    public Solution readInput() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        eventList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            long start, end;

            st = new StringTokenizer(br.readLine());
            start = Long.parseLong(st.nextToken());
            end = Long.parseLong(st.nextToken());
            if (start < end) continue;

            // Swap to simplify calculations
            eventList.add(new Event(start, Event.END_TYPE));
            eventList.add(new Event(end, Event.START_TYPE));
        }

        return this;
    }


    public Solution solve() {
        Collections.sort(eventList);

        int count = 0;
        long currentFirst = 0;
        long moveSum = 0;
        for(Event event : eventList) {
            if(event.type == Event.START_TYPE) {
                if(count==0) currentFirst = event.position;
                count++;

                continue;
            }

            count--;
            if (count==0) moveSum += event.position - currentFirst;
        }

        answer = moveSum*2 + M;
        return this;
    }

    public void writeOutput() throws IOException {
        bw.write(Long.toString(answer));
        bw.flush();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution() {
    }

    public static Solution createSolution() {
        return new Solution();
    }

    private static class Event implements Comparable<Event> {
        long position;
        int type;

        static final int START_TYPE = 1;
        static final int END_TYPE = 2;

        public Event(long position, int type) {
            this.position = position;
            this.type = type;
        }

        @Override
        public int compareTo(Event o) {
            if (this.position == o.position) {
                return Integer.compare(this.type, o.type);
            }
            return Long.compare(this.position, o.position);
        }
    }
}

