package _9;

import java.util.*;

class Solution {
    Map<String,Integer> termNameToAvailableMonth;
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        StringTokenizer st = new StringTokenizer(today,".");
        
        int nowYear = Integer.parseInt(st.nextToken());
        int nowMonth = Integer.parseInt(st.nextToken());
        int nowDay = Integer.parseInt(st.nextToken());
        Date now = new Date(nowYear,nowMonth,nowDay);
        
        termNameToAvailableMonth = new HashMap<>();
        for(String term : terms){
            st = new StringTokenizer(term);
            termNameToAvailableMonth.put(st.nextToken(),Integer.parseInt(st.nextToken()));
        }
        
        List<Integer> expiredIndexList = new ArrayList<>();
        for(int i=0; i<privacies.length; i++){
            String privacy = privacies[i];
            
            st = new StringTokenizer(privacy);
            String startDate = st.nextToken();
            String termName = st.nextToken();
            
            st = new StringTokenizer(startDate,".");
            int startYear = Integer.parseInt(st.nextToken());
            int startMonth = Integer.parseInt(st.nextToken());
            int startDay = Integer.parseInt(st.nextToken());
            
            Date expire = new Date(startYear,startMonth,startDay);
            expire.addMonth(termNameToAvailableMonth.get(termName));
            
            if(isExpired(now,expire)) expiredIndexList.add(i);
        }
        
        int[] answer = new int[expiredIndexList.size()];
        for(int i=0; i<answer.length; i++){
            answer[i] = expiredIndexList.get(i)+1;
        }

        return answer;
    }
    
    public boolean isExpired(Date now,Date expire){
        if(now.year==expire.year){
            if(now.month==expire.month){
                return now.day>=expire.day;
            }
            
            return now.month>expire.month;
        }
        
        return now.year>expire.year;
    }
}


class Date{
    int year;
    int month;
    int day;
        
    Date(int year,int month,int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }
        
    void addMonth(int month){
        this.month += month;
        
        while(this.month>12){
            this.month -= 12;
            this.year++;
        }
    }
}