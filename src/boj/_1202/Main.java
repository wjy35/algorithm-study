package _1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * https://www.acmicpc.net/problem/1202
 * 보석 도둑
 */

class Diamond {
    int m,v;

    public Diamond(int m, int v) {
        this.m = m;
        this.v = v;
    }
}

public class Main {
    static int n,k;
    static BufferedReader br;
    static StringTokenizer st;
    static ArrayList<Diamond> shop;
    static PriorityQueue<Diamond> pq;
    static int[] bag;
    static long result;
    public static void main(String[] args) throws IOException {
        int m,v,start;
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        shop = new ArrayList<>(n);
        bag = new int[k];

        pq = new PriorityQueue<>(new Comparator<Diamond>() {
            @Override
            public int compare(Diamond o1, Diamond o2) {
                return o2.v-o1.v;
            }
        });
        

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            shop.add(new Diamond(m,v));
        }
        
        Collections.sort(shop, new Comparator<Diamond>() {
            @Override
            public int compare(Diamond o1, Diamond o2) {
                return o1.m-o2.m;
            }
        });

        for(int i=0; i<k; i++){
            bag[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bag);


        result = 0;
        int j=0;
        for(int i=0; i<k; i++){
            for(; j<n; j++){
                if(shop.get(j).m<=bag[i]){
                    pq.offer(shop.get(j));
                }
                else{
                    break;
                }
            }
            if(!pq.isEmpty()) result = result + (long) pq.poll().v;
        }

        System.out.println(result);

    }
}