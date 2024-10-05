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

    int min = 1_000_001;

    final int IS_EMPTY_TARGET = 0;
    final int IS_OVER_STUDENT_COUNT_LIMIT = -1;
    final int UNION_SUCCESS = 1;
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
                min = Math.min(min, mid);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
    }

    private int[] dx = {-1,0};
    private int[] dy = {0,-1};
    private boolean isNotIn(int x,int y){
        return x<0 || x>=R || y<0 || y>=C;
    }

    private boolean isPossibleIn(int studentCountLimit){
        int partitionCount = 0;

        int total = R*C;
        parents = new int[total];
        Arrays.fill(parents,-1);
        studentCounts = new int[total];

        int y=0;
        while(y<C && partitionCount<=W){
            boolean isNotRequiredPartition = true;

            Queue<Integer> undoQueue = new ArrayDeque<>();
            int x=0;
            for(; x<R; x++){
                if(isEmpty[x][y]) continue;
                if(studentCountLimit==0) return false;

                int unionStatus = unionTopAndGetStatus(x,y,studentCountLimit);
                if(unionStatus==IS_OVER_STUDENT_COUNT_LIMIT){
                    for(Integer top : undoQueue) parents[top] = top;

                    isNotRequiredPartition = false;
                    break;
                }

                int lx = x;
                int ly = y-1;

                if(isNotIn(lx,ly)) continue;
                if(isEmpty[lx][ly]) continue;

                int current = x*C+y;
                int lineRoot = find(current,y);
                int currentRoot = find(lineRoot);

                int left = lx*C+ly;
                int leftRoot = find(left);

                if(currentRoot==leftRoot) continue;

                if(studentCounts[currentRoot]+studentCounts[leftRoot]>studentCountLimit) {
                    for(Integer top : undoQueue) parents[top] = top;

                    x++;
                    isNotRequiredPartition = false;
                    break;
                }else{
                    parents[currentRoot] = leftRoot;
                    studentCounts[leftRoot] += studentCounts[currentRoot];

                    undoQueue.add(lineRoot);
                }

            }

            if(isNotRequiredPartition){
                y++;
                continue;
            }

            if(partitionCount>=W) {
                return false;
            }

            for(; x<R; x++){
                if(isEmpty[x][y]) continue;

                int unionStatus = unionTopAndGetStatus(x,y,studentCountLimit);
                if(unionStatus==IS_OVER_STUDENT_COUNT_LIMIT){
                    return false;
                }
            }

            partitionCount++;
            y++;
        }

        return true;
    }

    private int unionTopAndGetStatus(int x, int y, int studentCountLimit){
        int current = x*C+y;
        if(studentCounts[current]==0){
            parents[current] = current;
            studentCounts[current] = 1;
        }

        int tx = x-1;
        int ty = y;
        if(isNotIn(tx,ty)) return IS_EMPTY_TARGET;
        if(isEmpty[tx][ty]) return IS_EMPTY_TARGET;

        int top = tx*C+ty;
        int lineRoot = find(top,y);
        int topRoot = find(lineRoot);

        if(studentCounts[topRoot]==studentCountLimit) return IS_OVER_STUDENT_COUNT_LIMIT;

        parents[current] = lineRoot;
        studentCounts[lineRoot]++;
        if(lineRoot != topRoot) studentCounts[topRoot]++;

        return 1;
    }


    private int find(int index){
        if(index == parents[index]) return index;

        return parents[index] = find(parents[index]);
    }

    private int find(int index, int y){
        if(index == parents[index]) return index;
        if(index%C==y && parents[index]%C!=y) return index;

        return parents[index] = find(parents[index],y);
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(min));
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