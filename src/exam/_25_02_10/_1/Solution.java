package _25_02_10._1;

import java.util.PriorityQueue;

public class Solution {
    final int SPACE = 0;
    final int WALL = 1;
    final int START = 2;
    final int GOAL = 3;
    final int INF = Integer.MAX_VALUE/2;

    int height,width;
    int[] dx = {1,0, -1,0};
    int[] dy = {0,1,0, -1};

    boolean isOut(int x,int y){ return 0>x || x>=height || 0>y || y>=width; }

    public int solution(int[][] board, int c) {
        int answer = 0;
        height = board.length;
        width = board[0].length;
        int startX = -1, startY = -1;
        int goalX = -1, goalY = -1;
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if (board[x][y] == START) {
                    startX = x;
                    startY = y;
                } else if (board[x][y] == GOAL) {
                    goalX = x;
                    goalY = y;
                }
            }
        }

        int[][] minCost = new int[height][width];
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                minCost[x][y] = INF;
            }
        }
        PriorityQueue<Point> pq = new PriorityQueue<>();

        pq.offer(new Point(startX, startY, 0));
        minCost[startX][startY] = 0;
        while (!pq.isEmpty()) {
            Point current = pq.poll();

            if (current.cost != minCost[current.x][current.y]) continue;
            if (current.x == goalX && current.y == goalY) {
                answer = current.cost;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (isOut(nx, ny)) continue;

                int nCost = current.cost + 1;
                if (board[nx][ny] == WALL) nCost += c;

                if (minCost[nx][ny] <= nCost) continue;

                pq.offer(new Point(nx, ny, nCost));
                minCost[nx][ny] = nCost;
            }
        }

        return answer;
    }
}

class Point implements Comparable<Point> {
    int x,y,cost;

    Point(int x,int y,int cost){
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Point target){
        return Integer.compare(this.cost,target.cost);
    }
}
