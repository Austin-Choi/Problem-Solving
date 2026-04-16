import java.util.*;
import java.io.*;
/*
괄호 하나를 기준으로 쪼개기
i쌍 짜리를 만들때 ( left ) right
-> 괄호 하나 이미 썻으니까 i-k-1로
왼쪽 k개 + 오른쪽 i-k-1개
*/

class Solution {
    public int solution(int n) {
        int[] dp = new int[15];
        dp[0] = 1;
        dp[1] = 1;
    
        for(int i = 2; i<=n; i++)
            for(int k = 0; k<i; k++)
                dp[i] += dp[k] * dp[i-1-k];
        
        return dp[n];
    }
}