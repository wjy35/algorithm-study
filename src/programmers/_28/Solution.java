package _28;

import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        Queue<Robot> q = new ArrayDeque<>();
        for(int i=0; i<routes.length; i++){
            int[] startPoint = points[routes[i][0]-1];
            
            q.offer(new Robot(startPoint[0],startPoint[1],routes[i],0));    
        }
        
        int answer = 0;        
        while(!q.isEmpty()){
            Map<Integer,Integer> hashToCount = new HashMap<>();
            for(Robot robot : q){
                int hash = robot.r*1000+robot.c;
                hashToCount.put(hash,hashToCount.getOrDefault(hash,0)+1);
            }
                        
            for(Integer count : hashToCount.values()){
              if (count > 1) {
                answer++;
              }
            }
            
            int size = q.size();
            for(int i=0; i<size; i++){
                Robot robot = q.poll();
              if (robot.counter < robot.route.length - 1) {
                q.offer(robot);
              }
            }
            
            size = q.size();
            for(int i=0; i<size; i++){
                Robot robot = q.poll();
                
                int nextPoint = robot.route[robot.counter+1];
                int nextR = points[nextPoint-1][0];
                int nextC = points[nextPoint-1][1];

              if (robot.r != nextR) {
                robot.r += robot.r < nextR ? 1 : -1;
              } else {
                robot.c += robot.c < nextC ? 1 : -1;
              }

              if (robot.r == nextR && robot.c == nextC) {
                robot.counter++;
              }
                
                q.offer(robot);
            }
        }
        
        return answer;
    }
    
}

class Robot{
    int r,c;
    int[] route;
    int counter;
    
    Robot(int r, int c,int[] route, int counter){
        this.r = r;
        this.c = c;
        this.route = route;
        this.counter = counter;
    }
}