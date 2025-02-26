package _3;

import java.util.*;

class Solution {

    private boolean[] flag;
    private int n;
    private int[][] dice;
    
    public int[] solution(int[][] dice) {
        this.dice = dice;
        n = dice.length;
        int endMask = 1<<n;
        
        List<Integer> maxAList = new ArrayList<>();
        int maxAWin = 0;
        
        for(int mask=0; mask<endMask; mask++){
            if(Integer.bitCount(mask)!=n/2) continue;
            
            List<Integer> aList = new ArrayList<>();
            List<Integer> bList = new ArrayList<>();
                
            for(int shift=0; shift<n; shift++){
                if(((1<<shift)&mask)>0){
                    aList.add(shift);
                }else{
                    bList.add(shift);
                }
            }
            
            List<Integer> aCases = createCases(aList);            
            int[] bCaseCount = createPrefixSum(createCases(bList));

            int aWin = 0;
            
            for(Integer aCase : aCases){
                aWin += bCaseCount[aCase-1];
            }
            
            if(aWin>maxAWin){
                maxAWin = aWin;
                maxAList = aList;
            }
        }
        
        int[] answer = new int[maxAList.size()];
        for(int i=0; i<answer.length; i++){
            answer[i] = maxAList.get(i)+1;
        }
        Arrays.sort(answer);
        
        return answer;
    }
    

    public List<Integer> createCases(List<Integer> diceIndexes){        
        List<Integer> current = new ArrayList<>();
        
        for(int number : dice[diceIndexes.get(0)]) current.add(number);
        
        for(int i=1; i<diceIndexes.size(); i++){
            List<Integer> next = new ArrayList<>();
            
            for(int newNumber : dice[diceIndexes.get(i)]){
                for(Integer existNumber : current){
                    next.add(existNumber + newNumber);
                }
            }
                                     
            current = next;
        }
        
        Collections.sort(current);
        return current;
    }
    
    public int[] createPrefixSum(List<Integer> cases) {
        int[] prefixSum = new int[501];
        
        for(Integer sum : cases){
            prefixSum[sum]++;
        }
            
        for(int i=1; i<=500; i++){
            prefixSum[i] = prefixSum[i-1]+prefixSum[i];
        }
        
        return prefixSum;
    }
}