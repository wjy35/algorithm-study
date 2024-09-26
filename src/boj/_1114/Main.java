package _1114;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1114
 * @title 통나무 자르기
 * @algorithm Binary Search
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int L,K,C;
    TreeSet<Integer> positionSet;

    String answer;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        positionSet = new TreeSet<>();
        for(int i=0; i<K; i++){
            positionSet.add(Integer.parseInt(st.nextToken()));
        }
    }

    private void solve(){
        int minLongestLength = 1_000_000_001;

        int l = 1;
        int r =  1_000_000_000;
        while(l<=r){
            int longestLength = (l+r)/2;

            if(canCut(0, 0,longestLength)){
                minLongestLength = Math.min(minLongestLength,longestLength);
                r = longestLength-1;
            }else{
                l = longestLength+1;
            }
        }

        int possibleStartPosition = -1;
        for(Integer position : positionSet){
            if(position>minLongestLength) break;

            if(canCut(position,1,minLongestLength)) {
                possibleStartPosition = position;
                break;
            }
        }

        answer = minLongestLength + " " + possibleStartPosition;
    }

    private boolean canCut(int startPosition,int cutCount,int longestLength){
        int leftPosition = startPosition;
        int rightPostion = L;
        while(cutCount<C){
            Integer cutPosition = positionSet.floor(leftPosition + longestLength);

            if(cutPosition==null) break;
            leftPosition = cutPosition;
            cutCount++;
        }

        return rightPostion-leftPosition <= longestLength;
    }


    private void writeOutput() throws IOException{
        bw.write(answer);
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

