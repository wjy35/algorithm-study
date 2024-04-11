package _1945;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1945
 * @title 직사각형
 * @algorithm Sweep Line
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
    List<Inclination> inclinationList;

    int answer;

    public Solution readInput() throws IOException{
        N = Integer.parseInt(br.readLine());
        inclinationList = new ArrayList<>(N*2);

        long xbl,ybl,xtr,ytr;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            xbl = Long.parseLong(st.nextToken());
            ybl = Long.parseLong(st.nextToken());
            xtr = Long.parseLong(st.nextToken());
            ytr = Long.parseLong(st.nextToken());

            inclinationList.add(Inclination.createMaxTypeInclination(ytr,xbl));
            inclinationList.add(Inclination.createMinTypeInclination(ybl,xtr));
        }

        return this;
    }

    public Solution solve(){
        Collections.sort(inclinationList);

        int maxCount = 0;
        int count = 0;
        for(Inclination inclination : inclinationList){
            if(inclination.isMinType()){
                count++;
                maxCount = Math.max(maxCount,count);
            }else {
                count--;
            }
        }

        answer = maxCount;
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


    private static class Inclination implements Comparable<Inclination>{
        long numerator;
        long denominator;
        int type;

        private static final int MAX_TYPE = 2;
        private static final int MIN_TYPE = 1;

        public static Inclination createMaxTypeInclination(long numerator,long denominator){
            return new Inclination(numerator,denominator,Inclination.MAX_TYPE);
        }

        public static Inclination createMinTypeInclination(long numerator,long denominator){
            return new Inclination(numerator,denominator,Inclination.MIN_TYPE);
        }

        private Inclination(long numerator, long denominator, int type) {
            this.numerator = numerator;
            this.denominator = denominator;
            this.type = type;
        }

        @Override
        public int compareTo(Inclination o) {
            long leftValue = this.numerator * o.denominator;
            long rightValue = this.denominator * o.numerator;

            if(leftValue==rightValue) return Long.compare(this.type,o.type);

            return Long.compare(leftValue,rightValue);
        }

        public boolean isMinType(){
            return this.type == Inclination.MIN_TYPE;
        }
    }
}

