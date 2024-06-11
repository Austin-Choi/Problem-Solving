import java.util.*;

class Solution {
    public String[] solution(String myStr) {
        String[] answer = {};
        ArrayList<String> l = new ArrayList<>();
        for(String s : myStr.split("[abc]")){
            if(!s.equals(""))
                l.add(s);
        }
        if(l.isEmpty())
            l.add("EMPTY");
        return l.stream().toArray(String[]::new);
    }
}