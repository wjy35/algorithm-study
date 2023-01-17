package _9251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * https://www.acmicpc.net/problem/9251
 * LCS
 */

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String str1,str2;
        int length1,length2;
        str1 = br.readLine();
        str2 = br.readLine();
        length1 = str1.length();
        length2 = str2.length();
        int[][] lcs = new int[length1+1][length2+1];

        for(int i=1; i<=length1; i++){
            for(int j=1; j<=length2; j++){
                if(str1.charAt(i-1)==str2.charAt(j-1))
                    lcs[i][j] = lcs[i-1][j-1]+1;
                else
                    lcs[i][j] = Math.max(lcs[i-1][j],lcs[i][j-1]);
            }
        }
        System.out.println(lcs[length1][length2]);

    }
}