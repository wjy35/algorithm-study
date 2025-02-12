package _2170;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/2170
 * @title
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
    Line[] lines;
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        lines = new Line[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            lines[i] = new Line(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
    }

    int sum = 0;
    private void solve(){
        Arrays.sort(lines);

        int start = lines[0].start;
        int end = lines[0].end;
        for(int i=1; i<lines.length; i++){
            if(end<lines[i].start){
                sum += end-start;
                start = lines[i].start;
                end = lines[i].end;

                continue;
            }

            end = Math.max(end,lines[i].end);
        }

        sum += end-start;
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(sum));
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        solve();
        writeOutput();
    }

    private static class Line implements Comparable<Line>{
        int start,end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line o) {
            return Integer.compare(this.start,o.start);
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

