package _20437;

import java.io.*;
import java.util.*;

/**
 * @link https://www.acmicpc.net/problem/20437
 * @title
 * @algorithm
 */
class Test {

  String W;
  int K;

  private void readInput() throws IOException {
    W = br.readLine();
    K = Integer.parseInt(br.readLine());
  }

  String answer;

  private void solve(int testNumber) {
    List<Integer>[] alphaToIndexes = new List[26];
    for(int i=0; i<alphaToIndexes.length; i++) alphaToIndexes[i] = new ArrayList<>();

    for (int i = 0; i < W.length(); i++) {
      char c = W.charAt(i);
      alphaToIndexes[c-'a'].add(i);
    }

    int min = Integer.MAX_VALUE;
    int max = -1;

    for(List<Integer> indexes : alphaToIndexes){
      if(indexes.size()<K) continue;

      for(int i=0; i+K<=indexes.size(); i++){
        int length = indexes.get(i+K-1)-indexes.get(i)+1;
        min = Math.min(min, length);
        max = Math.max(max, length);
      }
    }

    answer = min==Integer.MAX_VALUE ? "-1" : min + " " + max;
  }

  private void writeOutput() throws IOException {
    bw.write(answer);
    bw.write("\n");
    bw.flush();
  }

  private int readTestCaseCount() throws IOException {
    return Integer.parseInt(br.readLine());
  }

  public static void run() throws IOException {
    Test test = new Test();

    int T = test.readTestCaseCount();
    for (int t = 1; t <= T; t++) {
      test.readInput();
      test.solve(t);
      test.writeOutput();
    }
  }

  private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  private StringTokenizer st;
}

public class Main {

  public static void main(String[] args) throws IOException {
    Test.run();
  }
}

