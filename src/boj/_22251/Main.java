package _22251;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/22251
 * @title 빌런 석주
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,K,P,X;
    String answer;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
    }

    boolean[][] ledStatus;
    int[][] cost;
    int startMod = 1;

    private void solve(){
        initLed();
        initCost();
        initStartMod();

        int possibilities = -1;
        int[] sourceDigits = toDigits(X);
        for(int n=1; n<=N; n++){
            int[] targetDigits = toDigits(n);

            int costSum = 0;
            for(int i=0; i<K; i++){
                costSum += cost[sourceDigits[i]][targetDigits[i]];
            }

            if(costSum>P) continue;
            possibilities++;
        }

        answer = Integer.toString(possibilities);
    }

    private int[] toDigits(int number){
        int[] digits = new int[K];

        for(int i=0, mod=startMod; i<K; i++,mod/=10){
            digits[i] = number / mod;
            number = number % mod;
        }

        return digits;
    }

    private void initLed(){
        ledStatus = new boolean[10][7];
        ledStatus[0] = new boolean[]{true,true,true,false,true,true,true};
        ledStatus[1] = new boolean[]{false,false,true,false,false,true,false};
        ledStatus[2] = new boolean[]{true,false,true,true,true,false,true};
        ledStatus[3] = new boolean[]{true,false,true,true,false,true,true};
        ledStatus[4] = new boolean[]{false,true,true,true,false,true,false};
        ledStatus[5] = new boolean[]{true,true,false,true,false,true,true};
        ledStatus[6] = new boolean[]{true,true,false,true,true,true,true};
        ledStatus[7] = new boolean[]{true,false,true,false,false,true,false};
        ledStatus[8] = new boolean[]{true,true,true,true,true,true,true};
        ledStatus[9] = new boolean[]{true,true,true,true,false,true,true};
    }

    private void initCost(){
        cost = new int[10][10];
        for(int source=0; source<10; source++){
            for(int target=0; target<10; target++){
                int diffCount = 0;
                for(int ledIndex=0; ledIndex<7; ledIndex++){
                    if(ledStatus[source][ledIndex]== ledStatus[target][ledIndex]) continue;
                    diffCount++;
                }

                cost[source][target] = diffCount;
            }
        }
    }

    private void initStartMod(){
        for(int i=1; i<K; i++) startMod *= 10;
    }

    private void writeOutput() throws IOException{
        bw.write(answer);
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

