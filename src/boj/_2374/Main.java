package _2374;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2374
 * @title 같은 수로 만들기
 * @algorithm Greedy
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int n;
    long[] A;

    long answer = 0;
    private void readInput() throws IOException{
        n = Integer.parseInt(br.readLine());

        A = new long[n+1];
        for(int i=0; i<n; i++){
            A[i] = Long.parseLong(br.readLine());
            A[n] = Math.max(A[n],A[i]);
        }
    }

    private void solve(){
        Deque<Long> stack = new ArrayDeque<>();


        stack.offer(A[0]);
        for(int i=1; i<=n; i++){
            long min = stack.peekLast();
            if(stack.peekLast()==A[i]) continue;

            if(stack.peekLast()<A[i]) {
                while(!stack.isEmpty() && stack.peekLast()<=A[i]) stack.pollLast();
                answer += A[i]-min;
            }

            stack.offer(A[i]);
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Long.toString(answer));
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

