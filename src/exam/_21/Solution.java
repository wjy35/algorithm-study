package _21;


import java.util.*;

public class Solution {
    int[] a;
    int[] b;

    public Solution(int[] a, int[] b) {
        this.a = a;
        this.b = b;
    }

    public int[] solve(){
        int[] answer = new int[a.length];
        List<Section> sectionList = new LinkedList<>();

        for(int i=0; i<a.length; i++){
            ListIterator<Section> it = sectionList.listIterator();

            Section source = null;
            while(it.hasNext()){
                Section current = it.next();

                if(current.start<=a[i] && a[i]<=current.end) {
                    source = current;
                    source.end = Math.max(source.end, b[i]);
                    break;
                }

                if(a[i]<current.start){
                    it.previous();
                    source = new Section(a[i],b[i]);
                    it.add(source);
                    break;
                }
            }
            if(source==null){
                it.add(new Section(a[i],b[i]));

                answer[i] = sectionList.size();

                continue;
            }

            while(it.hasNext()) {
                Section target = it.next();

                if(source.end<target.start) break;

                source.end = Math.max(source.end,target.end);
                it.remove();
            }

            answer[i] = sectionList.size();
        }

        return answer;
    }

    private static class Section{
        int start,end;

        public Section(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static class Main{
        public static void main(String[] args) {

            int[] a = {1,5,6,3,3};
            int[] b = {3,7,8,5,5};

            int[] expected = {1,2,2,1,1};
            int[] actual = new Solution(a,b).solve();

            System.out.println("Result : " + Arrays.equals(expected,actual));

        }
    }
}




