package _1365;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1365
 * @title 꼬인 전깃줄
 * @algorithm bineary search LIS
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
    int[] a;

    final int INF = 100_001;

    public Solution readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        a = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        return this;
    }

    int answer;
    public Solution solve(){
        Integer[] lis = new Integer[N];
        Arrays.fill(lis,INF);
        Integer[] test = {1,2,3,4,6,22};

        Comparator comparator = new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1.equals(o2)) return 1;
                return o1.compareTo(o2);
            }
        };

        int lisIndex;
        for(int value : a){
            lisIndex = Arrays.binarySearch(lis,value,comparator);

            if (lisIndex<0){
                lisIndex = -lisIndex;
                lis[lisIndex-1] = value;
            }
        }

        answer = N-(Math.abs(Arrays.binarySearch(lis,INF,comparator))-1);

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

