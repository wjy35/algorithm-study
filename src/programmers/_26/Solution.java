package _26;

import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        int videoLen = toInt(video_len);
        int currentPos = toInt(pos);
        int opStart = toInt(op_start);
        int opEnd = toInt(op_end);
        
        for(String command : commands){
          if (opStart <= currentPos && currentPos <= opEnd) {
            currentPos = opEnd;
          }
            
            if("next".equals(command)){
                currentPos = Math.min(videoLen,currentPos+10);
            }else{
                currentPos = Math.max(0,currentPos-10);
            }
        }

      if (opStart <= currentPos && currentPos <= opEnd) {
        currentPos = opEnd;
      }

        return toString(currentPos);
    }
    
    
    public int toInt(String time){
        StringTokenizer st = new StringTokenizer(time,":");
        
        int mm = Integer.parseInt(st.nextToken());
        int ss = Integer.parseInt(st.nextToken());
        
        return mm*60 + ss;
    }
    
    public String toString(int time){
        return String.format("%02d:%02d",(time/60),(time%60));
    }
}