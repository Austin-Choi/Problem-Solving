import java.util.*;
//size 5일때
// 0 1 2 3 
// 1 2 3 4 로 나눠서 하면 양끝점 고려안해도됨
// 1 5 10 3 20
// 1
//   5
//     (10+1)
//        dp[i-1]이랑 m[i]+dp[i-2] 중에 큰거 현재 i = 4
class Solution {
    public int[] dp1 = new int[1000001];
    public int[] dp2 = new int[1000001];
    public int solution(int[] m) {
        int answer = 0;
        
        dp1[0] = m[0];
        dp1[1] = Math.max(m[0], m[1]);
        dp2[1] = m[1];
        dp2[2] = Math.max(m[1], m[2]);
        
        for(int i = 2; i<m.length-1; i++){
            dp1[i] = Math.max(dp1[i-1], m[i]+dp1[i-2]);
        }
        for(int i = 3; i<m.length; i++){
            dp2[i] = Math.max(dp2[i-1], m[i]+dp2[i-2]);
        }
        answer = Math.max(dp1[m.length-2], dp2[m.length-1]);
        return answer;
    }
}