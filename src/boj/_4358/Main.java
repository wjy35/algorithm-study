package _4358;
import java.io.*;
import java.util.*;
import java.util.function.Consumer;

/**
 * @link https://www.acmicpc.net/problem/4358
 * @title 생태학
 * @algorithm
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Solution.createSolution()
                .start();
    }
}

class Solution{

    Map<String, Integer> nameToCount = new HashMap<>();
    int countSum = 0;

    private void solve() throws IOException{
        while(true){
            String treeName = br.readLine();

            if(treeName==null) break;

            countSum++;
            if(nameToCount.containsKey(treeName)) nameToCount.put(treeName, nameToCount.get(treeName)+1);
            else nameToCount.put(treeName,1);
        }

        List<String> treeNameList = new ArrayList<>(nameToCount.keySet());
        Collections.sort(treeNameList);


        for(String treeName : treeNameList){
            bw.write(String.format("%s %.4f\n",treeName, nameToCount.get(treeName) * 100f / countSum) );
        }
        bw.flush();
    }

    public void start() throws IOException{
        solve();
    }

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private StringTokenizer st;

    private Solution(){}
    public static Solution createSolution(){
        return new Solution();
    }

}

