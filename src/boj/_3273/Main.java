package _3273;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/3273
 * @title 두 수의 합
 * @algorithm 
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{
    int n;
    int[] a;
    int x;

    int count = 0;
    private void readInput() throws IOException{
        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        a = new int[n];
        for(int i=0; i<n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        x = Integer.parseInt(br.readLine());
    }

    private void solve(){
        Arrays.sort(a);

        int l=0;
        int r=n-1;

        while(l<r){
            if(a[l]+a[r] == x){
                l++;
                r--;
                count++;
            }else if(a[l]+a[r] < x){
                l++;
            }else{
                r--;
            }
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

