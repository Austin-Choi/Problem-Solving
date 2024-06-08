import java.util.*;

class Solution {
    public List solution(String my_string) {
        ArrayList<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char[] lc = my_string.toCharArray();
        
        for(int i = 0; i<lc.length; i++){
            if(Character.isLetter(lc[i])){
                sb.append(lc[i]);
            }
            else{
                if(!sb.toString().isEmpty())
                    ans.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        if(!sb.toString().isEmpty())
            ans.add(sb.toString());
        return ans;
    }
}