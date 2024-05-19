package _9012;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/9012
 * @title 괄호
 * @algorithm Stack
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

    int N;
    String[] strings;
    boolean[] isVps;

    public Solution readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        strings = new String[N];
        for(int i=0; i<N; i++){
            strings[i] = br.readLine();
        }

        return this;
    }

    public Solution solve(){
        return this;
    }

    private boolean isVps(String string){
        Deque<Integer> q = new ArrayDeque<>();

        for(int i=0; i<string.length(); i++){
            if(string.charAt(i)=='('){
                q.offer(i);

                continue;
            }

            if(q.isEmpty()) return false;
            q.pollLast();
        }

        if(q.size()>0) return false;

        return true;
    }

    public void writeOutput() throws IOException{
        for(int i=0; i<N; i++){
            if(isVps(strings[i])) bw.write("YES\n");
            else bw.write("NO\n");
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

