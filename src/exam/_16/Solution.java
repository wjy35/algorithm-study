package _16;

import java.util.*;

class Solution {
    
    List<Integer>[] edgeLists;
    public int solution(int[] info, int[][] edges) {
        int n = info.length;
        
        edgeLists = new List[n];
        for(int i=0; i<n; i++) edgeLists[i] = new ArrayList<>();
        for(int[] edge : edges){
            edgeLists[edge[0]].add(edge[1]);
            edgeLists[edge[1]].add(edge[0]);
        }
        
        int max = 1;
        
        boolean[][] isVisited = new boolean[1<<n][n];
        Queue<Node> q = new ArrayDeque<>();
        
        isVisited[0][1] = true;
        q.offer(new Node(0,1,1,0));
        
        while(!q.isEmpty()){
            Node current = q.poll();
            for(Integer next : edgeLists[current.number]){                
                int nextVisited = (current.visited | (1<<next));
                int nextSheep = current.sheep;
                int nextWolf = current.wolf;
                
                if((current.visited & (1<<next))==0){
                    if(info[next]==1) nextWolf++;
                    else nextSheep++;
                }
                             
                int nextDiff= nextSheep - nextWolf;
                if(nextDiff<=0) continue;
                if(isVisited[nextVisited][next]) continue;

                max = Math.max(max,nextSheep);
                isVisited[nextVisited][next] = true;
                q.offer(new Node(next,nextVisited,nextSheep,nextWolf));
            }
        }
                    
        return max;
    }
}

class Node{
    int number;
    int visited;
    int sheep;
    int wolf;
    
    Node(int number, int visited,int sheep,int wolf){
        this.number = number;
        this.visited = visited;
        this.sheep = sheep;
        this.wolf = wolf;
    }
}
