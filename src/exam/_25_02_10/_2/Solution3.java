package _25_02_10._2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution3 {
    final int QR_SIZE = 6;
    Map<Integer,char[][]> numberToQr;
    int[][] equalCellCounts;

    int n;
    boolean[] isUsed;
    UsedNumberList maxUsedNumberList = UsedNumberList.of(1);

    public int[] solution(int n, String[][] qr) {
        this.n = n;

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

        this.isUsed = new boolean[n];
        dfs(0);

        return maxUsedNumberList.toArray();
    }

    void dfs(int number){
        if(number==n){
            if(isImPossible()) return;

            UsedNumberList usedNumberList = getUsedNumberList();

            if(maxUsedNumberList.isSuitableThan(usedNumberList)) return;
            maxUsedNumberList = usedNumberList;

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

    UsedNumberList getUsedNumberList() {
        UsedNumberList usedNumberList = new UsedNumberList();

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

    static class UsedNumberList {
        final static int AVG = 10;
        final List<Integer> underAvg;
        final List<Integer> orOverAvg;

        public UsedNumberList() {
            this.underAvg = new ArrayList<>();
            this.orOverAvg = new ArrayList<>();
        }

        public int size(){
            return underAvg.size() + orOverAvg.size();
        }

        public void add(int element){
            if(element<AVG) underAvg.add(element);
            else orOverAvg.add(element);
        }

        public boolean isSuitableThan(UsedNumberList target){
            if(this.size()==target.size()){
                return this.underAvg.size()>target.underAvg.size();
            }

            return this.size()>target.size();
        }

        public static UsedNumberList of(int element){
            UsedNumberList usedNumberList = new UsedNumberList();
            usedNumberList.add(element);

            return usedNumberList;
        }

        public int[] toArray(){
            int[] array = new int[this.size()];

            int underSize = underAvg.size();
            for(int i=0; i<underSize; i++){
                array[i] = underAvg.get(i);
            }

            int orOverSize = orOverAvg.size();
            for(int i=0; i<orOverSize; i++){
                array[i+underSize] = orOverAvg.get(i);
            }

            return array;
        }
    }
}
