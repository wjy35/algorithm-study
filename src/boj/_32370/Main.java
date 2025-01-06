package _32370;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/
 * @title Rook
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int a,b;
    int x,y;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
    }

    int count = 2;
    private void solve(){
        if(a==0){
            if(x==0 && y<b) count = 3;
            else count = 1;
        }

        if(b==0){
            if(y==0 && x<a) count = 3;
            else count = 1;
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(count));
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

