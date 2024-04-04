package _12015;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/12015
 * @title 가장 긴 증가하는 부분 수열 2
 * @algorithm Binary Search
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
    int[] A;

    final int INF = 1_000_001;

    int answer;
    public Solution readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        return this;
    }

    public Solution solve(){
        Integer[] lis = new Integer[N+1];
        Arrays.fill(lis,INF);

        Comparator comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1.equals(o2)) return 1;
                return o1.compareTo(o2);
            }
        };

        int lisIndex;
        for(int i=0; i<N; i++){
            lisIndex = Arrays.binarySearch(lis,A[i],comparator);
            lisIndex = -lisIndex-1;
            lis[lisIndex] = A[i];
        }

        answer = -Arrays.binarySearch(lis,INF,comparator)-1;
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

