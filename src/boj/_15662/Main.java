package _15662;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/15662
 * @title 톱니바퀴 (2)
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int T;
    Gear[] gears;
    private void readInput() throws IOException{
        T = Integer.parseInt(br.readLine());
        gears = new Gear[T+1];
        for(int i=1; i<=T; i++){
            gears[i] = new Gear(br.readLine().toCharArray());
        }
    }

    int K;
    int count = 0;
    private void query() throws IOException{
        K = Integer.parseInt(br.readLine());

        for(int k=0; k<K; k++){
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            int mainDirection = Integer.parseInt(st.nextToken());

            int[] directions = new int[T+1];
            directions[number] = mainDirection;

            for(int i=number-1; i>0; i--){
                if(gears[i].getRightPole()==gears[i+1].getLeftPole()) break;

                directions[i] = directions[i+1]*-1;
            }

            for(int i=number+1; i<=T; i++){
                if(gears[i-1].getRightPole()==gears[i].getLeftPole()) break;

                directions[i] = directions[i-1]*-1;
            }

            for(int i=1; i<=T; i++){
                if(directions[i]==0) continue;
                else if(directions[i]==1) gears[i].rotateClockwise();
                else gears[i].rotateCounterClockwise();
            }
        }

        for(int i=1; i<=T; i++){
            if(gears[i].isSPole()) count++;
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(count));
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        query();
        writeOutput();
    }

    private static class Gear{
        private Deque<Character> top;
        private Deque<Character> bottom;

        public Gear(char[] arr) {
            this.top = new ArrayDeque<>();
            this.bottom = new ArrayDeque<>();

            for(int i=0; i<2; i++) top.offerLast(arr[i]);
            for(int i=2; i<6; i++) bottom.offerFirst(arr[i]);
            for(int i=7; i>=6; i--) top.offerFirst(arr[i]);
        }

        public int getRightPole(){
            return bottom.peekLast();
        }

        public int getLeftPole(){
            return top.peekFirst();
        }

        public void rotateClockwise(){
            top.offerFirst(bottom.pollFirst());
            bottom.offerLast(top.pollLast());
        }

        public void rotateCounterClockwise(){
            bottom.offerFirst(top.pollFirst());
            top.offerLast(bottom.pollLast());
        }

        public boolean isSPole(){
            top.pollLast();
            return top.peekLast()=='1';
        }
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

