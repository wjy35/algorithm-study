package _9470;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/9470
 * @title Strahler 순서 
 * @algorithm Topological Sort
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Test.createTest()
                .readTestCount()
                .start();
    }
}
class Test {
    int T;
    int K,M,P;
    List<Integer>[] edge;

    int[] incomingEdgeCount;
    int[] Strahler;

    public void readTestInput() throws IOException{
        st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        edge = new List[M+1];
        for(int i=1; i<=M; i++) edge[i] = new ArrayList<>();

        incomingEdgeCount = new int[M+1];
        int u,v;
        for(int i=0; i<P; i++){
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            edge[u].add(v);
            incomingEdgeCount[v]++;
        }
    }

    public void test(){
        Strahler = new int[M+1];
        boolean[] isMulti = new boolean[M+1];
        Queue<Integer> q = new ArrayDeque<>();

        for(int i=1; i<=M; i++){
            if(incomingEdgeCount[i]!=0) continue;

            isMulti[i] = true;
            q.offer(i);
        }

        int now;
        while(!q.isEmpty()){
            now = q.poll();
            if(isMulti[now]) Strahler[now]++;

            for(int next : edge[now]){
                incomingEdgeCount[next]--;

                if(Strahler[next]<Strahler[now]){
                    Strahler[next] = Strahler[now];
                    isMulti[next] = false;
                }else if(Strahler[next]==Strahler[now]){
                    isMulti[next] = true;
                }

                if(incomingEdgeCount[next]==0) q.offer(next);
            }
        }
    }

    public void writeTestOutput() throws IOException{
        bw.write(Integer.toString(K));
        bw.write(" ");
        bw.write(Integer.toString(Strahler[M]));
        bw.write("\n");
    }

    public Test readTestCount() throws IOException{
        T = Integer.parseInt(br.readLine());
        return this;
    }

    public void start() throws IOException{
        for(int i=1; i<=T; i++){
            readTestInput();
            test();
            writeTestOutput();
        }
        bw.flush();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Test(){}
    public static Test createTest(){
        return new Test();
    }
}

