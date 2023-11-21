package _1781;
import java.io.*;
import java.util.*;

/**
 * @author 왕준영
 * @see <a href = "https://www.acmicpc.net/problem/1781">컵라면</a>
 * @category PriorityQueue, Greedy
 * @Note
 */
public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n;
    static List<Integer>[] countList;
    static PriorityQueue<Integer> countQueue;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        countList = new List[n+1];
        for(int i=1; i<=n; i++){
            countList[i] = new ArrayList<>();
        }

        int deadLine,count;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            deadLine = Integer.parseInt(st.nextToken());
            count = Integer.parseInt(st.nextToken());

            countList[deadLine].add(count);
        }

        countQueue = new PriorityQueue<>(Comparator.reverseOrder());

        int sum = 0;
        for(deadLine = n; deadLine>0; deadLine--){
            insertCountByDeadLine(deadLine);

            if (countQueue.isEmpty()) continue;

            sum += countQueue.poll();
        }

        bw.write(Integer.toString(sum));
        bw.flush();
    }

    static private void insertCountByDeadLine(int deadLine){
        List<Integer> list = countList[deadLine];

        for(Integer count : list){
            countQueue.offer(count);
        }
    }
}
