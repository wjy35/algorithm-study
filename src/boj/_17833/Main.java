package _17833;

import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/17833">홍익대학교 지하캠퍼스</a>
 * @category dijkstra
 * @Note
 */

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;


    // input Model
    private static class Model{
        int h;
        long t;
        int e1,e2;

        public Model(int h, long t, int e1, int e2) {
            this.h = h;
            this.t = t;
            this.e1 = e1;
            this.e2 = e2;
        }
    }

    /**
     * dijkstra 를 위한 PriorityQueue 의 Node
     * height : 현재 층
     * cost : 현재 층 까지의 소요시간
     */
    private static class Node implements Comparable<Node>{
        int heigth;
        long cost;

        public Node(int heigth, long cost) {
            this.heigth = heigth;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost,o.cost);
        }
    }

    static int n,m,r,d;

    // samplse : 좌우를 뒤집은 모든 model 을 저장하는 배열
    static Model[] samples;
    static final Long INF = Long.MAX_VALUE/2;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine()) * 2;
        samples = new Model[m];
        int inputH,inputE1,inputE2;
        long inputT;

        for(int i=0; i<m; i+=2){
            st = new StringTokenizer(br.readLine());
            inputH = Integer.parseInt(st.nextToken());
            inputT = Long.parseLong(st.nextToken());
            inputE1 = Integer.parseInt(st.nextToken());
            inputE2 = Integer.parseInt(st.nextToken());

            samples[i] = new Model(inputH,inputT,inputE1,inputE2);
            samples[i+1] = new Model(inputH,inputT,inputE2,inputE1);
        }

        dijkstra();

        bw.write(Long.toString(dijkstra()));
        bw.flush();
    }

    static long dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] time = new long[n+1];
        Arrays.fill(time,INF);

        Node start = new Node(r,0);
        time[r] = 0;
        pq.offer(start);

        Node now;
        int nextLayer;
        while(!pq.isEmpty()){
            now = pq.poll();

            if(now.cost> time[now.heigth]) continue;

            if(now.heigth == d) return now.cost;
            for(int i=0; i<m; i++){
                nextLayer = now.heigth+samples[i].e2-samples[i].e1;

                // 건물을 지을 수 있고 다음 층 까지의 소요시간이 원래 저장된 시간보다 빠르다면 pq에 offer
                if(isIn(now.heigth,samples[i]) && now.cost+samples[i].t<time[nextLayer]){
                    time[nextLayer] = now.cost+samples[i].t;
                    pq.offer(new Node(nextLayer,time[nextLayer]));
                }
            }
        }

        return -1;
    }

    /**
     *
     * @param height 현재 높이
     * @param model 지을 수 있는지 판단할 건물
     * @return model 을 지을 수 있는지 여부
     */
    static boolean isIn(int height,Model model){
        return height + (model.h-model.e1) <= n && model.e1 <= height;
    }
}
