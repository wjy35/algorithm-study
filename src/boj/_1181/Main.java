package _1181;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1181
 * @title 단어 정렬
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N;
    Set<String> words;
    private void readInputAndSolve() throws IOException{
        N = Integer.parseInt(br.readLine());

        words = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length()==o2.length()){
                    return o1.compareTo(o2);
                }

                return Integer.compare(o1.length(),o2.length());
            }
        });

        for(int i=0; i<N; i++){
            words.add(br.readLine());
        }
    }


    private void writeOutput() throws IOException{
        for(String word : words){
            bw.write(word);
            bw.write("\n");
        }
        bw.flush();
    }

    public void start() throws IOException{
        readInputAndSolve();
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

