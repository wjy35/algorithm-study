package _19591;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * https://www.acmicpc.net/problem/19591
 * 독특한 계산기
 */

public class Main {
    static final String delOper= "0123456789";   
    static final String delNumber= "*+-/";


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Long> number = new LinkedList<>();
        Deque<String> oper = new LinkedList<>();
        String input = br.readLine();
        String first,last;
        long firstResult,lastResult;
        int firstPriority,lastPriority;

        StringTokenizer st = new StringTokenizer(input,delOper);
        while(st.hasMoreTokens()){
            oper.offer(st.nextToken());
        }

        st = new StringTokenizer(input,delNumber);
        while(st.hasMoreTokens()){
            number.add(Long.parseLong(st.nextToken()));
        }

        if(input.charAt(0)=='-') {
            number.set(0,number.get(0)*-1);
            oper.pollFirst();
        }
        while(!oper.isEmpty()) {
            first = oper.peekFirst();
            last = oper.peekLast();
            firstPriority = getPriority(first);
            lastPriority = getPriority(last);
            firstResult = calc(number.get(0), number.get(1), firstPriority, first);
            lastResult = calc(number.get(number.size() - 2), number.get(number.size() - 1), lastPriority, last);

            if (firstPriority == lastPriority) {
                if (firstResult < lastResult) {
                    oper.pollLast();
                    number.pollLast();
                    number.pollLast();
                    number.offerLast(lastResult);
                } else {
                    oper.pollFirst();
                    number.pollFirst();
                    number.pollFirst();
                    number.offerFirst(firstResult);
                }
            } else if (firstPriority > lastPriority) {
                oper.pollFirst();
                number.pollFirst();
                number.pollFirst();
                number.offerFirst(firstResult);
            } else {
                oper.pollLast();
                number.pollLast();
                number.pollLast();
                number.offerLast(lastResult);
            }
        }
        System.out.println(number.get(0));
    }
    static long calc(long a,long b,int priority,String oper){
        if(priority == 1){
            if("*".equals(oper)){
                return a*b;
            }
            return a/b;
        }

        if("+".equals(oper)){
            return a+b;
        }
        return a-b;
    }

    static int getPriority(String op){
        if("*/".contains(op)) return 1;
        return 0;
    }

}