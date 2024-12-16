package _5073;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/5073
 * @title 삼각형과 세 변
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    List<String> triangleList;

    String answer = "";
    private void readInput() throws IOException{
        triangleList = new ArrayList<>();

        String line;
        while(!(line=br.readLine()).equals("0 0 0")){
            triangleList.add(line);
        }
    }

    private void solve(){
        StringBuilder answerBuilder = new StringBuilder();

        for(String triangle : triangleList){
            StringTokenizer st = new StringTokenizer(triangle);

            int[] sides = new int[3];
            for(int i=0; i<3; i++){
                sides[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(sides);
            if(sides[0]+sides[1]<=sides[2]) answerBuilder.append("Invalid");
            else if(sides[0]==sides[1] && sides[1]==sides[2]) answerBuilder.append("Equilateral");
            else if(sides[0]!=sides[1] && sides[1]!=sides[2] && sides[0]!=sides[2]) answerBuilder.append("Scalene");
            else answerBuilder.append("Isosceles");

            answerBuilder.append("\n");
        }

        answer = answerBuilder.toString();
    }

    private void writeOutput() throws IOException{
        bw.write(answer);
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        solve();
        writeOutput();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

