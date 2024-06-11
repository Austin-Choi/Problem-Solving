import java.util.*;

class Solution {
    public String[] solution(String myString) {
        String[] temp = myString.split("");
        int idx = 0;
        for(; idx<temp.length; idx++){
            if(!temp[idx].equals("x"))
                break;
        }
        String[] ls = myString.substring(idx).split("[x]+");
        Arrays.sort(ls);
        return ls;
    }
}