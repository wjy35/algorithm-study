package _19;

import java.util.*;

class Solution {
    
    public int[] solution(String[] id_list, String[] reports, int k) {
        Set<String> reportSet = new HashSet<>();
        Map<String, List<String>> idToReportList = new HashMap<>();
        Map<String, Integer> idToReportedCount = new HashMap<>();
        
        for(String id : id_list){
            idToReportList.put(id,new ArrayList<>());
            idToReportedCount.put(id,0);
        }
        
        for(String report : reports){
            if(!reportSet.add(report)) continue;
            
            StringTokenizer st = new StringTokenizer(report);
            
            String source = st.nextToken();
            String target = st.nextToken();
            
            idToReportList.get(source).add(target);
            idToReportedCount.put(target,idToReportedCount.get(target)+1);
        }
        
        int[] answer = new int[id_list.length];
        for(int i=0; i<answer.length; i++){
            int noticeCount = 0;
            
            for(String target : idToReportList.get(id_list[i])){
                if(idToReportedCount.get(target)<k) continue;
                
                noticeCount++;
            }
            
            answer[i] = noticeCount;
        }
        
        return answer;
    }
}