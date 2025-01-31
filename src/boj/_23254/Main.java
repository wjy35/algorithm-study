package _23254;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/23254
 * @title 나는 기말고사형 인간이야
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
    int[] a;
    int[] b;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        a = new int[M];
        for(int i=0; i<M; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        b = new int[M];
        for(int i=0; i<M; i++){
            b[i] = Integer.parseInt(st.nextToken());
        }
    }

    int score = 0;
    private void solve(){
        PriorityQueue<Element> pq = new PriorityQueue<>(M);
        for(int i=0; i<M; i++){
            if(a[i]+b[i]>100) b[i] = 100-a[i];

            pq.offer(new Element(a[i],b[i]));
        }


        int hour = 24*N;
        for(int i=0; i<hour; i++){
            Element current = pq.poll();

            current.a += current.b;

            if(current.a+current.b>100) current.b = 100-current.a;
            pq.offer(current);
        }


        for(Element element : pq){

            score += element.a;
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(score));
        bw.flush();
    }

    private static class Element implements Comparable<Element>{
        int a;
        int b;

        public Element(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Element target) {
            return Integer.compare(target.b,this.b);
        }
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

