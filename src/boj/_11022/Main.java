package _11022;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/11022
 * @title 
 * @algorithm 
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .solve();
    }
}

class Solution{

    String answer;

    public void solve() throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int testcase=1; testcase<=T; testcase++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            bw.write(String.format("Case #%d: %d + %d = %d\n",testcase,A,B,(A+B)));
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

