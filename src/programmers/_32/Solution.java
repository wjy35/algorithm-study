package _32;

class Solution {
    int n;
    int[][] q;
    int[] ans;

    int[] numbers = new int[6];
    int count = 0;

    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.ans = ans;

        dfs(1);
        return count;
    }


    void dfs(int i){
        if(i==6){
          if (isPossible()) {
            count++;
          }

            return;
        }

        for(numbers[i]=numbers[i-1]+1; numbers[i]<=n; numbers[i]++){
            dfs(i+1);
        }
    }

    boolean isPossible(){
        for(int i=0; i<q.length; i++){
            int equalCount = 0;

            for(int number : q[i]){
                for(int j=1; j<=5; j++){
                  if (number == numbers[j]) {
                    equalCount++;
                  }
                }
            }

          if (equalCount != ans[i]) {
            return false;
          }
        }

        return true;
    }
}