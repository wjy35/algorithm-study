package _25_02_10._4;

import java.util.Arrays;

class Solution {
    public int solution(int[] play_list, int listen_time) {
        int[] playList = new int[play_list.length*2];

        for(int i=0; i<play_list.length; i++){
            playList[i] = play_list[i];
            playList[i+play_list.length] = play_list[i];
        }

        for(int i=1; i<playList.length; i++){
            playList[i] += playList[i-1];
        }

        int maxCount = 0;
        for(int i=0; i<play_list.length; i++){
            int start = playList[i]-1;
            int key = start+listen_time;

            int position = Arrays.binarySearch(playList,key);
            if(position<0){
                position = position*-1-1;
            }

            maxCount = Math.max(Math.min(position-i+1,play_list.length),maxCount);
        }

        return maxCount;
    }
}