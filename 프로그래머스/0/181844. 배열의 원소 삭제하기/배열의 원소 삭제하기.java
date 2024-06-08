import java.util.*;

class Solution {
    public  List solution(int[] arr, int[] del) {
        ArrayList<Integer> il1 = new ArrayList<>();
        for(int i : arr){
            il1.add(i);
        }
        
        ArrayList<Integer> il2 = new ArrayList<>();
        for(int i : del){
            il2.add(i);
        }
        
        il1.removeAll(il2);
        
        return il1;
    }
}