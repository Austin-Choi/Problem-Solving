import java.util.*;

class Solution {
    public boolean check5N0(String s){
        String[] ls = s.split("");
        for(String ss : ls){
            if(!(ss.equals("5") || ss.equals("0")))
                return false;
        }
        return true;
    }
    public List solution(int l, int r) {
        ArrayList<Integer> ans = new ArrayList<>();
        for(;l<=r;l++){
            if(l%5 == 0){
                String s = String.valueOf(l);
                if(check5N0(s))
                    ans.add(l);
            }
        }
        if(ans.isEmpty())
            ans.add(-1);
        
        return ans;
    }
}