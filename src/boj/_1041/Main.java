package _1041;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1041
 * @title 주사위
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    long N;
    int[] dice;
    private void readInput() throws IOException{
        N = Long.parseLong(br.readLine());

        dice = new int[6];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<6; i++){
            dice[i] = Integer.parseInt(st.nextToken());
        }
    }

    BigInteger minSum;
    private void solve(){
        if(N==1){
            int sum = 0;
            int max = 0;
            for(int i=0; i<6; i++){
                sum += dice[i];
                max = Math.max(max,dice[i]);
            }

            minSum = BigInteger.valueOf(sum-max);

            return;
        }

        int oneSideMin = 51;
        for(int i=0; i<6; i++){
            oneSideMin = Math.min(oneSideMin,dice[i]);
        }

        int twoSideMin = 101;
        for(int i=0; i<6; i++){
            for(int j=i+1; j<6; j++){
                if(isOpposite(i,j)) continue;

                twoSideMin = Math.min(twoSideMin,dice[i]+dice[j]);
            }
        }

        int threeSideMin = 151;
        for(int i=0; i<6; i++){
            for(int j=i+1; j<6; j++){
                for(int k=j+1; k<6; k++){
                    if(isOpposite(i,j)) continue;
                    if(isOpposite(i,k)) continue;
                    if(isOpposite(j,k)) continue;

                    threeSideMin = Math.min(threeSideMin,dice[i]+dice[j]+dice[k]);
                }
            }
        }

        BigInteger oneSideSum = BigInteger.valueOf(5*N-6)
                .multiply(BigInteger.valueOf(N-2))
                .multiply(BigInteger.valueOf(oneSideMin));

        BigInteger twoSideSum = BigInteger.valueOf(8*N-12)
                .multiply(BigInteger.valueOf(twoSideMin));

        BigInteger threeSideSum = BigInteger.valueOf(threeSideMin*4);

        minSum = oneSideSum.add(twoSideSum).add(threeSideSum);
    }

    boolean isOpposite(int i,int j){
        if(i==1 && j==4) return true;
        if(i==2 && j==3) return true;
        if(i==0 && j==5) return true;

        return false;
    }

    private void writeOutput() throws IOException{
        bw.write(minSum.toString());
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

