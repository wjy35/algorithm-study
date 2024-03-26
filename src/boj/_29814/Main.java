package _29814;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/29814
 * @title 원교수님 과제가 너무 많아요
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

    int N,C;
    List<Integer>[] pointList;

    public Solution readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        pointList = new List[N];
        for(int i=0; i<N; i++) pointList[i] = new ArrayList<>();

        int d,t,p;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());

            pointList[d-t].add(p);
        }

        return this;
    }


    String answer;

    public Solution solve(){
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        Integer[] maxPointAt = new Integer[N];

        for(int day=N-1; day>=0; day--){
            for(int i=0; i<pointList[day].size(); i++){
                pq.offer(pointList[day].get(i));
            }

            if(pq.isEmpty()){
                maxPointAt[day] = 0;
                continue;
            }
            maxPointAt[day] = pq.poll();
        }

        Arrays.sort(maxPointAt,Comparator.reverseOrder());
        int pointSum = 0;

        for(int count=0; count<N; count++){
            pointSum += maxPointAt[count];

            if(pointSum>=C){
                answer = Integer.toString(count+1);
                return this;
            }
        }

        answer = "I'm sorry professor Won!";
        return this;
    }

    public void writeOutput() throws IOException{
        bw.write(answer);
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

