package _25_02_10._2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution2 {
    final int QR_SIZE = 6;
    Map<Integer,char[][]> numberToQr;
    int[][] equalCellCounts;

    int n;
    boolean[] isUsed;
    int maxUsedCount = 1;
    List<Integer> maxUsedCountNumberList = List.of(1);
    int minHashcode = Integer.MAX_VALUE;

    public int[] solution(int n, String[][] qr) {
        numberToQr = new HashMap<>();
        for (int number = 0; number < n; number++) {
            char[][] numberQr = new char[QR_SIZE][QR_SIZE];
            for (int i = 0; i<QR_SIZE; i++){
                numberQr[i] = qr[number][i].toCharArray();
            }
            numberToQr.put(number, numberQr);
        }

        equalCellCounts = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                char[][] source = numberToQr.get(i);
                char[][] target = numberToQr.get(j);

                int maxEqualCellCount = countEqualCell(source, target);
                for (int r = 0; r < 3; r++) {
                    target = rotate(target);
                    maxEqualCellCount = Math.max(maxEqualCellCount, countEqualCell(source, target));
                }

                equalCellCounts[i][j] = maxEqualCellCount;
                equalCellCounts[j][i] = maxEqualCellCount;
            }
        }

        this.n = n;
        this.isUsed = new boolean[n];
        dfs(0);

        int[] answer = new int[maxUsedCountNumberList.size()];
        for (int i = 0; i<answer.length; i++) {
            answer[i] = maxUsedCountNumberList.get(i);
        }

        return answer;
    }

    void dfs(int number){
        if(number==n){
            if(isImPossible()) return;

            List<Integer> usedNumberList = getUsedNumberList();
            int hashcode = hash(usedNumberList);

            if(maxUsedCount>usedNumberList.size()) return;
            if(maxUsedCount==usedNumberList.size()&&hashcode>=minHashcode) return;

            maxUsedCount = usedNumberList.size();
            maxUsedCountNumberList = usedNumberList;
            minHashcode = hashcode;

            return;
        }

        isUsed[number] = true;
        dfs(number+1);
        isUsed[number] = false;
        dfs(number+1);
    }

    boolean isImPossible(){
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                if(isUsed[i] && isUsed[j] && equalCellCounts[i][j]>18) return true;
            }
        }

        return false;
    }

    List<Integer> getUsedNumberList() {
        List<Integer> usedNumberList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (isUsed[i]) usedNumberList.add(i+1);
        }

        return usedNumberList;
    }

    int countEqualCell(char[][] source,char[][] target){
        int count = 0;
        for(int i=0; i<QR_SIZE; i++){
            for(int j=0; j<QR_SIZE; j++){
                if(source[i][j]==target[i][j]) count++;
            }
        }
        return count;
    }

    char[][] rotate(char[][] qr){
        char[][] rotatedQr = new char[QR_SIZE][QR_SIZE];

        for(int i=0; i<QR_SIZE; i++){
            for(int j=0; j<QR_SIZE; j++){
                rotatedQr[j][QR_SIZE-1-i] = qr[i][j];
            }
        }

        return rotatedQr;
    }

    int hash(List<Integer> numberList){
        int hashcode = 0;

        for(Integer number : numberList){
            if(number/10==1) hashcode *= 100;
            else hashcode *= 10;

            hashcode += number;
        }

        return hashcode;
    }
}
