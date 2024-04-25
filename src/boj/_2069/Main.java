package _2069;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2069
 * @title 보이는 산맥
 * @algorithm Sweep Line
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
    int[] maxEnd;

    final int MAX_X = 32768;
    int answer;
    public Solution readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        maxEnd = new int[MAX_X];

        int start,end;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            maxEnd[start] = Math.max(maxEnd[start],end);
        }

        return this;
    }

    public Solution solve(){
        int sum = 0;
        int nowMaxEnd = 0;
        for(int i=1; i<MAX_X; i++){
            if(maxEnd[i]==0) continue;
            if(maxEnd[i]<=nowMaxEnd) continue;

            sum += getArea(i,maxEnd[i]);
            if(nowMaxEnd>i){
                sum -= getArea(i,nowMaxEnd);
            }

            nowMaxEnd = maxEnd[i];
        }

        answer = sum;
        return this;
    }

    private int getArea(int start,int end){
        int width = end-start;

        return width*width;
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

