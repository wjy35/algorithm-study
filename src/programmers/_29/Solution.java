package _29;

import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {      
        List<Expression> unknowns = new ArrayList<>();
        
        boolean[] isPossible = new boolean[10];
        Arrays.fill(isPossible,2,10,true);
        
        for(int i=0; i<expressions.length; i++){
            StringTokenizer st = new StringTokenizer(expressions[i]);
            
            String A = st.nextToken();
            String op = st.nextToken();
            String B = st.nextToken();
            st.nextToken();
            String C = st.nextToken();
 
            Expression expression = new Expression(A,op,B,C);
            
            if("X".equals(expression.C)){
                unknowns.add(expression);
                for(int radix=2; radix<10; radix++){
                  if (expression.isPossibleLeft(radix)) {
                    continue;
                  }
                
                    isPossible[radix] = false;
                }
            }else{
                for(int radix=2; radix<10; radix++){
                  if (expression.isPossible(radix)) {
                    continue;
                  }
                
                    isPossible[radix] = false;
                }
            }
        }
        
        List<Integer> radixes = new ArrayList<>();
                
        for(int radix=2; radix<10; radix++){
          if (isPossible[radix]) {
            radixes.add(radix);
          }
        }
        
        List<String> answers = new ArrayList<>();
        for(Expression expression : unknowns){
            answers.add(expression.toString(radixes));
        }
                
        String[] answer = new String[answers.size()];
        for(int i=0; i<answer.length; i++){
            answer[i] = answers.get(i);
        }
        
        return answer;
    }
}

class Expression{
    String A;
    String B;
    String op;
    String C;
    
    Expression(String A,String op,String B,String C){
        this.A = A;
        this.B = B;
        this.op = op;
        this.C = C;
    }
    
    boolean isPossible(int radix){
        try{
            int a = Integer.parseInt(A,radix);
            int b = Integer.parseInt(B,radix);
            int c = Integer.parseInt(C,radix);
            
            return "+".equals(op) ? a+b==c : a-b==c;
        }catch(Exception e){
            return false;
        }
    }
    
    boolean isPossibleLeft(int radix){
        try{
            int a = Integer.parseInt(A,radix);
            int b = Integer.parseInt(B,radix);
        }catch(Exception e){
            return false;
        }
        
        return true;
    }
    
    int calculate(int radix){
        int a = Integer.parseInt(A,radix);
        int b = Integer.parseInt(B,radix);
        
        return "+".equals(op) ? a+b:a-b;
    }

    String toString(List<Integer> radixes){
        StringBuilder sb = new StringBuilder();
        
        sb.append(A).append(" ")
            .append(op).append(" ")
            .append(B).append(" ")
            .append("=").append(" ");
        
        int radix = radixes.get(0);
        String result = Integer.toString(calculate(radix),radix);
        
        
        for(int i=1; i<radixes.size(); i++){
            radix = radixes.get(i);

          if (result.equals(Integer.toString(calculate(radix), radix))) {
            continue;
          }
            return sb.append("?").toString();
        }
        
        return sb.append(result).toString();
    }
}