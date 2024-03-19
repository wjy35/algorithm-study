package _24337;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/24337">가희와 탑</a>
 * @category Greedy
 * @Note
 */
public class Main {
    static String answer;

    static int N,a,b;
    static final String NO_NUMBER_OF_CASES = "-1";
    
    static void solution(){
        if(a+b-1>N){
            answer = NO_NUMBER_OF_CASES;
            return;
        }

        StringBuilder answerSb = new StringBuilder();
        StringBuilder otherSb = new StringBuilder();
        StringBuilder aSb = new StringBuilder();
        StringBuilder bSb = new StringBuilder();

        for(int i=1; i<a; i++) aSb.append(i).append(" ");
        for(int i=b-1; i>0; i--) bSb.append(i).append(" ");
        N = N-a-b+1;
        for(int i=0; i<N; i++) otherSb.append(1).append(" ");

        int top = Math.max(a,b);
        if(a==1){
            answerSb.append(top).append(" ");
            answerSb.append(otherSb);
            answerSb.append(bSb);
        }else{
            answerSb.append(otherSb);
            answerSb.append(aSb);
            answerSb.append(top).append(" ");
            answerSb.append(bSb);
        }

        answer = answerSb.toString();
    }
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
    }

    static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        bw.write(answer);
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        output();
    }
}
