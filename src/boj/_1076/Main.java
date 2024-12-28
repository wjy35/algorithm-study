package _1076;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1076
 * @title
 * @algorithm 
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    String[] colors;
    private void readInput() throws IOException{
        colors = new String[3];
        for(int i=0; i<3; i++){
            colors[i] = br.readLine();
        }
    }

    Map<String,Long> colorToValue;
    Map<String,Long> colorToFactor;

    long ohm = 0;
    private void solve(){
        colorToValue = new HashMap<>();
        colorToFactor = new HashMap<>();

        put("black",0,1);
        put("brown",1,10);
        put("red",2,100);
        put("orange",3,1_000);
        put("yellow",4,10_000);
        put("green",5,100_000);
        put("blue",6,1_000_000);
        put("violet",7,10_000_000);
        put("grey",8,100_000_000);
        put("white",9,1_000_000_000);

        ohm = (10*colorToValue.get(colors[0]) + colorToValue.get(colors[1])) * colorToFactor.get(colors[2]);
    }

    private void put(String color, long value, long factor){
        colorToValue.put(color,value);
        colorToFactor.put(color,factor);
    }

    private void writeOutput() throws IOException{
        bw.write(Long.toString(ohm));
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

