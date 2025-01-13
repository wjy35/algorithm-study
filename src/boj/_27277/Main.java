package _27277;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/27277
 * @title 장기자랑
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
        int[] a;
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        a = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
    }

    int sum;
    private void solve(){
        sum = 0;

        Arrays.sort(a);

        for(int l=0,r=a.length-1; l<r; l++,r--){
            sum += a[r]-a[l];
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(sum));
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

