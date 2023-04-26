package _18119;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/"></a>
 * @category
 * @Note
 */
public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int[] list;
    static int status;
    static int n,m;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new int[n];
        status = (1 << 26) -1;
        for(int i=0; i<n; i++){
            list[i]=zip(br.readLine());
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            status = status ^ ( 1 << (st.nextToken().charAt(0)-'a'));
            bw.write(Integer.toString(count()));
            bw.write("\n");
        }
        bw.flush();
    }

    // 단어에 포함된 알파벳을 비트마스킹
    public static int zip(String word){
        int zipWord = 0;
        for(int i=0; i<word.length(); i++){
          zipWord = zipWord | (1 << (word.charAt(i)-'a'));
        }
        return zipWord;
    }

    // 기억하고 있는 단어의 개수 반환
    public static int count(){
        int before,after;
        int count=0;
        before = Integer.bitCount(status);

        for(int i=0; i<n; i++){
            after = Integer.bitCount(list[i] | status);
            if(before==after) count++;
        }
        return count;
    }



}