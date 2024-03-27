package _31677;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/31677
 * @title 특별한 드롭킥 2
 * @algorithm Greedy
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .readInput()
                .solve()
                .writeOutput();
    }
}

class Solution{

    int N,M;
    String corridor;

    public Solution readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        corridor = br.readLine();
        return this;
    }

    int answer;

    public Solution solve(){

        int time = 0;
        int count = 0;
        boolean isPreX = false;

        for(int i=0; i<corridor.length(); i++){
            if(isPreX){
                if(corridor.charAt(i)=='X'){
                    isPreX = true;
                    continue;
                }
                time+=1;
                isPreX=false;
            }else{
                if(corridor.charAt(i)=='X') {
                    time+=2;
                    count++;
                    isPreX = true;
                }
                else {
                    time+=1;
                    isPreX = false;
                }
            }
        }

        if(count<2) {
            answer = time;
            return this;
        }
        st = new StringTokenizer(corridor,"X");

        List<Integer> list = new ArrayList<>(count-1);
        if(corridor.charAt(0)!='X')st.nextToken();
        for(int i=0; i<count-1; i++){
            list.add(st.nextToken().length());
        }

        Collections.sort(list);

        int moveAmount = 0;
        int moveCount = 0;
        for(int i=0;  i<list.size() && moveAmount+list.get(i)<=M; i++){
            moveAmount += list.get(i);
            moveCount++;
        }

        answer = time-moveCount*2;
        return this;
    }

    public void writeOutput() throws IOException{
        bw.write(Integer.toString(answer));
        bw.flush();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }
}

