package _14;

import java.util.*;

class Solution {
    int maxAlp,maxCop;
    
    List<Problem> problemList;
    
    public int solution(int alp, int cop, int[][] problems) {
        problemList = new ArrayList<>();
        problemList.add(new Problem(0,0,0,1,1));
        problemList.add(new Problem(0,0,1,0,1));

        maxAlp = alp;
        maxCop = cop;
        for(int[] problem : problems){
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
            
            if(problem[2]==0 && problem[3]==0) continue;
            problemList.add(new Problem(problem[0],problem[1],problem[2],problem[3],problem[4]));
        }        
        
        return bfs(alp,cop);
    }
    
    public int bfs(int startAlp,int startCop){
        int INF = 301;
        int[][] minCost = new int[maxAlp+1][maxCop+1];
        for(int i=0; i<minCost.length; i++) Arrays.fill(minCost[i],INF);
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        minCost[startAlp][startCop] = 0;
        pq.offer(new Node(startAlp,startCop,0));
        
        while(!pq.isEmpty()){
            Node current = pq.poll();
            
            if(current.cost!=minCost[current.alp][current.cop]) continue;
            if(current.alp>=maxAlp && current.cop>=maxCop) return current.cost;
            
            for(Problem problem : problemList){
                if(current.alp<problem.alpReq) continue;
                if(current.cop<problem.copReq) continue;
                
                int nextAlp = Math.min(maxAlp, current.alp+problem.alpRwd);
                int nextCop = Math.min(maxCop, current.cop+problem.copRwd);
                int nextCost = current.cost + problem.cost;
                if(nextCost >= minCost[nextAlp][nextCop]) continue;
                
                pq.offer(new Node(nextAlp,nextCop,nextCost));
                minCost[nextAlp][nextCop] = nextCost;
            }
        }
        
        return 300;
    }
}


class Node implements Comparable<Node> {
    int alp;
    int cop;
    int cost;
    
    Node(int alp, int cop, int cost){
        this.alp = alp;
        this.cop = cop;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Node target){
        return Integer.compare(this.cost,target.cost);
    }
}

class Problem{
    int alpReq;
    int copReq;
    int alpRwd;
    int copRwd;
    int cost;
    
    Problem(int alpReq, int copReq, int alpRwd, int copRwd, int cost){
        this.alpReq = alpReq;
        this.copReq = copReq;
        this.alpRwd = alpRwd;
        this.copRwd = copRwd;
        this.cost = cost;
    }
}