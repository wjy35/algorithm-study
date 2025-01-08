package _31854;

import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/31854
 * @title 부등호 퍼즐
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N;
    List<Integer>[] edge;
    int[] inputEdgeCount;
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        edge = new List[N*N];
        inputEdgeCount = new int[edge.length];
        for(int i=0; i<edge.length; i++) edge[i] = new ArrayList<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N-1; j++){
                int l = i*N+j;
                int r = i*N+j+1;

                if("<".equals(st.nextToken())){
                    edge[r].add(l);
                    inputEdgeCount[l]++;
                }else{
                    edge[l].add(r);
                    inputEdgeCount[r]++;
                }
            }
        }

        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int t =  i*N+j;
                int b = (i+1)*N+j;

                if("<".equals(st.nextToken())){
                    edge[b].add(t);
                    inputEdgeCount[t]++;
                }else{
                    edge[t].add(b);
                    inputEdgeCount[b]++;
                }
            }
        }
    }

    int[] puzzle;
    private void solve(){
        puzzle = new int[N*N];

        int number = N*N;
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=0; i<inputEdgeCount.length; i++){
            if(inputEdgeCount[i]>0)continue;

            q.offer(i);
        }

        while(!q.isEmpty()){
            int current = q.poll();

            puzzle[current] = number;
            number--;

            for(int next : edge[current]){
                inputEdgeCount[next]--;

                if(inputEdgeCount[next]!=0) continue;
                q.offer(next);
            }
        }
    }

    private void writeOutput() throws IOException{
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                bw.write(Integer.toString(puzzle[i*N+j]));
                bw.write(" ");
            }
            bw.write("\n");
        }
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        solve();
        writeOutput();
    }

    private static class PuzzlePiece{
        int index,number;

        public PuzzlePiece(int index, int number) {
            this.index = index;
            this.number = number;
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

