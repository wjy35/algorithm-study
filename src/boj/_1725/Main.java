package _1725;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1725
 * @title 히스토그램
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N;
    long[] height;

    SegmentTree segmentTree;
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        height = new long[N];
        for(int i=0; i<N; i++){
            height[i] = Long.parseLong(br.readLine());
        }
    }

    private void solve(){
        segmentTree = SegmentTree.createBy(height);

        System.out.println(getMaxAreaInRange(1,N));
    }

    private long getMaxAreaInRange(int start, int end){
        if(end<start) return 0;
        long width = end-start + 1;
        Node minNode = segmentTree.getMinNodeInRange(start,end);

        long area = width * minNode.value;
        int centerIndex = minNode.index;

        area = Math.max(area,getMaxAreaInRange(start,centerIndex-1));
        area = Math.max(area,getMaxAreaInRange(centerIndex+1,end));

        return area;
    }

    private void writeOutput() throws IOException{
        bw.flush();
    }

    private static class SegmentTree{
        Node[] segmentTree;
        int leafNodeCount;

        static final int INF = 1_000_000_001;

        public SegmentTree(Node[] segmentTree, int leafNodeCount) {
            this.segmentTree = segmentTree;
            this.leafNodeCount = leafNodeCount;
        }

        public static SegmentTree createBy(long[] baseArray){
            int leafNodeCount = 1;
            while(leafNodeCount< baseArray.length) leafNodeCount = leafNodeCount<<1;

            Node[] segmentTree = new Node[leafNodeCount*2];
            for(int i=1; i<segmentTree.length; i++){
                segmentTree[i] = new Node(-1,INF);
            }

            for(int i=0; i<baseArray.length; i++){
                segmentTree[leafNodeCount+i].value = baseArray[i];
                segmentTree[leafNodeCount+i].index = i+1;
            }

            for(int i=leafNodeCount+baseArray.length-1; i>1; i--){
                if(segmentTree[i/2].value>segmentTree[i].value){
                    segmentTree[i/2].value = segmentTree[i].value;
                    segmentTree[i/2].index = segmentTree[i].index;
                }
            }

            return new SegmentTree(segmentTree,leafNodeCount);
        }

        public Node getMinNodeInRange(int start, int end){
            start = toSegmentTreeIndex(start);
            end = toSegmentTreeIndex(end);

            Node minNode = new Node(-1,INF);
            while(start<=end){
                if((start+1)%2==0 && segmentTree[start].value<minNode.value){
                    minNode.value = segmentTree[start].value;
                    minNode.index = segmentTree[start].index;
                }

                if((end-1)%2==1 && segmentTree[end].value<minNode.value) {
                    minNode.value = segmentTree[end].value;
                    minNode.index = segmentTree[end].index;
                }

                start = (start+1)/2;
                end = (end-1)/2;
            }

            return minNode;
        }

        public int toSegmentTreeIndex(int index){
            return index+leafNodeCount-1;
        }
    }

    private static class Node{
        int index;
        long value;

        public Node(int index, long value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", value=" + value +
                    '}';
        }
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

