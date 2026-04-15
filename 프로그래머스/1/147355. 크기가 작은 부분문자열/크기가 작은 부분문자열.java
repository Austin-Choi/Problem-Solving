import java.util.*;
import java.io.*;
/*
슬라이딩 윈도
*/

class Solution {
    // b = p
    boolean isSameSmall(char[] a, char[] b){
        boolean rst = true;
        for(int i = 0; i<b.length; i++){
            if(a[i] < b[i])
                return true;
            if(a[i] > b[i])
                return false;
        }
        return true;
    }
    
    // void debug1(char[] a){
    //     StringBuilder sb =new StringBuilder();
    //     for(char aa : a){
    //         sb.append(aa);
    //     }
    //     System.out.println(sb);
    // }
    public int solution(String t, String p) {
        int size = p.length();
        int l = 0;
        int r = l+size;
        
        int answer = 0;
        char[] tt = t.toCharArray();
        char[] pp = p.toCharArray();
        char[] cur = new char[size];
        while(l<=t.length()-size){
            for(int i = l; i<r; i++){
                cur[i-l] = tt[i];
            }
            if(isSameSmall(cur, pp)){
                answer++;
                //debug1(cur);
            }
            l++;
            r++;
        }
        
        return answer;
    }
}