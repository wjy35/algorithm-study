package _12;

import java.util.*;

class Solution {
    Map<Character,Integer> typeToPoint;
    
    public String solution(String[] survey, int[] choices) {
        typeToPoint = new HashMap<>();
        
        typeToPoint.put('R',0);
        typeToPoint.put('T',0);
        typeToPoint.put('C',0);
        typeToPoint.put('F',0);
        typeToPoint.put('J',0);
        typeToPoint.put('M',0);
        typeToPoint.put('A',0);
        typeToPoint.put('N',0);

        for(int i=0; i<survey.length; i++){            
            char type;
            if(choices[i]<4){
                type = survey[i].charAt(0);
                typeToPoint.put(type,typeToPoint.get(type) + (4-choices[i]));
            }else if(choices[i]>4){
                type = survey[i].charAt(1);
                typeToPoint.put(type,typeToPoint.get(type) + (choices[i]-4));
            }
        }

        String[] types = new String[4];
        
        types[0] = typeToPoint.get('T') > typeToPoint.get('R') ? "T":"R";
        types[1] = typeToPoint.get('F') > typeToPoint.get('C') ? "F":"C";
        types[2] = typeToPoint.get('M') > typeToPoint.get('J') ? "M":"J";
        types[3] = typeToPoint.get('N') > typeToPoint.get('A') ? "N":"A";
        
        return types[0]+types[1]+types[2]+types[3];
    }
}