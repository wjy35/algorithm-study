package _1062;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1062
 * @title 가르침
 * @algorithm BitMask, BackTracking
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{
    int n,k;
    int[] wordMasks;

    int maxCount = 0;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        wordMasks = new int[n];
        for(int i=0; i<n; i++){
            String word = br.readLine();

            for(int j=0; j<word.length(); j++){
                wordMasks[i] = wordMasks[i] | (1<<(word.charAt(j)-'a'));
            }
        }
    }

    private void solve(){
        if(k==26){
            maxCount = n;
            return;
        }
        if(k<5){
            maxCount = 0;
            return;
        }

        int status = 0;
        status = status | (1<<('a'-'a'));
        status = status | (1<<('n'-'a'));
        status = status | (1<<('t'-'a'));
        status = status | (1<<('c'-'a'));
        status = status | (1<<('i'-'a'));

        bf(5,1,status);
    }

    private void bf(int depth,int shiftCount,int status){
        if(depth==k){
            int tmpCount = 0;
            for(int i=0; i<n; i++){
                if(status<(status|wordMasks[i])) continue;

                tmpCount++;
            }
            maxCount = Math.max(tmpCount, maxCount);

            return;
        }

        if(shiftCount==26) return;

        int bit = 1<<shiftCount;
        if((bit&status) == 0) {
            bf(depth+1,shiftCount+1,status | bit);
        }

        bf(depth,shiftCount+1,status);
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(maxCount));
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

