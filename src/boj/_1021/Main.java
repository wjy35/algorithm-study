package _1021;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1021
 * @title 회전하는 큐
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,M;
    int[] numbers;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        numbers = new int[M];
        for(int i=0; i<M; i++) numbers[i] = Integer.parseInt(st.nextToken());
    }

    int moveSum = 0;
    private void solve(){
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i=1; i<=N; i++) dq.offer(i);

        for(int number : numbers){
            int move = 0;
            for(Integer element : dq){
                if(element==number) break;

                move++;
            }

            if(move<=dq.size()/2){
                moveSum += move;
                for(int i=0; i<move; i++) dq.offer(dq.pollFirst());
            }else{
                move = dq.size()-move;
                moveSum += move;

                for(int i=0; i<move; i++) dq.offerFirst(dq.pollLast());
            }

            dq.pollFirst();
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(moveSum));
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

