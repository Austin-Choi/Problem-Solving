class Solution {
    public String solution(String myString) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        char[] lc = myString.toCharArray();
        for(int i = 0; i<lc.length; i++){
            if(lc[i] == 'a')
                lc[i] = Character.toUpperCase(lc[i]);
            else if((lc[i] != 'A') && Character.isUpperCase(lc[i]))
                lc[i] = Character.toLowerCase(lc[i]);
        }
        for(char c : lc){
            sb.append(c);
        }
        return sb.toString();
    }
}