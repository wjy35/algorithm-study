package _11;

import java.util.*;

class Solution {
    int n;
    int[] gates;
    boolean[] isGate;
    boolean[] isSummit;
    
    List<Node>[] edgeLists;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        this.n = n;
        
        Arrays.sort(gates);
        this.gates = gates;
        
        isGate = new boolean[n+1];
        for(int gate : gates) isGate[gate] = true;
        
        
        isSummit = new boolean[n+1];
        for(int summit : summits) isSummit[summit] = true;
        
        edgeLists = new List[n+1];
        for(int i=1; i<=n; i++) edgeLists[i] = new ArrayList<>();
        
        for(int[] path : paths){
            edgeLists[path[0]].add(new Node(path[1],path[2]));
            edgeLists[path[1]].add(new Node(path[0],path[2]));
        }
        
        int minIntensity = 10_000_001;
        int minIntensitySumit = 50_001;
        
        int l = 0;
        int r = 10_000_000;
        while(l<=r){
            int intensity = (l+r)/2;
            
            int endSumit = getEndSummit(intensity);
            if(endSumit==50_001){
                l = intensity+1;
            }else{
                r = intensity-1;
                
                minIntensity = intensity;
                minIntensitySumit = endSumit;
            }
        }
        
        int[] answer = {minIntensitySumit,minIntensity};
        return answer;
    }
    
    public int getEndSummit(int limit){
        boolean[] isVisited = new boolean[n+1];
        Queue<Integer> q = new ArrayDeque<>();
        
        for(int gate : gates){
            isVisited[gate] = true;
            q.offer(gate);
        }
        
        int minSummit = 50_001;
        while(!q.isEmpty()){
            int current = q.poll();
            
            for(Node edge : edgeLists[current]){
                if(edge.w>limit) continue;
                if(isVisited[edge.to]) continue;
                if(isGate[edge.to]) continue;
                
                if(isSummit[edge.to]) {
                    minSummit = Math.min(minSummit,edge.to);
                    continue;
                }
                
                q.offer(edge.to);
                isVisited[edge.to] = true;
            }
        }
        
        return minSummit;
    }
}

class Node{
    int to;
    int w;
    
    Node(int to, int w){
        this.to = to;
        this.w = w;
    }
}