package _2812;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/2812">크게 만들기</a>
 * @category
 * @Note
 */

public class Main {
    static String answer;
    static int N,K;
    static String inputValue;

    static Deque<Character> answerStack;
    static int index,removeCount;
    static void solution(){
        removeCount=0;
        answerStack = new ArrayDeque<>();

        index=0;
        answerStack.offer(inputValue.charAt(index));
        index++;

        Character number;
        while(index<N){
            number = inputValue.charAt(index);

            while(!answerStack.isEmpty()
                    && answerStack.peekLast()<number
                    && removeCount<K
            ){
                answerStack.pollLast();
                removeCount++;
            }
            answerStack.offerLast(number);

            index++;
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        inputValue = br.readLine();
    }

    static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int answerLength = N-K;
        for(int i=0; i<answerLength; i++){
            bw.write(answerStack.pollFirst());
        }

        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        output();
    }
}