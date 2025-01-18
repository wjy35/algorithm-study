package _15820;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/15820
 * @title 맞았는데 왜 틀리죠?
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int totalSampleCount, totalSystemCount;
    int acceptedSystemCount;
    private void solve() throws IOException {
        st = new StringTokenizer(br.readLine());
        totalSampleCount = Integer.parseInt(st.nextToken());
        totalSystemCount = Integer.parseInt(st.nextToken());


        int acceptedSampleCount = 0;
        for(int i=0; i<totalSampleCount; i++){
            st = new StringTokenizer(br.readLine());
            int expected = Integer.parseInt(st.nextToken());
            int actual = Integer.parseInt(st.nextToken());

            if(expected==actual) acceptedSampleCount++;
        }

        int acceptedSystemCount = 0;
        for(int i=0; i<totalSystemCount; i++){
            st = new StringTokenizer(br.readLine());
            int expected = Integer.parseInt(st.nextToken());
            int actual = Integer.parseInt(st.nextToken());

            if(expected==actual) acceptedSystemCount++;
        }

        if(acceptedSampleCount+acceptedSystemCount == totalSampleCount+totalSystemCount) bw.write("Accepted\n");
        else if(acceptedSampleCount==totalSampleCount) bw.write("Why Wrong!!!");
        else bw.write("Wrong Answer");

        bw.flush();
    }

    public void start() throws IOException{
        solve();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

