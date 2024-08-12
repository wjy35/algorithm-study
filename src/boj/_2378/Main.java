package _2378;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2378
 * @title 불필요한 수
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,M;
    List<Integer> primeNumberList;

    List<Integer> answerList;

    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }

    private void solve(){

        primeNumberList = new ArrayList<>(10000);
        primeNumberList.addAll(List.of(2,3,5));

        for(int number=7; number<100_000; number+=2){
            boolean isNotPrimeNumber = false;
            for(int j=0; primeNumberList.get(j)*primeNumberList.get(j)<=number; j++){
                if(number%primeNumberList.get(j)!=0) continue;

                isNotPrimeNumber = true;
                break;
            }

            if(isNotPrimeNumber) continue;

            primeNumberList.add(number);
        }

        Map<Integer,Integer> primeFactorToPower = new HashMap<>();
        int m = M;
        int primeFactorCount = 0;
        for(int primeNumber : primeNumberList) {
            int power = 0;
            while(m%primeNumber==0){
                power++;
                m /= primeNumber;
            }

            if(power==0) continue;

            primeFactorCount++;
            primeFactorToPower.put(primeNumber,power);
        }

        if(primeFactorCount==0) {
            primeFactorToPower.put(M,1);
            primeFactorCount++;
        }

        Set<Integer> set = new HashSet<>();
        int n = N-1;
        int half_n = n/2;
        for(int denominator=1, numerator=n; denominator<=half_n; denominator++,numerator--){
            int number;

            number=numerator;
            for(int primeNumber : primeNumberList) {
                if(primeNumber>number) break;

                int power = 0;
                while(number % primeNumber==0){
                    power++;
                    number /= primeNumber;
                }

                if(power==0) continue;

                int tmp = primeFactorToPower.getOrDefault(primeNumber,0);

                if(tmp<=0) primeFactorCount++;

                primeFactorToPower.put(primeNumber,tmp-power);
                if(primeFactorToPower.get(primeNumber)<=0) primeFactorCount--;
            }

            number=denominator;
            for(int primeNumber : primeNumberList) {
                if(primeNumber>number) break;

                int power = 0;
                while(number % primeNumber==0){
                    power++;
                    number /= primeNumber;
                }

                if(power==0) continue;

                int tmp = primeFactorToPower.getOrDefault(primeNumber,0);

                if(tmp>0) primeFactorCount--;
                primeFactorToPower.put(primeNumber,tmp+power);
                if(primeFactorToPower.get(primeNumber)>0) primeFactorCount++;
            }

            if(primeFactorCount==0) {
                set.add(1+denominator);
                set.add(N-denominator);
            }
        }

        answerList = new ArrayList<>(set);
        Collections.sort(answerList);
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(answerList.size()));
        bw.write("\n");
        for(int i=0; i<answerList.size(); i++) {
            bw.write(Integer.toString(answerList.get(i)));
            bw.write(" ");
        }
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

