package _9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * https://www.acmicpc.net/problem/9935
 * 문자열 폭발
 */

public class Main {
    static String str;
    static String key;
    static int str_length,key_length;
    static Stack<Character> stack;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        key = br.readLine();
        str_length = str.length();
        key_length = key.length();
        stack = new Stack<>();
        int j;
        for(int i=0; i<str_length; i++){
            stack.push(str.charAt(i));
            if(stack.size()<key_length) continue;
            for(j=0; j<key_length; j++){
                if(stack.get(stack.size()-key_length+j)!=key.charAt(j)) break;
            }
            if(j==key_length){
                for(j=0; j<key_length; j++){
                    stack.pop();
                }
            }
        }
        if(stack.isEmpty()) {
            stack.push('F');
            stack.push('R');
            stack.push('U');
            stack.push('L');
            stack.push('A');
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<stack.size(); i++){
            sb.append(stack.get(i));
        }
        System.out.println(sb);

    }
}