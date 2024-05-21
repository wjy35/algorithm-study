package _2512;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2512
 * @title 예산
 * @algorithm bineary search
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
    int[] money;
    int maxMoney;

    int answer;
    public Solution readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        money = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) money[i] = Integer.parseInt(st.nextToken());

        maxMoney = Integer.parseInt(br.readLine());

        return this;
    }

    public Solution solve(){
        int l = 0;
        int r = maxMoney;

        int tmpLimitMoney;
        int limitMoney = 0;
        while(l<=r){
            tmpLimitMoney = (l+r)/2;

            if(isIn(tmpLimitMoney)){
                l = tmpLimitMoney+1;
                limitMoney = tmpLimitMoney;
            }else{
                r = tmpLimitMoney-1;
            }
        }

        answer = 0;
        for(int i=0; i<N; i++){
            if(money[i]>=limitMoney) {
                answer = limitMoney;
                break;
            }
            answer = Math.max(answer,money[i]);
        }
        return this;
    }

    public boolean isIn(int limitMoney){
        int moneySum = 0;

        for(int i=0; i<N; i++){
            moneySum += money[i]>limitMoney ? limitMoney : money[i];
        }

        return moneySum<=maxMoney;
    }

    public void writeOutput() throws IOException{
        bw.write(Integer.toString(answer));
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

