/*
user = 8, ban = 8 이므로
비트마스크로 각 유저를 표현할 수 있음 8이니까
*/
import java.util.*;
class Solution {
    String[] user, banned;
    // 비트마스크로 나타낸 가능한 경우를 저장하는 set
    Set<Integer> ss = new HashSet<>();
    
    // A = user, B = banned;
    boolean match(String uStr, String bStr){
        if(uStr.length() != bStr.length())
            return false;
        int N = uStr.length();
        for(int i = 0; i<N; i++){
            // 만약 와일드카드가 아니면서 둘 char가 다르면 매칭안됨
            if(bStr.charAt(i) != '*' && uStr.charAt(i) != bStr.charAt(i))
                return false;
        }
        return true;
    }
    
    void dfs(int depth, int mask){
        if(depth == banned.length){
            ss.add(mask);
            return;
        }
        
        for(int i = 0; i<user.length; i++){
            // 이미 i번째 사용자를 선택했다면 스킵
            if((mask & (1<<i)) != 0)
                continue;
            
            if(match(user[i], banned[depth])){
                dfs(depth+1, mask | (1<<i));
            }
        }
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        user = user_id;
        banned = banned_id;
        dfs(0,0);
        return ss.size();
    }
}