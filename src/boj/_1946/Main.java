package _1946;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @link https://www.acmicpc.net/problem/
 * @title 신입 사원
 * @algorithm Greedy, Sort
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

    int T;
    int N;
    int[] scores;

    public Solution readInput() throws IOException{
        T = Integer.parseInt(br.readLine());
        return this;
    }


    public Solution solve() throws IOException{
        for(int t=0; t<T; t++){
            N = Integer.parseInt(br.readLine());

            scores = new int[N+1];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                scores[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
            }

            int answer = N;
            int min = scores[1];
            for(int i=2; i<=N; i++){
                if(min>scores[i]){
                    min = scores[i];
                    continue;
                }

                answer--;
            }

            bw.write(Integer.toString(answer));
            bw.write("\n");
        }

        return this;
    }

    public void writeOutput() throws IOException{
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


