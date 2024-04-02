package _2696;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2696
 * @title 중앙값 구하기
 * @algorithm Data Structure
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .readInput()
                .testAll()
                .writeOutput();
    }
}

class Solution{

    int T;
    int M;
    int[] a;

    public Solution readInput() throws IOException{
        T = Integer.parseInt(br.readLine());

        return this;
    }

    public Solution testAll() throws IOException{
        for(int i=0; i<T; i++) test();
        return this;
    }

    private void test() throws IOException{
        M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        List<Integer> answer = new ArrayList<>(M/2+1);

        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Comparator.reverseOrder());

        maxPq.offer(Integer.parseInt(st.nextToken()));
        answer.add(maxPq.peek());

        int value;
        for(int i=1; i<M; i++){
            if(i%10==0) st = new StringTokenizer(br.readLine());

            value = Integer.parseInt(st.nextToken());
            if(maxPq.peek()>=value) maxPq.offer(value);
            else minPq.offer(value);

            if(minPq.size()> maxPq.size()) maxPq.offer(minPq.poll());
            if(minPq.size()+1< maxPq.size()) minPq.offer(maxPq.poll());

            if (i%2==0)answer.add(maxPq.peek());
        }

        bw.write(Integer.toString(answer.size()));
        bw.write("\n");
        for(int i=0; i<answer.size(); i++){
            bw.write(Integer.toString(answer.get(i)));
            bw.write(" ");
            if(i%10==9) bw.write("\n");
        }

        if(answer.size()%10!=0) bw.write("\n");
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

