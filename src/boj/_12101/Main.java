package _12101;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/12101
 * @title 1, 2, 3 더하기 2
 * @algorithm BackTracking
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int n,k;

    int order = 0;
    String expression = "-1";
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
    }

    private void solve(){
        bf(0,0);
    }

    private void bf(int sum,long numbers){
        if(sum==n){
            order++;
            if(order==k){
                StringBuilder sb = new StringBuilder();
                String strNumbers = Long.toString(numbers);

                for(int i=0; i<strNumbers.length(); i++){
                    sb.append("+").append(strNumbers.charAt(i));
                }
                sb.deleteCharAt(0);

                expression=sb.toString();
            }

            return;
        }

        for(int i=1; i<=3; i++){
            if(sum+i>n) break;

            bf(sum+i,numbers*10+i);
        }

    }
    private void writeOutput() throws IOException{

        bw.write(expression);
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

