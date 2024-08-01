package _9536;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/9536
 * @title 여우는 어떻게 울지?
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int T;

    private void solve() throws IOException{
        T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++){
            String record = br.readLine();
            Set<String> soundSet = new HashSet<>(100);

            while(true){
                String input = br.readLine();

                if(input.equals("what does the fox say?")) break;

                st = new StringTokenizer(input);
                st.nextToken();
                st.nextToken();

                soundSet.add(st.nextToken());
            }

            st = new StringTokenizer(record);
            StringBuilder sb = new StringBuilder();

            while(st.hasMoreTokens()){
                String sound = st.nextToken();

                if(soundSet.contains(sound)) continue;

                sb.append(sound).append(" ");
            }

            bw.write(sb.toString());
            bw.write("\n");
        }
        bw.flush();
    }


    public void start() throws IOException{
        solve();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

