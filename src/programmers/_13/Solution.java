package _13;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int[] mergedQueue = new int[queue1.length*2];
        
        long currentSum = 0;
        long midSum = 0;
        for(int i=0; i<queue1.length; i++){
            mergedQueue[i] = queue1[i];            
            mergedQueue[queue1.length + i] = queue2[i];
            
            currentSum += queue1[i];
            midSum += queue1[i];
            midSum += queue2[i];
        }
        midSum = midSum / 2;
        
        int operation = 0;
        int l = 0, r = queue1.length-1;
        while(currentSum!=midSum && l<=r && r<mergedQueue.length){
            if(currentSum>midSum){
                currentSum-=mergedQueue[l];
                l++;
            } else{
                r++;
                if(r==mergedQueue.length) break;
                currentSum+=mergedQueue[r];
            }
            operation++;
        }  
              
        if(currentSum==midSum) return operation;  
        return -1;  
    }
    
}