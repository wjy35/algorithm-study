package _31410;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/31410">제독 작전</a>
 * @category 
 * @Note
 */
public class Main {
    static long answer;

    static int N;
    static Poison[] poisonList;

    static void solution(){
        long leftCost=0, rightCost=0;
        long leftMin, rightMin;

        Arrays.sort(poisonList,1,poisonList.length);

        for(int i=1; i<N; i++){
            leftCost += i*(poisonList[i+1].x-poisonList[i].x);
            rightCost += i*(poisonList[N-i+1].x-poisonList[N-i].x);
        }

        leftMin = leftCost-(poisonList[N].x-poisonList[1].x)-poisonList[1].p;
        leftMin = Math.min(leftMin,leftCost-(N-1)*(poisonList[N].x-poisonList[N-1].x)-poisonList[N].p);
        for(int i=2; i<N; i++){
            leftMin = Math.min(leftMin,leftCost-(poisonList[N].x-poisonList[i].x)-poisonList[i].p);
        }

        rightMin = rightCost-(poisonList[N].x-poisonList[1].x)-poisonList[N].p;
        rightMin = Math.min(rightMin,rightCost-(N-1)*(poisonList[2].x-poisonList[1].x)-poisonList[1].p);
        for(int i=N-1; i>1; i--){
            rightMin = Math.min(rightMin,rightCost-(poisonList[i].x-poisonList[1].x)-poisonList[i].p);
        }

        answer = Poison.pSum+Math.min(leftMin,rightMin);
    }
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        poisonList = new Poison[N+1];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            poisonList[i] = new Poison(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
            Poison.pSum += poisonList[i].p;
        }
    }

    static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        bw.write(Long.toString(answer));
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        output();
    }

    static class Poison implements Comparable<Poison>{
        long x,p;
        static long pSum=0;

        public Poison(long x, long p) {
            this.x = x;
            this.p = p;
        }

        @Override
        public int compareTo(Poison p) {
            return Long.compare(this.x,p.x);
        }
    }
}
