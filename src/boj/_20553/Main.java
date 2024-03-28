package _20553;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/20553
 * @title 다오와 디지니의 데이트
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

    int N,T;
    long[] map;

    long[] prefixSum;

    public Solution readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new long[N+1];
        prefixSum = new long[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            map[i] = Long.parseLong(st.nextToken());
            prefixSum[i] = prefixSum[i-1]+map[i];
        }

        return this;
    }

    long answer;

    public Solution solve(){
        long maxSection = 0;
        long maxHappySum = 0;

        int sectionCount = T/2-1;
        int index = 2;

        while(index<=N && sectionCount>=0){
            maxSection = Math.max(maxSection,map[index]+map[index-1]);
            maxHappySum = Math.max(maxHappySum,prefixSum[index]+prefixSum[index-1]+sectionCount*maxSection);
            sectionCount--;
            index++;
        }

        answer = maxHappySum;

        return this;
    }

    public void writeOutput() throws IOException{
        bw.write(Long.toString(answer));
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

