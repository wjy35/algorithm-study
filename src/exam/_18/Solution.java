package _18;

import java.util.*;

class Solution {
    Map<String, String> plateNumberToInTime;
    Map<String, Integer> plateNumberToUsageTime;
    
    public int[] solution(int[] fees, String[] records) {
        plateNumberToInTime = new HashMap<>();
        plateNumberToUsageTime = new HashMap<>();
        
        for(String record : records){
            StringTokenizer st = new StringTokenizer(record);
            String time = st.nextToken();
            String plateNumber = st.nextToken();
            String type = st.nextToken();
            
            if("IN".equals(type)){
                plateNumberToInTime.put(plateNumber,time);
            }else{
                String inTime = plateNumberToInTime.remove(plateNumber);
                
                int minuteDiff = getMinuteDiff(inTime, time);
                plateNumberToUsageTime.put(
                    plateNumber,
                    plateNumberToUsageTime.getOrDefault(plateNumber,0)+minuteDiff
                );
            }
        }
        
        for(String plateNumber : plateNumberToInTime.keySet()){
            String inTime = plateNumberToInTime.get(plateNumber);
            
            int minuteDiff = getMinuteDiff(inTime, "23:59");
            plateNumberToUsageTime.put(
                plateNumber,
                plateNumberToUsageTime.getOrDefault(plateNumber,0)+minuteDiff
            );
        }
        
        List<String> plateNumberList = new ArrayList<>(plateNumberToUsageTime.keySet());
        Collections.sort(plateNumberList);
        
        int[] invoices = new int[plateNumberList.size()];
        for(int i=0; i<invoices.length; i++){
            String plateNumber = plateNumberList.get(i);
            
            int usageTime = plateNumberToUsageTime.get(plateNumber);
            
            if(usageTime<=fees[0]) {
                invoices[i] = fees[1];
                continue;
            }
            
            usageTime -= fees[0];
            
            int session = usageTime / fees[2] + (usageTime%fees[2]==0 ? 0:1);
            invoices[i] = fees[1] + session * fees[3];
        }
        
        return invoices;
    }
    
    public int getMinuteDiff(String in,String out){
        return toIntegerMinute(out) - toIntegerMinute(in);
    }
    
    public int toIntegerMinute(String time){
        StringTokenizer st = new StringTokenizer(time,":");
        
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        
        return hour*60+minute;
    }
}

