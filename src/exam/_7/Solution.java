package _7;

import java.util.*;

class Solution {
    int[] parents;
    String[] rootValues;
    final String EMPTY = "EMPTY";
    
    public String[] solution(String[] commands) {
        parents = new int[2500];
        for(int i=1; i<2500; i++) parents[i] = i;
        
        rootValues = new String[2500];
        Arrays.fill(rootValues,EMPTY);
        
        List<String> outputList = new ArrayList<>();
        
        for(String command : commands){
            StringTokenizer st = new StringTokenizer(command);
            
            String commandType = st.nextToken();
            if("UPDATE".equals(commandType)){
                int count = st.countTokens();
                if(count==2){
                    String value1 = st.nextToken();
                    String value2 = st.nextToken();
                    
                    update(value1,value2);
                }else{
                    int r = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    String value = st.nextToken();
                    
                    update(r,c,value);
                }
            }else if("MERGE".equals(commandType)){
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                
                merge(r1,c1,r2,c2);
            }else if("UNMERGE".equals(commandType)){
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                
                unMerge(r,c);
            }else{
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                
                outputList.add(print(r,c));
            }
            
        }
        
        String[] answer = new String[outputList.size()];
        for(int i=0; i<answer.length; i++){
            answer[i] = outputList.get(i);
        }
        
        return answer;
    }
    
    private void update(String value1,String value2){
        for(int i=0; i<rootValues.length; i++){
            if(parents[i]==i && rootValues[i].equals(value1)) rootValues[i] = value2;
        }
    }
    
    private void update(int r,int c,String value){
        int index = pointToIndex(r,c);
        
        rootValues[find(index)] = value;   
    }
    
    private void merge(int r1,int c1,int r2,int c2){
        union(pointToIndex(r1,c1),pointToIndex(r2,c2));
    }
    
    private void unMerge(int r,int c){
        int index = pointToIndex(r,c);
        int rootIndex = find(index);
        String rootValue = rootValues[rootIndex];
        
        List<Integer> unMergeIndexes = new ArrayList<>();
        for(int i=0; i<parents.length; i++){
            if(find(i)!=rootIndex) continue;
            
            unMergeIndexes.add(i);
        }
        
        for(Integer unMergeIndex : unMergeIndexes){
            parents[unMergeIndex] = unMergeIndex;
            rootValues[unMergeIndex] = EMPTY;
        }
        
        rootValues[index] = rootValue;
    }
    
    private String print(int r, int c){
        return rootValues[find(pointToIndex(r,c))];
    }
    
    private int pointToIndex(int r,int c){
        return (r-1)*50+(c-1);
    }
    
    private void union(int sourceIndex,int targetIndex){
        int sourceRoot = find(sourceIndex);
        int targetRoot = find(targetIndex);
        
        if(sourceRoot==targetRoot) return;
        
        
        parents[targetRoot] = sourceRoot;
        if(rootValues[sourceRoot].equals(EMPTY)){
            rootValues[sourceRoot] = rootValues[targetRoot];
        }
    }
    
    private int find(int index){
        if(parents[index]==index) return index;
        
        return parents[index] = find(parents[index]);
    }
}