package _5637;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/5637
 * @title 가장 긴 단어
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{
    String maxLengthWord = "";

    private void readInputAndSolve() throws IOException{

        while(true){
            String input = br.readLine();

            if (input==null) return;

            String[] words = input.split("[^a-zA-Z-]");
            for(String word : words) if(maxLengthWord.length()<word.length()) maxLengthWord = word;
        }
    }

    private void writeOutput() throws IOException{
        bw.write(maxLengthWord.toLowerCase());
        bw.flush();
    }

    public void start() throws IOException{
        readInputAndSolve();
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

