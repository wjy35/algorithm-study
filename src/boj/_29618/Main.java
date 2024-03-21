package _29618;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/29618">미술 시간</a>
 * @category 
 * @Note
 */
public class Main {
    static int[] answer;

    static int N,Q;

    static int[] disjointSet;
    static void readAndUpdate() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        answer = new int[N+1];

        disjointSet = new int[N+2];
        for(int i=1; i<=N+1; i++){
            disjointSet[i] = i;
        }

        for(int i=0; i<Q; i++){
            st = new StringTokenizer(br.readLine());
            query(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()
                    ));
        }
    }

    static void query(int a,int b,int x){
        int i=a;
        while(i<=b){
            if(disjointSet[i]==i){
                answer[i] = x;
                disjointSet[i] = findRoot(i+1);
            }
            findRoot(i);
            i = disjointSet[i];
        }
    }

    static int findRoot(int i){
        if(disjointSet[i]==i) return i;

        return disjointSet[i] = findRoot(disjointSet[i]);
    }

    static void write() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i=1; i<=N; i++){
            bw.write(Integer.toString(answer[i]));
            bw.write(" ");
        }
        bw.flush();
    }

    public static void main(String[] args) throws IOException {
        readAndUpdate();
        write();
    }
}
