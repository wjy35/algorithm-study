package _24;

import java.util.*;

public class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;

        Queue<Integer> q = new ArrayDeque<>();
        for(int i=0; i<24; i++){

            while(!q.isEmpty() && q.peek()<=i) q.poll();

            int n = players[i]/m;
            if(n <= q.size()) continue;

            int required = n-q.size();
            answer += required;
            while(required-->0) q.offer(i+k);
        }

        return answer;
    }
}