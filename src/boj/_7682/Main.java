package _7682;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/7682
 * @title 틱택토
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    List<String> gameList;
    List<String> validateResult;
    private void readInput() throws IOException{
        gameList = new ArrayList<>();
        String line;
        while(!(line=br.readLine()).equals("end")){
            gameList.add(line);
        }
    }

    private void solve(){
        validateResult = new ArrayList<>();
        for(String game : gameList){
            validateResult.add(validate(gameToCharArray(game)));
        }
    }

    private char[][] gameToCharArray(String game){
        int gameIndex = 0;
        char[][] pan = new char[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                pan[i][j] = game.charAt(gameIndex++);
            }
        }

        return pan;
    }

    private String validate(char[][] pan){
        int oCount = 0;
        int xCount = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(pan[i][j]=='O') oCount++;
                if(pan[i][j]=='X') xCount++;
            }
        }
        if(!(xCount==oCount || xCount==oCount+1)) return "invalid";

        int xCompleteCount = getSymbolCompleteCount('X',pan);
        int oCompleteCount = getSymbolCompleteCount('O',pan);

        if(xCount+oCount==0) return "valid";
        if(xCount+oCount==9 && ((xCompleteCount==0 && oCompleteCount==0) || (xCompleteCount==2 && oCompleteCount==0))) return "valid";
        if(xCompleteCount==1 && oCompleteCount==0 && xCount>oCount) return "valid";
        if(xCompleteCount==0 && oCompleteCount==1 && oCount==xCount) return "valid";

        return "invalid";
    }

    private int getSymbolCompleteCount(char symbol,char[][] pan){
        int completeCount = 0;
        for(int i=0; i<3; i++){
            if(pan[i][0]==symbol && pan[i][1]==symbol && pan[i][2]==symbol) completeCount++;
            if(pan[0][i]==symbol && pan[1][i]==symbol && pan[2][i]==symbol) completeCount++;
        }
        if(pan[0][0]==symbol && pan[1][1]==symbol && pan[2][2]==symbol) completeCount++;
        if(pan[0][2]==symbol && pan[1][1]==symbol && pan[2][0]==symbol) completeCount++;

        return completeCount;
    }

    private void writeOutput() throws IOException{
        for(String validateResult : validateResult){
            bw.write(validateResult);
            bw.write("\n");
        }
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

