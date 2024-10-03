package _2320;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2320
 * @title 끝말잇기
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
    String[] words;
    HashMap<Character,Integer> charToIndex = new HashMap<>();

    int[][] dp;
    int ALL_VISITED_MASK;
    int maxScore = 0;
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        words = new String[N];
        for(int i=0; i<N; i++) words[i] = br.readLine();
    }

    private void solve(){
        initCharToIndex();
        ALL_VISITED_MASK = (1<<N)-1;
        dp = new int[ALL_VISITED_MASK<<1][5];

        for(int i=0; i<N; i++){
            dfs(1<<i,getEndChar(words[i]),words[i].length());
        }
    }

    private void initCharToIndex(){
        charToIndex = new HashMap<>();
        charToIndex.put('A',0);
        charToIndex.put('E',1);
        charToIndex.put('I',2);
        charToIndex.put('O',3);
        charToIndex.put('U',4);
    }

    private void dfs(int visitedMask, char endChar, int score){
        if(visitedMask==ALL_VISITED_MASK) return;

        for(int i=0; i<N; i++){
            if(((1<<i)&visitedMask)>0) continue;
            if(words[i].charAt(0)!=endChar) continue;

            int currentVisitedMask = ((1<<i)|visitedMask);
            int currentScore = score + words[i].length();
            char currentEndChar = getEndChar(words[i]);

            if(dp[currentVisitedMask][charToIndex.get(currentEndChar)]!=0) continue;
            dp[currentVisitedMask][charToIndex.get(currentEndChar)] = currentScore;

            maxScore = Math.max(maxScore, currentScore);
            dfs(currentVisitedMask, currentEndChar, currentScore);
        }
    }

    private char getEndChar(String word){
        return word.charAt(word.length()-1);
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(maxScore));
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

