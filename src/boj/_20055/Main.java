package _20055;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/20055
 * @title
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,K;
    Container container;

    int step = 0;

    class Container {
        private Deque<Slot> top, bottom;
        private int brokenCount;

        private class Slot {
            int durability;
            boolean existRobot;

            public Slot(int durability) {
                this.durability = durability;
                this.existRobot = false;
            }

            public void putRobot() {
                this.durability--;
                if(durability==0) brokenCount++;

                this.existRobot = true;
            }

            public void removeRobot() {
                this.existRobot = false;
            }

            public boolean isBroken() {
                return this.durability <= 0;
            }
        }

        public Container() {
            this.top = new ArrayDeque<>();
            this.bottom = new ArrayDeque<>();
            this.brokenCount = 0;
        }

        public void createTopSlotByDurability(int durability){
            top.offer(new Slot(durability));
        }

        public void createBottomSlotByDurability(int durability){
            bottom.offerFirst(new Slot(durability));
        }

        public void rotate(){
            this.top.offerFirst(bottom.pollFirst());
            this.bottom.offerLast(top.pollLast());

            dropRobot();
        }

        public void moveRobot(){
            for(int i=0; i<N-1; i++){
                Slot next = top.pollLast();
                Slot now = top.peekLast();
                top.offerFirst(next);

                if(!now.existRobot) continue;
                if(next.isBroken()) continue;
                if(next.existRobot) continue;

                now.removeRobot();
                next.putRobot();
            }
            top.offerFirst(top.pollLast());

            dropRobot();
        }

        public void dropRobot(){
            if(top.peekLast().existRobot) top.peekLast().removeRobot();
        }

        public void raiseRobot(){
            if(!top.peekFirst().isBroken()) {
                top.peekFirst().putRobot();
            }
        }

        public int getBrokenCount() {
            return brokenCount;
        }
    }

    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        container = new Container();
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) container.createTopSlotByDurability(Integer.parseInt(st.nextToken()));
        for(int i=0; i<N; i++) container.createBottomSlotByDurability(Integer.parseInt(st.nextToken()));
    }

    private void solve(){
        do{
            step++;

            container.rotate();
            container.moveRobot();
            container.raiseRobot();
        }while(container.getBrokenCount()<K);
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(step));
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        solve();
        writeOutput();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

