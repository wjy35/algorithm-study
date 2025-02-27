package _25;

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {        
        int answer = 0;
        
        for(int member=0; member<schedules.length; member++){
            boolean isEventMember = true;
            
            for(int day=0; day<timelogs[member].length; day++){
              if (isWeekend(startday, day)) {
                continue;
              }
                if(addTenMinute(schedules[member])<timelogs[member][day]){
                    isEventMember = false;
                    break;
                }
            }

          if (isEventMember) {
            answer++;
          }
        }
        
        return answer;
    }
    
    public boolean isWeekend(int startday,int offset){
        int current = (startday+offset)%7;
        return current==6 || current==0;
    }
    
    public int addTenMinute(int currentTime){
        int nextTime = currentTime+10;

      if (nextTime % 100 < 60) {
        return nextTime;
      }
        
        return nextTime+40;
    }
}