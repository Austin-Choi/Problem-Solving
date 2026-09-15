/*
011 11 110 10
011 11 10
011 1 110
0110110111

문자열에서 가장 뒤에 있는 110부터 하나씩 뽑아서
0을 제외하고 사전 순으로 적게 되는 곳의 가장 앞에서부터 배치하면 뭐가 되는지 출력

100 11 110 0
100 110 110

스택에 하나씩 넣으면서 110 완성되면 cnt++
적당한 삽입 위치 정하기
거기다가 110 전부 줄줄이 넣고 완성
*/
import java.util.*;
class Solution {
    public String[] solution(String[] s) {
        String[] ans = new String[s.length];
        for(int idx = 0; idx<s.length; idx++){
            String cur = s[idx];
            StringBuilder sb = new StringBuilder();
            int cnt = 0;
            
            // 문자열당 뒤에서부터 110 찾아서 cnt 수 셈
            for(char c : cur.toCharArray()){
                sb.append(c);
                int len = sb.length();
                if(len >= 3 
                  && sb.charAt(len-1) == '0'
                  && sb.charAt(len-2) == '1'
                  && sb.charAt(len-3) == '1'){
                    sb.delete(len-3, len);
                    cnt++;
                }
            }
            
            int len = sb.length();
            int p = len;
            // 마지막 0 자리 찾기
            while(p > 0 && sb.charAt(p-1) == '1'){
                p--;
            }
            
            // 위치에 붙이기
            for(int i = 0; i<cnt; i++){
                sb.insert(p, "110");
                p+=3;
            }
            
            ans[idx] = sb.toString();
        }
        return ans;
    }
}