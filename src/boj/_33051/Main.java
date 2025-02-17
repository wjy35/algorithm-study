package _33051;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/33051
 * @title 나는 이 우마를 지배할 수 있다
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int N,K,M;
    int[] scores;
    int[][] dCount;
    private void readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        scores = new int[4];
        dCount = new int[4][4];
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());

            int[] a = new int[4];
            for(int i=0; i<4; i++){
                a[i] = Integer.parseInt(st.nextToken())-1;
            }

            int[] s = new int[4];
            for(int i=0; i<4; i++){
                s[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=0; i<4; i++){
                scores[a[i]] += s[i];
            }

            for(int i=0; i<4; i++){
                dCount[a[i]][i]++;
            }
        }
    }

    int[] d;
    boolean isImpossible;
    private void solve(){

        d = new int[4];
        for(d[3]=-100; d[3]<=0; d[3]++){
            for(d[2]=d[3]; d[2]<=100; d[2]++){
                for(d[1]=d[2]; d[1]<=100; d[1]++){
                    d[0] = -d[3]-d[2]-d[1];
                    if(d[0]<d[1]) continue;
                    if(d[0]>100) continue;

                    int[] tmp = Arrays.copyOf(scores,scores.length);

                    for(int i=0; i<4; i++){
                        for(int j=0; j<4; j++){
                            tmp[i] += dCount[i][j]*d[j];
                        }
                    }

                    int place = 0;
                    for(int i=0; i<K-1; i++){
                        if(tmp[i]<tmp[K-1]) continue;
                        place++;
                    }
                    for(int i=K; i<4; i++){
                        if(tmp[i]<=tmp[K-1]) continue;
                        place++;
                    }

                    if(place+1==M) return;
                }
            }
        }

        isImpossible = true;
    }

    private void writeOutput() throws IOException{
        if(isImpossible) bw.write("-1");
        else {
            for(int i=0; i<4; i++){
                bw.write(Integer.toString(d[i]));
                bw.write(" ");
            }
        }
        bw.flush();
    }

    public void start() throws IOException{
        readInput();
        solve();
        writeOutput();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

