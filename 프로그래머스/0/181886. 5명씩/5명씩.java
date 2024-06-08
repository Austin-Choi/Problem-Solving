import java.util.*;

class Solution {
    public List solution(String[] names) {
        ArrayList<String> l = new ArrayList<>();
        for(int i = 0; i<names.length; i++){
            if(i%5 == 0)
                l.add(names[i]);
        }
        return l;
    }
}