package _34002;

import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/34002"/>
 */
class Solution {

  int A, B, C;
  int S, V;
  int L;

  void readInput() throws IOException {
    st = new StringTokenizer(br.readLine());
    A = Integer.parseInt(st.nextToken());
    B = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    S = Integer.parseInt(st.nextToken());
    V = Integer.parseInt(st.nextToken());

    L = Integer.parseInt(br.readLine());
  }

  int requiredMinute = 0;
  void solve() {
    int lastExp = (250 - L) * 100;

    int maxMinute =  30 * V;
    if(lastExp - (maxMinute * C) <= 0){
      requiredMinute += lastExp / C + (lastExp % C == 0 ? 0 : 1);
      return;
    }else{
      requiredMinute += 30 * V;
      lastExp -= (maxMinute * C);
    }

    maxMinute =  30 * S;
    if(lastExp - (maxMinute * B) <= 0){
      requiredMinute += lastExp / B + (lastExp % B == 0 ? 0 : 1);
      return;
    }else{
      requiredMinute += 30 * S;
      lastExp -= (maxMinute * B);
    }

    requiredMinute += lastExp / A + (lastExp % A == 0 ? 0 : 1);
  }


  void writeOutput() throws IOException {
    bw.write(Integer.toString(requiredMinute));
    bw.flush();
  }

  public static void run() throws IOException {
    Solution solution = new Solution();
    solution.readInput();
    solution.solve();
    solution.writeOutput();
  }

  private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  private StringTokenizer st;
}

public class Main {

  public static void main(String[] args) throws IOException {
    Solution.run();
  }
}