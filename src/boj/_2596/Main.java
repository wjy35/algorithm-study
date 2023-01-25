package _2596;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


/*
 * https://www.acmicpc.net/problem/2596
 * 비밀편지
 */


public class Main{
    static String[] c = {"A","B","C","D","E","F","G","H"};
    static String[] code={"000000","001111","010011","011100","100110","101001","110101","111010"};
    static class Code implements Comparable<Code>{
        int num;
        int seq;

        public Code(int num, int seq) {
            super();
            this.num = num;
            this.seq = seq;
        }

        @Override
        public int compareTo(Code o) {
            return o.seq - this.seq;
        }

    }

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n,length;
        String input;

        n = Integer.parseInt(br.readLine());
        input = br.readLine();

        length = input.length();
        PriorityQueue<Code> pq;
        Code now;
        int seq,alpha;
        int flag = -1;
        for(int i=0; i<n; i++) {
            pq = new PriorityQueue<>();

            for(int p=0; p<code.length; p++) {
                seq = 0;
                for(int j=i*6,start=0; j<(i+1)*6; j++,start++) {
                    if(input.charAt(j)==code[p].charAt(start)) {
                        seq++;
                    }
                }
                pq.offer(new Code(p,seq));

            }
            now = pq.poll();
            if(now.seq == pq.peek().seq) {
                flag = i;
                break;
            }
            else {
                sb.append(c[now.num]);
            }
        }

        if(flag == -1) {
            System.out.println(sb);
        }
        else {
            System.out.println(flag+1);
        }

    }

}