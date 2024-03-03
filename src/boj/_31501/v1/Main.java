package _31501.v1;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/31501">DP (Small)</a>
 * @category 
 * @Note
 */
public class Main {
    static int N,Q;
    static Integer[] D;

    static Integer[] answer;
    static void solution(){
        answer = new Integer[N+1];
        for(int i=1; i<=N; i++){
            answer[i] = leftLIS(i)+ rightLIS(i)+1;
        }
    }

    static int rightLIS(int start){
        Integer[] cache = new Integer[N+1];
        int cacheSize = 0;
        int cacheIndex;
        for(int i=start; i<=N; i++){
            if(D[i]<=D[start]) continue;
            cacheIndex = -Arrays.binarySearch(cache,0,cacheSize,D[i]
                    ,new Comparator<Integer>(){
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            if(o1.equals(o2)) return 1;
                            return o1.compareTo(o2);
                        }
                    });

            if(cacheSize<cacheIndex) cacheSize++;
            cache[cacheIndex-1] = D[i];
        }

        return cacheSize;
    }

    static int leftLIS(int end){
        Integer[] cache = new Integer[N+1];
        int cacheSize = 0;
        int cacheIndex;
        for(int i=1; i<end; i++){
            if(D[i]>=D[end]) continue;
            cacheIndex = -Arrays.binarySearch(cache,0,cacheSize,D[i]
                    ,new Comparator<Integer>(){
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            if(o1.equals(o2)) return 1;
                            return o1.compareTo(o2);
                        }
                    });

            if(cacheSize<cacheIndex) cacheSize++;
            cache[cacheIndex-1] = D[i];
        }

        return cacheSize;
    }


    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        D = new Integer[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            D[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void query() throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i=0; i<Q; i++){
            bw.write(answer[Integer.parseInt(br.readLine())].toString());
            bw.write("\n");
        }
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        query();
    }
}
