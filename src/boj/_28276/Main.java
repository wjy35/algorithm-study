package _28276;

import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/28276
 * @title Yawned-Zoned
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{
    int R,C,W;
    boolean[][] isEmpty;

    int[] parents;
    int[] studentCounts;
    int minStudentCountLimit = 1_000_001;
    Queue<Integer> leftUnionedLineRootQueue;
    final int FAIL = 0;
    final int OVER_STUDENT_COUNT_LIMIT = -1;
    final int SUCCESS = 1;
    
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        isEmpty = new boolean[R][C];
        for(int x=0; x<R; x++) {
            int y = 0;
            for(char c : br.readLine().toCharArray()){
                isEmpty[x][y] = c=='0';
                y++;
            }
        }
    }

    private void solve(){
        int l = 0;
        int r = R*C;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (isPossibleIn(mid)) {
                minStudentCountLimit = Math.min(minStudentCountLimit, mid);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
    }

    private boolean isNotIn(int x,int y){
        return x<0 || x>=R || y<0 || y>=C;
    }

    private boolean isPossibleIn(int studentCountLimit){
        int usedPartitionCount = 0;

        int spaceCount = R*C;
        parents = new int[spaceCount];
        studentCounts = new int[spaceCount];
        Arrays.fill(parents,-1);

        int y=0;
        while(y<C && usedPartitionCount<=W){
            boolean isPartitionNotUsed = true;
            leftUnionedLineRootQueue = new ArrayDeque<>();

            int x=0;
            for(; x<R; x++){
                if(isEmpty[x][y]) continue;
                if(studentCountLimit==0) return false;

                if(unionTopAndGetStatus(x,y,studentCountLimit)==OVER_STUDENT_COUNT_LIMIT){
                    usePartition();
                    isPartitionNotUsed = false;
                    break;
                }

                if(unionLeftAndGetStatus(x,y,studentCountLimit)==OVER_STUDENT_COUNT_LIMIT){
                    usePartition();
                    x++;
                    isPartitionNotUsed = false;
                    break;
                }
            }

            if(isPartitionNotUsed){
                y++;
                continue;
            }

            if(usedPartitionCount>=W) {
                return false;
            }

            for(; x<R; x++){
                if(isEmpty[x][y]) continue;

                if(unionTopAndGetStatus(x,y,studentCountLimit)==OVER_STUDENT_COUNT_LIMIT) return false;
            }

            usedPartitionCount++;
            y++;
        }

        return true;
    }

    private int unionTopAndGetStatus(int x, int y, int studentCountLimit){
        /* init Node */
        int current = x*C+y;
        if(studentCounts[current]==0){
            parents[current] = current;
            studentCounts[current] = 1;
        }

        int tx = x-1;
        if(isNotIn(tx,y)) return FAIL;
        if(isEmpty[tx][y]) return FAIL;

        int top = tx*C+y;
        int lineRoot = findLineRoot(top,y);
        int topRoot = find(lineRoot);

        if(studentCounts[topRoot]==studentCountLimit) return OVER_STUDENT_COUNT_LIMIT;

        parents[current] = lineRoot;
        studentCounts[lineRoot]++;
        if(lineRoot != topRoot) studentCounts[topRoot]++;

        return SUCCESS;
    }

    private int unionLeftAndGetStatus(int x, int y, int studentCountLimit){
        int ly = y-1;

        if(isNotIn(x,ly)) return FAIL;
        if(isEmpty[x][ly]) return FAIL;

        int current = x*C+y;
        int lineRoot = findLineRoot(current,y);
        int currentRoot = find(lineRoot);

        int left = x*C+ly;
        int leftRoot = find(left);

        if(currentRoot==leftRoot) return FAIL;

        if(studentCounts[currentRoot]+studentCounts[leftRoot]>studentCountLimit) {
            return OVER_STUDENT_COUNT_LIMIT;
        }

        parents[currentRoot] = leftRoot;
        studentCounts[leftRoot] += studentCounts[currentRoot];
        leftUnionedLineRootQueue.add(lineRoot);

        return SUCCESS;
    }

    private void usePartition(){
        for(Integer top : leftUnionedLineRootQueue) parents[top] = top;
    }

    private int find(int index){
        if(index == parents[index]) return index;

        return parents[index] = find(parents[index]);
    }

    private int findLineRoot(int index, int y){
        if(index == parents[index]) return index;
        if(index%C==y && parents[index]%C!=y) return index;

        return parents[index] = findLineRoot(parents[index],y);
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(minStudentCountLimit));
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