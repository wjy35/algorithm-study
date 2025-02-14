package _27372;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/27372
 * @title 미니 빙고
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Test.createTest()
                .start();
    }
}

class Test {

    String S;
    Map<Character,Point> charToPoint;

    private void readInput() throws IOException{
        S = br.readLine();

        charToPoint = new HashMap<>();
        for(int i=0; i<3; i++){
            String line = br.readLine();
            for(int j=0; j<3; j++){
                charToPoint.put(line.charAt(j),new Point(i,j));
            }
        }
    }

    char[] seed;
    int[] seedScores;
    private void solve(){
        seed = S.toCharArray();
        seedScores = getScores(seed);

        Arrays.sort(seed);

        for(; !Arrays.equals(seedScores,getScores(seed)); nextPermutation(seed));
    }

    int[] getScores(char[] array){
        int[] scores = new int[9];
        boolean[][] isUsed = new boolean[3][3];

        for(int i=0; i<9; i++){
            Point point = charToPoint.get(array[i]);

            isUsed[point.x][point.y] = true;

            scores[i] = getRoundScore(isUsed,point);
        }

        return scores;
    }

    int getRoundScore(boolean[][] isUsed, Point point){
        int score = 0;

        if(isUsed[point.x][0]==isUsed[point.x][1] && isUsed[point.x][1]==isUsed[point.x][2]) score++;
        if(isUsed[0][point.y]==isUsed[1][point.y] && isUsed[1][point.y]==isUsed[2][point.y]) score++;

        int position = point.x+point.y;

        if(position==1||position==3) return score;

        if(point.x==1){
            if(isUsed[0][0]==isUsed[1][1] && isUsed[1][1]==isUsed[2][2]) score++;
            if(isUsed[2][0]==isUsed[1][1] && isUsed[1][1]==isUsed[0][2]) score++;

            return score;
        }

        if(isUsed[point.x][point.y]==isUsed[1][1] && isUsed[1][1]==isUsed[2-point.x][2-point.y]) score++;

        return score;
    }

    public boolean nextPermutation(char[] array){
        int source = array.length-2;
        while(source>=0 && array[source]>array[source+1]) source--;

        if(source==-1) return false;

        int target = array.length-1;
        while(target>source && array[source]>array[target]) target--;

        swap(array,source,target);

        for(int i=1; source+i<array.length-i; i++){
            swap(array,source+i,array.length-i);
        }

        return true;
    }

    public void swap(char[] array,int i,int j){
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private void writeOutput() throws IOException{
        for(int i=0; i<9; i++){
            bw.write(Integer.toString(seedScores[i]));
        }
        bw.write(" ");
        bw.write(String.valueOf(seed));
        bw.write("\n");
    }

    int testCaseCount;
    private void readTestCaseCount() throws IOException{
        testCaseCount = Integer.parseInt(br.readLine());
    }

    public void start() throws IOException{
        readTestCaseCount();

        for(int i=0; i<testCaseCount; i++){
            readInput();
            solve();
            writeOutput();
        }
        bw.flush();
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
