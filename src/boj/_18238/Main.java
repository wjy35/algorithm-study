package _18238;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/18238
 * @title ZOAC 2
 * @algorithm 
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    char[] characters;

    int costSum = 0;
    private void readInput() throws IOException{
        characters = br.readLine().toCharArray();
    }

    private void solve(){
        char current = 'A';
        for(char character : characters){
            int cost = Math.abs(character-current);
            costSum += Math.min(cost,26-cost);

            current = character;
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(costSum));
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

