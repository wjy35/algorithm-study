package _32178;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/32178
 * @title 용액 2
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
    long[] cumulativePH;

    int L = -1;
    int R = -1;
    long minPH = 1_000_000_000L * 1_000_000L + 1;
    private void readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        cumulativePH = new long[N+1];
        for(int i=1; i<=N; i++){
            cumulativePH[i] = cumulativePH[i-1]+Integer.parseInt(st.nextToken());;
        }
    }

    private void solve(){
        TreeSet<Mixture> treeSet = new TreeSet<>();
        treeSet.add(new Mixture(0, cumulativePH[0]));

        for(int i=1; i<=N; i++){
            Mixture currentMixture = new Mixture(i, cumulativePH[i]);

            updateAnswer(treeSet.floor(currentMixture),currentMixture);
            updateAnswer(treeSet.ceiling(currentMixture),currentMixture);

            treeSet.add(currentMixture);
        }
    }

    private void updateAnswer(Mixture target, Mixture currentMixture){
        if(target==null) return;
        Long diff = currentMixture.pH - target.pH;
        if(Math.abs(diff) < Math.abs(minPH)){
            minPH = diff;
            L = target.index+1;
            R = currentMixture.index;
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Long.toString(minPH));
        bw.write("\n");
        bw.write(Long.toString(L));
        bw.write(" ");
        bw.write(Long.toString(R));
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
}


class Mixture implements Comparable<Mixture>{
    int index;
    long pH;

    public Mixture(int index, long pH) {
        this.index = index;
        this.pH = pH;
    }

    @Override
    public int compareTo(Mixture o) {
        return Long.compare(this.pH, o.pH);
    }
}