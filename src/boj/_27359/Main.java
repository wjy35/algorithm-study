package _27359;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/27359
 * @title 태양광 충전
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Test.createTest()
                .start();
    }
}

class Test {
    int N,B,C;
    int[] P;
    long[] F;
    int[] D;
    private final long INF = Long.MAX_VALUE;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        P = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            P[i] = Integer.parseInt(st.nextToken());
        }

        F = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            F[i] = Long.parseLong(st.nextToken());
        }

        D = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            D[i] = Integer.parseInt(st.nextToken());
        }
    }

    long answer;
    private void solve(){
        Queue<Integer> q = new ArrayDeque<>();
        long[] currentFees = new long[C+1];
        q.offer(B);
        currentFees[B] = 0;

        for(int i=0; i<N; i++){
            long[] nextFees = new long[C+1];
            Arrays.fill(nextFees,INF);

            int size = q.size();
            for(int j=0; j<size; j++){
                int current = q.poll();
                int next;

                next = Math.min(current+P[i],C);
                if(nextFees[next]==INF) q.offer(next);
                nextFees[next] = Math.min(currentFees[current]+D[i]*F[i], nextFees[next]);

                if(current<D[i]) continue;

                next = current-D[i];
                if(nextFees[next]==INF) q.offer(next);
                nextFees[next] = Math.min(currentFees[current], nextFees[next]);
            }

            currentFees = nextFees;
        }

        answer = INF;
        for(Integer b : q){
            if(b<B) continue;

            answer = Math.min(currentFees[b], answer);
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Long.toString(answer));
        bw.newLine();
    }

    int testCaseCount;
    private void readTestCaseCount() throws IOException{
        testCaseCount = Integer.parseInt(br.readLine());
    }

    public void start() throws IOException{
        readTestCaseCount();

        for(int i=0; i<testCaseCount; i++){
            readInput();
            solve();
            writeOutput();
        }
        bw.flush();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Test(){}
    public static Test createTest(){
        return new Test();
    }
}
