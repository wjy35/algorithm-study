package _1275;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1275
 * @title 커피숍2
 * @algorithm SegmentTree
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{
    int N,Q;
    long[] array;
    Query[] queries;

    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        array = new long[N];
        for(int i=0; i<N; i++) array[i] = Long.parseLong(st.nextToken());

        queries = new Query[Q];
        for(int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            queries[i] = new Query(
                    Math.min(x,y),
                    Math.max(x,y),
                    a,
                    b
            );
        }
    }

    private void solve() throws IOException {
        SegmentTree segmentTree = SegmentTree.createSegmentTree(array);

        for(Query query: queries) {
            bw.write(Long.toString(segmentTree.getRangeSum(query.x,query.y)));
            bw.write("\n");
            segmentTree.update(query.a,query.b);
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

        public static SegmentTree createSegmentTree(long[] baseArray){
            int leafNodeSize = 1;
            while(leafNodeSize<baseArray.length) leafNodeSize = leafNodeSize<<1;

            long[] segmentTree = new long[leafNodeSize*2];
            for(int i=0; i<baseArray.length; i++){
                segmentTree[i+leafNodeSize] = baseArray[i];
            }

            for(int i=leafNodeSize+baseArray.length-1; i>1; i--){
                segmentTree[i/2] += segmentTree[i];
            }

            return new SegmentTree(segmentTree, leafNodeSize);
        }

        public void update(int index, long value){
            long diff = this.segmentTree[segmentTree.length-leafNodeSize+index-1]-value;
            segmentTree[segmentTree.length-leafNodeSize+index-1] = value;

            update(index,1,diff,1,leafNodeSize);
        }

        private void update(int targetIndex,int currentIndex, long diff, int start, int end){
            if(targetIndex<start || targetIndex>end) return;
            if(start==end) return;
            this.segmentTree[currentIndex] -= diff;

            update(targetIndex,currentIndex*2,diff,start,(start+end)/2);
            update(targetIndex,currentIndex*2+1,diff,(start+end)/2+1,end);
        }

        public long getRangeSum(int left, int right){
            return getRangeSum(1,left,right,1,leafNodeSize);
        }

        private long getRangeSum(int index,int left, int right, int start, int end){
            if(left>end ||start>right) return 0;
            if(left<=start && end<=right) return segmentTree[index];

            return getRangeSum(index*2,left,right,start,(start+end)/2) + getRangeSum(index*2+1,left,right,(start+end)/2+1,end) ;
        }
    }

    public void start() throws IOException{
        readInput();
        solve();
    }

    private static class Query{
        private final int x,y,a;
        private final long b;

        public Query(int x, int y, int a, long b) {
            this.x = x;
            this.y = y;
            this.a = a;
            this.b = b;
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

