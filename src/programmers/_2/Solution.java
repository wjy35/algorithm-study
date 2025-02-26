package _2;

import java.util.*;

class Solution {
    List<Integer>[] edgeLists;
    int[] inputEdgeCount;
    boolean[] isVisited;

    final int DONUT = 1;
    final int STICK = 2;
    final int EIGHT = 3;

    public int[] solution(int[][] edges) {
        edgeLists = new List[1_000_001];
        inputEdgeCount = new int[1_000_001];
        isVisited = new boolean[1_000_001];

        for(int node=1; node<edgeLists.length; node++){
            edgeLists[node] = new ArrayList<>();
        }

        for(int[] edge : edges){
            edgeLists[edge[0]].add(edge[1]);
            inputEdgeCount[edge[1]]++;
        }

        int midNode = 0;
        for(int node=1; node<1_000_001; node++){
            if(inputEdgeCount[node]!=0) continue;
            if(edgeLists[node].size()<2) continue;

            midNode = node;
            break;
        }

        int[] answer = new int[4];
        answer[0] = midNode;

        for(Integer startNode : edgeLists[midNode]){
            answer[getType(startNode)]++;
        }

        return answer;
    }

    public int getType(int startNode){
        int edgeCount = 0;
        int nodeCount = 0;
        Queue<Integer> q = new ArrayDeque<>();

        q.offer(startNode);
        isVisited[startNode] = true;

        while(!q.isEmpty()){
            int currentNode = q.poll();

            nodeCount++;
            edgeCount += edgeLists[currentNode].size();
            for(Integer nextNode : edgeLists[currentNode]){
                if(currentNode==nextNode) continue;
                if(isVisited[nextNode]) continue;

                isVisited[nextNode] = true;
                q.offer(nextNode);
            }
        }

        if(nodeCount==edgeCount) return DONUT;
        if(nodeCount-1==edgeCount) return STICK;

        return EIGHT;
    }
}