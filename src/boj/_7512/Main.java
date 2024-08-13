package _7512;

import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/7512
 * @title 연속하는 소수의 합
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .readInputAndTest();
    }
}

class Solution{

    int T;
    boolean[] isPrimeNumber;
    int[] primeNumber;
    int[] prefixSum;

    public void readInputAndTest() throws IOException{
        T = Integer.parseInt(br.readLine());

        initPrimeNumber();
        initPrefixSum();

        for(int t=1; t<=T; t++){
            int m = Integer.parseInt(br.readLine());
            int[] n = new int[m];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<m; i++) n[i] = Integer.parseInt(st.nextToken());

            List<Integer> commonPrimeNumber = new ArrayList<>();

            for(int i=n[0]; i<prefixSum.length; i++) {
                int sum = prefixSum[i]-prefixSum[i-n[0]];

                if(sum>10_000_000) break;
                if(isPrimeNumber[sum]) commonPrimeNumber.add(sum);
            }

            for(int i=1; i<m; i++){
                List<Integer> tmp = new ArrayList<>();

                for(int j=n[i]; j<prefixSum.length; j++){
                    int sum = prefixSum[j]-prefixSum[j-n[i]];

                    if(sum>10_000_000) break;
                    if(isPrimeNumber[sum] && Collections.binarySearch(commonPrimeNumber,sum)>=0) tmp.add(sum);
                }

                commonPrimeNumber = tmp;
            }


            bw.write("Scenario ");
            bw.write(Integer.toString(t));
            bw.write(":\n");
            bw.write(commonPrimeNumber.get(0).toString());
            bw.write("\n\n");
        }

        bw.flush();
    }

    private void initPrimeNumber(){
        primeNumber = new int[664579];
        int primeNumberIndex = 0;
        primeNumber[primeNumberIndex++] = 2;
        isPrimeNumber = new boolean[10_000_001];

        for(int i=3; i<10_000_001; i+=2){

            boolean isNotPrimeNumber = false;

            for(int j=0; j<primeNumberIndex && primeNumber[j]*primeNumber[j]<10_000_000; j++) {
                if(i%primeNumber[j]!=0) continue;

                isNotPrimeNumber = true;
                break;
            }

            if(isNotPrimeNumber) continue;

            primeNumber[primeNumberIndex++] = i;
            isPrimeNumber[i] = true;
        }
    }

    private void initPrefixSum(){
        prefixSum = new int[664580];

        for(int i=1; i<prefixSum.length; i++){
            prefixSum[i] = prefixSum[i-1]+primeNumber[i-1];
        }
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

