package _23059;
import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/23059
 * @title 리그 오브 레게노
 * @algorithm Topological Sort
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

    int N;

    Map<String,List<String>> nameToEdgeList;
    Map<String,Integer> nameToCount;
    Deque<String> buyOrder;

    public Solution readInput() throws IOException{
        N = Integer.parseInt(br.readLine());

        nameToEdgeList = new HashMap<>();
        nameToCount = new HashMap<>();

        String u,v;
        List<String> edgeList;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            u = st.nextToken();
            v = st.nextToken();

            if(!nameToEdgeList.containsKey(v)) nameToEdgeList.put(v,new ArrayList<>());
            if(nameToEdgeList.containsKey(u)){
                nameToEdgeList.get(u).add(v);
            }else {
                edgeList = new ArrayList<>();
                edgeList.add(v);
                nameToEdgeList.put(u,edgeList);
            }

            if(!nameToCount.containsKey(u)) nameToCount.put(u,0);
            if(nameToCount.containsKey(v)){
                nameToCount.put(v, nameToCount.get(v)+1);
            }else{
                nameToCount.put(v,1);
            }
        }

        return this;
    }


    public Solution solve(){
        PriorityQueue<Item> pq = new PriorityQueue<>();
        buyOrder = new ArrayDeque<>();

        for(String itemName : nameToCount.keySet()){
            if(nameToCount.get(itemName).equals(0)) pq.offer(new Item(itemName,0));
        }

        Item now;
        while(!pq.isEmpty()){
            now = pq.poll();
            buyOrder.add(now.name);

            for(String nextItemName: nameToEdgeList.get(now.name)){
                nameToCount.put(nextItemName, nameToCount.get(nextItemName)-1);

                if(nameToCount.get(nextItemName).equals(0)){
                    pq.offer(new Item(nextItemName,now.priority+1));
                }
            }
        }

        if(buyOrder.size()== nameToCount.keySet().size()) return this;

        buyOrder = new ArrayDeque<>();
        buyOrder.offer("-1");
        return this;
    }

    public void writeOutput() throws IOException{
        while(!buyOrder.isEmpty()){
            bw.write(buyOrder.poll());
            bw.write("\n");
        }

        bw.flush();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }

    private static class Item implements Comparable<Item>{
        String name;
        int priority;

        @Override
        public int compareTo(Item o) {
            if(this.priority==o.priority){
                return this.name.compareTo(o.name);
            }

            return Integer.compare(this.priority,o.priority);
        }

        public Item(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }
    }
}

