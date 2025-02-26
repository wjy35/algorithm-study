package _4;

class Solution {
    int n;
    int goal;
    
    public int solution(int coin, int[] cards) {
        n = cards.length;
        goal = n+1;
        
        boolean[] isUsed = new boolean[n+1];
        int startCardCount = n/3;
        
        boolean[] have = new boolean[n+1];
        for(int i=0; i<startCardCount; i++){
            have[cards[i]] = true;
        }
        
        boolean[] tmp = new boolean[n+1];
        
        int round = 1;
        int startCardIndex = startCardCount;
        
        
        while(startCardIndex<cards.length){
            tmp[cards[startCardIndex]]=true;
            tmp[cards[startCardIndex+1]]=true;
            
            boolean isAccepted = false;
            int endNumber = have.length/2;
            for(int number=1; number<=endNumber; number++){
                if(isUsed[number]) continue;
                
                if(!have[number]) continue;
                if(!have[goal-number]) continue;
                        
                isUsed[number] = true;
                isUsed[goal-number] = true;
                isAccepted = true;
                break;
            }
            
            if(isAccepted){
                round++;
                startCardIndex += 2;
                continue;
            }
        
            endNumber = have.length-1;
            for(int number=1; number<=endNumber; number++){
                if(isUsed[number]) continue;
                
                int otherNumber = Math.abs(goal-number);
                if(isUsed[otherNumber]) continue;
                
                if(!have[number]) continue;
                if(!tmp[otherNumber]) continue;
                
                if(coin<1) return round;
                
                coin--;
                isUsed[number] = true;
                isUsed[otherNumber] = true;
                isAccepted = true;
                
                break;
            }
            if(isAccepted){
                round++;
                startCardIndex += 2;
                continue;
            }
            
            endNumber = have.length-1;
            for(int number=1; number<=endNumber; number++){
                if(isUsed[number]) continue;
                
                int otherNumber = Math.abs(goal-number);
                if(isUsed[otherNumber]) continue;
                
                if(!tmp[number]) continue;
                if(!tmp[otherNumber]) continue;
                
                if(coin<2) return round;
                
                coin -= 2;
                isUsed[number] = true;
                isUsed[otherNumber] = true;
                isAccepted = true;
                
                break;
            }
            if(isAccepted){
                round++;
                startCardIndex += 2;
                continue;
            }
            
            break;
        }
        
        return round;
    }
}