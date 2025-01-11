package _19638;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/19638
 * @title 센티와 마법의 뿅망치
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,H,T;
    PriorityQueue<Integer>  heights;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        heights = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0; i<N; i++){
            heights.offer(Integer.parseInt(br.readLine()));
        }
    }

    int count;
    int maxHeight;
    private void solve(){
        count = 0;

        for(; count<T && heights.peek()>=H && heights.peek()>1; count++){
            heights.offer(heights.poll()/2);
        }
    }

    private void writeOutput() throws IOException{
        if(heights.peek()<H){
            bw.write("YES\n");
            bw.write(Integer.toString(count));
        }else{
            bw.write("NO\n");
            bw.write(Integer.toString(heights.peek()));
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

