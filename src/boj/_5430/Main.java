package _5430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * https://www.acmicpc.net/problem/5430
 * AC
 */

public class Main {
    static int T;
    static String cmd;
    static char[] cmdd;

    static boolean isReverse;
    static int n,length,size;
    static LinkedList<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case<=T; test_case++){
            int i;
            cmd = br.readLine();
            cmdd = cmd.toCharArray();
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(),"[],");
            length = cmd.length();
            list = new LinkedList<>();

            for(i=0; i<n; i++){
                list.add(Integer.parseInt(st.nextToken()));
            }

            isReverse = false;

            for(i=0; i<length; i++){
                if(cmd.charAt(i)=='R'){
                    isReverse = !isReverse;
                }
                else{
                    if(list.isEmpty()){
                        break;
                    }
                    if(isReverse){
                        list.removeLast();
                    }
                    else{
                        list.removeFirst();
                    }
                }
            }

            sb = new StringBuilder();
            if(i==length){
                sb.append("[");
                size = list.size();

                if(size>0){
                    if(isReverse) {

                        sb.append(list.removeLast());

                        while(!list.isEmpty()) {
                            sb.append(",").append(list.removeLast());
                        }
                    }
                    else{
                        sb.append(list.removeFirst());
                        while(!list.isEmpty()) {
                            sb.append(",").append(list.removeFirst());
                        }
                    }

                }
                sb.append("]");
            }
            else{
                sb.append("error");
            }
            System.out.println(sb);
        }
    }
}
