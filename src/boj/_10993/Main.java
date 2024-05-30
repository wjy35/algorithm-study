package _10993;

import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/10993
 * @title 별 찍기 - 18
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution {
    int N;
    String star = "*";
    String space = " ";
    Deque<Deque<String>> superStart;

    private void readInput() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    private void solve() {
        superStart = new ArrayDeque<>();
        Deque<String> line = new ArrayDeque<>();
        line.offer(star);
        superStart.offer(line);

        int size;
        for (int i = 2; i<=N; i++) {
            Deque<Deque<String>> tmpStar;
            if(i%2==1){
                tmpStar = new ArrayDeque<>();
                while(!superStart.isEmpty()) tmpStar.offerFirst(superStart.poll());
                superStart = tmpStar;
            }

            size = superStart.size();
            line = superStart.pollLast();
            line.offerFirst(star);
            line.offerLast(star);
            superStart.offerFirst(line);

            for (int j = 1; j < size; j++) {
                line = superStart.pollLast();
                for (int k = 0; k < j*2; k++) {
                    line.offerFirst(space);
                    line.offerLast(space);
                }

                line.offerFirst(star);
                line.offerLast(star);
                superStart.offerFirst(line);
            }

            line = new ArrayDeque<>();
            for(int j=0; j<superStart.peek().size()+2; j++){
                line.offer(star);
            }
            superStart.offerFirst(line);

            line = superStart.peekLast();
            size = line.size() - 2;


            while (size > 1) {
                line = new ArrayDeque<>();
                line.offer(star);
                for (int j = 0; j < size - 2; j++) {
                    line.offer(space);
                }
                line.offer(star);
                superStart.offerLast(line);
                size -= 2;
            }
            line = new ArrayDeque<>();
            line.offerLast(star);
            superStart.offerLast(line);

            if(i%2==1){
                tmpStar = new ArrayDeque<>();
                while(!superStart.isEmpty()) tmpStar.offerFirst(superStart.poll());
                superStart = tmpStar;
            }
        }

    }

    private void writeOutput() throws IOException {
        Deque<String> line;
        if(N%2==1){
            int spaceSize = superStart.size()-1;
            while(!superStart.isEmpty()){
                for(int i=spaceSize; i>0; i--) bw.write(space);
                spaceSize--;

                line = superStart.poll();
                while(!line.isEmpty()){
                    bw.write(line.poll());
                }
                bw.write("\n");

            }
        }else{
            int spaceSize = 0;
            while(!superStart.isEmpty()){
                for(int i=0; i<spaceSize; i++) bw.write(space);
                spaceSize++;

                line = superStart.poll();
                while(!line.isEmpty()){
                    bw.write(line.poll());
                }
                bw.write("\n");
            }
        }


        bw.flush();
    }

    public void start() throws IOException {
        readInput();
        solve();
        writeOutput();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution() {
    }

    public static Solution createSolution() {
        return new Solution();
    }
}

