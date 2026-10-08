/*
dp[i][j] = i~j중 가장 긴 팰린드롬의 길이 O(N^2)

*/
import java.util.*;
class Solution
{
    public int solution(String s)
    {
        int N = s.length();
        boolean[][] dp = new boolean[N][N];
        for(int i = 0; i<N; i++)
            dp[i][i] = true;
        
        int ans = 1;
        for(int len = 2; len<=N; len++){
            for(int i = 0; i+len-1 < N; i++){
                int j = i+len-1;
                if(s.charAt(i) == s.charAt(j)){
                    if(len == 2 || dp[i+1][j-1]){
                        dp[i][j] = true;
                        ans = Math.max(ans, len);
                    }
                }
            }
        }
        return ans;
    }
}