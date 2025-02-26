package _22;

import java.util.*;

class Solution {
    int n,m;
    final int RED_BALL = 1;
    final int BLUE_BALL = 2;
    final int RED_GOAL = 3;
    final int BLUE_GOAL = 4;
    final int WALL = 5;
    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        
        Point redStart = null;
        Point blueStart = null;
        
        for(int x=0; x<n; x++){
            for(int y=0; y<m; y++){
                if(maze[x][y]==RED_BALL){
                    redStart = new Point(x,y,1<<(x*m+y));
                }else if(maze[x][y]==BLUE_BALL){
                    blueStart = new Point(x,y,1<<(x*m+y));
                }
            }
        }
        
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(redStart,blueStart,0));
        while(!q.isEmpty()){
            Node current = q.poll();   
            
            for(int rd=0; rd<4; rd++){
                for(int bd=0; bd<4; bd++){
                    int rnx = current.red.x;
                    int rny = current.red.y;
                    
                    if(maze[rnx][rny]!=RED_GOAL){
                        rnx += dx[rd];
                        rny += dy[rd];
                    }
                    
                    int bnx = current.blue.x;
                    int bny = current.blue.y;
                    if(maze[bnx][bny]!=BLUE_GOAL){
                        bnx += dx[bd];
                        bny += dy[bd];
                    }

                    if(isOut(rnx,rny) || isOut(bnx,bny)) continue;
                    if(rnx==current.blue.x && rny==current.blue.y 
                       && bnx==current.red.x && bny==current.red.y) continue;
                    if(maze[rnx][rny]==RED_GOAL && maze[bnx][bny]==BLUE_GOAL) return current.count+1;
                    if(maze[rnx][rny]==WALL || maze[bnx][bny]==WALL) continue;
                    if(maze[rnx][rny]!=RED_GOAL && (current.red.bit & (1<<(rnx*m+rny)))>0) continue;
                    if(maze[bnx][bny]!=BLUE_GOAL && (current.blue.bit & (1<<(bnx*m+bny)))>0) continue;
                    if(rnx==bnx && rny==bny) continue;
                    
                    q.offer(new Node(
                        new Point(rnx,rny,current.red.bit | (1<<(rnx*m+rny))),
                        new Point(bnx,bny,current.blue.bit | (1<<(bnx*m+bny))),
                        current.count+1));
                }
            }
            
        }
        
        return 0;
    }
    
    int[] dx = {1,0,-1,0};
    int[] dy = {0,1,0,-1};
    boolean isOut(int x,int y){ return 0>x||x>=n||0>y||y>=m;}
    
    private static class Node{
        Point red,blue;
        int count;
        
        Node(Point red,Point blue,int count){
            this.red = red;
            this.blue = blue;
            this.count = count;
        }
    }
    
    private static class Point{
        int x,y;
        int bit;
        
        Point(int x,int y, int bit){
            this.x = x;
            this.y = y;
            this.bit = bit;
        }
    }
}