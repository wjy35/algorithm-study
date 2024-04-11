package _17611;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/17611
 * @title
 * @algorithm 
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
    List<int[]> pointList;

    public Solution readInput() throws IOException{
        n = Integer.parseInt(br.readLine());

        pointList = new ArrayList<>(n+1);
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            pointList.add(new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
        }
        pointList.add(pointList.get(0));

        return this;
    }

    int answer;
    public Solution solve(){
        List<Event> xCoordinateEvent = new ArrayList<>(n);
        List<Event> yCoordinateEvent = new ArrayList<>(n);


        int eventSize = pointList.size()-1;
        int[] now,next;
        for(int i=0; i<eventSize; i++){
            now = pointList.get(i);
            next = pointList.get(i+1);

            if(now[0]==next[0]) {
                xCoordinateEvent.add(Event.createStartEvent(Math.min(now[1],next[1])));
                xCoordinateEvent.add(Event.createEndEvent(Math.max(now[1],next[1])));
            } else {
                yCoordinateEvent.add(Event.createStartEvent(Math.min(now[0],next[0])));
                yCoordinateEvent.add(Event.createEndEvent(Math.max(now[0],next[0])));
            }
        }

        answer = Math.max(getMaxCount(xCoordinateEvent),getMaxCount(yCoordinateEvent));
        return this;
    }

    public int getMaxCount(List<Event> eventList){
        Collections.sort(eventList);

        int maxCount = 0;
        int count = 0;
        int eventSize = eventList.size();
        for(int i=0; i<eventSize; i++){
            if(eventList.get(i).isStart()) {
                count++;
                maxCount = Math.max(maxCount, count);
            }
            else {
                count--;
            }
        }

        return maxCount;
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

    private static class Event implements Comparable<Event>{
        int position;
        int type;

        private static final int START = 1;
        private static final int END = -1;

        private Event(int position, int type) {
            this.position = position;
            this.type = type;
        }

        public static Event createStartEvent(int position){
            return new Event(position, Event.START);
        }

        public static Event createEndEvent(int position){
            return new Event(position, Event.END);
        }

        public boolean isStart(){
            return type == Event.START;
        }

        @Override
        public int compareTo(Event o) {
            if(this.position==o.position){
                return Integer.compare(this.type,o.type);
            }
            
            return Integer.compare(this.position,o.position);
        }
    }
}

