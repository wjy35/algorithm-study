package _1963;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/1963">소수 경로</a>
 * @category BFS, Sieve of Eratosthenes
 * @Study 각 자리수 별로 차이가 날때 분기를 나누면 8 * 9 * 9 * 9 로 더 적은 연산으로 통과 가능
 */
public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int T;

    static boolean[] isPn;
    static ArrayList<Integer> pn;
    static int start,end,result;


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        pn = new ArrayList<>(1062);

        makeIsPn();

        for(int test_case = 1; test_case<=T; test_case++){
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            if(start == end) {
                bw.write("0");
                bw.write("\n");
                continue;
            }
            result = bfs();

            if(result <0){
                bw.write("Impossible");
                bw.write("\n");
                continue;
            }
            bw.write(Integer.toString(result));
            bw.write("\n");
        }
        bw.flush();
    }

    public static void makeIsPn(){
        isPn = new boolean[10000];

        for(int i=2; i<10000; i++){
            if(isPn[i]) continue;
            if(i>1000) pn.add(i);
            for(int j=i*i; j<10000; j+=i){
                isPn[j] = true;
            }
        }
    }

    public static int bfs(){
        Queue<Integer> q = new ArrayDeque<>();
        int[] visit = new int[10000];

        q.offer(start);
        visit[start] = 1;

        int now,next;
        while(!q.isEmpty()){
            now = q.poll();

            for(int i=0; i<pn.size(); i++){
                next = pn.get(i);

                if(countDif(now,next) == 1 && visit[next]==0){
                    if(next == end) return visit[now];
                    q.offer(next);
                    visit[next] = visit[now]+1;
                }
            }
        }

        return -1;
    }

    public static int countDif(int a,int b){
        int dif = 0;

        for(int div=1000; div>1; div=div/10){
            if(a/div != b/div) dif++;
            a=a%div;
            b=b%div;
        }
        if(a != b) dif++;

        return dif;
    }
}