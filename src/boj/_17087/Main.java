package _17087;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/17087
 * @title 숨바꼭질 6
 * @algorithm GCD
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .readInput()
                .solve()
                .writeOutput();
    }
}

class Solution{

    int N,S;
    int[] d;

    int answer;
    public Solution readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        d = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            d[i] = Math.abs(Integer.parseInt(st.nextToken())-S);
        }

        return this;
    }

    public Solution solve(){
        int gcd = d[0];

        for(int i=1; i<N; i++){
            gcd = getGcd(gcd,d[i]);
        }

        answer = gcd;
        return this;
    }

    public int getGcd(int a,int b){
        int q = Math.max(a,b);
        int p = Math.min(a,b);

        int r = -1;
        while(r!=0){
            r = q%p;
            q = p;
            p = r;
        }

        return q;
    }

    public void writeOutput() throws IOException{
        bw.write(Integer.toString(answer));
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

