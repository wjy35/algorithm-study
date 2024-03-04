package _31501.v2;

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

    static Integer[] leftUpDp;
    static Integer[] rightDownDp;
    static int[] dp;

    static void solution(){
        updateLeftUpDp();
        updateLds();
        updateDp();
    }

    static void updateDp(){
        dp = new int[N+1];
        for(int i=1; i<=N; i++){
            dp[i] = leftUpDp[i]+ rightDownDp[i]-1;
        }
    }

    static void updateLeftUpDp(){
        leftUpDp = new Integer[N+1];
        List<Integer> lis = new ArrayList<>(N);
        int lisIndex;
        for(int i=1; i<=N; i++){
            lisIndex = -Collections.binarySearch(lis,D[i],new Comparator<Integer>(){
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            if(o1.equals(o2)) return 1;
                            return o1.compareTo(o2);
                        }
                    });

            if(lis.size()<lisIndex) lis.add(D[i]);
            else lis.set(lisIndex-1,D[i]);

            leftUpDp[i] = lisIndex;
        }
    }

    static void updateLds(){
        rightDownDp = new Integer[N+1];

        List<Integer> lds = new ArrayList<>(N);
        int ldsIndex;
        int l,r,mid;

        rightDownDp[N] = 1;
        lds.add(D[N]);
        for(int i=N-1; i>=1; i--){
            l = 0;
            r = lds.size()-1;
            mid = 0;
            while(l<=r){
                mid = (l+r)/2;

                if(lds.get(mid)<=D[i]) r = mid-1;
                else l = mid+1;
            }

            ldsIndex = mid;
            if(lds.get(mid)>D[i]) ldsIndex++;

            if(lds.size()<ldsIndex+1) lds.add(D[i]);
            else lds.set(ldsIndex,D[i]);

            rightDownDp[i] = ldsIndex+1;
        }
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
            bw.write(Integer.toString(dp[Integer.parseInt(br.readLine())]));
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
