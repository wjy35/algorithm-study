package _31092;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/31092">스티커 재배치</a>
 * @category 
 * @Note
 */
public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int N,M,K;
    static int[] board;
    static int[] minPrice;
    static Sticker[] sticker;
    static final int INF = 1_001;
    static char[] S;
    static int ans;
    static PriorityQueue<Sticker> pq;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        sticker = new Sticker[M+1];
        minPrice = new int['z'-'a'+1];
        Arrays.fill(minPrice,INF);
        for(int i=1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            sticker[i]=new Sticker(
                    st.nextToken().charAt(0),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
            minPrice[sticker[i].s-'a']=Math.min(minPrice[sticker[i].s-'a'],sticker[i].a);
        }

        board = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N;i++){
            board[i]=Integer.parseInt(st.nextToken());
        }

        S=br.readLine().toCharArray();
        if(S.length>N || isNoEnough()){
            ans = -1;
        }else{
            ans = Integer.MAX_VALUE;
            for(int i=1; i+S.length-1<=N; i++){
                ans = Math.min(getCost(i),ans);
            }
        }

        bw.write(Integer.toString(ans));
        bw.flush();
    }
    static int getCost(int startIndex){
        int cost=0;
        Map<Character,PriorityQueue<Integer>> costMap = new HashMap<>();
        Queue<Character> q = new ArrayDeque<>();
        for(int i=0; i<S.length; i++){
            if(sticker[board[startIndex+i]].s==S[i]) continue;
            cost += sticker[board[startIndex+i]].d;
            q.add(S[i]);

            if(!costMap.containsKey(sticker[board[startIndex+i]].s))costMap.put(sticker[board[startIndex+i]].s,new PriorityQueue<>());
            costMap.get(sticker[board[startIndex+i]].s).offer(0);
        }

        for(int i=1; i<startIndex; i++){
            if(!costMap.containsKey(sticker[board[i]].s))costMap.put(sticker[board[i]].s,new PriorityQueue<>());
            costMap.get(sticker[board[i]].s).offer(sticker[board[i]].d);
        }

        for(int i=startIndex+S.length; i<=N; i++){
            if(!costMap.containsKey(sticker[board[i]].s))costMap.put(sticker[board[i]].s,new PriorityQueue<>());
            costMap.get(sticker[board[i]].s).offer(sticker[board[i]].d);
        }

        char now;
        while(!q.isEmpty()){
            now = q.poll();
            if(costMap.get(now)==null || costMap.get(now).isEmpty() || minPrice[now-'a']<costMap.get(now).peek()){
                cost+=minPrice[now-'a'];
            }else{
                cost+=costMap.get(now).poll();
            }
        }

        return cost;
    }

    static boolean isNoEnough(){
        for(int i=0; i<S.length; i++){
            if(minPrice[S[i]-'a']==INF) return true;
        }

        return false;
    }

    static class Sticker{
        char s;
        int d;
        int a;

        public Sticker(char s, int d, int a) {
            this.s = s;
            this.d = d;
            this.a = a;
        }
    }
}
