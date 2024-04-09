package _1655;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1655
 * @title 가운데를 말해요
 * @algorithm Data Structure
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .readInput()
                .solveAndWriteOutput();
    }
}

class Solution{

    int N;

    public Solution readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        return this;
    }

    public Solution solveAndWriteOutput() throws IOException{
        MedianCalculator medianCalculator = MedianCalculator.createMedianCalculator();
        for(int i=0; i<N; i++){
            medianCalculator.add(Integer.parseInt(br.readLine()));
            bw.write(medianCalculator.getMedian().toString());
            bw.write("\n");
        }

        bw.flush();
        return this;
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }

    private static class MedianCalculator{
        private PriorityQueue<Integer> maxPq;
        private PriorityQueue<Integer> minPq;


        private MedianCalculator() {
            this.minPq = new PriorityQueue<>();
            this.maxPq = new PriorityQueue<>(Comparator.reverseOrder());
        }

        public void add(Integer value){
            if(maxPq.isEmpty() || maxPq.peek()>=value) maxPq.offer(value);
            else minPq.offer(value);

            if(minPq.size()>maxPq.size()){
                maxPq.offer(minPq.poll());

                return;
            }

            if(maxPq.size()>minPq.size()+1){
                minPq.offer(maxPq.poll());

                return;
            }
        }

        public Integer getMedian(){
            return maxPq.peek();
        }

        static public MedianCalculator createMedianCalculator(){
            return new MedianCalculator();
        }
    }
}

