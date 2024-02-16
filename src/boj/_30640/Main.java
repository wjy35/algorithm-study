package _30640;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/30640">운전 연습</a>
 * @category Greedy
 * @Note
 */
public class Main {
    static String answer;

    static int N;
    static GasStation[] gasStationInfo;
    static class GasStation{
        long A,F;

        public GasStation(long a, long f) {
            A = a;
            F = f;
        }
    }

    static void solution(){
        StringBuilder sb = new StringBuilder();

        int x=0;
        long y=0;
        long nowFuel = gasStationInfo[0].F;

        boolean isFuelEmpty = false;
        for(int i=1; i<=N; i++){
            if(isFuelEmpty){
                sb.append("-1 -1\n");
                continue;
            }
            nowFuel -= (gasStationInfo[i].A-gasStationInfo[i-1].A);

            if(nowFuel<0){
                isFuelEmpty = true;
                sb.append("-1 -1\n");
                continue;
            }
            y += getChargeFuel(i);

            sb.append(x);
            sb.append(" ");
            sb.append(Math.abs(y));
            sb.append("\n");

            if(y<=0){
                x = i;
                y = 0;
            }

            nowFuel += gasStationInfo[i].F;
        }
        answer = sb.toString();
    }

    static long getChargeFuel(int i){
        return gasStationInfo[i-1].F-(gasStationInfo[i].A-gasStationInfo[i-1].A);
    }
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        gasStationInfo = new GasStation[N+1];
        for(int i=0; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            gasStationInfo[i] = new GasStation(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        }
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
