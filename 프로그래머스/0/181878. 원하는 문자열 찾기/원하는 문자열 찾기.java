class Solution {
    public int solution(String myString, String pat) {
        int answer = 0;
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        
        String[] l1 = myString.split("");
        String[] l2 = pat.split("");
        
        for(int i = 0; i<myString.length(); i++){
            l1[i] = l1[i].toLowerCase();
        }
        for(int i = 0; i<pat.length(); i++){
            l2[i] = l2[i].toLowerCase();
        }
        for(int i = 0; i<myString.length(); i++){
            sb1.append(l1[i]);
        }
        for(int i = 0; i<pat.length(); i++){
            sb2.append(l2[i]);
        }
        
        if(sb1.toString().contains(sb2.toString()))
            return 1;
        else 
            return 0;
    }
}