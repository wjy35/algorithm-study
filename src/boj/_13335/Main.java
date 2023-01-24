package _13335;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/13335
 * 트럭
 */

class Truck{
    int weight;
    int last;

    public Truck(int weight, int last) {
        this.weight = weight;
        this.last = last;
    }
}


public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n,w,l;
    static Truck[] trucks;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        trucks = new Truck[n];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++){
            trucks[i] = new Truck(Integer.parseInt(st.nextToken()),w);
        }


        int bridge_front=0;
        int bridge_end=1;
        int now_weight=trucks[bridge_front].weight;
        int now_cnt=1;
        int t=1;

        while(bridge_front<n){
            if(trucks[bridge_front].last==1){
                now_weight-= trucks[bridge_front].weight;
                now_cnt--;
                bridge_front++;
            }

            for(int i=bridge_front; i<bridge_end; i++){
                trucks[i].last--;
            }

            if(bridge_end<n&&trucks[bridge_end].weight+now_weight<=l && now_cnt<w){
                now_weight+= trucks[bridge_end].weight;
                now_cnt++;
                bridge_end++;
            }
            t++;
        }
        System.out.print(t);

    }
}
