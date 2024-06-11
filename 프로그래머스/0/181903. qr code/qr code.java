class Solution {
    public String solution(int q, int r, String code) {
        String answer = "";
        for(int i = 0; i<code.length(); i++){
            if(i%q == r)
                answer += code.split("")[i];
        }
        return answer;
    }
}