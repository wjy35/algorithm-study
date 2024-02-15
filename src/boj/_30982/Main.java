package _30982;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/30982">폭탄주를 피해라! 파란댕댕이!</a>
 * @category 
 * @Note
 */
public class Main {
    static String answer;

    static int N,M,T;
    static int[] Q;
    static int P;

    static boolean[][] leftTable;
    static boolean[][] rightTable;

    static void solution(){
        answer = "NO";

        M = M-Q[P];
        if(M<0) return;
        if(M==0){
            answer = "YES";
            return;
        }

        initLeftTable();
        initRightTable();
        if(isGroupingPossible()) answer = "YES";
    }

    static boolean isGroupingPossible(){
        for(int count=T; count>0; count-=2){
            if(isGroupingPossible(count)){
                return true;
            }
        }

        return false;
    }

    static boolean isGroupingPossible(int count){
        return isGroupingPossible((T-count)/2,count) || isGroupingPossible(count,(T-count)/2);
    }

    static boolean isGroupingPossible(int leftCount,int rightCount){
        int maxLeftCount = leftCount;
        if(maxLeftCount>=P) maxLeftCount = P-1;

        int maxRightCount = rightCount;
        if(maxRightCount>N-P) maxRightCount = N-P;

        for(int i=0; i<=M; i++){
            if(leftTable[maxLeftCount][i] && M>=i && rightTable[maxRightCount][M-i]) return true;
        }

        return false;
    }

    static void initLeftTable(){
        leftTable = new boolean[P][M+1];

        leftTable[0][0] = true;
        for(int i=1; i<leftTable.length; i++){
            for(int j=0; j<=M; j++){
                if(leftTable[i-1][j]) {
                    leftTable[i][j] = true;
                    continue;
                }
                if(j>=Q[P-i] && leftTable[i-1][j-Q[P-i]] ) leftTable[i][j] = true;
            }
        }
    }

    static void initRightTable(){
        rightTable = new boolean[N-P+1][M+1];

        rightTable[0][0] = true;
        for(int i=1; i<rightTable.length; i++){
            for(int j=0; j<=M; j++){
                if(rightTable[i-1][j]){
                    rightTable[i][j] = true;
                    continue;
                }
                if(j>=Q[P+i] && rightTable[i-1][j-Q[P+i]]) rightTable[i][j] = true;
            }
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Q = new int[N+1];
        for(int i=1; i<=N; i++){
            Q[i] = Integer.parseInt(st.nextToken());
        }

        P = Integer.parseInt(br.readLine());
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
