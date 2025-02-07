package _25_02_10._2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution1 {
    final int QR_SIZE = 6;
    Map<Integer,char[][]> numberToQr;
    int[][] equalCellCount;
    int n;
    boolean[] isUsed;
    int maxUsedCount = 1;
    List<Integer> maxCountUsedNumberList = List.of(1);

    public int[] solution(int n, String[][] qr) {
        numberToQr = new HashMap<>();
        for (int number = 0; number < n; number++) {
            char[][] numberQr = new char[QR_SIZE][QR_SIZE];
            for (int i = 0; i<QR_SIZE; i++){
                numberQr[i] = qr[number][i].toCharArray();
            }
            numberToQr.put(number, numberQr);
        }

        equalCellCount = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                char[][] source = numberToQr.get(i);
                char[][] target = numberToQr.get(j);

                int maxEqualCellCount = countEqualCell(source, target);
                for (int r = 0; r < 3; r++) {
                    target = rotate(target);
                    maxEqualCellCount = Math.max(maxEqualCellCount, countEqualCell(source, target));
                }

                equalCellCount[i][j] = maxEqualCellCount;
                equalCellCount[j][i] = maxEqualCellCount;
            }
        }

        this.n = n;
        this.isUsed = new boolean[n];
        dfs(0);
        int[] answer = new int[maxCountUsedNumberList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = maxCountUsedNumberList.get(i);
        }

        return answer;
    }

    void dfs(int currentNumber){
        if(currentNumber==n){
            if(isPossible()){
                List<Integer> usedNumberList = getUsedNumberList();

                if(maxUsedCount<usedNumberList.size()){
                    maxUsedCount = usedNumberList.size();
                    maxCountUsedNumberList = usedNumberList;
                }
            }
            return;
        }

        isUsed[currentNumber] = true;
        dfs(currentNumber+1);
        isUsed[currentNumber] = false;
        dfs(currentNumber+1);
    }

    boolean isPossible(){
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                if(isUsed[i] && isUsed[j] && equalCellCount[i][j]>18) return false;
            }
        }
        return true;
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
}
