package _32387;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/32387
 * @title 충전하기
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,Q;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
    }

    int[] time;
    private void solve() throws IOException{
        time = new int[N+1];
        TreeSet<Integer> set = new TreeSet<>();
        for(int i=1; i<=N; i++) set.add(i);

        for(int i=1; i<=Q; i++){
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            int number = Integer.parseInt(st.nextToken());

            if("1".equals(type)){
                Integer result = set.ceiling(number);

                if(result==null){
                    bw.write("-1\n");
                    continue;
                }

                bw.write(Integer.toString(result));
                bw.write("\n");
                set.remove(result);
                time[result] = i;
            }else{
                if(set.contains(number)){
                    bw.write("-1\n");
                    continue;
                }

                set.add(number);
                bw.write(Integer.toString(time[number]));
                bw.write("\n");
            }

        }
    }

    private void writeOutput() throws IOException{
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

