package _11439;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/11439
 * @title 이항 계수 5
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,K,M;

    int[] primeNumbers = new int[283147];
    Map<Integer,Integer> factorToCount = new HashMap<>();

    long answer = 1;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }

    private void solve(){
        initPrimeNumberList();
        updateFactor(1,N);
        updateFactor(-1,K);
        updateFactor(-1,N-K);

        for(int factor : factorToCount.keySet()){
            int count = factorToCount.get(factor);

            if(count==0) continue;

            answer = (answer *power(factor,count))%M;
        }
    }

    private void initPrimeNumberList(){
        int index = 0;
        primeNumbers[index++]=2;
        primeNumbers[index++]=3;
        primeNumbers[index++]=5;

        for(int number=7; number<=4_000_000; number+=2){
            boolean isNotPrimeNumber = false;
            for(int j=0; primeNumbers[j]*primeNumbers[j]<=number; j++){
                if(number%primeNumbers[j]!=0) continue;

                isNotPrimeNumber = true;
                break;
            }

            if(isNotPrimeNumber) continue;

            primeNumbers[index++] = number;
        }
    }

    private int calCount(int x, long p){
        int count = 0;
        long div = p;
        while(div<=x){
            count += x/div;

            div *= p;
        }

        return count;
    }

    private void updateFactor(int flag, int x){
        for(int i=0; primeNumbers[i]<=x; i++){
            int primeNumber = primeNumbers[i];
            int count = calCount(x,primeNumber);
            if(count==0) continue;

            factorToCount.put(primeNumber, factorToCount.getOrDefault(primeNumber,0)+flag*count);
        }
    }

    private int power(int x,int p){
        if(p==1) return x%M;

        int halfPower = power(x,p/2);
        int a = 1;
        if(p%2!=0) a = x;

        return ((((a*halfPower)%M)*halfPower)%M);
    }

    private void writeOutput() throws IOException{
        bw.write(Long.toString(answer));
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

