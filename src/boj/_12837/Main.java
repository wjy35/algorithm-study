package _12837;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/12837
 * @title 가계부 (Hard)
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{
    public void start() throws IOException{
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        SegmentTree segmentTree = SegmentTree.createBy(new long[N]);

        for(int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());

            int type = Integer.parseInt(st.nextToken());

            if(type==1){
                int p = Integer.parseInt(st.nextToken());
                long x = Long.parseLong(st.nextToken());
                segmentTree.add(p,x);
            }else{
                int p = Integer.parseInt(st.nextToken());
                int q = Integer.parseInt(st.nextToken());
                bw.write(Long.toString(segmentTree.getSumInRange(p,q)));
                bw.write("\n");
            }
        }
        bw.flush();
    }

    private static class SegmentTree{
        long[] segmentTree;
        int leafNodeSize;

        private SegmentTree(long[] segmentTree, int leafNodeSize) {
            this.segmentTree = segmentTree;
            this.leafNodeSize = leafNodeSize;
        }

        public static SegmentTree createBy(long[] baseArray){
            int leafNodeSize = 1;
            while(leafNodeSize<baseArray.length) leafNodeSize = leafNodeSize<<1;

            long[] segmentTree = new long[leafNodeSize*2];
            for(int i=0; i<baseArray.length; i++) {
                segmentTree[leafNodeSize+i] = baseArray[i];
            }

            for(int i=leafNodeSize+baseArray.length-1; i>1; i--){
                segmentTree[i/2] += segmentTree[i];
            }

            return new SegmentTree(segmentTree, leafNodeSize);
        }


        public long getSumInRange(int start, int end){
            long sum = 0;
            start = toSegmentTreeIndex(start);
            end = toSegmentTreeIndex(end);

            while(start<=end){
                if((start+1)%2==0) sum += segmentTree[start];
                if((end-1)%2==1) sum += segmentTree[end];

                start = (start+1)/2;
                end = (end-1)/2;
            }

            return sum;
        }

        public void add(int index,long value){
            index = toSegmentTreeIndex(index);

            for(; index>0; index/=2){
                segmentTree[index] += value;
            }
        }

        private int toSegmentTreeIndex(int index){
            return index+leafNodeSize-1;
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