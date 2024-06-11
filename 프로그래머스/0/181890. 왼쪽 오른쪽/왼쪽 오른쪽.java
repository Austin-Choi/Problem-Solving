import java.util.*;

class Solution {
    public String[] solution(String[] str_list) {
        ArrayList<String> l = new ArrayList<>();
        int idx = 0;
        boolean lflag = true;
        for(String s : str_list){
            if(s.equals("l"))
                break;
            if(s.equals("r")){
                lflag = false;
                break;
            }
            idx++;
        }
        if(idx != str_list.length){
            if(lflag){
                for(int i = 0; i<idx; i++){
                    l.add(str_list[i]);
                }
            }
            else{
                for(int i = idx+1; i<str_list.length; i++){
                    l.add(str_list[i]);
                }
            }
        }
        return l.stream().toArray(String[]::new);
    }
}