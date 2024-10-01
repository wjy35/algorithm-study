package _25168;

import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/25168
 * @title 게으른 아리를 위한 접종 계획
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
    List<Edge>[] numberToEdgeList;
    int[] numberToInputEdgeCount;

    int maxPossibleDate;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numberToInputEdgeCount = new int[N+1];

        numberToEdgeList = new ArrayList[N+1];
        for(int i=1; i<=N; i++){numberToEdgeList[i] = new ArrayList<>();}

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            numberToEdgeList[S].add(new Edge(E,W));
            numberToInputEdgeCount[E]++;
        }
    }

    private void solve(){
        int[] numberToPossibleDate = new int[N+1];
        Queue<Integer> q = new ArrayDeque<>();
        for(int number=1; number<=N; number++) {
            if(numberToInputEdgeCount[number]!=0) continue;

            numberToPossibleDate[number] = 1;
            q.add(number);
        }

        while(!q.isEmpty()){
            int curNumber = q.poll();

            for(Edge edge : numberToEdgeList[curNumber]){
                numberToInputEdgeCount[edge.number]--;

                if(edge.day<7){
                    numberToPossibleDate[edge.number] = Math.max(numberToPossibleDate[edge.number], numberToPossibleDate[curNumber]+edge.day);
                }else{
                    numberToPossibleDate[edge.number] = Math.max(numberToPossibleDate[edge.number], numberToPossibleDate[curNumber]+edge.day+1);
                }

                if(numberToInputEdgeCount[edge.number]==0) {
                    q.offer(edge.number);
                }
            }
        }

        maxPossibleDate = 0;
        for(int i=1; i<=N; i++){
            maxPossibleDate = Math.max(maxPossibleDate, numberToPossibleDate[i]);
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(maxPossibleDate));
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        solve();
        writeOutput();
    }


    private static class Edge{
        int number;
        int day;

        public Edge(int number, int day) {
            this.number = number;
            this.day = day;
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

