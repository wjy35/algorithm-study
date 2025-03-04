package _30;

class Solution {
    private boolean[][][] cache;
    private int[][] info;
    private int n;
    private int m;
    
    public int solution(int[][] info, int n, int m) {
        this.info = info;
        this.n = n;
        this.m = m;
        this.cache = new boolean[121][121][info.length+1];
        
        dfs(0,0,0);
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(cache[i][j][info.length]) { return i;}
            }
        }
        
        return -1;
    }
    
    public void dfs(int aCount,int bCount,int i){
      if (cache[aCount][bCount][i]) {
        return;
      }
        cache[aCount][bCount][i] = true;

      if (i == info.length) {
        return;
      }

      if (aCount + info[i][0] < n) {
        dfs(aCount + info[i][0], bCount, i + 1);
      }
      if (bCount + info[i][1] < m) {
        dfs(aCount, bCount + info[i][1], i + 1);
      }
    }
    
}