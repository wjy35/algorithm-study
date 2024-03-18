package _1654;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/">랜선 자르기</a>
 * @category 
 * @Note
 */
public class Main {
    static long answer;

    static int N,K;
    static int[] lan;
    
    static void solution(){
        long l=1;
        long r= Integer.MAX_VALUE;
        long mid;
        long count;
        long tmp = 1;

        while(l<=r){
            mid = (l+r)/2;
            count = 0;

            for(int i=0; i<K; i++){
                count += lan[i]/mid;
            }

            if(count>=N){
                tmp = mid;
                l = mid+1;
            }else {
                r = mid-1;
            }
        }

        answer = tmp;
    }
    
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        lan = new int[K];
        for(int i=0; i<K; i++){
            lan[i] = Integer.parseInt(br.readLine());
        }
    }

    static void output() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        bw.write(Long.toString(answer));
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        output();
    }
}
