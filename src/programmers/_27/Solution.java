package _27;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int l = 1;
        int r = 100_000;
        
        while(l<=r){
            int mid = (l+r)/2;
            
            long timeSum = times[0];
            for(int i=1; i<diffs.length; i++){
                timeSum += ((long) Math.max(0, (diffs[i] - mid))*(times[i-1]+times[i])) + times[i];

              if (timeSum > limit) {
                break;
              }
            }
            
            if(timeSum>limit){
                l = mid+1;
            }else{
                answer = mid;
                r = mid-1;
            }
        }
        
        return answer;
    }
}