package _8892;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/8892
 * @title 팰린드롬
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .readInput()
                .testAll()
                .writeOutput();
    }
}

class Solution{

    int T;

    String answer;
    public Solution readInput() throws IOException{
        T = Integer.parseInt(br.readLine());

        return this;
    }


    public Solution testAll() throws IOException {
        StringBuilder sb = new StringBuilder();
        for(int t=0; t<T; t++){
            int k = Integer.parseInt(br.readLine());

            String[] words = new String[k];
            for(int i=0; i<k; i++){
                words[i] = br.readLine();
            }

            sb.append(test(k,words)).append("\n");
        }

        answer = sb.toString();
        return this;
    }

    private String test(int k,String[] words){

        for(int i=0; i<k; i++){
            for(int j=i+1; j<k; j++){
                String tmp = words[i]+words[j];
                if(isPalindrome(tmp)) return tmp;

                tmp = words[j]+words[i];
                if(isPalindrome(tmp)) return tmp;
            }
        }

        return "0";
    }

    public boolean isPalindrome(String word){
        int length = word.length()/2;

        for(int i=0; i<=length; i++){
            if(word.charAt(i) != word.charAt(word.length()-1-i)) return false;
        }

        return true;
    }

    public void writeOutput() throws IOException{
        bw.write(answer);
        bw.flush();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

