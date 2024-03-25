package _29810;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/29810
 * @title 배신자
 * @algorithm Disjoint Set
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
    int imposterNumber;
    Queue<Relation> allRelationQueue;

    public Solution readInput() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        allRelationQueue = new ArrayDeque<>();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            allRelationQueue.offer(new Relation(
               Integer.parseInt(st.nextToken()),
               Integer.parseInt(st.nextToken())
            ));
        }

        imposterNumber = Integer.parseInt(br.readLine());

        return this;
    }

    int[] root;
    int[] groupSize;

    int answer;
    public Solution solve(){
        root = new int[N+1];
        groupSize = new int[N+1];

        for(int i=1; i<=N; i++) {
            root[i] = i;
            groupSize[i] = 1;
        }


        Queue<Relation> imposterRelationQueue = new ArrayDeque<>();
        int allRelationSize = allRelationQueue.size();
        Relation relation;
        for(int i=0; i<allRelationSize; i++){
            relation = allRelationQueue.poll();

            if(relation.A==imposterNumber || relation.B==imposterNumber){
                imposterRelationQueue.offer(relation);
                continue;
            }

            union(relation);
        }

        for(int i=1; i<=N; i++) find(i);

        int[] imposterFriendCount = new int[N+1];
        while(!imposterRelationQueue.isEmpty()){
            relation = imposterRelationQueue.poll();
            if(relation.A==imposterNumber) imposterFriendCount[root[relation.B]]++;
            else imposterFriendCount[root[relation.A]]++;
        }

        int maxGroupSize = 1;
        for(int i=1; i<=N; i++){
            if(groupSize[i]==0) continue;

            if(imposterFriendCount[i]==1) groupSize[i]++;

            maxGroupSize = Math.max(maxGroupSize, groupSize[i]);
        }

        answer = maxGroupSize;

        return this;
    }

    public int find(int x){
        if(root[x]==x) return x;

        return root[x] = find(root[x]);
    }

    public boolean union(Relation relation){
        int ra = find(relation.A);
        int rb = find(relation.B);

        if(ra==rb) return false;

        root[rb] = ra;
        groupSize[ra] += groupSize[rb];
        groupSize[rb] = 0;

        return true;
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

    static class Relation{
        int A,B;

        public Relation(int a, int b) {
            A = a;
            B = b;
        }
    }

}

