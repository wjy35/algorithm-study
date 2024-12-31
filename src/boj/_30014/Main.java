package _30014;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/30014
 * @title 준영이의 사랑
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N;
    int[] P;

    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        P = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            P[i] = Integer.parseInt(st.nextToken());
        }
    }

    int price;
    Deque<Integer> order;
    private void solve(){
        Arrays.sort(P);

        price = 0;
        order = new ArrayDeque<>();
        order.offer(P[0]);
        for(int i=1; i+1<N; i+=2){
            price += P[i] * order.peekFirst();
            order.offerFirst(P[i]);

            price += P[i+1] * order.peekLast();
            order.offerLast(P[i+1]);
        }
        if(N%2==0) {
            price += P[N-1] * order.peekFirst();
            order.offerFirst(P[N-1]);
        }
        price += order.peekFirst() * order.peekLast();
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(price));
        bw.write("\n");

        for(Integer A : order){
            bw.write(Integer.toString(A));
            bw.write(" ");
        }
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

