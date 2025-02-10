package _20944;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/20944
 * @title 팰린드롬 척화비
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .solve();
    }
}

class Solution{

    int N;
    public void solve() throws IOException{
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            bw.write("a");
        }
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

