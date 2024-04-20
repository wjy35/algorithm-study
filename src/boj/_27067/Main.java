package _27067;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/27067
 * @title 데이터 순서 복원
 * @algorithm Topological Sort
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
    int[] B;
    int[] C;

    Integer[] answer;
    public Solution readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        B = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            B[i] = Integer.parseInt(st.nextToken());
        }

        C = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            C[i] = Integer.parseInt(st.nextToken());
        }
        return this;
    }

    public Solution solve(){
        int[] indexInA = new int[N+1];
        int[] indexInB = new int[N+1];
        int[] indexInC = new int[N+1];

        for(int i=0; i<N; i++){
            indexInA[A[i]] = i;
            indexInB[B[i]] = i;
            indexInC[C[i]] = i;
        }

        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int count = 0;

                if(indexInA[o1]<indexInA[o2]) count++;
                if(indexInB[o1]<indexInB[o2]) count++;
                if(indexInC[o1]<indexInC[o2]) count++;

                if(count>=2) return -1;
                return 1;
            }
        };

        answer = new Integer[N];
        for(int i=0; i<N; i++) answer[i] = i+1;

        Arrays.sort(answer,comparator);

        return this;
    }

    public void writeOutput() throws IOException{
        for(int i=0; i<N; i++){
            bw.write(answer[i].toString());
            bw.write(" ");
        }
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

