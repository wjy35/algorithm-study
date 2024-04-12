package _2437;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2437
 * @title 저울
 * @algorithm PrefixSum, Sort
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
    int[] A;

    int answer;

    public Solution readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        A = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        return this;
    }

    public Solution solve(){
        Arrays.sort(A);

        int[] prefixSum = new int[N+1];
        for(int i=1; i<=N; i++){
            prefixSum[i] = prefixSum[i-1]+A[i];
        }


        for(int i=1; i<=N; i++){
            if(prefixSum[i-1]+1>=A[i]) continue;

            answer = prefixSum[i-1]+1;
            return this;
        }

        answer = prefixSum[N]+1;
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
}

