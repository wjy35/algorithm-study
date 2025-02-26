package _1;

import java.util.*;

class Solution {
    Map<String, Integer> nameToIndex;
    int[] hashToCount;
    int[] indexToGiftPoint;
    public int solution(String[] friends, String[] gifts) {
        int currentIndex = 0 ;

        nameToIndex = new HashMap<>();
        hashToCount = new int[10000];
        indexToGiftPoint = new int[friends.length];

        for(String friend : friends){
            nameToIndex.put(friend,currentIndex);
            currentIndex++;
        }

        for(String gift : gifts){
            StringTokenizer st = new StringTokenizer(gift);
            int source = nameToIndex.get(st.nextToken());
            int target = nameToIndex.get(st.nextToken());

            hashToCount[toHash(source,target)]++;
            indexToGiftPoint[source]++;
            indexToGiftPoint[target]--;
        }

        int maxGiftCount = 0;
        for(String me : friends){
            int giftCount = 0;

            for(String friend : friends){
                int meIndex = nameToIndex.get(me);
                int friendIndex = nameToIndex.get(friend);

                if(meIndex==friendIndex) continue;

                int meGiveCount = hashToCount[toHash(meIndex,friendIndex)];
                int friendGiveCount = hashToCount[toHash(friendIndex,meIndex)];

                if(meGiveCount>friendGiveCount){
                    giftCount++;
                    continue;
                }


                if(meGiveCount!=friendGiveCount) continue;

                if(indexToGiftPoint[meIndex]>indexToGiftPoint[friendIndex]){
                    giftCount++;
                }
            }

            maxGiftCount = Math.max(maxGiftCount, giftCount);
        }

        return maxGiftCount;
    }

    private int toHash(int source, int target){
        return source * 100 + target;
    }
}

