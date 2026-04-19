import java.util.*;
/*
뭔가 배낭문제같은 dp냄새남
근데 배낭은 아닌거같음
최적해 = 원래 있던거 + 마지막 문자열
조각 길이 최대제한 있으므로 그 제한까지만 보고 원래 있던거랑 비교해보면 될듯
dp[i] = t의 i번째까지 진행했을때 여기까지 만드는 최소 수 구하기

strs에서 존재하는지 hashset
strs 최대 문자열길이 구해서 그만큼만 뒤에서 체크하기

dp[j]가 유효값이고 최대 길이만큼 뒤에 하나씩 뜯어서 그 뜯은 문자열이
set에 들어있으면 +1로 갱신가능
min(dp[i], dp[j]+1)
*/

class Solution {
    // t길이 + 1
    final int INF = 20001;
    HashSet<String> m = new HashSet<>();
    
    public int solution(String[] strs, String t) {
        int answer = 0;
        int maxL = 0;
        int N = t.length();
        
        for(String s : strs){
            maxL = Math.max(maxL, s.length());
            m.add(s);
        }
        
        int[] dp = new int[N+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        for(int i = 1; i<=N; i++){
            for(int j = i-1; j >= Math.max(0, i-maxL); j--){
                if(dp[j] != INF && m.contains(t.substring(j,i))){
                    dp[i] = Math.min(dp[i], dp[j]+1);
                }
            }
        }

        return dp[N] == INF ? -1 : dp[N];
    }
}