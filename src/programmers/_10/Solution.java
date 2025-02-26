package _10;

class Solution {
    int[][] users;
    int[] emoticons;
    int[] discounts;
    
    int maxSubscribe = -1;
    int maxRevenue = -1;
    
    public int[] solution(int[][] users, int[] emoticons) {
        this.discounts = new int[emoticons.length];
        this.users = users;
        this.emoticons = emoticons;
        
        dfs(0);
        
        int[] answer = {maxSubscribe,maxRevenue};
        return answer;
    }
    
    void dfs(int emoticonIndex){
        if(emoticonIndex==discounts.length){ 
            int revenue = 0;
            int subscribe = 0;

            for(int[] user : users){
                int avgDiscount = user[0];
                int avgPrice = user[1];
                
                int pay = 0;
                for(int i=0; i<emoticons.length; i++){
                    if(discounts[i]<avgDiscount) continue;
                    
                    pay += emoticons[i] / 100 * (100-discounts[i]);
                }
                                

                if(pay>=avgPrice) subscribe++;
                else revenue += pay;
            }

            
            if(subscribe>maxSubscribe){
                maxSubscribe = subscribe;
                maxRevenue = revenue;
            }else if(subscribe==maxSubscribe){
                maxRevenue = Math.max(maxRevenue, revenue);
            }
            
            return;
        }
        
        for(int i=1; i<5; i++){
            discounts[emoticonIndex] = 10*i;
            dfs(emoticonIndex+1);
        }
    }
    
}