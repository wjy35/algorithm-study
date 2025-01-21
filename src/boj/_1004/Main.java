package _1004;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1004
 * @title 어린 왕자
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Test.createTest()
                .start();
    }
}

class Test {
    int requiredCount;
    private void readInputAndSolve() throws IOException{
        st = new StringTokenizer(br.readLine());
        Point start = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        Point end = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));

        requiredCount = 0;
        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            Circle circle = new Circle(x,y,r);

            int count = 0;
            if(circle.istIn(start)) count++;
            if(circle.istIn(end)) count++;

            if(count==1) requiredCount++;
        }
    }

    private void writeOutput() throws IOException{
        bw.write(Integer.toString(requiredCount));
        bw.write("\n");
        bw.flush();
    }

    int testCaseCount;
    private void readTestCaseCount() throws IOException{
        testCaseCount = Integer.parseInt(br.readLine());
    }

    public void start() throws IOException{
        readTestCaseCount();

        for(int i=0; i<testCaseCount; i++){
            readInputAndSolve();
            writeOutput();
        }
    }

    private static class Circle extends Point{
        int r;

        Circle(int x,int y, int r) {
            super(x,y);
            this.r = r;
        }

        public boolean istIn(Point target){
            int xDiff = target.x-this.x;
            int yDiff = target.y-this.y;
            return xDiff*xDiff+yDiff*yDiff<r*r;
        }
    }

    private static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Test(){}
    public static Test createTest(){
        return new Test();
    }
}
