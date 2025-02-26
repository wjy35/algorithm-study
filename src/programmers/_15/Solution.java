package _15;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        
        int[][] deltaBoard = new int[n+1][m+1];
        for(int[] skillDetail : skill){
            int degree = skillDetail[5];
            
            if(skillDetail[0]==1) degree *= -1; 
            
            int r1 = skillDetail[1]+1;
            int c1 = skillDetail[2]+1;
            int r2 = skillDetail[3]+1;
            int c2 = skillDetail[4]+1;
            
            deltaBoard[r1][c1] += degree;
            if(c2<m) deltaBoard[r1][c2+1] -= degree;
            if(r2<n) deltaBoard[r2+1][c1] -= degree;
            if(r2<n && c2<m) deltaBoard[r2+1][c2+1] += degree;
        }
        
        
        
        int count = n*m;
        for(int i=1; i<=n; i++){
            int[] prefixSum = new int[m+1];
            for(int j=1; j<=m; j++){
                prefixSum[j] = prefixSum[j-1] + deltaBoard[i][j];
                
                deltaBoard[i][j] = prefixSum[j] + deltaBoard[i-1][j];
                
                if(deltaBoard[i][j] + board[i-1][j-1]>0) continue;
                
                count--;
            }
        }
                
        return count;
    }
}