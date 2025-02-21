package _23;

import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int[] wanhoScore = scores[0];
        int wanhoTotalScore = scores[0][0] + scores[0][1];
        
        Arrays.sort(
            scores,
            (source,target)-> source[0]==target[0] 
            ? Integer.compare(source[1],target[1]):-Integer.compare(source[0],target[0]));
        
        int answer = 1;
        int maxPeerScore = 0;
        for(int[] score : scores){
            if(score==wanhoScore && maxPeerScore>score[1]) return -1;
            if(maxPeerScore>score[1]) continue;
            if(wanhoTotalScore>=score[0]+score[1]) continue;

            maxPeerScore = score[1];
            answer++;
        }
        
        return answer;
    }
    
}