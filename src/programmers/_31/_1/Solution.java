package _31._1;

import java.util.*;

class Solution {
    int n,m;
    char[][] storage;
    boolean[][] isRemoved;
    Map<Character,List<Point>> alphaToPoints;
    
    public int solution(String[] storage, String[] requests) {  
        this.n = storage.length;
        this.m = storage[0].length();
        this.storage = new char[n][m];
        this.isRemoved = new boolean[n][m];
      for (int i = 0; i < n; i++) {
        this.storage[i] = storage[i].toCharArray();
      }
        
        alphaToPoints = new HashMap<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                Character target = this.storage[i][j];
                
                this.alphaToPoints
                    .computeIfAbsent(target,(key)->new ArrayList<>())
                    .add(new Point(i,j));
            }
        }
        
        for(String request : requests){

          if (request.length() == 1) {
            updateByBFS(request.charAt(0));
          } else {
            updateByMap(request.charAt(0));
          }
        }
        
        int answer = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
              if (isRemoved[i][j]) {
                continue;
              }

                answer++;
            }
        }
        
        return answer;
    }
    
    void print(){
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                System.out.print(isRemoved[i][j]+ " ");
            }
            System.out.println();
        }
                    System.out.println();

    }

    int[] dx = {1,0,-1,0};
    int[] dy = {0,1,0,-1};
    boolean isOut(int x,int y){
        return 0>x || x>=n || 0>y || y>=m;
    }
    
    void updateByBFS(char target){        
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] isVisited = new boolean[n][m];
        
        for(int i=0; i<m; i++){
            q.offer(new Point(-1,i));
            q.offer(new Point(n,i));
        }
        
        for(int i=0; i<n; i++){
            q.offer(new Point(i,-1));
            q.offer(new Point(i,m));
        }
        
        while(!q.isEmpty()){
            Point current = q.poll();
            
            for(int i=0; i<4; i++){
                int nx = current.x+dx[i];
                int ny = current.y+dy[i];

              if (isOut(nx, ny)) {
                continue;
              }
              if (isVisited[nx][ny]) {
                continue;
              }
                if(isRemoved[nx][ny]){
                    isVisited[nx][ny] = true;
                    q.offer(new Point(nx,ny));
                    continue;
                }
                
                if(storage[nx][ny]==target){
                    isRemoved[nx][ny] = true;
                    isVisited[nx][ny] = true;
                }
            }
        }
    }
    
    void updateByMap(char target){
        for(Point point : alphaToPoints.getOrDefault(target,new ArrayList<>())){
            isRemoved[point.x][point.y] = true;
        }
    }
}

class Point{
    int x,y;
    
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}