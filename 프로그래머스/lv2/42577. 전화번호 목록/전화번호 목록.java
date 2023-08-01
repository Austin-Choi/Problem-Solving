import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Map<String, Integer> m = new HashMap<>();
        for(String s : phone_book)
            m.put(s, 1);
        
        for(String s : phone_book){
            for(int i = 0; i<s.length(); i++){
                String temp = s.substring(0, i);
                if(m.get(temp) != null)
                    return false;
            }
        }
        
        return true;
    }
}