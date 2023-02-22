package _20543;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

/**
 * @author 왕준영
 * @see <a href="https://www.acmicpc.net/problem/20543"> 폭탄 던지는 태영이 </a>
 */

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n,m;
    static long[][] h;
    static long[][] g;

    static int start,end,r;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = new long[n][n];
        g = new long[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                h[i][j] = (-1)*Long.parseLong(st.nextToken());
            }
        }
        r = m/2;
        start = r;
        end = n-r-1;

        for(int i=start; i<=end; i++){
            for(int j=start; j<=end; j++){
                g[i][j] = h[i-r][j-r];
                if(i-r>0) g[i][j]-=h[i-r-1][j-r];
                if(j-r>0) g[i][j]-=h[i-r][j-r-1];
                if(i-r>0 && j-r>0) g[i][j] += h[i-r-1][j-r-1];
                if(j-m>=r) g[i][j] += g[i][j-m];
                if(i-m>=r) g[i][j] += g[i-m][j];
                if(i-m>=r&&j-m>=r) g[i][j] -= g[i-m][j-m];
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                bw.write(Long.toString(g[i][j]));
                bw.write(" ");
            }
            bw.write("\n");
        }
        bw.flush();
    }
}

