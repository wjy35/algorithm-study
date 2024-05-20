package _15922;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/15922
 * @title 아우으 우아으이야!!
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

class Solution{

    int N;
    List<Event> eventList;

    int answer;
    public Solution readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        eventList = new ArrayList<>(2*N);
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            eventList.add(Event.createStartEventAt(Integer.parseInt(st.nextToken())));
            eventList.add(Event.createEndEventAt(Integer.parseInt(st.nextToken())));
        }

        return this;
    }

    public Solution solve(){
        Collections.sort(eventList);

        int count = 0;
        int current = -1;
        int lineSum = 0;
        for(Event event : eventList){
            if(event.isStart()){
                if(count==0){
                    current = event.position;
                }
                count++;
                continue;
            }

            count--;
            if(count==0){
                lineSum += event.position-current;
            }
        }

        answer = lineSum;

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

    public static class Event implements Comparable<Event>{
        int position;
        int type;

        private Event(int position, int type) {
            this.position = position;
            this.type = type;
        }

        public static Event createStartEventAt(int position){
            return new Event(position,0);
        }

        public static Event createEndEventAt(int position){
            return new Event(position,1);
        }

        @Override
        public int compareTo(Event o) {
            if(this.position==o.position){
                return Integer.compare(this.type,o.type);
            }

            return Integer.compare(this.position,o.position);
        }

        public boolean isStart(){
            return this.type==0;
        }
    }
}

