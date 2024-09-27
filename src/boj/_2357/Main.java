package _2357;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2357
 * @title
 * @algorithm 
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,M;
    int[] numbers;
    List<Query> queryList;
    final int INF =  1_000_000_001;

    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N];
        for(int i=0; i<N; i++){
            numbers[i] = Integer.parseInt(br.readLine());
        }

        queryList = new ArrayList<>();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            queryList.add(new Query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }

    private void solve() throws IOException {
        MinSegmentTree minSegmentTree = new MinSegmentTree(numbers);
        MaxSegmentTree maxSegmentTree = new MaxSegmentTree(numbers);

        for(Query query: queryList){
            bw.write(Integer.toString(minSegmentTree.getMinInRange(query.start,query.end)));
            bw.write(" ");
            bw.write(Integer.toString(maxSegmentTree.getMaxInRange(query.start,query.end)));
            bw.write("\n");
        }
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        solve();
    }

    private class Query{
        int start, end;

        public Query(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static class MinSegmentTree{
        static final int INF = 1_000_000_001;
        int[] segmentTree;
        int rangePrefix;

        public MinSegmentTree(int[] baseArray) {
            this.rangePrefix = 1;
            while(rangePrefix< baseArray.length) rangePrefix = rangePrefix<<1;

            this.segmentTree = new int[rangePrefix*2];
            Arrays.fill(this.segmentTree, INF);

            for(int i=0; i<baseArray.length; i++) segmentTree[rangePrefix+i] = baseArray[i];

            for(int i=rangePrefix+baseArray.length-1; i>0; i--){
                segmentTree[i/2] = Math.min(segmentTree[i/2],segmentTree[i]);
            }
        }

        public int getMinInRange(int start, int end){
            int min = INF;

            start = start+rangePrefix-1;
            end = end+rangePrefix-1;
            while(start<=end){
                if((start+1)%2==0) min = Math.min(min,segmentTree[start]);
                if((end-1)%2==1) min = Math.min(min,segmentTree[end]);

                start = (start+1)/2;
                end = (end-1)/2;
            }

            return min;
        }
    }
    private static class MaxSegmentTree{
        int[] segmentTree;
        int rangePrefix;

        public MaxSegmentTree(int[] baseArray) {
            this.rangePrefix = 1;
            while(rangePrefix< baseArray.length) rangePrefix = rangePrefix<<1;

            this.segmentTree = new int[rangePrefix*2];

            for(int i=0; i<baseArray.length; i++) segmentTree[rangePrefix+i] = baseArray[i];

            for(int i=rangePrefix+baseArray.length-1; i>0; i--){
                segmentTree[i/2] = Math.max(segmentTree[i/2],segmentTree[i]);
            }
        }

        public int getMaxInRange(int start, int end){
            int max = 0;

            start = start+rangePrefix-1;
            end = end+rangePrefix-1;
            while(start<=end){
                if((start+1)%2==0) max = Math.max(max,segmentTree[start]);
                if((end-1)%2==1) max = Math.max(max,segmentTree[end]);

                start = (start+1)/2;
                end = (end-1)/2;
            }

            return max;
        }
    }


    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

