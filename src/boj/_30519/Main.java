package _30519;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/30519
 * @title
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    String lighter;
    String smallant;

    final int MOD = 1_000_000_007;
    String answer;
    private void readInput() throws IOException{
        lighter = br.readLine();
        smallant = br.readLine();
    }

    private void solve(){
        /**
         * PP : 0, RP : 1, SP : 2
         * PR : 3, RR : 4, SR : 5
         * PS : 6, RS : 7, SS : 8
         */
        int[] dp = new int[9];

        if(lighter.charAt(0)=='P') dp[0] = 1;
        else if(lighter.charAt(0)=='R') dp[4] = 1;
        else dp[8] = 1;

        int length = smallant.length();
        for(int i=0; i<length; i++){
            char c = smallant.charAt(i);

            int[] tmp = new int[3];
            if(c=='P'){
                tmp[0] = (dp[0]+dp[1])%MOD;
                tmp[1] = ((dp[3]+dp[4])%MOD+dp[5])%MOD;
                tmp[2] = ((dp[6]+dp[7])%MOD+dp[8])%MOD;
                for(int j=0; j<3; j++){
                    dp[j] = (dp[j]+tmp[j])%MOD;
                }
            }else if(c=='R'){
                tmp[0] = ((dp[0]+dp[1])%MOD+dp[2])%MOD;
                tmp[1] = (dp[4]+dp[5])%MOD;
                tmp[2] = ((dp[6]+dp[7])%MOD+dp[8])%MOD;
                for(int j=0; j<3; j++){
                    dp[3+j] = (dp[3+j]+tmp[j])%MOD;
                }
            }else{
                tmp[0] = ((dp[0]+dp[1])%MOD+dp[2])%MOD;
                tmp[1] = ((dp[3]+dp[4])%MOD+dp[5])%MOD;
                tmp[2] = (dp[6]+dp[8])%MOD;
                for(int j=0; j<3; j++){
                    dp[6+j] = (dp[6+j]+tmp[j])%MOD;
                }
            }
        }

        int sum = 0;
        for(int i=0; i<9; i++) sum = (sum + dp[i])%MOD;

        answer = Integer.toString((sum-1)%MOD);
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

