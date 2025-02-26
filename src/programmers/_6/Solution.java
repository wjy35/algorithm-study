package _6;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i=0; i<numbers.length; i++){
            answer[i] = isPossible(Long.toBinaryString(numbers[i]));
        }
        return answer;
    }
    
    private int isPossible(String binaryNumber){
        int treeLength = 1;
        int length = binaryNumber.length();
        
        while(treeLength-1<length) treeLength = treeLength<<1;
        treeLength--;

        int[] tree = new int[treeLength];        
        for(int i=1; i<=binaryNumber.length(); i++){
            tree[tree.length-i] = binaryNumber.charAt(binaryNumber.length()-i)-'0';
        }

        return isPossible(0,treeLength-1,tree);
    }
    
    private int isPossible(int start,int end,int[] tree){
        if(start>=end) return 1;
        
        int mid = (start+end)/2;
        if(tree[mid]==0){
            for(int i=start; i<=end; i++){
                if(i==mid) continue;
                if(tree[i]==1) return 0;
            }
            
            return 1;
        }
        
        if(isPossible(start,mid-1,tree)==0) return 0;
        if(isPossible(mid+1,end,tree)==0) return 0;
        
        return 1;
    }
    
}