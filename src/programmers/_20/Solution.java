package _20;

import java.util.*;

class Solution {
    long[] primeNumbers;
    public int solution(int n, int k) {                
        initPrimeNumbers();
        
        int answer = 0;
        StringTokenizer st = new StringTokenizer(Integer.toString(n,k),"0");
        while(st.hasMoreTokens()){
            String number = st.nextToken();
            if(isPrimeNumber(number)) answer++; 
        }
        
        return answer;
    }
    
    public void initPrimeNumbers(){
        primeNumbers = new long[10_000_001];
        int last = 0;
        primeNumbers[last] = 2;
        last++;
        primeNumbers[last] = 3;
        last++;
        
        for(long number=5; number<=10_000_000; number+=2){
            boolean isPrimeNumber = true;
            
            for(int i=0; primeNumbers[i]*primeNumbers[i]<=number; i++){
                if(number%primeNumbers[i]==0){
                    isPrimeNumber = false;
                    break;
                }
            }
            
            if(isPrimeNumber){
                primeNumbers[last] = number;
                last++;
            }
        }
    }
    
    public boolean isPrimeNumber(String number){        
        long p = Long.parseLong(number);
        if(p==1) return false;
                
        for(int i=0; primeNumbers[i]*primeNumbers[i]<=p; i++){
            if(p%primeNumbers[i]==0) return false;
        }
        
        return true;
    }
}