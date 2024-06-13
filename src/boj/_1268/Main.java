package _1268;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/1268
 * @title 임시 반장 정하기
 * @algorithm 
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    int studentCount;

    int[][] classInfo;

    int classMonitor = 1;
    int maxFriendCount = 0;
    private void readInput() throws IOException{
        studentCount = Integer.parseInt(br.readLine());

        classInfo = new int[studentCount+1][6];
        for(int student=1; student<=studentCount; student++){
            st = new StringTokenizer(br.readLine());
            for(int grade=1; grade<6; grade++) classInfo[student][grade] = Integer.parseInt(st.nextToken());
        }
    }

    private void solve(){
        Set<Integer>[] friends = new Set[studentCount+1];
        for(int student=1; student<=studentCount; student++) friends[student] = new HashSet<>();

        for(int grade=1; grade<6; grade++){
            for(int i=1; i<=studentCount; i++){
                for(int j=i+1; j<=studentCount; j++){
                    if(classInfo[i][grade]==classInfo[j][grade]){
                        friends[i].add(j);
                        friends[j].add(i);
                    }
                }
            }
        }

        for(int student=1; student<=studentCount; student++){
            if(maxFriendCount<friends[student].size()){
                maxFriendCount = friends[student].size();
                classMonitor = student;
            }
        }
    }


    private void writeOutput() throws IOException{
        bw.write(Integer.toString(classMonitor));
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

