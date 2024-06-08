import java.util.*;

class Solution {
    public List solution(String my_string) {
        String[] answer = {};
        ArrayList<String> l = new ArrayList<>();
        for(int i = 0; i<my_string.length(); i++){
            l.add(my_string.substring(i,my_string.length()));
        }
        
        Collections.sort(l);
        
        return l;
    }
}