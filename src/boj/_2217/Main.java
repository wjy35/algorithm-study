package _2217;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2217
 * @title 로프
 * @algorithm Math
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
    int[] weight;

    int answer;

    public Solution readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        weight = new int[N];
        for(int i=0; i<N; i++){
            weight[i] = Integer.parseInt(br.readLine());
        }

        return this;
    }

    public Solution solve(){
        Arrays.sort(weight);

        int maxWeight = 0;
        for(int i=0; i<N; i++){
            maxWeight = Math.max(maxWeight,weight[i]*(N-i));
        }

        answer = maxWeight;
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

