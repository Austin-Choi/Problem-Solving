class Solution {
    public int solution(String[] arr) {
        int cnt = 0;
        String[] bb = {"aya", "ye", "woo", "ma"};
        
        for(String s : arr){
            int pos = 0;
            while(pos<s.length()){
                boolean matched = false;
                for(String w : bb){
                    // s의 pos 위치부터 w와 일치하면
                    if(pos + w.length() <= s.length() && s.startsWith(w, pos)){
                        pos += w.length();
                        matched = true;
                        break;
                    }
                }
                if(!matched)
                    break;
            }
            if(pos == s.length())
                cnt++;
        }
        return cnt;
    }
}