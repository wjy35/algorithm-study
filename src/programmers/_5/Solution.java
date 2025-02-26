package _5;

class Solution {
    private final int MOD = 10_007;
    public int solution(int n, int[] tops) {
        int endIndex = 2*n+1;
        int[] dp = new int[endIndex];
        
        dp[0] = 1;
        if(tops[0]==1) dp[1] = 3;
        else dp[1] = 2;
        
        for(int i=2; i<endIndex; i++){
            if(i%2==0){
                dp[i] = (dp[i-1] + dp[i-2]) % MOD;
                continue;
            }
            
            dp[i] = (dp[i-1] + dp[i-2])%MOD;
            if(tops[i/2]==1) dp[i] = (dp[i] + dp[i-1])%MOD;
        }
        
        return dp[endIndex-1];
    }
}