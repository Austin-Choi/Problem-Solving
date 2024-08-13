import java.util.*;

/*
aaabbcccccccc
aaaabbcccc

result : 40329
*/

class Solution {
    public int solution(String str1, String str2) {
        List<String> l1 = new ArrayList<String>();
        for(int i = 0; i<str1.length()-1; i++){
            String temp = str1.substring(i,i+2);
            //영문자 아니면 안넣음
            if(temp.matches(".*[^a-zA-Z].*"))
                continue;
            else{
                temp = temp.toLowerCase();
                l1.add(temp);
            }       
        }
        for(String s : l1){
            System.out.print(s+" ");
        }
        System.out.println();
        
        List<String> l2 = new ArrayList<String>();
        for(int i = 0; i<str2.length()-1; i++){
            String temp = str2.substring(i, i+2);
            //영문자 아니면 안넣음
            if(temp.matches(".*[^a-zA-Z].*"))
                continue;
            else{
                temp = temp.toLowerCase();
                l2.add(temp);
            }       
        }
        for(String s : l2){
            System.out.print(s+" ");
        }
        System.out.println();
        
        if(l1.size() == 0 && l2.size() == 0)
            return 65536;
        
        int a = l1.size();
        int b = l2.size();
        int inner = 0;
        //교집합
        // l1이 l2보다 작을 때 
        // 
        if(a < b){
            for(String s : l1){
                if(l2.contains(s)){
                    if(l2.remove(s))
                        inner++;
                }
            }
        }
        else{
            for(String s : l2){
                if(l1.contains(s)){
                    if(l1.remove(s))
                        inner++;
                }
            }
        }
        //합집합
        int union = a+b - inner;
        
        double ans = ((double) inner / (double) union) * 65536;

        return (int) ans;
    }
}