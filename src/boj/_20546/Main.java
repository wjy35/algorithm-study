package _20546;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/20546
 * @title 🐜 기적의 매매법 🐜
 * @algorithm Simulation
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int seed;
    int[] stockPrice;

    String answer;
    private void readInput() throws IOException{
        seed = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        stockPrice = new int[14];
        for(int i=0; i<14; i++) stockPrice[i] = Integer.parseInt(st.nextToken());
    }

    private void solve(){
        Asset jh = new Asset(seed);
        Asset sm = new Asset(seed);

        for(int day=0; day<14; day++){
            bnp(jh,day);
            timing(sm,day);
        }

        int bnpResult = jh.calResult(stockPrice[13]);
        int timingResult = sm.calResult(stockPrice[13]);

        if(bnpResult == timingResult) answer = "SAMESAME";
        else if(bnpResult > timingResult) answer = "BNP";
        else answer = "TIMING";
    }

    private void bnp(Asset asset,int day){
        if(asset.cash<stockPrice[day]) return;

        asset.buy(stockPrice[day]);
    }

    private void timing(Asset asset,int day){
        if(day<3) return;

        int upCount = 0;
        int downCount = 0;
        for(int i=0; i<3; i++){
            if(stockPrice[day-i]==stockPrice[day-i-1]) return;
            else if(stockPrice[day-i]<stockPrice[day-i-1]) downCount++;
            else upCount++;
        }

        if(upCount==3) asset.sell(stockPrice[day]);
        if(downCount==3) asset.buy(stockPrice[day]);
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

    private class Asset{
        int cash;
        int stockCount;

        public Asset(int cash) {
            this.cash = cash;
            this.stockCount = 0;
        }

        public void buy(int stockPrice){
            int buyCount = this.cash/stockPrice;
            this.stockCount += buyCount;
            this.cash -= stockPrice * buyCount;
        }

        public void sell(int stockPrice){
            this.cash += stockPrice * this.stockCount;
            this.stockCount = 0;
        }

        public int calResult(int stockPrice){
            return cash + this.stockCount * stockPrice;
        }
    }
}

