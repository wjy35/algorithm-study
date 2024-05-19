package _28359;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/28359
 * @title 수열의 가치 
 * @algorithm Greedy
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

    int[] count;

    int answer;
    public Solution readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        A = new int[N];
        count = new int[1001];

        for(int i=0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
            count[A[i]]++;
        }

        return this;
    }


    public Solution solve(){
        int max = 0;
        int sum = 0;
        int tmp;
        for(int i=1; i<=1000; i++){
            tmp = i*count[i];
            sum += tmp;

            if(tmp>max){
                max = tmp;
            }
        }

        answer = sum+max;
        Arrays.sort(A);

        return this;
    }

    public void writeOutput() throws IOException{
        bw.write(Integer.toString(answer));
        bw.write("\n");
        for(int i=0; i<N; i++) {
            bw.write(Integer.toString(A[i]));
            bw.write(" ");
        }

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

