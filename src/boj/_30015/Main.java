package _30015;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/30015
 * @title 학생회 뽑기
 * @algorithm binary search
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

    int N,K;
    List<Integer> A;

    int answer;

    public Solution readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new ArrayList<>(N);
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            A.add(Integer.parseInt(st.nextToken()));
        }

        return this;
    }

    public Solution solve(){
        int max=0;
        int digitBit;
        List<Integer> numberList,hasDigitBitNumberList;

        numberList = A;
        for(int i=19; i>=0; i--){
            digitBit = 1<<i;
            hasDigitBitNumberList = new ArrayList<>(N);
            for(int j=0; j<numberList.size(); j++){
                if((numberList.get(j)&digitBit)>0) hasDigitBitNumberList.add(numberList.get(j));
            }

            if(hasDigitBitNumberList.size()>=K){
                max = max|digitBit;
                numberList = hasDigitBitNumberList;
            }
        }

        answer = max;

        return this;
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

