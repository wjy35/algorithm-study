package _30912;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/30912
 * @title 위잉위잉
 * @algorithm Implement
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .readInput()
                .solve()
                .writeOutput();
    }
}

class Solution{

    int N;
    Point[] points;

    long hx,hy;

    public Solution readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        points = new Point[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            points[i] = new Point(Long.parseLong(st.nextToken()),Long.parseLong(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        hx = Long.parseLong(st.nextToken());
        hy = Long.parseLong(st.nextToken());

        return this;
    }

    public Solution solve(){
        for(int i=0; i<N; i++) points[i].setQuadrant(hx,hy);

        Comparator<Point> comparator = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.quadrant==o2.quadrant){
                    long left = (o1.y-hy)*(o2.x-hx);
                    long right = (o1.x-hx)*(o2.y-hy);

                    if(left==right) return Long.compare((o1.x-hx)*(o1.x-hx)+(o1.y-hy)*(o1.y-hy),(o2.x-hx)*(o2.x-hx)+(o2.y-hy)*(o2.y-hy));

                    return Long.compare(left,right);
                }

                return Integer.compare(o1.quadrant,o2.quadrant);
            }
        };

        Arrays.sort(points,comparator);

        return this;
    }

    public void writeOutput() throws IOException{
        for(int i=0; i<N; i++){
            bw.write(Long.toString(points[i].x));
            bw.write(" ");
            bw.write(Long.toString(points[i].y));
            bw.write("\n");
        }
        bw.flush();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }

    static class Point{
        long x,y;
        int quadrant;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
            this.quadrant = Integer.MAX_VALUE;
        }

        public void setQuadrant(long avgX,long avgY) {
            if(avgX==this.x && avgY<this.y) quadrant = 1;
            if(this.x<avgX && avgY<this.y) quadrant = 2;
            if(this.x<avgX && avgY==this.y) quadrant = 3;
            if(this.x<avgX && avgY>this.y) quadrant = 4;
            if(this.x==avgX && this.y<avgY) quadrant = 5;
            if(this.x>avgX && this.y<avgY) quadrant = 6;
            if(this.x>avgX && this.y==avgY) quadrant = 7;
            if(this.x>avgX && this.y>avgY) quadrant = 8;
        }
    }
}

