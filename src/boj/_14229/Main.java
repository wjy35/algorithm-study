package _14229;
import java.io.*;
import java.util.*;

/**
 * @link <a href="https://www.acmicpc.net/problem/14229"/>
 */
class Solution {

  String S;

  void readInput() throws IOException {
    S = br.readLine();
  }

  static class Node{
    Character c;
    Node parent;

    Map<Character,Node> childs = new HashMap<>();

    public Node(Character c, Node parent) {
      this.c = c;
      this.parent = parent;
    }
  }

  static class Trie{
    Node root = new Node(null,null);

    void insert(String line){
      Node current = root;

      for(int i=0; i<line.length(); i++){
        char c = line.charAt(i);

        if(!current.childs.containsKey(c)){
          current.childs.put(c,new Node(c,current));
        }

        current = current.childs.get(c);
      }
    }
  }

  String answer;
  void solve() {
    Trie trie = new Trie();
    for(int i=0; i<S.length(); i++){
      trie.insert(S.substring(i));
    }

    Character[] dna = {'A','C','G','T'};

    Queue<Node> q = new ArrayDeque<>();
    q.offer(trie.root);

    while(!q.isEmpty()){
      Node current = q.poll();

      for(int i=0; i<4; i++){
        if(!current.childs.containsKey(dna[i])){
          StringBuilder sb = new StringBuilder();
          sb.append(dna[i]);
          for(Node leaf = current; leaf.parent != null; leaf = leaf.parent){
            sb.append(leaf.c);
          }
          answer = sb.reverse().toString();
          return;
        }

        q.offer(current.childs.get(dna[i]));
      }
    }
  }


  void writeOutput() throws IOException {
    bw.write(answer);
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