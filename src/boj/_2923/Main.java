package _2923;

import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/2923">숫자 게임</a>
 * @category 그리디, 정렬, 투 포인터
 * @Note
 */

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n;
    static int[] aCount;
    static int[] bCount;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        aCount = new int[100];
        bCount = new int[100];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            aCount[Integer.parseInt(st.nextToken())]++;
            bCount[Integer.parseInt(st.nextToken())]++;

            bw.write(Integer.toString(getMinSum()));
            bw.write("\n");
        }

        bw.flush();
    }

    public static int getMinSum(){
        int[] copyedACount = Arrays.copyOf(aCount, aCount.length);
        int[] copyedBCount = Arrays.copyOf(bCount, bCount.length);
        int minSum = 0;

        int l=1,r=99;

        while(true){
            while(l<100 && copyedACount[l]==0){
                l++;
            }
            while(r>0 && copyedBCount[r]==0){
                r--;
            }
            if(l>99 || r<1){
                break;
            }
            if(copyedACount[l]>copyedBCount[r]){
                copyedACount[l] -= copyedBCount[r];
                copyedBCount[r] = 0;
                minSum = Math.max(minSum,l+r);
            }else{
                copyedBCount[r] -= copyedACount[l];
                copyedACount[l] = 0;
                minSum = Math.max(minSum,l+r);
            }
        }

        return minSum;
    }
}
