package _17;

import java.util.*;

class Solution {
    
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = toIntegerTime(play_time);
        int advTime = toIntegerTime(adv_time);
        long[] countPrefixSum = new long[playTime+1];
        
        for(int i=0; i<logs.length; i++){
            StringTokenizer st = new StringTokenizer(logs[i],"-");
            
            int start = toIntegerTime(st.nextToken());
            int end = toIntegerTime(st.nextToken());
            
            countPrefixSum[start] += 1;
            countPrefixSum[end] += -1;
        }
        
        for(int i=1; i<countPrefixSum.length; i++){
            countPrefixSum[i] += countPrefixSum[i-1];
        }
        
        for(int i=1; i<countPrefixSum.length; i++){
            countPrefixSum[i] += countPrefixSum[i-1];
        }
        
        int startWhenMaxSum = 0;
        long maxSum = 0;
        for(int start = 0; start+advTime-1<countPrefixSum.length; start++){
            int end = start+advTime-1;
            
            long sum = countPrefixSum[end] - (start-1<0 ? 0 : countPrefixSum[start-1]);
            if(sum>maxSum){
                maxSum = sum;
                startWhenMaxSum = start;
            }
        }
        
        return toStringTime(startWhenMaxSum);
    }
    
    public int toIntegerTime(String time){
        StringTokenizer st = new StringTokenizer(time,":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());
        
        return hour*3600 + minute*60 + second;        
    }
    
    public String toStringTime(int time){
        int hour = time / 3600;
        int minute = (time % 3600) / 60;
        int second = (time % 3600) % 60;
        
        return String.format("%02d:%02d:%02d",hour,minute,second);
    }
}