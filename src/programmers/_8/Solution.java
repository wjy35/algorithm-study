package _8;

class Solution {
    
    String answer;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {   
        if((getMht(x,y,r,c)-k)%2!=0) return "impossible";
        if((getMht(x,y,r,c)-k)>0) return "impossible";
        
        StringBuilder sb = new StringBuilder();        
        while(k>0){
            int last = k-getMht(x,y,r,c);
            
            if(x<n && (r>x || last>0)){
                x++;
                k--;
                sb.append("d");
            } else if(y>1 && (y>c || last>0)){
                y--;
                k--;
                sb.append("l");
            } else if(y<m && (y<c || last>0)){
                y++;
                k--;
                sb.append("r");
            }else if(x>1){
                x--;
                k--;
                sb.append("u");
            } else{
                k-=2;
                sb.append("rl");
            } 
        }
        
        return sb.toString();
    }
    
    public int getMht(int x,int y,int r,int c){
        return Math.abs(x-r) + Math.abs(y-c);
    }
}