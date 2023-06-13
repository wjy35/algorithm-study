package _1976;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/1976">여행 가자</a>
 * @category union find, bfs
 * @Note
 */

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int n,m;

    static int[] root;
    static int startCity;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        makeSet();

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                if(Integer.parseInt(st.nextToken())==1) {
                    union(i,j);
                }
            }
        }

        if(isInSameRoot()) bw.write("YES");
        else bw.write("NO");

        bw.flush();
    }

    static boolean isInSameRoot() throws Exception {

        st = new StringTokenizer(br.readLine());
        startCity = find(Integer.parseInt(st.nextToken()));

        for(int i=1; i<m; i++){
            if(startCity != find(Integer.parseInt(st.nextToken()))) return false;
        }
        return true;
    }

    static void makeSet(){
        root = new int[n+1];

        for(int i=1; i<=n; i++){
            root[i] = i;
        }
    }

    static void union(int x1,int x2){
        int r1 = find(x1);
        int r2 = find(x2);

        if(r1==r2) return;

        root[r1]=r2;
    }

    static int find(int x){
        if(root[x]==x) return x;

        return root[x] = find(root[x]);
    }

}




