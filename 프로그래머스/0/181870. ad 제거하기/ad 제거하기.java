import java.util.*;
class Solution {
    public List<String> solution(String[] strArr) {
        ArrayList<String> l = new ArrayList<>();
        for(String s : strArr){
            if(!s.contains("ad"))
                l.add(s);
        }
        return l;
    }
}